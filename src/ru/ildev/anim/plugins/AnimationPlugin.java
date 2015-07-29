/*
 *
 */
package ru.ildev.anim.plugins;

import ru.ildev.anim.core.ControllableAnimation;

import java.util.logging.Logger;

/**
 * Класс плагина анимации.
 *
 * @author Shafigin Ilyas (Шафигин Ильяс)
 * @version 0.0.0
 */
public interface AnimationPlugin {

    /**
     * Состояния инициализации.
     */
    int INITIALIZE = 1;

    /**
     * Регистратор.
     */
    Logger LOGGER = Logger.getLogger(AnimationPlugin.class.getName());

    /**
     * Инициализирует плагин.
     *
     * @param animation анимация.
     */
    void initialize(ControllableAnimation animation);

    /**
     * Запускается после начала анимации.
     *
     * @param animation анимация.
     */
    void begin(ControllableAnimation animation);

    /**
     * Запускается после окончания анимации.
     *
     * @param parameters анимация.
     */
    void end(ControllableAnimation parameters);

    /**
     * Обновляет плагин.
     *
     * @param animation анимация.
     */
    void update(ControllableAnimation animation);

    /**
     * Проверяет состояние.
     *
     * @return {@code true}, если плагин имеет состояние {@code state}
     */
    boolean hasState(int state);

}
