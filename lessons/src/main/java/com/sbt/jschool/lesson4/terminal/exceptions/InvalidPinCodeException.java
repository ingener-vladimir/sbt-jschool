package com.sbt.jschool.lesson4.terminal.exceptions;

/**
 * Класс исключения невалидной введенного пин кода
 *
 * @version 0.1
 * @autor Саньков Владимир
 */
public class InvalidPinCodeException extends Exception {
    /**
     * Конструктор, в который передается сообщение исключения
     * Оно передается дальше в родительский класс Exception
     *
     * @param message Сообщение
     */
    public InvalidPinCodeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
