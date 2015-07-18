/*
 *
 */
package ru.ildev.anim.easings;

import ru.ildev.math.MoreMath;

/**
 * Класс эффекта анимации, который получается резким измерением скорости
 * анимации. Скорость анимации прямопропорциональна квадратному корню прогресса
 * анимации.
 * <p>На основе проекта <a href="http://www.greensock.com">GreenSock</a></p>
 *
 * @author Ilyas74
 * @version 0.0.0
 */
public class Circle extends Easing {

    /**
     * Стандартный конструктор
     */
    public Circle() {
        this.name = "circle";
    }

    /**
     * Конструктор, устанавливающий модификацию.
     *
     * @param mode модификация.
     */
    public Circle(int mode) {
        this();
        this.mode = mode;
    }

    @Override
    public float easeIn(float state, float now, float first, float last,
                        float duration) {
        return -last * (MoreMath.sqrt(1.0f - state * state) - 1.0f) + first;
    }

    @Override
    public float easeOut(float state, float now, float first, float last,
                         float duration) {
        return last * MoreMath.sqrt(1.0f - (state -= 1.0f) * state) + first;
    }

    @Override
    public float easeInOut(float state, float now, float first, float last,
                           float duration) {
        if ((state *= 2.0f) < 1.0f) {
            return -last * 0.5f * (MoreMath.sqrt(1.0f - state * state) - 1.0f) + first;
        } else {
            return last * 0.5f * (MoreMath.sqrt(1.0f - (state -= 2.0f) * state) + 1.0f) + first;
        }
    }

}
