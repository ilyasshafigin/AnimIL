/*
 *
 */
package ru.ildev.anim.plugins.property;

import ru.ildev.anim.easings.Easing;
import ru.ildev.anim.plugins.property.evaluator.TypeEvaluator;

/**
 * @param <T>
 * @author Shafigin Ilyas (Шафигин Ильяс) <Ilyas74>
 * @version 0.0.0
 */
public interface ArrayProperty<T> extends Property<T> {

    /**
     * Получает индекс элемента в массиве, значение которого будет изменятся.
     *
     * @return индекс анимируемого элемента.
     */
    int getIndex();

    /**
     * Создает свойство произвольного типа.
     *
     * @param index индекс.
     */
    static <P> ArrayProperty<P> ofObject(int index, TypeEvaluator<P> evaluator) {
        return new AbstractArrayProperty<>(index, evaluator);
    }

    /**
     * Создает свойство произвольного типа.
     *
     * @param index индекс.
     * @param end   конечное значение.
     */
    static <P> ArrayProperty<P> ofObject(int index, P end, TypeEvaluator<P> evaluator) {
        return new AbstractArrayProperty<>(index, end, evaluator);
    }

    /**
     * Создает свойство произвольного типа.
     *
     * @param index индекс.
     * @param begin начальное значение.
     * @param end   конечное значение.
     */
    static <P> ArrayProperty<P> ofObject(int index, P begin, P end, TypeEvaluator<P> evaluator) {
        return new AbstractArrayProperty<>(index, begin, end, evaluator);
    }

    /**
     * Создает свойство произвольного типа.
     *
     * @param index  индекс.
     * @param end    конечное значение.
     * @param easing эффект анимации.
     */
    static <P> ArrayProperty<P> ofObject(int index, P end, Easing easing, TypeEvaluator<P> evaluator) {
        return new AbstractArrayProperty<>(index, end, evaluator);
    }

    /**
     * Создает свойство произвольного типа.
     *
     * @param index  индекс.
     * @param begin  начальное значение.
     * @param end    конечное значение.
     * @param easing эффект анимации.
     */
    static <P> ArrayProperty<P> ofObject(int index, P begin, P end, Easing easing, TypeEvaluator<P> evaluator) {
        return new AbstractArrayProperty<>(index, begin, end, easing, evaluator);
    }

    /**
     * Создает свойство произвольного типа.
     *
     * @param index     индекс.
     * @param operation операция.
     * @param value     значение.
     */
    static <P> ArrayProperty<P> ofObject(int index, String operation, P value, TypeEvaluator<P> evaluator) {
        return new AbstractArrayProperty<>(index, operation, value, evaluator);
    }

    /**
     * Создает свойство произвольного типа.
     *
     * @param index     индекс.
     * @param operation операция.
     * @param value     конечное значение.
     * @param easing    эффект анимации.
     */
    static <P> ArrayProperty<P> ofObject(int index, String operation, P value, Easing easing,
                                         TypeEvaluator<P> evaluator) {
        return new AbstractArrayProperty<>(index, operation, value, easing,
                evaluator);
    }

    /**
     * Создает свойство типа {@code Integer}.
     *
     * @param index индекс.
     */
    static ArrayProperty<Integer> ofInt(int index) {
        return new AbstractArrayProperty<>(index, TypeEvaluator.INTEGER);
    }

    /**
     * Создает свойство типа {@code Integer}.
     *
     * @param index индекс.
     * @param end   конечное значение.
     */
    static ArrayProperty<Integer> ofInt(int index, int end) {
        return new AbstractArrayProperty<>(index, end, TypeEvaluator.INTEGER);
    }

    /**
     * Создает свойство типа {@code Integer}.
     *
     * @param index индекс.
     * @param begin начальное значение.
     * @param end   конечное значение.
     */
    static ArrayProperty<Integer> ofInt(int index, int begin, int end) {
        return new AbstractArrayProperty<>(index, begin, end,
                TypeEvaluator.INTEGER);
    }

    /**
     * Создает свойство типа {@code Integer}.
     *
     * @param index  индекс.
     * @param end    конечное значение.
     * @param easing эффект анимации.
     */
    static ArrayProperty<Integer> ofInt(int index, int end, Easing easing) {
        return new AbstractArrayProperty<>(index, end, TypeEvaluator.INTEGER);
    }

    /**
     * Создает свойство типа {@code Integer}.
     *
     * @param index  индекс.
     * @param begin  начальное значение.
     * @param end    конечное значение.
     * @param easing эффект анимации.
     */
    static ArrayProperty<Integer> ofInt(int index, int begin, int end, Easing easing) {
        return new AbstractArrayProperty<>(index, begin, end, easing, TypeEvaluator.INTEGER);
    }

    /**
     * Создает свойство типа {@code Integer}.
     *
     * @param index     индекс.
     * @param operation операция.
     * @param value     значение.
     */
    static ArrayProperty<Integer> ofInt(int index, String operation, int value) {
        return new AbstractArrayProperty<>(index, operation, value, TypeEvaluator.INTEGER);
    }

