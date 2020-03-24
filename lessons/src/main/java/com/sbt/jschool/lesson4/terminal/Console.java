package com.sbt.jschool.lesson4.terminal;

import com.sbt.jschool.lesson4.terminal.exceptions.*;
import java.util.Scanner;

/**
 * Класс для взаимодействия с терминалом
 *
 * @version 0.1
 * @autor Саньков Владимир
 */
public class Console {
    private TerminalServer _terminalServer;
    private PinValidator _pinValidator;
    private TerminalImpl _terminal;
    private int _choice;

    public Console() {
        _terminalServer = new TerminalServer();
        _pinValidator = new PinValidator();
        _terminal = new TerminalImpl(_terminalServer, _pinValidator);
    }

    public void workTerminal() {
        Scanner scanner = new Scanner(System.in);
        while (_choice != 4) {
            try {
                System.out.print("Enter pin code:");
                _terminal.setPin(scanner.nextInt());
                _terminal.checkPin();
                _terminal.printMenu();
                _choice = scanner.nextInt();
                switch (_choice) {
                    case 1: {
                        _terminal.checkScore();
                        break;
                    }
                    case 2: {
                        System.out.print("Enter amount: ");
                        long amount = scanner.nextLong();
                        _terminal.getMoney(amount);
                        break;
                    }
                    case 3: {
                        System.out.print("Enter amount: ");
                        long amount = scanner.nextLong();
                        _terminal.putMoney(amount);
                        break;
                    }
                    case 4: {
                        System.out.println("Good bay");
                        break;
                    }
                    default: {
                        System.out.println("Undefined value");
                        break;
                    }
                }
            } catch (InvalidAmountException iae) {
                System.out.println(iae);
            } catch (AccountIsLockedException aie) {
                System.out.println(aie);
            } catch (InvalidPinCodeException aie) {
                System.out.println(aie);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
