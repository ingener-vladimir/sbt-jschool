package com.sbt.jschool.lesson17;

import org.springframework.mail.javamail.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import java.sql.*;

import java.time.LocalDate;

class ConnectionController {
    private final Connection connection;

    public ConnectionController(Connection connection) {
        this.connection = connection;
    }

    public PreparedStatement getPreparedStatement(String statement) {
        PreparedStatement ps = null;

        if (connection != null) {
            try {
                ps = connection.prepareStatement(statement);
            } catch (SQLException throwables) {
                System.out.println("error creating statement");
            } finally {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    System.out.println("error close connection");
                }
            }
        }

        return ps;
    }
}

class PreparedStatementWorker {
    private final ConnectionController connectionController;

    private static final String QUERY = "select emp.id as emp_id, emp.name as amp_name, sum(salary) as salary from employee emp left join" +
            "salary_payments sp on emp.id = sp.employee_id where emp.department_id = ? and" +
            " sp.date >= ? and sp.date <= ? group by emp.id, emp.name";

    public PreparedStatementWorker(ConnectionController connectionController) {
        this.connectionController = connectionController;
    }

    PreparedStatement createStatement(String departmentId,
                                      LocalDate dateFrom,
                                      LocalDate dateTo) {
        PreparedStatement ps = null;

        if (connectionController != null) {
            // prepare statement with sql
            ps = connectionController.getPreparedStatement(QUERY);
        }

        if (ps != null) {
            try {
                // inject parameters to sql
                ps.setString(0, departmentId);
                ps.setDate(1, new Date(dateFrom.toEpochDay()));
                ps.setDate(2, new Date(dateTo.toEpochDay()));
            } catch (SQLException throwables) {
                System.out.println("error set parameters statement");
                return null;
            }
        }

        return ps;
    }
}

class ResultSetWorker {
    private final PreparedStatementWorker preparedStatementWorker;
    private StringBuilder stringBuilderResultingHtml = new StringBuilder();

    private static final String TAG = "<html><body><table><tr><td>Employee</td><td>Salary</td></tr>";

    public ResultSetWorker(PreparedStatementWorker preparedStatementWorker) {
        this.preparedStatementWorker = preparedStatementWorker;
    }

    public String getStringResultingHtml(String departmentId,
                                         LocalDate dateFrom,
                                         LocalDate dateTo) {

        if (preparedStatementWorker != null) {
            // execute query and get the results
            ResultSet results = null;
            try {
                PreparedStatement ps = preparedStatementWorker.createStatement(departmentId, dateFrom, dateTo);

                if(ps != null){
                    results = ps.executeQuery();
                }
            } catch (SQLException throwables) {
                System.out.println("error execute query");
                return "";
            }
            // create a StringBuilder holding a resulting html
            stringBuilderResultingHtml.append(TAG);
            calculateTotals(results);
        }
        return stringBuilderResultingHtml.toString();
    }

    private void calculateTotals(ResultSet resultSet) {
        if(resultSet == null)
            return;

        double totals = 0;
        try {
            while (resultSet.next()) {
                // process each row of query results
                stringBuilderResultingHtml.append("<tr>"); // add row start tag
                stringBuilderResultingHtml.append("<td>").append(resultSet.getString("emp_name")).append("</td>"); // appending employee name
                stringBuilderResultingHtml.append("<td>").append(resultSet.getDouble("salary")).append("</td>"); // appending employee salary for period
                stringBuilderResultingHtml.append("</tr>"); // add row end tag
                totals += resultSet.getDouble("salary"); // add salary to totals
            }
            stringBuilderResultingHtml.append("<tr><td>Total</td><td>").append(totals).append("</td></tr>");
            stringBuilderResultingHtml.append("</table></body></html>");
        } catch (SQLException throwables) {
            System.out.println("error result set");
        }
    }
}

class MailSender {
    private final JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
    private final ResultSetWorker resultSetWorker;

    public MailSender(ResultSetWorker resultSetWorker, String host) {
        this.resultSetWorker = resultSetWorker;
        // we're going to use google mail to send this message
        mailSender.setHost(host);
    }

    public void sendMessage(String recipients) throws MessagingException {
        // construct the message
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(recipients);
        // setting message text, last parameter 'true' says that it is HTML format

        String stringResult = "";
        if (resultSetWorker != null) {
            stringResult = resultSetWorker.getStringResultingHtml("id",
                    LocalDate.of(2018, 03, 04),
                    LocalDate.of(2018, 04, 04));
        }

        helper.setText(stringResult.toString(), true);
        helper.setSubject("Monthly department salary report");
        // send the message
        mailSender.send(message);
    }
}

public class SalaryHtmlReportNotifier {
    private final MailSender mailSender;

    public SalaryHtmlReportNotifier(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendHtmlSalaryReport(String recipients) {
        if (mailSender != null) {
            try {
                mailSender.sendMessage(recipients);
            } catch (MessagingException e) {
                System.out.println("error sending message");
            }
        }
    }

    public static void main(String[] args) {
        Connection connection = null;
        ConnectionController connectionController = new ConnectionController(connection);
        PreparedStatementWorker preparedStatementWorker = new PreparedStatementWorker(connectionController);
        ResultSetWorker resultSetWorker = new ResultSetWorker(preparedStatementWorker);
        MailSender mailSender = new MailSender(resultSetWorker, "mail.google.com");
        SalaryHtmlReportNotifier salaryHtmlReportNotifier = new SalaryHtmlReportNotifier(mailSender);
        salaryHtmlReportNotifier.sendHtmlSalaryReport("Admin");
    }
}
