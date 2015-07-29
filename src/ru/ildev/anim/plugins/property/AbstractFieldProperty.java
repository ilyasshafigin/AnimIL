/*
 *
 */
package ru.ildev.anim.plugins.property;

import ru.ildev.anim.core.ControllableAnimation;
import ru.ildev.anim.easings.Easing;
import ru.ildev.anim.plugins.AnimationPlugin;
import ru.ildev.anim.plugins.property.evaluator.TypeEvaluator;

import java.lang.reflect.Field;

/**
 * Абстрактный класс свойства, содержащего поле.
 *
 * @param <T> тип свойства.
 * @author Shafigin Ilyas (Шафигин Ильяс)
 * @version 0.1.4
 */
public class AbstractFieldProperty<T> extends AbstractProperty<T> implements FieldProperty<T> {

    /**
     * Поле.
     */
    protected Field field = null;

    /**
     * Стандартный конструктор.
     */
    public AbstractFieldProperty() {
        super();
    }

    /**
     * Конструктор, устанавливающий имя свойству.
     *
     * @param name      имя свойства.
     * @param evaluator
     */
    public AbstractFieldProperty(String name, TypeEvaluator<T> evaluator) {
        super(name, evaluator);
    }

    /**
     * Конструктор, устанавливающий имя свойства и конечное значение.
     *
     * @param name      имя.
     * @param end       конечное значение.
     * @param evaluator
     */
    public AbstractFieldProperty(String name, T end, TypeEvaluator<T> evaluator) {
        super(name, end, evaluator);
    }

    /**
     * Конструктор, устанавливающий имя свойства, начальное и конечное значения.
     *
     * @param name      имя.
     * @param begin     начальное значение.
     * @param end       конечное значение.
     * @param evaluator
     */
    public AbstractFieldProperty(String name, T begin, T end,
                                 TypeEvaluator<T> evaluator) {
        super(name, begin, end, evaluator);
    }

    /**
     * Конструктор, устанавливающий имя свойства, конечное значение и эффект
     * анимации.
     *
     * @param name      имя.
     * @param end       конечное значение.
     * @param easing    эффект анимации.
     * @param evaluator
     */
    public AbstractFieldProperty(String name, T end, Easing easing,
                                 TypeEvaluator<T> evaluator) {
        super(name, end, easing, evaluator);
    }

    /**
     * Конструктор, устанавливающий имя свойства, начальное и конечное значения,
     * эффект анимации.
     *
     * @param name      имя.
     * @param begin     начальное значение.
     * @param end       конечное значение.
     * @param easing    эффект анимации.
     * @param evaluator
     */
    public AbstractFieldProperty(String name, T begin, T end, Easing easing,
                                 TypeEvaluator<T> evaluator) {
        super(name, begin, end, easing, evaluator);
    }

    /**
     * Конструктор, устанавливающий имя свойства и временное значение.
     *
     * @param name      имя.
     * @param operation операция.
     * @param value     значение.
     * @param evaluator
     */
    public AbstractFieldProperty(String name, String operation, T value,
                                 TypeEvaluator<T> evaluator) {
        super(name, operation, value, evaluator);
    }

    /**
     * Конструктор, устанавливающий имя свойства, временное значение и эффект
     * анимации.
     *
     * @param name      имя.
     * @param operation операция.
     * @param value     конечное значение.
     * @param easing    эффект анимации.
     * @param evaluator
     */
    public AbstractFieldProperty(String name, String operation, T value,
                                 Easing easing, TypeEvaluator<T> evaluator) {
        super(name, operation, value, easing, evaluator);
    }

    @Override
    public void initialize(ControllableAnimation animation) {
        super.initialize(animation);

        if (this.field == null) {
            this.setState(INITIALIZE, false);
            // Находим поле по названию свойства.
            try {
                if (this.name.indexOf('.') > 0) {
                    String[] names = this.name.split("\\.");
                    if (names.length < 2) return;

                    for (int i = 0; i < names.length; i++) {
                        String name = names[i].trim();
                        if (name.isEmpty()) return;
                        if (i != 0) this.target = this.field.get(this.target);
                        if (this.target == null) return;

                        this.name = name;
                        try {
                            this.field = this.target.getClass().getDeclaredField(name);
                        } catch (NoSuchFieldException | SecurityException throwable) {
                            this.field = this.target.getClass().getField(this.name);
                        }
                        this.field.setAccessible(true);
                    }
                } else {
                    try {
                        this.field = this.target.getClass().getDeclaredField(name);
                    } catch (NoSuchFieldException | SecurityException throwable) {
                        this.field = this.target.getClass().getField(this.name);
                    }
                    this.field.setAccessible(true);
                }
            } catch (IllegalArgumentException | IllegalAccessException
                    | NoSuchFieldException | SecurityException throwable) {
                //AnimationPlugin.LOGGER.throwing(this.getClass().getName(), "initialize", throwable);
                throwable.printStackTrace();
                return;
            }

            if (this.field != null) {
                this.setState(INITIALIZE, true);
            } else {
                AnimationPlugin.LOGGER.warning("Not found \"" + this.name + "\" field");
                return;
            }
        }
    }

    @Override
    public void set(T value) {
        try {
            this.field.set(this.target, value);
        } catch (IllegalArgumentException | IllegalAccessException exception) {
            AnimationPlugin.LOGGER.throwing(this.getClass().getName(), "set", exception);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get() {
        try {
            return (T) this.field.get(this.target);
        } catch (IllegalArgumentException | IllegalAccessException exception) {
            AnimationPlugin.LOGGER.throwing(this.getClass().getName(), "get",
                    exception);
            return null;
        }
    }

}
