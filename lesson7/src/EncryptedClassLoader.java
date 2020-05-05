import java.io.*;

/**
 * Класс-загрузчик, который умеет загружать классы из файлов, дешифруя их
 *
 * @version 0.1
 * @autor Саньков Владимир
 */
public class EncryptedClassLoader extends ClassLoader {
    /**
     * Ключ
     */
    private final String key;

    /**
     * Директория с файлами типа .class
     */
    private final String dir;

    /**
     * Конструкор с параметрами
     * @param key Ключ
     * @param dir Директория с файлами типа .class
     * @param parent Родительский класс-лоадер
     */
    public EncryptedClassLoader(String key,
                                String dir,
                                ClassLoader parent) {
        super(parent);
        this.key = key;
        this.dir = dir;
    }

    /**
     * Переопределенная функция поиска класса
     * @param name Имя класса
     * @return Class
     * @throws ClassNotFoundException Если класс не найден
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String splitStr = (dir + name).replace(".", "/");
        File f = new File( splitStr + ".class");
        if(f == null){
            return findSystemClass(name);
        }

        byte[] bytes = decodeArrayByte(f);
        Class<?> clazz = defineClass(splitStr, bytes, 0, bytes.length);
        return clazz;
    }

    /**
     * Декодировать класс из файла
     * @param file Файл класса
     * @return Массив байт
     */
    private byte[] decodeArrayByte(File file){
        byte[] bytes = new byte[(int)file.length()];
        try(InputStream fileInputStream = new FileInputStream(file)) {
            fileInputStream.read(bytes, 0, bytes.length);
            bytes = decryptData(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bytes;
    }

    /**
     * Расшифровать массив байт, используя ключ
     * @param bytes Массив байт
     * @return Расшифрованный массив
     */
    private byte[] decryptData(byte[] bytes){
        byte[] newBytes = new byte[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            newBytes[i] = (byte) (bytes[i] - (byte)key.hashCode());
        }

        return newBytes;
    }
}
