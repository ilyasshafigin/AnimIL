/*
 *
 */
package ru.ildev.anim.events;

import ru.ildev.anim.core.Animation;

/**
 * Интерфейс обработчика событий аниматора.
 *
 * @author Ilyas74
 * @version 0.2.1
 */
public interface AnimatorListener {

    /**
     * Событие старта аниматора.
     */
    default void onStart() {
    }

    /**
     * Событие остановки аниматора.
     */
    default void onStop() {
    }

    /**
     * Событие паузы аниматора.
     */
    default void onPause() {
    }

    /**
     * Событие шага аниматора.
     */
    default void onStep() {
    }

    /**
     * Событие возобновления аниматора после паузы.
     */
    default void onResume() {
    }

    /**
     * Событие начала анимирования анимации.
     *
     * @param animation анимация.
     */
    default void onAnimate(Animation animation) {
    }

}
