package com.sbt.jschool.lesson4.terminal;

import com.sbt.jschool.lesson4.terminal.exceptions.*;

/**
 * Класс терминала
 *
 * @version 0.1
 * @autor Саньков Владимир
 */
public class TerminalImpl{
    /** Экземпляр удаленного сервера */
    private final TerminalServer _terminalServer;
    /** Экземпляр валидатора пин кода */
    private final PinValidator _pinValidator;
    /** Пин код */
    private int _pin;

    /**
     * Конструктор - создание нового терминала
     *
     * @param terminalServer - экземпляр удаленного сервера
     * @param pinValidator - экземпляр валидатора пин кода
     */
    public TerminalImpl(TerminalServer terminalServer, PinValidator pinValidator) {
        this._terminalServer = terminalServer;
        this._pinValidator = pinValidator;
    }

    /**
     * Установить пин код
     *
     * @param _pin - пин код
     */
    public void setPin(int _pin) {
        this._pin = _pin;
    }

    /**
     * Вывести сумму, которая в наличии
     *
     */
    public void checkScore(){
        String str = "null";
        if(_terminalServer != null)
            str = "Current amount is " + _terminalServer.checkScore();

        System.out.println(str);
    }

    /**
     * Положить деньги через терминал
     *
     * @param money - сумма
     * @exception InvalidAmountException невалидная сумма
     */
    public void putMoney(long money) throws Exception {
        checkValidAmount(money);

        if (_terminalServer != null)
            _terminalServer.putMoney(money);
    }

    /**
     * Получить деньги через терминал
     *
     * @param money - сумма
     * @exception InvalidAmountException невалидная сумма
     */
    public void getMoney(long money) throws Exception {
        checkValidAmount(money);

        if (_terminalServer != null)
            _terminalServer.takeOffMoney(money);
    }

    /**
     * Высести меню в консоль
     */
    public void printMenu(){
        System.out.println("Menu:\n"
                + "1. Get balance\n"
                + "2. Get money\n"
                + "3. Put money\n"
                + "4. Exit");
    }

    /**
     * Проверить на валидность сумму
     *
     * @param money - сумма
     * @exception InvalidAmountException невалидная сумма
     */
    private void checkValidAmount(long money) throws InvalidAmountException{
        if (money % 100 != 0)
            throw new InvalidAmountException("The amount must be a multiply of 100");
        else if(money < 0)
            throw new InvalidAmountException("The amount must be more than 0");
    }

    /**
     * Проверить на валидность пин код
     *
     * @exception InvalidPinCodeException невалидный пин код
     * @exception AccountIsLockedException терминал заблокирован
     */
    public void checkPin() throws AccountIsLockedException, InvalidPinCodeException {
        if(_pinValidator != null)
            _pinValidator.checkPin(_pin);
    }
}
