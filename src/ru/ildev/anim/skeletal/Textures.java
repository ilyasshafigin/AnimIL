/**
 *
 */
package ru.ildev.anim.skeletal;

import java.util.*;

/**
 * Класс, объединяющий сразу несколько текстур и предоставляющий быстрый доступ
 * к ним по их именам.
 *
 * @author Shafigin Ilyas <ilyas174@gmail.com>
 * @version 0.1.0
 */
public class Textures implements Iterable<Texture> {

    /**
     * Карта текстур.
     */
    protected Map<String, Texture> map = new HashMap<>();
    /**
     * Список текстур.
     */
    protected List<Texture> list = new ArrayList<>();
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

        for (int i = 0; i < textures.length; i++) {
            Texture texture = textures[i];

            if (texture == null) throw new NullPointerException("texture[" + i + "] == null");

            this.map.put(texture.name, texture);
            this.list.add(texture);
        }
    }

    /**
     * Создает объект текстур и добавляет в него список текстур.
     *
     * @param textures список текстур.
     */
    public Textures(List<Texture> textures) {
        if (textures == null) throw new NullPointerException("textures == null");

        int tSize = textures.size();
        for (int i = 0; i < tSize; i++) {
            Texture texture = textures.get(i);

            if (texture == null) throw new NullPointerException("texture(" + i + ") == null");

            this.map.put(texture.name, texture);
            this.list.add(texture);
        }
    }

    /**
     * Получает карту текстур.
     *
     * @return карту текстур.
     */
    public Map<String, Texture> getTexturesMap() {
        return this.map;
    }

    /**
     * Получает список текстур.
     *
     * @return список текстур.
     */
    public List<Texture> getTexturesList() {
        return this.list;
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
     * Получает текстуру под заданным индексом.
     *
     * @param index индекс.
     * @return текстуру.
     */
    public Texture getTexture(int index) {
        return this.list.get(index);
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
            this.list.remove(texture);
        }

        this.map.put(texture.name, texture);
        this.list.add(texture);
    }

    /**
     * Удаляет текстуру из объекта.
     *
     * @param name имя текстуры.
     * @return удаленная текстура.
     */
    public Texture removeTexture(String name) {
        if (name == null) return null;

        Texture removed = this.map.remove(name);
        if (removed != null) this.list.remove(removed);
        return removed;
    }

    /**
     * Удаляет текстуру из объекта.
     *
     * @param texture текстура.
     */
    public void removeTexture(Texture texture) {
        if (texture == null) return;
        this.map.remove(texture.name);
        this.list.remove(texture);
    }

    /**
     * Получает количество добавленных текстур.
     *
     * @return количество текстур.
     */
    public int size() {
        return this.list.size();
    }

    @Override
    public Iterator<Texture> iterator() {
        return this.list.iterator();
    }

}
