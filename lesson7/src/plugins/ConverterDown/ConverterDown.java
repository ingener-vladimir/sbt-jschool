package plugins.ConverterDown;

import interfaces.Plugin;

/**
 * Класс, имплементирующий класс Plugin
 *
 * @version 0.1
 * @autor Саньков Владимир
 */
public class ConverterDown implements Plugin {
    /**
     * Вывод в консоль имя класса
     */
    @Override
    public void printMessage() {
        System.out.println("ConverterDown");
    }
}
