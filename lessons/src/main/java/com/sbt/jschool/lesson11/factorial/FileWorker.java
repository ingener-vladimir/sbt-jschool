package com.sbt.jschool.lesson11.factorial;

import java.io.*;

/**
 * Класс для работы с файлом
 *
 * @version 0.1
 * @autor Саньков Владимир
 */

public class FileWorker {
    /**
     * Файл для работы
     */
    final private File file;

    /**
     * Коснтруктор с параметрами
     * @param fileName Имя файла
     */
    public FileWorker(String fileName) {
        file = new File(fileName);
    }

    /**
     * Читает данные из файла
     * @return Считанная строка
     * @throws IOException Если файла не существует
     */
    public String read() throws IOException {
        if(!file.exists()){
            throw new FileNotFoundException("File not exists");
        }

        String result = "";
        try(FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader)){
            while(bufferedReader.ready()){
                result = bufferedReader.readLine();
            }
        }

        return result;
    }
}
