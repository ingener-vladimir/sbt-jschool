package com.sbt.jschool.lesson5.reflection;

import java.lang.reflect.*;

/**
 * @version 0.1
 * @autor Саньков Владимир
 */

public class BeanUtils {
    public static void assign(Object to, Object from) throws InvocationTargetException, IllegalAccessException {
        Class clazzFrom = from.getClass();
        Class clazzTo = to.getClass();
        Field[] fields = clazzTo.getDeclaredFields();

        for (int i = 0; i < fields.length; i++) {
            String nameField = fields[i].getName();
            if (haveUpperCaseSecondSymbol(nameField) == false)
                nameField = fields[i].getName().replaceFirst(fields[i].getName().substring(0, 1),
                        fields[i].getName().substring(0, 1).toUpperCase());
            try {
                Method methodGet = clazzFrom.getMethod("get" + nameField);
                Method methodSet = clazzTo.getMethod("set" + nameField, fields[i].getType());;

                Class parameter = methodSet.getParameterTypes()[0];
                Class returnType = methodGet.getReturnType();

                if ((parameter.isPrimitive() && parameter.getName().equals(returnType.getName()))
                    | returnType.isInstance(from)) {
                    Object value = methodGet.invoke(from);
                    methodSet.invoke(to, value);
                }
            } catch (NoSuchMethodException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Проверить имя поля класса на наличие заглавной буквы. стоящей на втором месте.
     * К примеру, если имя поля aPit, то IDE сгенерирует set/get с именами
     * setaPit и getaPit соответсвенно
     */
    private static boolean haveUpperCaseSecondSymbol(String str) {
        if (str.length() > 1) {
            return Character.isUpperCase(Character.valueOf(str.charAt(1)));
        }
        return false;
    }

    public static void main(String[] args) {
    }
}