    /**
     * Создает свойство типа {@code Integer}.
     *
     * @param index     индекс.
     * @param operation операция.
     * @param value     конечное значение.
     * @param easing    эффект анимации.
     */
    static ArrayProperty<Integer> ofInt(int index, String operation, int value, Easing easing) {
        return new AbstractArrayProperty<>(index, operation, value, easing, TypeEvaluator.INTEGER);
    }

    /**
     * Создает свойство типа {@code Float}.
     *
     * @param index индекс.
     */
    static ArrayProperty<Float> ofFloat(int index) {
        return new AbstractArrayProperty<>(index, TypeEvaluator.FLOAT);
    }

    /**
     * Создает свойство типа {@code Float}.
     *
     * @param index индекс.
     * @param end   конечное значение.
     */
    static ArrayProperty<Float> ofFloat(int index, float end) {
        return new AbstractArrayProperty<>(index, end, TypeEvaluator.FLOAT);
    }

    /**
     * Создает свойство типа {@code Float}.
     *
     * @param index индекс.
     * @param begin начальное значение.
     * @param end   конечное значение.
     */
    static ArrayProperty<Float> ofFloat(int index, float begin, float end) {
        return new AbstractArrayProperty<>(index, begin, end, TypeEvaluator.FLOAT);
    }

    /**
     * Создает свойство типа {@code Float}.
     *
     * @param index  индекс.
     * @param end    конечное значение.
     * @param easing эффект анимации.
     */
    static ArrayProperty<Float> ofFloat(int index, float end, Easing easing) {
        return new AbstractArrayProperty<>(index, end, TypeEvaluator.FLOAT);
    }

    /**
     * Создает свойство типа {@code Float}.
     *
     * @param index  индекс.
     * @param begin  начальное значение.
     * @param end    конечное значение.
     * @param easing эффект анимации.
     */
    static ArrayProperty<Float> ofFloat(int index, float begin, float end, Easing easing) {
        return new AbstractArrayProperty<>(index, begin, end, easing, TypeEvaluator.FLOAT);
    }

    /**
     * Создает свойство типа {@code Float}.
     *
     * @param index     индекс.
     * @param operation операция.
     * @param value     значение.
     */
    static ArrayProperty<Float> ofFloat(int index, String operation, float value) {
        return new AbstractArrayProperty<>(index, operation, value, TypeEvaluator.FLOAT);
    }

    /**
     * Создает свойство типа {@code Float}.
     *
     * @param index     индекс.
     * @param operation операция.
     * @param value     конечное значение.
     * @param easing    эффект анимации.
     */
    static ArrayProperty<Float> ofFloat(int index, String operation, float value, Easing easing) {
        return new AbstractArrayProperty<>(index, operation, value, easing, TypeEvaluator.FLOAT);
    }

    /**
     * Создает свойство типа {@code Integer}.
     *
     * @param index индекс.
     */
    static ArrayProperty<Integer> ofARGB(int index) {
        return new AbstractArrayProperty<>(index, TypeEvaluator.ARGB);
    }

    /**
     * Создает свойство типа {@code Integer}.
     *
     * @param index индекс.
     * @param end   конечное значение.
     */
    static ArrayProperty<Integer> ofARGB(int index, int end) {
        return new AbstractArrayProperty<>(index, end, TypeEvaluator.ARGB);
    }

    /**
     * Создает свойство типа {@code Integer}.
     *
     * @param index индекс.
     * @param begin начальное значение.
     * @param end   конечное значение.
     */
    public static ArrayProperty<Integer> ofARGB(int index, int begin, int end) {
        return new AbstractArrayProperty<>(index, begin, end, TypeEvaluator.ARGB);
    }

    /**
     * Создает свойство типа {@code Integer}.
     *
     * @param index  индекс.
     * @param end    конечное значение.
     * @param easing эффект анимации.
     */
    static ArrayProperty<Integer> ofARGB(int index, int end, Easing easing) {
        return new AbstractArrayProperty<>(index, end, TypeEvaluator.ARGB);
    }

    /**
     * Создает свойство типа {@code Integer}.
     *
     * @param index  индекс.
     * @param begin  начальное значение.
     * @param end    конечное значение.
     * @param easing эффект анимации.
     */
    static ArrayProperty<Integer> ofARGB(int index, int begin, int end, Easing easing) {
        return new AbstractArrayProperty<>(index, begin, end, easing, TypeEvaluator.ARGB);
    }

    /**
     * Создает свойство типа {@code Integer}.
     *
     * @param index     индекс.
     * @param operation операция.
     * @param value     значение.
     */
    static ArrayProperty<Integer> ofARGB(int index, String operation, int value) {
        return new AbstractArrayProperty<>(index, operation, value, TypeEvaluator.ARGB);
    }

    /**
     * Создает свойство типа {@code Integer}.
     *
     * @param index     индекс.
     * @param operation операция.
     * @param value     конечное значение.
     * @param easing    эффект анимации.
     */
    static ArrayProperty<Integer> ofARGB(int index, String operation, int value, Easing easing) {
        return new AbstractArrayProperty<>(index, operation, value, easing, TypeEvaluator.ARGB);
    }

}
