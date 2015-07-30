/*
 *
 */
package ru.ildev.anim.plugins.property;

import ru.ildev.anim.easings.Easing;

/**
 * Интерфейс свойства, содержащего ключевые кадры.
 *
 * @author Shafigin Ilyas (Шафигин Ильяс)
 * @version 2.2.3
 */
public interface KeyFrameProperty extends Property<Float> {

    /**
     * Интерфейс ключевого кадра. Его класы должны содержать поля со значением
     * и время относительно продолжительности анимации, от 0 до 1.
     */
    class KeyFrame {

        /**
         * Значение ключевого кадра.
         */
        protected float value;
        /**
         * Время ключевого кадра.
         */
        protected float time;
        /**
         * Эффект анимации.
         */
        protected Easing easing = null;

        /**
         * Конструктор, устанавливающий значение ключевого кадра и
         * продолжительность анимации.
         *
         * @param value значение.
         * @param time  время.
         */
        public KeyFrame(float value, float time) {
            this.value = value;
            this.time = time;
        }

        /**
         * Конструктор, создающий объект ключевого кадра с заданным
         * значением, продолжительностью и эффектом анимации.
         *
         * @param value  значение.
         * @param time   время.
         * @param easing эфект анимации.
         */
        public KeyFrame(float value, float time, Easing easing) {
            this.value = value;
            this.time = time;
            this.easing = easing;
        }

        /**
         * Получает значение ключевого кадра.
         *
         * @return значение.
         */
        public float getValue() {
            return this.value;
        }

        /**
         * Получает время ключевого кадра.
         *
         * @return время ключевого кадра.
         */
        public float getTime() {
            return this.time;
        }

        /**
         * Получает эффект анимации.
         *
         * @return эффект анимации.
         */
        public Easing getEasing() {
            return this.easing;
        }

    }

    /**
     * Создает свойство.
     * @param keys ключевые кадры
     * @param property иное свойство, которое используется для установки значений.
     * @return свойство.
     */
    static KeyFrameProperty of(KeyFrame[] keys, Property<Float> property) {
        return new AbstractKeyFrameProperty(keys, property);
    }

    /**
     * Создает свойство.
     *
     * @param keys     двухмерный массив ключевых кадров, имеет вид: [[значение1, время1], [значение2, время2], ...]
     * @param property иное свойство, которое используется для установки значений.
     * @return свойство.
     */
    static KeyFrameProperty of(float[][] keys, Property<Float> property) {
        if (keys == null) throw new NullPointerException("keys == null");
        if (keys.length == 0) throw new IllegalArgumentException("keys.length == 0");

        KeyFrame[] keyFrames = new KeyFrame[keys.length];
        for (int i = 0; i < keys.length; i++) {
            float[] key = keys[i];
            keyFrames[i] = new KeyFrame(key[0], key[1]);
        }
        return new AbstractKeyFrameProperty(keyFrames, property);
    }

}
