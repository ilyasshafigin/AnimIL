/*
 *
 */
package ru.ildev.anim.skeletal;

import java.awt.*;

/**
 * Класс текстуры изображения.
 *
 * @author Shafigin Ilyas (Шафигин Ильяс) <Ilyas74>
 * @version 0.0.0
 */
public class ImageTexture extends Texture {

    /**
     * Изображение текстуры.
     */
    protected Image image = null;

    /**
     * Конструктор, устанавливающий имя текстуре и изображение.
     *
     * @param name  имя.
     * @param image изображение.
     */
    public ImageTexture(String name, Image image) {
        super(name);

        if (image == null) throw new NullPointerException("image == null");

        this.image = image;
        this.width = image.getWidth(null);
        this.height = image.getHeight(null);
    }

    /**
     * Конструктор, устанавливающий имя, изображение и размеры.
     *
     * @param name   имя.
     * @param image  изображение.
     * @param width  ширина.
     * @param height высота.
     */
    public ImageTexture(String name, Image image, float width, float height) {
        super(name, width, height);

        if (image == null) throw new NullPointerException("image == null");

        this.image = image;
    }

    /**
     * Получает изображение текстуры.
     *
     * @return изображение текстуры.
     */
    public Image getImage() {
        return this.image;
    }

}
