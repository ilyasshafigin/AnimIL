/*
 *
 */
package ru.ildev.anim.easings;

import ru.ildev.math.MoreMath;

/**
 * Класс эффекта анимации, который получается резким измерением скорости
 * анимации. Скорость анимации изменяется по синусоиде.
 * <p>На основе проекта <a href="http://www.greensock.com">GreenSock</a></p>
 *
 * @author Ilyas74
 * @version 0.0.0
 */
public class Sine extends Easing {

    /**
     * Стандартный конструктор
     */
    public Sine() {
        this.name = "sine";
    }

    /**
     * Конструктор, устанавливающий модификацию.
     *
     * @param mode модификация.
     */
    public Sine(int mode) {
        this();
        this.mode = mode;
    }

    @Override
    public float easeIn(float state, float now, float first, float last,
                        float duration) {
        return last * (1.0f - MoreMath.cos(state * MoreMath.HALF_PI)) + first;
    }

    @Override
    public float easeOut(float state, float now, float first, float last,
                         float duration) {
        return last * MoreMath.sin(state * MoreMath.HALF_PI) + first;
    }

    @Override
    public float easeInOut(float state, float now, float first, float last,
                           float duration) {
        return -last * 0.5f * (MoreMath.cos(state * MoreMath.PI) - 1.0f) + first;
    }

}
