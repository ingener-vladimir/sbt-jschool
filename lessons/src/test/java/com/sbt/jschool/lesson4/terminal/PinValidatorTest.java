package com.sbt.jschool.lesson4.terminal;

import com.sbt.jschool.lesson4.terminal.exceptions.*;
import org.junit.Test;

/**
 * @version 0.1
 * @autor Саньков Владимир
 */
public class PinValidatorTest {

    @Test(expected = InvalidPinCodeException.class)
    public void testInvalidPinCodeException() throws AccountIsLockedException, InvalidPinCodeException {
        PinValidator pinValidator = new PinValidator();
        pinValidator.checkPin(1);
    }
}