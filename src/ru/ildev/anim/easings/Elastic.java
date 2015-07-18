/*
 *
 */
package ru.ildev.anim.easings;

import ru.ildev.math.MoreMath;

/**
 * Класс эластичного эффекта анимации.
 * <p>На основе проекта <a href="http://www.greensock.com">GreenSock</a></p>
 *
 * @author Ilyas74
 * @version 0.0.0
 */
public class Elastic extends Easing {

    /**
     * Амплитуда колебаний.
     */
    private float amplitude = 1.0f;
    /**
     * Период колебаний.
     */
    private float period = 0.3f;

    private float p = this.period / MoreMath.TWO_PI * MoreMath.asin(1.0f / this.amplitude);

    /**
     * Стандартный конструктор
     */
    public Elastic() {
        this.name = "elastic";
    }

    /**
     * Конструктор, устанавливающий модификацию.
     *
     * @param mode модификация.
     */
    public Elastic(int mode) {
        this();
        this.mode = mode;
    }

    /**
     * Конструктор, устанавливающий амплитуду и период колебаний.
     *
     * @param amplitude амплитуда.
     * @param period    период.
     */
    public Elastic(float amplitude, float period) {
        this();
        this.amplitude = amplitude;
        this.period = period;
        this.p = this.period / MoreMath.TWO_PI * MoreMath.asin(1.0f / this.amplitude);
    }

    /**
     * Конструктор, устанавливающий модификацию, амплитуду и период колебаний.
     *
     * @param mode      модификация.
     * @param amplitude амплитуда.
     * @param period    период.
     */
    public Elastic(int mode, float amplitude, float period) {
        this();
        this.mode = mode;
        this.amplitude = amplitude;
        this.period = period;
        this.p = this.period / MoreMath.TWO_PI * MoreMath.asin(1.0f / this.amplitude);
    }

    @Override
    public float easeIn(float state, float now, float first, float last,
                        float duration) {
        return -last * this.amplitude * MoreMath.pow(2.0f, 10.0f * (state -= 1.0f)) * MoreMath.sin((state - this.p) * MoreMath.TWO_PI / this.period) + first;
    }

    @Override
    public float easeOut(float state, float now, float first, float last,
                         float duration) {
        return last * (this.amplitude * MoreMath.pow(2.0f, -10.0f * state) * MoreMath.sin((state - this.p) * MoreMath.TWO_PI / this.period) + 1.0f) + first;
    }

    @Override
    public float easeInOut(float state, float now, float first, float last,
                           float duration) {
        if ((state *= 2.0f) < 1.0f) {
            return -0.5f * (last * this.amplitude * MoreMath.pow(2.0f, 10.0f * (state -= 1.0f)) * MoreMath.sin((state - this.p) * MoreMath.TWO_PI / this.period)) + first;
        } else {
            return last * (this.amplitude * MoreMath.pow(2.0f, -10.0f * (state -= 1.0f)) * MoreMath.sin((state - this.p) * MoreMath.TWO_PI / this.period) * 0.5f + 1.0f) + first;
        }
    }

}
