package ru.ildev.anim.plugins.property.evaluator;

import static ru.ildev.anim.core.AnimationConstants.*;

/**
 * @author Ilyas Shafigin
 * @since 30.07.15
 */
public class RGBAEvaluator implements NumberEvaluator<java.lang.Integer> {

    @Override
    public Integer evaluate(float position, Integer from, Integer to) {
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
    public Integer calculate(String operation, Integer from, Integer to) {
        int fromA = 0xff & (from >> 24);
        int fromR = 0xff & (from >> 16);
        int fromG = 0xff & (from >> 8);
        int fromB = 0xff & from;

        int toA = 0xff & (to >> 24);
        int toR = 0xff & (to >> 16);
        int toG = 0xff & (to >> 8);
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
        return (0xff & toA) << 24 | (0xff & toR) << 16 | (0xff & toG) << 8 | 0xff & toB;
    }

}
