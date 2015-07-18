/*
 *
 */
package ru.ildev.anim.skeletal;

import ru.ildev.geom.Transform2;

/**
 * Класс текстуры.
 *
 * @author Shafigin Ilyas (Шафигин Ильяс) <Ilyas74>
 * @version 0.1.2
 */
public class Texture {

    /**
     * Имя текстуры.
     */
    protected String name = "texture";
    /**
     * Путь к файлу текстуры.
     */
    protected String path = "";
    /**
     * Ширина текстуры.
     */
    protected float width;
    /**
     * Высота текстуры.
     */
    protected float height;
    /**
     * Трасформация текстуры.
     */
    protected Transform2 transform = null;

    /**
     * Стандартный конструктор.
     */
    public Texture() {
    }

    /**
     * Конструктор, устанавливающий имя текстуре.
     *
     * @param name имя.
     */
    public Texture(String name) {
        if (name == null) throw new NullPointerException("name == null");

        this.name = name;
    }

    /**
     * Конструктор, устанавливающий имя и размеры текстуре.
     *
     * @param name   имя.
     * @param width  ширина.
     * @param height высота.
     */
    public Texture(String name, float width, float height) {
        if (name == null) throw new NullPointerException("name == null");
        if (width <= 0.0f) throw new IllegalArgumentException("width <= 0.0");
        if (height <= 0.0f) throw new IllegalArgumentException("height <= 0.0");

        this.name = name;
        this.width = width;
        this.height = height;
    }

    /**
     * Получает имя текстуры.
     *
     * @return имя текстуры.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Устанавливает имя текстуре.
     *
     * @param name имя.
     */
    public void setName(String name) {
        if (name == null) throw new NullPointerException("name == null");

        this.name = name;
    }

    /**
     * Получает путь к файлу текстуры.
     *
     * @return путь к файлу текстуры.
     */
    public String getPath() {
        return this.path;
    }

    /**
     * Устанавливает путь к файлу текстуры.
     *
     * @param path путь к файлу текстуры.
     */
    public void setPath(String path) {
        if (path == null) throw new NullPointerException("path == null");

        this.path = path;
    }

    /**
     * Получает ширину текстуры.
     *
     * @return ширину текстуры.
     */
    public float getWidth() {
        return this.width;
    }

    /**
     * Устанавливает ширину текстуре.
     *
     * @param width ширина.
     */
    public void setWidth(float width) {
        if (width <= 0.0f) throw new IllegalArgumentException("width <= 0.0");

        this.width = width;
    }

    /**
     * Получает высоту текстуры.
     *
     * @return высоту текстуры.
     */
    public float getHeight() {
        return this.height;
    }

    /**
     * Устанавливает высоту текстуре.
     *
     * @param height высота.
     */
    public void setHeight(float height) {
        if (height <= 0.0f) throw new IllegalArgumentException("height <= 0.0");

        this.height = height;
    }

    /**
     * Получает трансформацию текстуры.
     *
     * @return трансформацию текстуры.
     */
    public Transform2 getTransform() {
        return this.transform;
    }

    /**
     * Устанавливает трансформацию текстуре.
     *
     * @param transform трнасформация.
     */
    public void setTransform(Transform2 transform) {
        this.transform = transform;
    }

    @Override
    public String toString() {
        return "Texture[name=" + this.name + "]";
    }

}
