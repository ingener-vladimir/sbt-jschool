package com.sbt.jschool.lesson1;

/**
 * Класс человека
 *
 * @version 0.1
 * @autor Саньков Владимир
 */

public class Person {
    /** Пол человека */
    private final boolean man;
    /** Имя человека */
    private final String name;
    /** Супруг */
    private Person spouse;

    /**
     * Конструктор - создание нового человека
     *
     * @param man  - пол
     * @param name - имя
     */
    public Person(boolean man, String name) {
        this.man = man;
        this.name = name;
    }

    /**
     * метод для создания брака
     *
     * @param person - человек, с кем устанваливается брак
     * @return результат попытки пожениться
     * @throws IllegalArgumentException Если в метод передан нулевой объект
     */
    public boolean marry(Person person) {
        if (person == null) {
            throw new IllegalArgumentException("Must be not null");
        }
        boolean res = false;

        if (this.man != person.man) {
            if (this.spouse != null) {
                if (this.spouse == person) {
                    System.out.println(this.name + " and " + person.name + " are already married");
                    return false;
                }
                divorce();
                person.divorce();
            }
            this.spouse = person;
            person.spouse = this;
            res = true;
            System.out.println("The wedding " + this.name + " and " + person.name + " took place");
        } else {
            System.out.println("Genders must be different");
        }

        return res;
    }

    /**
     * метод для расторжения брака
     *
     * @return результат
     */
    public boolean divorce() {
        boolean res = false;

        if (this.spouse != null) {
            spouse.spouse = null;
            spouse = null;
            res = true;
            System.out.println("The divorce was successful");
        }

        return res;
    }
}

