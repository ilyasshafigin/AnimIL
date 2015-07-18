/*
 *
 */
package ru.ildev.anim.plugins.property;

import ru.ildev.anim.easings.Easing;
import ru.ildev.anim.plugins.property.evaluator.TypeEvaluator;

/**
 * Интерфейс свойства, классы которого должны содержать поля.
 *
 * @param <T> тип свойства.
 * @author Shafigin Ilyas (Шафигин Ильяс) <Ilyas74>
 * @version 2.4.3
 */
public interface FieldProperty<T> extends Property<T> {

    /**
     * Создает свойство произвольного типа.
     *
     * @param name имя.
     * @param evaluator
     */
    static <P> FieldProperty<P> ofObject(String name, TypeEvaluator<P> evaluator) {
        return new AbstractFieldProperty<>(name, evaluator);
    }

    /**
     * Создает свойство произвольного типа.
     *
     * @param name имя.
     * @param end  конечное значение.
     * @param evaluator
     */
    static <P> FieldProperty<P> ofObject(String name, P end, TypeEvaluator<P> evaluator) {
        return new AbstractFieldProperty<>(name, end, evaluator);
    }

    /**
     * Создает свойство произвольного типа.
     *
     * @param name  имя.
     * @param begin начальное значение.
     * @param end   конечное значение.
     * @param evaluator
     */
    static <P> FieldProperty<P> ofObject(String name, P begin, P end, TypeEvaluator<P> evaluator) {
        return new AbstractFieldProperty<>(name, begin, end, evaluator);
    }

    /**
     * Создает свойство произвольного типа.
     *
     * @param name   имя.
     * @param end    конечное значение.
     * @param easing эффект анимации.
     * @param evaluator
     */
    static <P> FieldProperty<P> ofObject(String name, P end, Easing easing, TypeEvaluator<P> evaluator) {
        return new AbstractFieldProperty<>(name, end, evaluator);
    }

    /**
     * Создает свойство произвольного типа.
     *
     * @param name   имя.
     * @param begin  начальное значение.
     * @param end    конечное значение.
     * @param easing эффект анимации.
     * @param evaluator
     */
    static <P> FieldProperty<P> ofObject(String name, P begin, P end, Easing easing, TypeEvaluator<P> evaluator) {
        return new AbstractFieldProperty<>(name, begin, end, easing, evaluator);
    }

    /**
     * Создает свойство произвольного типа.
     *
     * @param name      имя.
     * @param operation операция.
     * @param value     значение.
     * @param evaluator
     */
    static <P> FieldProperty<P> ofObject(String name, String operation, P value, TypeEvaluator<P> evaluator) {
        return new AbstractFieldProperty<>(name, operation, value, evaluator);
    }

    /**
     * Создает свойство произвольного типа.
     *
     * @param name      имя.
     * @param operation операция.
     * @param value     конечное значение.
     * @param easing    эффект анимации.
     * @param evaluator
     */
    static <P> FieldProperty<P> ofObject(String name, String operation, P value, Easing easing,
                                         TypeEvaluator<P> evaluator) {
        return new AbstractFieldProperty<>(name, operation, value, easing, evaluator);
    }

    /**
     * Создает свойство типа {@code Integer}.
     *
     * @param name имя.
     */
    static FieldProperty<Integer> ofInt(String name) {
        return new AbstractFieldProperty<>(name, TypeEvaluator.INTEGER);
    }

    /**
     * Создает свойство типа {@code Integer}.
     *
     * @param name имя.
     * @param end  конечное значение.
     */
    static FieldProperty<Integer> ofInt(String name, int end) {
        return new AbstractFieldProperty<>(name, end, TypeEvaluator.INTEGER);
    }

    /**
     * Создает свойство типа {@code Integer}.
     *
     * @param name  имя.
     * @param begin начальное значение.
     * @param end   конечное значение.
     */
    static FieldProperty<Integer> ofInt(String name, int begin, int end) {
        return new AbstractFieldProperty<>(name, begin, end, TypeEvaluator.INTEGER);
    }

    /**
     * Создает свойство типа {@code Integer}.
     *
     * @param name   имя.
     * @param end    конечное значение.
     * @param easing эффект анимации.
     */
    static FieldProperty<Integer> ofInt(String name, int end, Easing easing) {
        return new AbstractFieldProperty<>(name, end, TypeEvaluator.INTEGER);
    }

    /**
     * Создает свойство типа {@code Integer}.
     *
     * @param name   имя.
     * @param begin  начальное значение.
     * @param end    конечное значение.
     * @param easing эффект анимации.
     */
    static FieldProperty<Integer> ofInt(String name, int begin, int end, Easing easing) {
        return new AbstractFieldProperty<>(name, begin, end, easing, TypeEvaluator.INTEGER);
    }

    /**
     * Создает свойство типа {@code Integer}.
     *
     * @param name      имя.
     * @param operation операция.
     * @param value     значение.
     */
    static FieldProperty<Integer> ofInt(String name, String operation, int value) {
        return new AbstractFieldProperty<>(name, operation, value, TypeEvaluator.INTEGER);
    }

