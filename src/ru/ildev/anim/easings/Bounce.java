/*
 *
 */
package ru.ildev.anim.easings;

/**
 * Класс подпрыгивающего эффекта анимации.
 * <p>На основе проекта <a href="http://www.greensock.com">GreenSock</a></p>
 *
 * @author Ilyas74
 * @version 0.0.0
 */
public class Bounce extends Easing {

    /**
     * Стандартный конструктор
     */
    public Bounce() {
        this.name = "bounce";
    }

    /**
     * Конструктор, устанавливающий модификацию.
     *
     * @param mode модификация.
     */
    public Bounce(int mode) {
        this();
        this.mode = mode;
    }

    @Override
    public float easeIn(float state, float now, float first, float last,
                        float duration) {
        if ((state = 1.0f - state) < 1.0f / 2.75f) {
            return last * (1.0f - (7.5625f * state * state)) + first;
        } else if (state < 2.0f / 2.75f) {
            return last * (1.0f - (7.5625f * (state -= 1.5f / 2.75f) * state + 0.75f)) + first;
        } else if (state < 2.5f / 2.75f) {
            return last * (1.0f - (7.5625f * (state -= 2.25f / 2.75f) * state + 0.9375f)) + first;
        } else {
            return last * (1.0f - (7.5625f * (state -= 2.625f / 2.75f) * state + 0.984375f)) + first;
        }
    }

    @Override
    public float easeOut(float state, float now, float first, float last,
                         float duration) {
        if ((now /= duration) < 1.0f / 2.75f) {
            return last * (7.5625f * now * now) + first;
        } else if (now < 2.0f / 2.75f) {
            return last * (7.5625f * (now -= 0.54545455f) * now + 0.75f) + first;
        } else if (now < 2.5f / 2.75f) {
            return last * (7.5625f * (now -= 0.81818182f) * now + 0.9375f) + first;
        } else {
            return last * (7.5625f * (now -= 0.95454545f) * now + 0.984375f) + first;
        }
    }

    @Override
    public float easeInOut(float state, float now, float first, float last,
                           float duration) {
        boolean invert = false;
        if (state < 0.5) {
            invert = true;
            state = 1.0f - (state * 2.0f);
        } else {
            state = (state * 2.0f) - 1.0f;
        }

        if (state < 1.0f / 2.75f) {
            state = 7.5625f * state * state;
        } else if (state < 2.0f / 2.75f) {
            state = 7.5625f * (state -= 1.5f / 2.75f) * state + 0.75f;
        } else if (state < 2.5f / 2.75f) {
            state = 7.5625f * (state -= 2.25f / 2.75f) * state + 0.9375f;
        } else {
            state = 7.5625f * (state -= 2.625f / 2.75f) * state + 0.984375f;
        }

        return invert ? (1.0f - state) * 0.5f : state * 0.5f + 0.5f;
    }

}
