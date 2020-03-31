import java.io.*;
import java.util.*;

/**
 * Класс-загрузчик плагинов
 *
 * @version 0.1
 * @autor Саньков Владимир
 */
public class PluginLoader extends ClassLoader {
    /** путь к классам */
    private static String pathPluginsClass;
    /** словарь с именем класса и самим классом */
    private Map<String, Class> map = new HashMap<>();

    /**
     * Конструктор
     *
     * @param rootPath путь с классами
     * @param parent родительский загрузчик классов
     *
     */
    public PluginLoader(String rootPath, ClassLoader parent) {
        super(parent);
        pathPluginsClass = rootPath;;
    }

    /**
     * Поиск класса по имени
     *
     * @param name имя класса
     * @return класс
     * @exception ClassNotFoundException если класс не найден
     *
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class result= (Class)map.get(name);
        if (result != null) {
            return result;
        }

        String splitStr = (pathPluginsClass + name).replace(".", "/");
        File f = new File( splitStr + ".class");
        if(f == null){
            return findSystemClass(name);
        }

        byte[] bytes = new byte[(int)f.length()];
        try(InputStream fileInputStream = new FileInputStream(f)) {
            fileInputStream.read(bytes, 0, bytes.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Class<?> clazz = defineClass(splitStr, bytes, 0, bytes.length);
        map.put(name, clazz);
        return clazz;
    }
}
