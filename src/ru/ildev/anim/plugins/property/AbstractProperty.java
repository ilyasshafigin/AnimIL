/*
 *
 */
package ru.ildev.anim.plugins.property;

import ru.ildev.anim.core.AnimationConstants;
import ru.ildev.anim.core.ControllableAnimation;
import ru.ildev.anim.easings.Easing;
import ru.ildev.anim.plugins.property.evaluator.TypeEvaluator;

/**
 * Абстрактный класс свойства.
 *
 * @param <T> тип свойства.
 * @author Shafigin Ilyas (Шафигин Ильяс)
 * @version 1.2.2
 */
public abstract class AbstractProperty<T> implements Property<T> {

    /**
     * Название свойства.
     */
    protected String name = null;
    /**
     * Анимируемый объект.
     */
    protected Object target = null;
    /**
     * Эффект анимации.
     */
    protected Easing easing = null;
    /**
     * Опрерация над значениями свойства.
     */
    protected String operation = AnimationConstants.NONE;
    /**
     * Состояние свойства.
     */
    protected int state;
    /**
     * Начальное значение.
     */
    protected T begin;
    /**
     * Конечное значение.
     */
    protected T end;
    /**  */
    protected TypeEvaluator<T> evaluator = null;

    /**
     * Стандартный конструктор.
     */
    public AbstractProperty() {
    }

    /**
     * Конструктор, устанавливающий имя свойству.
     *
     * @param name имя свойства.
     */
    public AbstractProperty(String name, TypeEvaluator<T> evaluator) {
        if (name == null) throw new NullPointerException("name == null");
        if (evaluator == null) throw new NullPointerException(
                "evaluator == null");
        this.name = name;
        this.evaluator = evaluator;
        this.setState(SETUP, true);
    }

    /**
     * Конструктор, устанавливающий имя свойства и конечное значение.
     *
     * @param name      имя.
     * @param end       конечное значение.
     * @param evaluator
     */
    public AbstractProperty(String name, T end, TypeEvaluator<T> evaluator) {
        if (name == null) throw new NullPointerException("name == null");
        if (evaluator == null) throw new NullPointerException(
                "evaluator == null");
        if (end == null) throw new NullPointerException("end == null");
        this.name = name;
        this.evaluator = evaluator;
        this.end = end;
    }

    /**
     * Конструктор, устанавливающий имя свойства, начальное и конечное значения.
     *
     * @param name      имя.
     * @param begin     начальное значение.
     * @param end       конечное значение.
     * @param evaluator
     */
    public AbstractProperty(String name, T begin, T end,
                            TypeEvaluator<T> evaluator) {
        if (name == null) throw new NullPointerException("name == null");
        if (evaluator == null) throw new NullPointerException(
                "evaluator == null");
        if (begin == null) throw new NullPointerException("begin == null");
        if (end == null) throw new NullPointerException("end == null");
        this.name = name;
        this.evaluator = evaluator;
        this.begin = begin;
        this.end = end;
        this.setState(SETUP, true);
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
    public AbstractProperty(String name, T end, Easing easing,
                            TypeEvaluator<T> evaluator) {
        if (name == null) throw new NullPointerException("name == null");
        if (evaluator == null) throw new NullPointerException(
                "evaluator == null");
        if (end == null) throw new NullPointerException("end == null");
        this.name = name;
        this.evaluator = evaluator;
        this.end = end;
        this.easing = easing;
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
    public AbstractProperty(String name, T begin, T end, Easing easing,
                            TypeEvaluator<T> evaluator) {
        if (name == null) throw new NullPointerException("name == null");
        if (evaluator == null) throw new NullPointerException(
                "evaluator == null");
        if (begin == null) throw new NullPointerException("begin == null");
        if (end == null) throw new NullPointerException("end == null");

        this.name = name;
        this.evaluator = evaluator;
        this.begin = begin;
        this.end = end;
        this.easing = easing;
        this.setState(SETUP, true);
    }

    /**
     * Конструктор, устанавливающий имя свойства и временное значение.
     *
     * @param name      имя.
     * @param operation операция.
     * @param value     значение.
     * @param evaluator
     */
    public AbstractProperty(String name, String operation, T value,
                            TypeEvaluator<T> evaluator) {
        if (name == null) throw new NullPointerException("name == null");
        if (evaluator == null) throw new NullPointerException(
                "evaluator == null");
        if (value == null) throw new NullPointerException("value == null");
        this.name = name;
        this.evaluator = evaluator;
        this.operation = operation;
        this.end = value;
    }

    /**
     * Конструктор, устанавливающий имя свойства, временное значение и эффект
     * анимации.
     *
     * @param name      имя.
     * @param operation операция.
     * @param value     значение.
     * @param easing    эффект анимации.
     * @param evaluator
     */
    public AbstractProperty(String name, String operation, T value,
                            Easing easing, TypeEvaluator<T> evaluator) {
        if (name == null) throw new NullPointerException("name == null");
        if (evaluator == null) throw new NullPointerException(
                "evaluator == null");
        if (value == null) throw new NullPointerException("value == null");

        this.name = name;
        this.evaluator = evaluator;
        this.operation = operation;
        this.end = value;
        this.easing = easing;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Object getTarget() {
        return this.target;
    }

    @Override
    public Easing getEasing() {
        return this.easing;
    }

    @Override
    public String getOperation() {
        return this.operation;
    }

    @Override
    public TypeEvaluator<T> getEvaluator() {
        return this.evaluator;
    }

    @Override
    public boolean hasState(int state) {
        return (this.state & state) == state;
    }

    /**
     * Устанавливает состояние
     *
     * @param state состояние.
     * @param flag флаг.
     */
    protected void setState(int state, boolean flag) {
        if (flag) {
            this.state |= state;
        } else {
            this.state &= ~state;
        }
    }

    /**
     * Получает начальное значение.
     *
     * @return начальное значение.
     */
    public T getBegin() {
        return this.begin;
    }

    /**
     * Получает конечное значение.
     *
     * @return конечное значение.
     */
    public T getEnd() {
        return this.end;
    }

    @Override
    public void initialize(ControllableAnimation animation) {
        // Устанавливаем анимируемый объект.
        this.target = animation.getTarget();
        // Если нет анимируемого объекта.
        if (this.target == null) {
            // Устанавливаем состояние.
            this.setState(INITIALIZE, false);
            // Выходим.
            return;
        }
        // Если не установлен эффект анимации свойства,
        if (this.easing == null) {
            // то устанавливаем стандартный.
            this.easing = animation.getEasing();
        }
        // Устанавливаем состояние.
        this.setState(INITIALIZE, true);
    }

    @Override
    public void begin(ControllableAnimation animation) {
        if (this.hasState(INITIALIZE) && !this.hasState(INITIALIZE)) {
            this.begin = this.get();

            // Если начального или конечного значений нет.
            if (this.begin == null || this.end == null) {
                // Устанавливаем состояние.
                this.setState(INITIALIZE, false);
                // Выходим.
                return;
            }

            // Применяем операции.
            this.end = this.evaluator.calculate(this.operation, this.begin, this.end);
            // Устанавливаем состояние.
            this.setState(SETUP, true);
        }
    }

    @Override
    public void end(ControllableAnimation animation) {
        //
    }

    @Override
    public void update(ControllableAnimation animation) {
        if (this.hasState(SETUP)) {
            float position = animation.getPosition(this.easing);
            this.set(this.evaluator.evaluate(position, this.begin, this.end));
        }
    }

}
