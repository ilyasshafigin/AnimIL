/**
 *
 */
package ru.ildev.anim.skeletal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс, объединяющий сразу несколько текстур и предоставляющий быстрый доступ
 * к ним по их именам.
 *
 * @author Shafigin Ilyas <ilyas174@gmail.com>
 * @version 0.1.0
 */
public class Textures {

    /**
     * Карта текстур.
     */
    protected Map<String, Texture> map = new HashMap<>();
    /**
     * Путь к папке, в которой находятся текстуры.
     */
    protected String folder = "";

    /**
     * Создает пустой объект.
     */
    public Textures() {
    }

    /**
     * Создает объект текстур и добавляет в него массив текстур.
     *
     * @param textures массив текстур.
     */
    public Textures(Texture... textures) {
        if (textures == null) throw new NullPointerException("textures == null");

        for (Texture texture : textures) {
            if (texture == null) throw new NullPointerException("texture in array is null");
            this.map.put(texture.name, texture);
        }
    }

    /**
     * Создает объект текстур и добавляет в него список текстур.
     *
     * @param textures список текстур.
     */
    public Textures(List<Texture> textures) {
        if (textures == null) throw new NullPointerException("textures == null");

        for (Texture texture : textures) {
            if (texture == null) throw new NullPointerException("texture in list is null");
            this.map.put(texture.name, texture);
        }
    }

    /**
     * Получает путь к папке с текстурами.
     *
     * @return путь к папке с текстурами.
     */
    public String getFolder() {
        return this.folder;
    }

    /**
     * Устанавливает путь к папке с текстурами.
     *
     * @param folder путь к папке с текстурами.
     */
    public void setFolder(String folder) {
        if (folder == null) throw new NullPointerException("folder == null");
        this.folder = folder;
    }

    /**
     * Получает текстуру под заданным именем.
     *
     * @param name имя текстуры.
     * @return текстуру.
     */
    public Texture getTexture(String name) {
        if (name == null) return null;
        return this.map.get(name);
    }

    /**
     * Добавляет текстуру в объект.
     *
     * @param texture текстура.
     */
    public void addTexture(Texture texture) {
        if (texture == null) return;
        if (this.map.containsKey(texture.name)) {
            this.map.remove(texture.name);
        }

        this.map.put(texture.name, texture);
    }

    /**
     * Удаляет текстуру из объекта.
     *
     * @param name имя текстуры.
     * @return удаленная текстура.
     */
    public Texture removeTexture(String name) {
        if (name == null) return null;

        return this.map.remove(name);
    }

    /**
     * Удаляет текстуру из объекта.
     *
     * @param texture текстура.
     */
    public void removeTexture(Texture texture) {
        if (texture == null) return;
        this.map.remove(texture.name);
    }

    /**
     * Получает количество добавленных текстур.
     *
     * @return количество текстур.
     */
    public int size() {
        return this.map.size();
    }

    /**
     * @return
     */
    public Map<String, Texture> getMap() {
        return this.map;
    }

}
