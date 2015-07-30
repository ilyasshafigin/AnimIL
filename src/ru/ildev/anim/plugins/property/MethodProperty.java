/*
 *
 */
package ru.ildev.anim.plugins.property;

import ru.ildev.anim.easings.Easing;
import ru.ildev.anim.plugins.property.evaluator.TypeEvaluator;

/**
 * Интерфейс свойства, классы которого должны содержать матоды получения и
 * установки значений.
 *
 * @param <T> тип свойства.
 * @author Shafigin Ilyas (Шафигин Ильяс)
 * @version 1.0.1
 */
public interface MethodProperty<T> extends Property<T> {

    /**
     * Создает свойство произвольного типа.
     *
     * @param getName название метода получения
     * @param setName название метода установки
     * @param end     конечное значение.
     */
    static <P> MethodProperty<P> ofObject(String getName, String setName, TypeEvaluator<P> evaluator) {
        return new AbstractMethodProperty<>(getName, setName, evaluator);
    }

    /**
     * Создает свойство произвольного типа.
     *
     * @param getName название метода получения
     * @param setName название метода установки
     * @param end     конечное значение.
     */
    static <P> MethodProperty<P> ofObject(String getName, String setName, P end, TypeEvaluator<P> evaluator) {
        return new AbstractMethodProperty<>(getName, setName, end, evaluator);
    }

    /**
     * Создает свойство произвольного типа.
     *
     * @param getName название метода получения
     * @param setName название метода установки
     * @param begin   начальное значение.
     * @param end     конечное значение.
     */
    static <P> MethodProperty<P> ofObject(String getName, String setName, P begin, P end,
                                                 TypeEvaluator<P> evaluator) {
        return new AbstractMethodProperty<>(getName, setName, begin, end, evaluator);
    }

    /**
     * Создает свойство произвольного типа.
     *
     * @param getName название метода получения
     * @param setName название метода установки
     * @param end     конечное значение.
     * @param easing  эффект анимации.
     */
    static <P> MethodProperty<P> ofObject(String getName, String setName, P end, Easing easing,
                                                 TypeEvaluator<P> evaluator) {
        return new AbstractMethodProperty<>(getName, setName, end, evaluator);
    }

    /**
     * Создает свойство произвольного типа.
     *
     * @param getName название метода получения
     * @param setName название метода установки
     * @param begin   начальное значение.
     * @param end     конечное значение.
     * @param easing  эффект анимации.
     */
    static <P> MethodProperty<P> ofObject(String getName, String setName, P begin, P end, Easing easing,
                                          TypeEvaluator<P> evaluator) {
        return new AbstractMethodProperty<>(getName, setName, begin, end, easing, evaluator);
    }

    /**
     * Создает свойство произвольного типа.
     *
     * @param getName   название метода получения
     * @param setName   название метода установки
     * @param operation операция.
     * @param value     значение.
     */
    static <P> MethodProperty<P> ofObject(String getName, String setName, String operation, P value,
                                          TypeEvaluator<P> evaluator) {
        return new AbstractMethodProperty<>(getName, setName, operation, value, evaluator);
    }

    /**
     * Создает свойство произвольного типа.
     *
     * @param getName   название метода получения
     * @param setName   название метода установки
     * @param operation операция.
     * @param value     конечное значение.
     * @param easing    эффект анимации.
     */
    static <P> MethodProperty<P> ofObject(String getName, String setName, String operation, P value,
                                                 Easing easing, TypeEvaluator<P> evaluator) {
        return new AbstractMethodProperty<>(getName, setName, operation, value, easing, evaluator);
    }

    /**
     * Создает свойство типа {@code Integer}.
     *
     * @param getName название метода получения
     * @param setName название метода установки
     * @param end     конечное значение.
     */
    static MethodProperty<Integer> ofInt(String getName, String setName) {
        return new AbstractMethodProperty<>(getName, setName, TypeEvaluator.INTEGER);
    }

