package com.sbt.jschool.lesson4.terminal;

import com.sbt.jschool.lesson4.terminal.exceptions.InvalidAmountException;
import java.net.SocketException;

/**
 * Класс удаленного сервера терминала
 *
 * @version 0.1
 * @autor Саньков Владимир
 */
public class TerminalServer{
    /** Сумма денег */
    private long _pounds;
    /** Наличие подключения */
    private boolean _isConnect = true;

    /**
     * Установить флаг наличия подключения
     * @param isConnect состояние подключения
     */
    public void setConnect(boolean isConnect) {
        this._isConnect = isConnect;
    }

    /**
     * Получить денежную сумму в наличии
     * @return сумма
     */
    public long checkScore(){
        return _pounds;
    }

    /**
     * Положить денежную сумму
     * @param money состояние подключения
     * @exception SocketException исключение подключения
     */
    public void putMoney(long money) throws SocketException{
        _pounds += money;
        checkConnection();
    }

    /**
     * Получить денежную сумму
     * @param money состояние подключения
     * @exception InvalidAmountException исключение некорретной запрашиваемой суммы
     */
    public void takeOffMoney(long money) throws InvalidAmountException {
        if (money > _pounds)
            throw new InvalidAmountException("The amount for getting must be less a common amount(" + _pounds + ")");
        _pounds -= money;
    }

    /**
     * Проверить наличие подключения
     * @exception SocketException исключение подключения
     */
    private void checkConnection() throws SocketException {
        if (!_isConnect)
            throw new SocketException("Error connection!");
    }
}
