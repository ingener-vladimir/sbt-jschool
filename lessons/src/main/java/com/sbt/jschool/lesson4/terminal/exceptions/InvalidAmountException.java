package com.sbt.jschool.lesson4.terminal.exceptions;

/**
 * Класс исключения невалидной введенной суммы
 *
 * @version 0.1
 * @autor Саньков Владимир
 */
public class InvalidAmountException extends Exception{
    /**
     * Конструктор, в который передается сообщение исключения
     * Оно передается дальше в родительский класс Exception
     *
     * @param message Сообщение
     */
    public InvalidAmountException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
