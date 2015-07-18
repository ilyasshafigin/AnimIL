/*
 *
 */
package ru.ildev.anim.plugins;

import ru.ildev.anim.core.AnimationParameters;

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
     * @param parameters параметры анимации.
     */
    void initialize(AnimationParameters parameters);

    /**
     * Запускается после начала анимации.
     *
     * @param parameters параметры анимации.
     */
    void begin(AnimationParameters parameters);

    /**
     * Запускается после окончания анимации.
     *
     * @param parameters параметры анимации.
     */
    void end(AnimationParameters parameters);

    /**
     * Обновляет плагин.
     *
     * @param parameters параметры анимации.
     */
    void update(AnimationParameters parameters);

    /**
     * Проверяет состояние.
     *
     * @return {@code true}, если плагин имеет состояние {@code state}
     */
    boolean hasState(int state);

}
