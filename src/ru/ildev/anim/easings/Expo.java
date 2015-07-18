/*
 *
 */
package ru.ildev.anim.easings;

import ru.ildev.math.MoreMath;

/**
 * Класс эффекта анимации, который получается резким измерением скорости
 * анимации. Скорость анимации прямопропорциональна экспоненте прогресса
 * анимации.
 * <p>На основе проекта <a href="http://www.greensock.com">GreenSock</a></p>
 *
 * @author Ilyas74
 */
public class Expo extends Easing {

    /**
     * Стандартный конструктор
     */
    public Expo() {
        this.name = "expo";
    }

    /**
     * Конструктор, устанавливающий модификацию.
     *
     * @param mode модификация.
     */
    public Expo(int mode) {
        this();
        this.mode = mode;
    }

    @Override
    public float easeIn(float state, float now, float first, float last,
                        float duration) {
        return last * MoreMath.exp(10.0f * (state - 1.0f)) + first;
    }

    @Override
    public float easeOut(float state, float now, float first, float last,
                         float duration) {
        return last * (1.0f - MoreMath.exp(-10.0f * state)) + first;
    }

    @Override
    public float easeInOut(float state, float now, float first, float last,
                           float duration) {
        if ((state *= 2.0f) < 1.0f) {
            return last * 0.5f * MoreMath.exp(10.0f * (state - 1.0f)) + first;
        } else {
            return last * 0.5f * (2.0f - MoreMath.exp(-10.0f * (state - 1.0f))) + first;
        }
    }

}
