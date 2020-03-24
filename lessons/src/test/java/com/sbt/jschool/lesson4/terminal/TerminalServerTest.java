package com.sbt.jschool.lesson4.terminal;

import com.sbt.jschool.lesson4.terminal.exceptions.InvalidAmountException;
import org.junit.Test;

import java.net.SocketException;

import static org.junit.Assert.*;

/**
 * @version 0.1
 * @autor Саньков Владимир
 */
public class TerminalServerTest {

    @Test
    public void checkScore() {
        TerminalServer terminalServer = new TerminalServer();
        assertEquals(0, terminalServer.checkScore());
    }

    @Test(expected = SocketException.class)
    public void putMoney() throws SocketException {
        TerminalServer terminalServer = new TerminalServer();
        terminalServer.setConnect(false);
        terminalServer.putMoney(100);
    }

    @Test(expected = com.sbt.jschool.lesson4.terminal.exceptions.InvalidAmountException.class)
    public void testInvalidAmountException() throws InvalidAmountException {
        TerminalServer terminalServer = new TerminalServer();
        System.out.println(terminalServer.checkScore());
        terminalServer.takeOffMoney(1000);
    }
}