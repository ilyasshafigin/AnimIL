/*
 *
 */
package ru.ildev.anim.easings;

/**
 * Класс эффекта анимации, который получается резким измерением скорости
 * анимации. Скорость анимации прямопропорциональна прогрессу анимации в пятой
 * степени.
 * <p>На основе проекта <a href="http://www.greensock.com">GreenSock</a></p>
 *
 * @author Ilyas74
 * @version 0.0.0
 */
public class Quint extends Easing {

    /**
     * Стандартный конструктор
     */
    public Quint() {
        this.name = "quint";
    }

    /**
     * Конструктор, устанавливающий модификацию.
     *
     * @param mode модификация.
     */
    public Quint(int mode) {
        this();
        this.mode = mode;
    }

    @Override
    public float easeIn(float state, float now, float first, float last,
                        float duration) {
        return last * state * state * state * state * state + first;
    }

    @Override
    public float easeOut(float state, float now, float first, float last,
                         float duration) {
        return last * ((state -= 1.0f) * state * state * state * state + 1.0f) + first;
    }

    @Override
    public float easeInOut(float state, float now, float first, float last,
                           float duration) {
        if ((state *= 2.0f) < 1.0f) {
            return last * 0.5f * state * state * state * state * state + first;
        } else {
            return last * 0.5f * ((state -= 2.0f) * state * state * state * state + 2.0f) + first;
        }
    }

}
