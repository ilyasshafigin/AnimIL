/*
 *
 */
package ru.ildev.anim.easings;

import ru.ildev.math.MoreMath;

/**
 * Класс эффекта анимации, который получается резким измерением скорости
 * анимации. Скорость анимации прямопропорциональна прогрессу анимации
 * в определенной степени.
 *
 * @author Ilyas74
 * @version 0.0.0
 */
public class Power extends Easing {

    /**
     * Степень.
     */
    private float power = 1.0f;

    /**
     * Конструктор, устанавливающий степень.
     *
     * @param power степень.
     */
    public Power(float power) {
        this.name = "power";
        this.power = power;
    }

    /**
     * Конструктор, устанавливающий модификацию и степень.
     *
     * @param mode  модификация.
     * @param power степень.
     */
    public Power(int mode, float power) {
        this.name = "power";
        this.mode = mode;
        this.power = power;
    }

    @Override
    public float easeIn(float state, float now, float firstNum, float lastNum,
                        float duration) {
        return lastNum * MoreMath.pow(state, this.power) + firstNum;
    }

    @Override
    public float easeOut(float state, float now, float firstNum, float lastNum,
                         float duration) {
        return -lastNum * (MoreMath.pow(1.0f - state, this.power) - 1.0f) + firstNum;
    }

    @Override
    public float easeInOut(float state, float now, float firstNum, float lastNum,
                           float duration) {
        if ((state *= 2.0f) < 1.0f) {
            return lastNum * 0.5f * MoreMath.pow(state, this.power) + firstNum;
        } else {
            return -lastNum * 0.5f * (MoreMath.pow(2.0f * (1.0f - state), this.power) - 2.0f) + firstNum;
        }
    }

}
