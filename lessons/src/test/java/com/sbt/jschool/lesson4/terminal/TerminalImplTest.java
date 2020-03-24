package com.sbt.jschool.lesson4.terminal;

import com.sbt.jschool.lesson4.terminal.exceptions.InvalidAmountException;
import org.junit.Test;

/**
 * @version 0.1
 * @autor Саньков Владимир
 */
public class TerminalImplTest {

    @Test
    public void checkScore() {
        new TerminalImpl(null, null).checkScore();
    }

    @Test(expected = InvalidAmountException.class)
    public void testInvalidAmountExceptionMultiply() throws Exception {
        new TerminalImpl(null, null).putMoney(150);
    }

    @Test(expected = InvalidAmountException.class)
    public void testInvalidAmountExceptionNegative() throws Exception {
        new TerminalImpl(null, null).putMoney(-100);
    }
}