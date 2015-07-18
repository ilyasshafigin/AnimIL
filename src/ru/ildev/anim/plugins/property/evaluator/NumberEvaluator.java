/**
 *
 */
package ru.ildev.anim.plugins.property.evaluator;

import static ru.ildev.anim.core.AnimationConstants.*;

/**
 * @author Shafigin Ilyas (Шафигин Ильяс)
 * @version 0.0.0
 */
public interface NumberEvaluator<T extends Number> extends TypeEvaluator<T> {

    /**
     * @author Shafigin Ilyas <ilyas174@gmail.com>
     */
    class Integer implements NumberEvaluator<java.lang.Integer> {

        @Override
        public java.lang.Integer evaluate(float position,
                                          java.lang.Integer from, java.lang.Integer to) {
            return (int) (from + position * (to - from));
        }

        @Override
        public java.lang.Integer calculate(String operation,
                                           java.lang.Integer from, java.lang.Integer to) {
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

    /**
     * @author Shafigin Ilyas <ilyas174@gmail.com>
     */
    class Float implements NumberEvaluator<java.lang.Float> {

        @Override
        public java.lang.Float evaluate(float position, java.lang.Float from,
                                        java.lang.Float to) {
            return from + position * (to - from);
        }

        @Override
        public java.lang.Float calculate(String operation,
                                         java.lang.Float from, java.lang.Float to) {
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

    /**
     * @author Shafigin Ilyas <ilyas174@gmail.com>
     */
    class ARGB implements NumberEvaluator<java.lang.Integer> {

        @Override
        public java.lang.Integer evaluate(float position,
                                          java.lang.Integer from, java.lang.Integer to) {
            int fromA = 0xff & from >> 24;
            int fromR = 0xff & from >> 16;
            int fromG = 0xff & from >> 8;
            int fromB = 0xff & from;

            int toA = 0xff & to >> 24;
            int toR = 0xff & to >> 16;
            int toG = 0xff & to >> 8;
            int toB = 0xff & to;

            int nowA = (int) (fromA + position * (toA - fromA));
            int nowR = (int) (fromR + position * (toR - fromR));
            int nowG = (int) (fromG + position * (toG - fromG));
            int nowB = (int) (fromB + position * (toB - fromB));

            return nowA << 24 | nowR << 16 | nowG << 8 | nowB;
        }

        @Override
        public java.lang.Integer calculate(String operation,
                                           java.lang.Integer from, java.lang.Integer to) {
            int fromA = 0xff & from >> 24;
            int fromR = 0xff & from >> 16;
            int fromG = 0xff & from >> 8;
            int fromB = 0xff & from;

            int toA = 0xff & to >> 24;
            int toR = 0xff & to >> 16;
            int toG = 0xff & to >> 8;
            int toB = 0xff & to;
            switch (operation) {
                case ADD: {
                    toA += fromA;
                    toR += fromR;
                    toG += fromG;
                    toB += fromB;
                    break;
                }
                case SUB: {
                    toA -= fromA;
                    toR -= fromR;
                    toG -= fromG;
                    toB -= fromB;
                    break;
                }
                case MUL: {
                    toA *= fromA;
                    toR *= fromR;
                    toG *= fromG;
                    toB *= fromB;
                    break;
                }
                case DIV: {
                    toA /= fromA;
                    toR /= fromR;
                    toG /= fromG;
                    toB /= fromB;
                    break;
                }
                case MOD: {
                    toA %= fromA;
                    toR %= fromR;
                    toG %= fromG;
                    toB %= fromB;
                    break;
                }
                case NONE:
                default:
                    break;
            }
            return (0xff & toA) << 24 | (0xff & toR) << 16 | (0xff & toG) << 8
                    | 0xff & toB;
        }

    }

}
