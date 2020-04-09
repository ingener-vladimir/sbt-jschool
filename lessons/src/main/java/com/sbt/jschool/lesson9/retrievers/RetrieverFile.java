package com.sbt.jschool.lesson9.retrievers;

import com.sbt.jschool.lesson9.interfaces.IRetriever;
import java.io.*;
import java.util.zip.*;

/**
 * Класс, реализующий интерфейс IRetriever
 * Служит для хоанения кэшированных данных в файле
 * @version 0.1
 * @autor Саньков Владимир
 */
public class RetrieverFile implements IRetriever {
    /**
     * Рутовая директория
     */
    private String rootDir;
    /**
     * Нужно архивировать
     */
    private boolean isZip;

    /**
     * Конструктор
     * @param rootDir Рутовая папака
     * @param zip Нужно архивировать
     */
    public RetrieverFile(String rootDir, boolean zip) {
        this.rootDir = (rootDir == null ? "" : rootDir);
        isZip = zip;
    }

    /**
     * @see IRetriever
     */
    @Override
    public boolean find(long hash) {
        return get(hash) != null;
    }

    /**
     * @see IRetriever
     */
    @Override
    public boolean add(long hash, Object object) {
        boolean res = false;

        try (FileOutputStream fileOutputStream = new FileOutputStream(rootDir + "cache_" + hash + ".ini");
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(object);
            res = true;

            if (isZip) {
                try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(rootDir + hash + ".zip"))) {
                    ZipEntry entry1 = new ZipEntry(hash + ".ini");
                    zout.putNextEntry(entry1);
                    zout.closeEntry();
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error: not found a file by add");
        } catch (IOException e) {
            System.out.println("Error: not create Output Stream by add");
        }

        return res;
    }

    /**
     * @see IRetriever
     */
    @Override
    public Object get(long hash) {
        Object addingObject = null;

        if (isZip) {
            try (ZipInputStream zin = new ZipInputStream(new FileInputStream(rootDir + hash + ".zip"))) {
                ZipEntry entry;
                String name;
                while((entry = zin.getNextEntry()) != null){
                    name = entry.getName(); // получим название файла
                    // распаковка
                    FileOutputStream fout = new FileOutputStream(name);
                    for (int c = zin.read(); c != -1; c = zin.read()) {
                        fout.write(c);
                    }
                    fout.flush();
                    zin.closeEntry();
                    fout.close();
                }
            } catch (FileNotFoundException e) {
                System.out.println("Error: not found a file by read ZIP");
            } catch (IOException e) {
                System.out.println("Error: not create Zip Input Stream");
            }
        }

        try (FileInputStream fileInputStream = new FileInputStream(rootDir + "cache_" + hash + ".ini");
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            addingObject = objectInputStream.readObject();

        } catch (FileNotFoundException e) {
            System.out.println("Error: not found a file by get");
        } catch (IOException e) {
            System.out.println("Error: not create Input Stream by get");
        } catch (ClassNotFoundException e) {
            System.out.println("Error: not found a class by get");
        }

        return addingObject;
    }
}
