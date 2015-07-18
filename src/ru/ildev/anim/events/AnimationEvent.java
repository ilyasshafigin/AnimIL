/*
 *
 */
package ru.ildev.anim.events;

import ru.ildev.anim.core.Animation;
import ru.ildev.anim.core.AnimationParameters;

/**
 * Класс события анимации.
 *
 * @author Shafigin Ilyas (Шафигин Ильяс) <Ilyas74>
 * @version 0.1.0
 */
public class AnimationEvent {

    /**
     * Перечисление типов событий.
     */
    public enum Type {

        /**
         * Событие запуска анимации.
         */
        START,
        /**
         * Событие остановки анимации.
         */
        STOP,
        /**
         * Событие перезапуска анимации.
         */
        RESTART,
        /**
         * Событие паузы анимации.
         */
        PAUSE,
        /**
         * Событие повтора анимации.
         */
        REPEAT,
        /**
         * Событие шага анимации.
         */
        STEP

    }

    /**
     * Анимация.
     */
    protected /*Controllable*/ Animation animation;
    /**
     * Параметры анимации.
     */
    protected AnimationParameters parameters;
    /**
     * Тип события.
     */
    protected Type type;

    /**
     * Конструктор.
     *
     * @param animation  анимация.
     * @param parameters параметры анимации.
     * @param type       тип события.
     */
    public AnimationEvent(Animation animation, AnimationParameters parameters,
                          Type type) {
        if (animation == null) throw new NullPointerException("animation == null");
        if (parameters == null) throw new NullPointerException("parameters == null");
        if (type == null) throw new NullPointerException("type == null");

        this.animation = animation;
        this.parameters = parameters;
        this.type = type;
    }

    /**
     * Получает анимацию.
     *
     * @return анимацию.
     */
    public Animation getAnimation() {
        return this.animation;
    }

    /**
     * Получает параметры анимации.
     *
     * @return параметры анимации.
     */
    public AnimationParameters getParameters() {
        return this.parameters;
    }

    /**
     * Получает тип события.
     *
     * @return тип события.
     */
    public Type getType() {
        return this.type;
    }

}
