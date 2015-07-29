/*
 *
 */
package ru.ildev.anim.events;


/**
 * Интерфейс обработчика событий анимации объекта.
 *
 * @author Ilyas74
 * @version 0.3.0
 */
@FunctionalInterface
public interface AnimationListener {

    /**
     * Событие анимации.
     *
     * @param event событие.
     */
    void onEvent(AnimationEvent event);

}
