package com.sbt.jschool.lesson4;

import org.junit.Test;

import java.io.IOException;
import java.net.MalformedURLException;

/**
 * @version 0.1
 * @autor Саньков Владимир
 */
public class UrlControllerTest {
    private UrlController urlController = new UrlController();

    @Test(expected = MalformedURLException.class)
    public void tesException() throws IOException {
        urlController.readContent("some url");
    }

    @Test(expected = MalformedURLException.class)
    public void testReadContent(){
        try {
            urlController.readContent("some url");
        } catch (IOException e) {
            System.out.println(e.getMessage());;
        }
    }
}