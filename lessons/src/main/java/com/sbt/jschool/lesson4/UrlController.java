package com.sbt.jschool.lesson4;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

/**
 * @version 0.1
 * @autor Саньков Владимир
 */
public class UrlController {

    public static void readContent(String url) throws IOException {
        URLConnection connection = new URL(url).openConnection();
        try (Scanner scanner = new Scanner(connection.getInputStream())) {
            scanner.useDelimiter("\\Z");
            while (scanner.hasNext()) {
                System.out.println(scanner.next());
            }
        }
    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Ведите адрес ресурса:");
            String url = scanner.next();
            try {
                readContent(url + " ");
                break;
            } catch (MalformedURLException e) {
                System.out.println("Некорректный адрес! Введите еще раз");
            } catch (UnknownServiceException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        while (true);
    }
}
