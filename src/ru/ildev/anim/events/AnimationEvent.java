/*
 *
 */
package ru.ildev.anim.events;

import ru.ildev.anim.core.ControllableAnimation;

/**
 * Класс события анимации.
 *
 * <pre> {@code
 * forward :      BEGIN                                   COMPLETE
 * forward :      START    END      START    END      START    END
 * |--------------[XXXXXXXXXX]------[XXXXXXXXXX]------[XXXXXXXXXX]
 * backward:      END   START       END   START       END   START
 * backward:      COMPLETE                                  BEGIN
 * }</pre>
 *
 * @author Shafigin Ilyas (Шафигин Ильяс) <Ilyas74>
 * @version 0.1.0
 */
public class AnimationEvent {

    /**
     * Начало анимации
     */
    public static final int BEGIN = 1;
    /**
     * Начало повтора анимации
     */
    public static final int START = 1 << 1;
    /**
     * Конец повтора анимации
     */
    public static final int END = 1 << 2;
    /**
     * Завершение анимации
     */
    public static final int COMPLETE = 1 << 3;
    /**
     * Событие перезапуска анимации
     */
    public static final int RESTART = 1 << 4;
    /**
     * Событие приостановки анимации
     */
    public static final int PAUSE = 1 << 5;
    /**
     * Событие запуска анимации после приостановки
     */
    public static final int RESUME = 1 << 6;
    /**
     * Событие остановки анимации
     */
    public static final int STOP = 1 << 7;
    /**
     * Событие шага анимации
     */
    public static final int STEP = 1 << 8;

    /**
     * Анимация.
     */
    protected ControllableAnimation animation;
    /**
     * Тип события.
     */
    protected int type;

    /**
     * Конструктор.
     *
     * @param animation  анимация.
     * @param type       тип события.
     */
    public AnimationEvent(ControllableAnimation animation, int type) {
        this.animation = animation;
        this.type = type;
    }

    /**
     * Получает анимацию.
     *
     * @return анимацию.
     */
    public ControllableAnimation getAnimation() {
        return this.animation;
    }

    /**
     * Получает тип события.
     *
     * @return тип события.
     */
    public int getType() {
        return this.type;
    }

}
