/*
 *
 */
package ru.ildev.anim.plugins.property;

import ru.ildev.anim.core.AnimationParameters;
import ru.ildev.anim.easings.Easing;
import ru.ildev.anim.plugins.AnimationPlugin;
import ru.ildev.anim.plugins.property.evaluator.TypeEvaluator;

/**
 * @param <T>
 * @author Shafigin Ilyas (Шафигин Ильяс)
 * @version 0.1.0
 */
public class AbstractArrayProperty<T> extends AbstractProperty<T> implements ArrayProperty<T> {

    /** */
    protected int index;

    /**
     *
     */
    public AbstractArrayProperty() {
    }

    /**
     * @param index
     * @param evaluator
     */
    public AbstractArrayProperty(int index, TypeEvaluator<T> evaluator) {
        super(String.valueOf(index), evaluator);
        this.index = index;
    }

    /**
     * @param index
     * @param end
     * @param evaluator
     */
    public AbstractArrayProperty(int index, T end, TypeEvaluator<T> evaluator) {
        super(String.valueOf(index), end, evaluator);
        this.index = index;
    }

    /**
     * @param index
     * @param begin
     * @param end
     * @param evaluator
     */
    public AbstractArrayProperty(int index, T begin, T end, TypeEvaluator<T> evaluator) {
        super(String.valueOf(index), begin, end, evaluator);
        this.index = index;
    }

    /**
     * @param index
     * @param end
     * @param easing
     * @param evaluator
     */
    public AbstractArrayProperty(int index, T end, Easing easing, TypeEvaluator<T> evaluator) {
        super(String.valueOf(index), end, easing, evaluator);
        this.index = index;
    }

    /**
     * @param index
     * @param begin
     * @param end
     * @param easing
     * @param evaluator
     */
    public AbstractArrayProperty(int index, T begin, T end, Easing easing, TypeEvaluator<T> evaluator) {
        super(String.valueOf(index), begin, end, easing, evaluator);
        this.index = index;
    }

    /**
     * @param index
     * @param operation
     * @param value
     * @param evaluator
     */
    public AbstractArrayProperty(int index, String operation, T value, TypeEvaluator<T> evaluator) {
        super(String.valueOf(index), operation, value, evaluator);
        this.index = index;
    }

    /**
     * @param index
     * @param operation
     * @param value
     * @param easing
     * @param evaluator
     */
    public AbstractArrayProperty(int index, String operation, T value, Easing easing, TypeEvaluator<T> evaluator) {
        super(String.valueOf(index), operation, value, easing, evaluator);
        this.index = index;
    }

    /**
     *
     */
    @Override
    public int getIndex() {
        return this.index;
    }

    @Override
    public void initialize(AnimationParameters parameters) {
        super.initialize(parameters);

        try {
            @SuppressWarnings("unchecked")
            T[] array = (T[]) this.target;
            if (this.index < 0 || this.index > array.length) {
                throw new IndexOutOfBoundsException("index " + this.index);
            }
        } catch (IndexOutOfBoundsException throwable) {
            this.setState(INITIALIZE, false);
            AnimationPlugin.LOGGER.throwing(this.getClass().getName(), "initialize", throwable);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void set(T value) {
        try {
            ((T[]) this.target)[this.index] = value;
        } catch (Exception exception) {
            AnimationPlugin.LOGGER.throwing(this.getClass().getName(), "set", exception);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get() {
        try {
            return ((T[]) this.target)[this.index];
        } catch (Exception exception) {
            AnimationPlugin.LOGGER.throwing(this.getClass().getName(), "get", exception);
            return null;
        }
    }

}
