package ru.ildev.anim.plugins.property.evaluator;

import static ru.ildev.anim.core.AnimationConstants.*;

/**
 * @author Ilyas Shafigin
 * @since 30.07.15
 */
public class IntegerEvaluator implements NumberEvaluator<Integer> {

    @Override
    public Integer evaluate(float position, Integer from, Integer to) {
        return (int) (from + position * (to - from));
    }

    @Override
    public Integer calculate(String operation, Integer from, Integer to) {
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
