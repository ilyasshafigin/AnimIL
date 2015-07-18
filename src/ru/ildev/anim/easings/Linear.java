/*
 *
 */
package ru.ildev.anim.easings;


/**
 * Класс линейного эффекта анимации.
 *
 * @author Ilyas74
 * @version 0.0.0
 */
public class Linear extends Easing {

    /**
     * Стандартный конструктор
     */
    public Linear() {
        this.name = "linear";
    }

    @Override
    public float easeIn(float state, float now, float first, float last,
                        float duration) {
        return first + last * state;
    }

    @Override
    public float easeOut(float state, float now, float first, float last,
                         float duration) {
        return first + last * state;
    }

    @Override
    public float easeInOut(float state, float now, float first, float last,
                           float duration) {
        return first + last * state;
    }

}
