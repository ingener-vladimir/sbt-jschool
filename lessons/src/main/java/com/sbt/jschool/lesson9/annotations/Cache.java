package com.sbt.jschool.lesson9.annotations;

import java.lang.annotation.*;

/**
 * Аннотация, применяемая к методам
 * Задает некоторые параметры, которые учитваются при реализации
 * @version 0.1
 * @autor Саньков Владимир
 */
@Target(value = ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Cache {
    /**
     * Где хранить кэшированные данные
     */
    CacheType cacheType() default CacheType.JVM;
    /**
     * Список имен классов, которые задают уникальность
     */
    Class[] uniqueArg() default {};
    /**
     * Нужно ли архивировать
     */
    boolean zip() default false;
}
