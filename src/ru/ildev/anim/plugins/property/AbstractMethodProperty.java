/*
 *
 */
package ru.ildev.anim.plugins.property;

import ru.ildev.anim.core.AnimationParameters;
import ru.ildev.anim.easings.Easing;
import ru.ildev.anim.plugins.AnimationPlugin;
import ru.ildev.anim.plugins.property.evaluator.TypeEvaluator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Абстрактный класс свойства, содержащего методы получания и установки
 * значениям свойства.
 *
 * @param <T> тип свойства.
 * @author Shafigin Ilyas (Шафигин Ильяс) <Ilyas74>
 * @version 0.1.2
 */
public class AbstractMethodProperty<T> extends AbstractProperty<T> implements MethodProperty<T> {

    /**
     * Метод получения.
     */
    protected Method get = null;
    /**
     * Метод установки.
     */
    protected Method set = null;
    /**
     * Название метода получения.
     */
    protected String getName;
    /**
     * Название метода установки.
     */
    protected String setName;

    /**
     * Стандартный констуктор.
     */
    public AbstractMethodProperty() {
    }

    /**
     * Конструктор, устанавливающий названия методам.
     *
     * @param getName   название метода получания.
     * @param setName   название метода установки.
     * @param evaluator
     */
    public AbstractMethodProperty(String getName, String setName, TypeEvaluator<T> evaluator) {
        if (getName == null) throw new NullPointerException("getName == null");
        if (setName == null) throw new NullPointerException("setName == null");

        this.getName = getName;
        this.setName = setName;
        this.evaluator = evaluator;
    }

    /**
     * Конструктор, устанавливающий названия методам и конечное значение.
     *
     * @param getName   название метода получания.
     * @param setName   название метода установки.
     * @param end       конечное значение.
     * @param evaluator
     */
    public AbstractMethodProperty(String getName, String setName, T end, TypeEvaluator<T> evaluator) {
        this(getName, setName, evaluator);
        if (end == null) throw new NullPointerException("end == null");
        this.end = end;
    }

    /**
     * Конструктор, устанавливающий названия методам, начальное и конечное
     * значения.
     *
     * @param getName   название метода получания.
     * @param setName   название метода установки.
     * @param begin     начальное значение.
     * @param end       конечное значение.
     * @param evaluator
     */
    public AbstractMethodProperty(String getName, String setName, T begin, T end, TypeEvaluator<T> evaluator) {
        this(getName, setName, evaluator);
        if (begin == null) throw new NullPointerException("begin == null");
        if (end == null) throw new NullPointerException("end == null");
        this.begin = begin;
        this.end = end;
        this.setState(SETUP, true);
    }

    /**
     * Конструктор, устанавливающий названия методам, конечное значение и эффект
     * анимации.
     *
     * @param getName   название метода получания.
     * @param setName   название метода установки.
     * @param end       конечное значение.
     * @param easing    эффект анимации.
     * @param evaluator
     */
    public AbstractMethodProperty(String getName, String setName, T end,
                                  Easing easing, TypeEvaluator<T> evaluator) {
        this(getName, setName, evaluator);
        if (end == null) throw new NullPointerException("end == null");
        this.end = end;
        this.easing = easing;
    }

    /**
     * Конструктор, устанавливающий названия методам, начальное и конечное
     * значения, эффект анимации.
     *
     * @param getName   название метода получания.
     * @param setName   название метода установки.
     * @param begin     начальное значение.
     * @param end       конечное значение.
     * @param easing    эффект анимации.
     * @param evaluator
     */
    public AbstractMethodProperty(String getName, String setName, T begin,
                                  T end, Easing easing, TypeEvaluator<T> evaluator) {
        this(getName, setName, evaluator);
        if (begin == null) throw new NullPointerException("begin == null");
        if (end == null) throw new NullPointerException("end == null");
        this.begin = begin;
        this.end = end;
        this.easing = easing;
        this.setState(SETUP, true);
    }

    /**
     * Конструктор, устанавливающий названия методам и временное значение.
     *
     * @param getName   название метода получания.
     * @param setName   название метода установки.
     * @param operation операция.
     * @param value     значение.
     * @param evaluator
     */
    public AbstractMethodProperty(String getName, String setName,
                                  String operation, T value, TypeEvaluator<T> evaluator) {
        this(getName, setName, evaluator);
        if (value == null) throw new NullPointerException("value == null");
        this.operation = operation;
        this.end = value;
    }

    /**
     * Конструктор, устанавливающий названия методам, временное значение и
     * эффект анимации.
     *
     * @param getName   название метода получания.
     * @param setName   название метода установки.
     * @param operation операция.
     * @param value     конечное значение.
     * @param easing    эффект анимации.
     * @param evaluator
     */
    public AbstractMethodProperty(String getName, String setName,
                                  String operation, T value, Easing easing, TypeEvaluator<T> evaluator) {
        this(getName, setName, evaluator);
        if (value == null) throw new NullPointerException("value == null");
        this.operation = operation;
        this.end = value;
        this.easing = easing;
    }

    @Override
    public void initialize(AnimationParameters parameters) {
        super.initialize(parameters);

        if (this.get == null || this.set == null) {
            Method get, set;
            try {
                get = this.target.getClass().getDeclaredMethod(this.getName);
                set = this.target.getClass().getDeclaredMethod(this.setName/*,this.getType()*/);
            } catch (NoSuchMethodException | SecurityException exception) {
                AnimationPlugin.LOGGER.throwing(this.getClass().getName(), "initialize", exception);
                return;
            }

            try {
                get.setAccessible(true);
                set.setAccessible(true);
            } catch (SecurityException exception) {
                AnimationPlugin.LOGGER.throwing(this.getClass().getName(), "initialize", exception);
                return;
            }

            this.get = get;
            this.set = set;
        }
    }

    @Override
    public void set(T value) {
        try {
            this.set.invoke(this.target, value);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException exception) {
            AnimationPlugin.LOGGER.throwing(this.getClass().getName(), "set", exception);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get() {
        try {
            return (T) this.get.invoke(this.target);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException exception) {
            AnimationPlugin.LOGGER.throwing(this.getClass().getName(), "initialize", exception);
            return null;
        }
    }

}
