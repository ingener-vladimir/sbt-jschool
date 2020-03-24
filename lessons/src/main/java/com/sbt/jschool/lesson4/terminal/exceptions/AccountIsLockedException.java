package com.sbt.jschool.lesson4.terminal.exceptions;

/**
 * Класс исключения блокировки доступа к терминалу
 *
 * @version 0.1
 * @autor Саньков Владимир
 */
public class AccountIsLockedException extends Exception{
    /**
     * Конструктор, в который передается сообщение исключения
     * Оно передается дальше в родительский класс Exception
     *
     * @param message Сообщение
     */
    public AccountIsLockedException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
