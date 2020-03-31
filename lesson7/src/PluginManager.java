import interfaces.Plugin;

import java.io.*;
import java.util.*;

/**
 * Загрузчик плагинов
 *
 * @version 0.1
 * @autor Саньков Владимир
 */
public class PluginManager {
    /** список классов из директории */
    private List<String> _array;
    /** корневой путь к плагинам */
    private final String _pluginRootDirectory;
    /** пользовательский загрузчик классов */
    private PluginLoader classLoader;

    /**
     * Конструктор
     *
     * @param pluginRootDirectory корневой путь к плагинам
     *
     */
    public PluginManager(String pluginRootDirectory) {
        this._pluginRootDirectory = pluginRootDirectory;

        File dirs = new File(new File(pluginRootDirectory).getAbsolutePath());

        String[] directories = dirs.list(new FilenameFilter() {
            @Override
            public boolean accept(File current, String name) {
                return new File(current, name).isDirectory();
            }
        });

        _array = new ArrayList<String>(Arrays.asList(directories));

        classLoader = new PluginLoader(pluginRootDirectory, ClassLoader.getSystemClassLoader());
    }

    /**
     * Загрузить плагины
     *
     * @param pluginName имя плагина
     * @param pluginClassName имя класса
     * @return загруженный плагин
     *
     */
    public Plugin load(String pluginName, String pluginClassName) {
        Plugin plugin = null;
        String splitStr = pluginClassName.replace("/", ".");
        try {
            plugin = (Plugin) classLoader.loadClass(splitStr).newInstance();
        } catch (InstantiationException e) {
            System.out.println("Error instantiation of class" + pluginName);
        } catch (IllegalAccessException e) {
            System.out.println("Error access to class" + pluginName);
        } catch (ClassNotFoundException e) {
            System.out.println("Error search of class" + pluginName);
        }

        return plugin;
    }

    /**
     * Получить список классов
     *
     * @return список классов
     */
    public List<String> getArray() {
        return _array;
    }

    public static void main(String[] args) {
        final String ROOT = "./out/production/lesson7/";
        final String PLUGINS_DIR = "plugins" + File.separator;

        PluginManager pluginManager = new PluginManager(ROOT + PLUGINS_DIR);

        for (String str : pluginManager.getArray()) {
            Plugin plugin = pluginManager.load(str, PLUGINS_DIR + str + File.separator + str);
            if (plugin != null)
                plugin.printMessage();
        }
    }
}