    /**
     * Создает свойство типа {@code Integer}.
     *
     * @param name      имя.
     * @param operation операция.
     * @param value     конечное значение.
     * @param easing    эффект анимации.
     */
    static FieldProperty<Integer> ofInt(String name, String operation, int value, Easing easing) {
        return new AbstractFieldProperty<>(name, operation, value, easing, TypeEvaluator.INTEGER);
    }

    /**
     * Создает свойство типа {@code Float}.
     *
     * @param name имя.
     */
    static FieldProperty<Float> ofFloat(String name) {
        return new AbstractFieldProperty<>(name, TypeEvaluator.FLOAT);
    }

    /**
     * Создает свойство типа {@code Float}.
     *
     * @param name имя.
     * @param end  конечное значение.
     */
    static FieldProperty<Float> ofFloat(String name, float end) {
        return new AbstractFieldProperty<>(name, end, TypeEvaluator.FLOAT);
    }

    /**
     * Создает свойство типа {@code Float}.
     *
     * @param name  имя.
     * @param begin начальное значение.
     * @param end   конечное значение.
     */
    static FieldProperty<Float> ofFloat(String name, float begin, float end) {
        return new AbstractFieldProperty<>(name, begin, end, TypeEvaluator.FLOAT);
    }

    /**
     * Создает свойство типа {@code Float}.
     *
     * @param name   имя.
     * @param end    конечное значение.
     * @param easing эффект анимации.
     */
    static FieldProperty<Float> ofFloat(String name, float end, Easing easing) {
        return new AbstractFieldProperty<>(name, end, easing, TypeEvaluator.FLOAT);
    }

    /**
     * Создает свойство типа {@code Float}.
     *
     * @param name   имя.
     * @param begin  начальное значение.
     * @param end    конечное значение.
     * @param easing эффект анимации.
     */
    static FieldProperty<Float> ofFloat(String name, float begin, float end, Easing easing) {
        return new AbstractFieldProperty<>(name, begin, end, easing, TypeEvaluator.FLOAT);
    }

    /**
     * Создает свойство типа {@code Float}.
     *
     * @param name      имя.
     * @param operation операция.
     * @param value     значение.
     */
    static FieldProperty<Float> ofFloat(String name, String operation, float value) {
        return new AbstractFieldProperty<>(name, operation, value, TypeEvaluator.FLOAT);
    }

    /**
     * Создает свойство типа {@code Float}.
     *
     * @param name      имя.
     * @param operation операция.
     * @param value     конечное значение.
     * @param easing    эффект анимации.
     */
    static FieldProperty<Float> ofFloat(String name, String operation, float value, Easing easing) {
        return new AbstractFieldProperty<>(name, operation, value, easing, TypeEvaluator.FLOAT);
    }

    /**
     * Создает свойство типа {@code Integer}.
     *
     * @param name имя.
     */
    static FieldProperty<Integer> ofARGB(String name) {
        return new AbstractFieldProperty<>(name, TypeEvaluator.ARGB);
    }

    /**
     * Создает свойство типа {@code Integer}.
     *
     * @param name имя.
     * @param end  конечное значение.
     */
    static FieldProperty<Integer> ofARGB(String name, int end) {
        return new AbstractFieldProperty<>(name, end, TypeEvaluator.ARGB);
    }

    /**
     * Создает свойство типа {@code Integer}.
     *
     * @param name  имя.
     * @param begin начальное значение.
     * @param end   конечное значение.
     */
    static FieldProperty<Integer> ofARGB(String name, int begin, int end) {
        return new AbstractFieldProperty<>(name, begin, end, TypeEvaluator.ARGB);
    }

    /**
     * Создает свойство типа {@code Integer}.
     *
     * @param name   имя.
     * @param end    конечное значение.
     * @param easing эффект анимации.
     */
    static FieldProperty<Integer> ofARGB(String name, int end, Easing easing) {
        return new AbstractFieldProperty<>(name, end, TypeEvaluator.ARGB);
    }

    /**
     * Создает свойство типа {@code Integer}.
     *
     * @param name   имя.
     * @param begin  начальное значение.
     * @param end    конечное значение.
     * @param easing эффект анимации.
     */
    static FieldProperty<Integer> ofARGB(String name, int begin, int end, Easing easing) {
        return new AbstractFieldProperty<>(name, begin, end, easing, TypeEvaluator.ARGB);
    }

    /**
     * Создает свойство типа {@code Integer}.
     *
     * @param name      имя.
     * @param operation операция.
     * @param value     значение.
     */
    static FieldProperty<Integer> ofARGB(String name, String operation, int value) {
        return new AbstractFieldProperty<>(name, operation, value, TypeEvaluator.ARGB);
    }

    /**
     * Создает свойство типа {@code Integer}.
     *
     * @param name      имя.
     * @param operation операция.
     * @param value     конечное значение.
     * @param easing    эффект анимации.
     */
    static FieldProperty<Integer> ofARGB(String name, String operation, int value, Easing easing) {
        return new AbstractFieldProperty<>(name, operation, value, easing, TypeEvaluator.ARGB);
    }

}
