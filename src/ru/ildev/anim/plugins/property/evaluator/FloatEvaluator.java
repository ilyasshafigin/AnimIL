package ru.ildev.anim.plugins.property.evaluator;

import static ru.ildev.anim.core.AnimationConstants.*;

/**
 * @author Ilyas Shafigin
 * @since 30.07.15
 */
public class FloatEvaluator implements NumberEvaluator<Float> {

    @Override
    public Float evaluate(float position, Float from, Float to) {
        return from + position * (to - from);
    }

    @Override
    public Float calculate(String operation, Float from, Float to) {
        switch (operation) {
            case ADD:
                return from + to;
            case SUB:
                return from - to;
            case MUL:
                return from * to;
            case DIV:
                return from / to;
            case MOD:
                return from % to;
            case NONE:
            default:
                return to;
        }
    }

}
