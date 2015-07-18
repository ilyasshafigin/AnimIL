/*
 *
 */
package ru.ildev.anim.plugins.property;

import ru.ildev.anim.easings.Easing;
import ru.ildev.anim.plugins.AnimationPlugin;
import ru.ildev.anim.plugins.property.evaluator.TypeEvaluator;

/**
 * Интерфейс анимационного свойства.
 *
 * @param <T> тип свойства.
 * @author Ilyas74
 * @version 5.17.10
 */
public interface Property<T> extends AnimationPlugin {

    /**
     * Состояния установки всех значений.
     */
    int SETUP = 1 << 1;

    /**
     * Получает название свойства.
     *
     * @return название свойства.
     */
    String getName();

    /**
     * Получает анимируемый объект.
     *
     * @return анимируемый объект.
     */
    Object getTarget();

    /**
     * Получает эффект анимации.
     *
     * @return эффект анимации.
     */
    Easing getEasing();

    /**
     * Получает операцию над значениями свойства.
     *
     * @return операцию.
     */
    String getOperation();

    /**
     * @return
     */
    TypeEvaluator<T> getEvaluator();

    /**
     * Получает тип свойства в виде объекта класса.
     * @return объекта класса свойства.
     */
    //Class<T> getType();

    /**
     * Получает значение свойства.
     *
     * @return значение.
     */
    T get();

    /**
     * Устанавливает значение свойству.
     *
     * @param value значение.
     */
    void set(T value);

}
