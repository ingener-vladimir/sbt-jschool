package com.sbt.jschool.lesson4.terminal;

import com.sbt.jschool.lesson4.terminal.exceptions.*;

/**
 * Класс проверки корректности введенного пин кода
 *
 * @version 0.1
 * @autor Саньков Владимир
 */
public class PinValidator {
    /**
     * Количество секунд, на которое блокируется терминал
     */
    private static final byte SECS_LOCK = 5;
    /**
     * Количество максимальных попыток ввести пин код
     */
    private static final byte MAX_TRY = 3;
    /**
     * Пин код по-умолчанию
     */
    private static final short DEFAULT_CODE = 1234;
    /**
     * Количество попыток ввести пин код
     */
    private byte _countTry;
    /**
     * Колисетво секунд от начала суток, когда был введен 3 раз неправильный пин код
     */
    private long _timePrevLock;
    /**
     * Флаг блокировки терминала
     */
    private boolean isLocked;

    /**
     * Проверить пин код на валидность
     *
     * @param pin код
     * @exception InvalidPinCodeException  Не корректный пин код
     * @exception AccountIsLockedException Терминал заблокирован
     */
    public void checkPin(int pin) throws InvalidPinCodeException, AccountIsLockedException {
        checkLockPin();
        if (pin != DEFAULT_CODE) {
            _countTry++;
            if (_countTry == MAX_TRY && !isLocked) {
                isLocked = true;
                _timePrevLock = System.currentTimeMillis();
            }
            throw new InvalidPinCodeException("Invalid pin code! Try again");
        }
        _countTry = 0;
    }

    /**
     * Проверить на наличие блокировки терминала
     *
     * @exception AccountIsLockedException Терминал заблокирован
     */
    private void checkLockPin() throws AccountIsLockedException {
        if (isLocked) {
            double time = (System.currentTimeMillis() - _timePrevLock) / 1000;
            if (time <= SECS_LOCK)
                throw new AccountIsLockedException("Terminal will be available through " + (SECS_LOCK - time) + " seconds");
            else {
                isLocked = false;
            }
        }
    }
}