    /**
     * Создает свойство типа {@code Integer}.
     *
     * @param getName название метода получения
     * @param setName название метода установки
     * @param end     конечное значение.
     */
    public static MethodProperty<Integer> ofInt(String getName, String setName,
                                                int end) {
        return new AbstractMethodProperty<>(getName, setName, end, TypeEvaluator.INTEGER);
    }

    /**
     * Создает свойство типа {@code Integer}.
     *
     * @param getName название метода получения
     * @param setName название метода установки
     * @param begin   начальное значение.
     * @param end     конечное значение.
     */
    static MethodProperty<Integer> ofInt(String getName, String setName, int begin, int end) {
        return new AbstractMethodProperty<>(getName, setName, begin, end, TypeEvaluator.INTEGER);
    }

    /**
     * Создает свойство типа {@code Integer}.
     *
     * @param getName название метода получения
     * @param setName название метода установки
     * @param end     конечное значение.
     * @param easing  эффект анимации.
     */
    static MethodProperty<Integer> ofInt(String getName, String setName, int end, Easing easing) {
        return new AbstractMethodProperty<>(getName, setName, end, TypeEvaluator.INTEGER);
    }

    /**
     * Создает свойство типа {@code Integer}.
     *
     * @param getName название метода получения
     * @param setName название метода установки
     * @param begin   начальное значение.
     * @param end     конечное значение.
     * @param easing  эффект анимации.
     */
    static MethodProperty<Integer> ofInt(String getName, String setName, int begin, int end, Easing easing) {
        return new AbstractMethodProperty<>(getName, setName, begin, end, easing, TypeEvaluator.INTEGER);
    }

    /**
     * Создает свойство типа {@code Integer}.
     *
     * @param getName   название метода получения
     * @param setName   название метода установки
     * @param operation операция.
     * @param value     значение.
     */
    static MethodProperty<Integer> ofInt(String getName, String setName, String operation, int value) {
        return new AbstractMethodProperty<>(getName, setName, operation, value, TypeEvaluator.INTEGER);
    }

    /**
     * Создает свойство типа {@code Integer}.
     *
     * @param getName   название метода получения
     * @param setName   название метода установки
     * @param operation операция.
     * @param value     конечное значение.
     * @param easing    эффект анимации.
     */
    static MethodProperty<Integer> ofInt(String getName, String setName, String operation, int value, Easing easing) {
        return new AbstractMethodProperty<>(getName, setName, operation, value, easing, TypeEvaluator.INTEGER);
    }

    /**
     * Создает свойство типа {@code Float}.
     *
     * @param getName название метода получения
     * @param setName название метода установки
     */
    static MethodProperty<Float> ofFloat(String getName, String setName) {
        return new AbstractMethodProperty<>(getName, setName, TypeEvaluator.FLOAT);
    }

    /**
     * Создает свойство типа {@code Float}.
     *
     * @param getName название метода получения
     * @param setName название метода установки
     * @param end     конечное значение.
     */
    static MethodProperty<Float> ofFloat(String getName, String setName, float end) {
        return new AbstractMethodProperty<>(getName, setName, end, TypeEvaluator.FLOAT);
    }

    /**
     * Создает свойство типа {@code Float}.
     *
     * @param getName название метода получения
     * @param setName название метода установки
     * @param begin   начальное значение.
     * @param end     конечное значение.
     */
    static MethodProperty<Float> ofFloat(String getName, String setName, float begin, float end) {
        return new AbstractMethodProperty<>(getName, setName, begin, end, TypeEvaluator.FLOAT);
    }

    /**
     * Создает свойство типа {@code Float}.
     *
     * @param getName название метода получения
     * @param setName название метода установки
     * @param end     конечное значение.
     * @param easing  эффект анимации.
     */
    static MethodProperty<Float> ofFloat(String getName, String setName, float end, Easing easing) {
        return new AbstractMethodProperty<>(getName, setName, end, TypeEvaluator.FLOAT);
    }

