/*
 *
 */
package ru.ildev.anim.events;


/**
 * Интерфейс обработчика событий анимации объекта.
 *
 * @author Ilyas74
 * @version 0.2.0
 */
public interface AnimationListener {

    /**
     * Событие запуска анимации.
     *
     * @param event событие анимации.
     */
    default void onStart(AnimationEvent event) {
    }

    /**
     * Событие остановки анимации.
     *
     * @param event событие анимации.
     */
    default void onStop(AnimationEvent event) {
    }

    /**
     * Событие перезапуска анимации.
     *
     * @param event событие анимации.
     */
    default void onRestart(AnimationEvent event) {
    }

    /**
     * Событие паузы анимации.
     *
     * @param event событие анимации.
     */
    default void onPause(AnimationEvent event) {
    }

    /**
     * Событие повтора анимации.
     *
     * @param event событие анимации.
     */
    default void onRepeat(AnimationEvent event) {
    }

    /**
     * Событие шага анимации.
     *
     * @param event событие анимации.
     */
    default void onStep(AnimationEvent event) {
    }

}
