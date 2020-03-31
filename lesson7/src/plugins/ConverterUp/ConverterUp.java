package plugins.ConverterUp;

import interfaces.Plugin;

/**
 * Класс, имплементирующий класс Plugin
 *
 * @version 0.1
 * @autor Саньков Владимир
 */
public class ConverterUp implements Plugin {
    /**
     * Вывод в консоль имя класса
     */
    @Override
    public void printMessage() {
        System.out.println("ConverterUp");
    }
}