    /**
     * Создает свойство типа {@code Float}.
     *
     * @param getName название метода получения
     * @param setName название метода установки
     * @param begin   начальное значение.
     * @param end     конечное значение.
     * @param easing  эффект анимации.
     */
    static MethodProperty<Float> ofFloat(String getName, String setName, float begin, float end, Easing easing) {
        return new AbstractMethodProperty<>(getName, setName, begin, end, easing, TypeEvaluator.FLOAT);
    }

    /**
     * Создает свойство типа {@code Float}.
     *
     * @param getName   название метода получения
     * @param setName   название метода установки
     * @param operation операция.
     * @param value     значение.
     */
    static MethodProperty<Float> ofFloat(String getName, String setName, String operation, float value) {
        return new AbstractMethodProperty<>(getName, setName, operation, value, TypeEvaluator.FLOAT);
    }

    /**
     * Создает свойство типа {@code Float}.
     *
     * @param getName   название метода получения
     * @param setName   название метода установки
     * @param operation операция.
     * @param value     конечное значение.
     * @param easing    эффект анимации.
     */
    static MethodProperty<Float> ofFloat(String getName, String setName, String operation, float value,
                                                Easing easing) {
        return new AbstractMethodProperty<>(getName, setName, operation, value, easing, TypeEvaluator.FLOAT);
    }

    /**
     * Создает свойство типа {@code Integer}.
     *
     * @param getName название метода получения
     * @param setName название метода установки
     * @param end     конечное значение.
     */
    static MethodProperty<Integer> ofARGB(String getName, String setName) {
        return new AbstractMethodProperty<>(getName, setName, TypeEvaluator.RGBA);
    }

    /**
     * Создает свойство типа {@code Integer}.
     *
     * @param getName название метода получения
     * @param setName название метода установки
     * @param end     конечное значение.
     */
    static MethodProperty<Integer> ofARGB(String getName, String setName, int end) {
        return new AbstractMethodProperty<>(getName, setName, end, TypeEvaluator.RGBA);
    }

    /**
     * Создает свойство типа {@code Integer}.
     *
     * @param getName название метода получения
     * @param setName название метода установки
     * @param begin   начальное значение.
     * @param end     конечное значение.
     */
    static MethodProperty<Integer> ofARGB(String getName, String setName, int begin, int end) {
        return new AbstractMethodProperty<>(getName, setName, begin, end, TypeEvaluator.RGBA);
    }

    /**
     * Создает свойство типа {@code Integer}.
     *
     * @param getName название метода получения
     * @param setName название метода установки
     * @param end     конечное значение.
     * @param easing  эффект анимации.
     */
    static MethodProperty<Integer> ofARGB(String getName, String setName, int end, Easing easing) {
        return new AbstractMethodProperty<>(getName, setName, end, TypeEvaluator.RGBA);
    }

    /**
     * Создает свойство типа {@code Integer}.
     *
     * @param getName название метода получения
     * @param setName название метода установки
     * @param begin   начальное значение.
     * @param end     конечное значение.
     * @param easing  эффект анимации.
     */
    static MethodProperty<Integer> ofARGB(String getName, String setName, int begin, int end, Easing easing) {
        return new AbstractMethodProperty<>(getName, setName, begin, end, easing, TypeEvaluator.RGBA);
    }

    /**
     * Создает свойство типа {@code Integer}.
     *
     * @param getName   название метода получения
     * @param setName   название метода установки
     * @param operation операция.
     * @param value     значение.
     */
    static MethodProperty<Integer> ofARGB(String getName, String setName, String operation, int value) {
        return new AbstractMethodProperty<>(getName, setName, operation, value, TypeEvaluator.RGBA);
    }

    /**
     * Создает свойство типа {@code Integer}.
     *
     * @param getName   название метода получения
     * @param setName   название метода установки
     * @param operation операция.
     * @param value     конечное значение.
     * @param easing    эффект анимации.
     */
    static MethodProperty<Integer> ofARGB(String getName, String setName, String operation, int value, Easing easing) {
        return new AbstractMethodProperty<>(getName, setName, operation, value, easing, TypeEvaluator.RGBA);
    }

}
