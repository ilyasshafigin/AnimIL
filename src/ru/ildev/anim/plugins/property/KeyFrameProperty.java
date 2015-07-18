/*
 *
 */
package ru.ildev.anim.plugins.property;

import ru.ildev.anim.easings.Easing;

/**
 * Интерфейс свойства, содержащего ключевые кадры.
 *
 * @param <T> тип свойства.
 * @author Shafigin Ilyas (Шафигин Ильяс)
 * @version 2.2.3
 */
public interface KeyFrameProperty<T> extends Property<T> {

    /**
     * Интерфейс ключевого кадра. Его класы должны содержать поля со значением
     * и время относительно продолжительности анимации, от 0 до 1.
     *
     * @param <T> тип ключевого кадра.
     */
    abstract class KeyFrame<T> {

        /**
         * Значение ключевого кадра.
         */
        protected T value;
        /**
         * Время ключевого кадра.
         */
        protected float time;
        /**
         * Эффект анимации.
         */
        protected Easing easing = null;

        /**
         * Получает значение ключевого кадра.
         *
         * @return значение.
         */
        public T getValue() {
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

        /**
         * Класс ключевого кадра с типом {@code java.lang.Integer}.
         */
        public static class Integer extends KeyFrame<java.lang.Integer> {

            /**
             * Конструктор, создающий объект ключевого кадра с заданным
             * значением  и продолжительностью анимации.
             *
             * @param value значение.
             * @param time  время.
             */
            public Integer(int value, float time) {
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
            public Integer(int value, float time, Easing easing) {
                this.value = value;
                this.time = time;
                this.easing = easing;
            }

        }

        /**
         * Класс ключевого кадра с типом {@code java.lang.Float}.
         */
        public static class Float extends KeyFrame<java.lang.Float> {

            /**
             * Конструктор, устанавливающий значение ключевого кадра и
             * продолжительность анимации.
             *
             * @param value значение.
             * @param time  время.
             */
            public Float(float value, float time) {
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
            public Float(float value, float time, Easing easing) {
                this.value = value;
                this.time = time;
                this.easing = easing;
            }

        }

    }

    /**
     * @param keys
     * @param property
     * @return
     */
    static KeyFrameProperty<Integer> ofInt(KeyFrame<Integer>[] keys, Property<Integer> property) {
        return new AbstractKeyFrameProperty<>(keys, property);
    }

    /**
     * @param keys
     * @param property
     * @return
     */
    static KeyFrameProperty<Float> ofFloat(KeyFrame<Float>[] keys, Property<Float> property) {
        return new AbstractKeyFrameProperty<>(keys, property);
    }

}
