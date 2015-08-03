/*
 *
 */
package ru.ildev.anim.utils;

/**
 * Интерфейс построителя объектов.
 *
 * @param <T> тип объекта.
 * @author Ilyas74
 * @version 0.0.0
 */
@FunctionalInterface
public interface Builder<T> {

    /**
     * Строит объект.
     *
     * @return объект.
     */
    T build();

}
