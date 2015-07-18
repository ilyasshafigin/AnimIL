/*
 *
 */
package ru.ildev.anim.easings;

/**
 * Класс проскакивающего эффекта анимации.
 * <p>На основе проекта <a href="http://www.greensock.com">GreenSock</a></p>
 *
 * @author Ilyas74
 * @version 0.0.0
 */
public class Back extends Easing {

    /**
     * Значение проскакивания.
     */
    private float overshoot = 1.70158f;

    /**
     * Стандартный конструктор
     */
    public Back() {
        this.name = "back";
    }

    /**
     * Конструктор, устанавливающий модификацию.
     *
     * @param mode модификация.
     */
    public Back(int mode) {
        this();
        this.mode = mode;
    }

    /**
     * Конструктор, устанавливающий значение проскакивания.
     *
     * @param overshoot проскакивание.
     */
    public Back(float overshoot) {
        this();
        this.overshoot = overshoot;
    }

    /**
     * Конструктор, устанавливающий модификацию и значение проскакивания.
     *
     * @param mode      модификация.
     * @param overshoot проскакивание.
     */
    public Back(int mode, float overshoot) {
        this();
        this.mode = mode;
        this.overshoot = overshoot;
    }

    @Override
    public float easeIn(float state, float now, float first, float last,
                        float duration) {
        return last * state * state * ((this.overshoot + 1.0f) * state - this.overshoot) + first;
    }

    @Override
    public float easeOut(float state, float now, float first, float last,
                         float duration) {
        return last * ((state -= 1.0f) * state * ((this.overshoot + 1.0f) * state + this.overshoot) + 1.0f) + first;
    }

    @Override
    public float easeInOut(float state, float now, float first, float last,
                           float duration) {
        float p = this.overshoot * 1.525f;

        if ((state *= 2.0f) < 1.0f) {
            return last * 0.5f * state * state * ((p + 1.0f) * state - p) + first;
        } else {
            return last * 0.5f * ((state -= 2.0f) * state * ((p + 1.0f) * state + p) + 2.0f) + first;
        }
    }

}
