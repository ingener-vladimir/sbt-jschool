package com.sbt.jschool.lesson2;

import java.util.*;

public class Exercise2056 {
    static public void maxCountRepeated(String str) {
        String[] strings = str.replaceAll("\\n", " ").toLowerCase().split(" ");
        Map<String, Integer> stringIntegerMap = new HashMap<>();
        for (String word : strings) {
            if (!stringIntegerMap.containsKey(word)) {
                stringIntegerMap.put(word, 0);
            }
            stringIntegerMap.put(word, stringIntegerMap.get(word) + 1);
        }

        int maxRepeat = Collections.max(stringIntegerMap.values());

        List<String> stringList = new ArrayList<>();
        for (Map.Entry<String, Integer> it : stringIntegerMap.entrySet()) {
            if (it.getValue().equals(maxRepeat)) {
                stringList.add(it.getKey());
            }
        }

        Collections.sort(stringList, new Comparator<String>() {
            @Override
            public int compare(String t, String t1) {
                return t.length() == t1.length() ? 0 : t.length() > t1.length() ? 2 : -1;
            }
        });

        for (String value : stringList) {
            System.out.println(value);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        Exercise2056.maxCountRepeated(str);
    }
}
