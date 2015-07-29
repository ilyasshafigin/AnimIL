/*
 *
 */
package ru.ildev.anim.core;

import ru.ildev.anim.easings.Easing;
import ru.ildev.anim.events.AnimationEvent;
import ru.ildev.anim.events.AnimationListener;

/**
 * Объект опций анимации. Содержит все необходимые параметры для создания
 * объектов анимации.
 *
 * @author Shafigin Ilyas (Шафигин Ильяс)
 * @version 0.12.12
 */
public class AnimationOptions {

    /**
     * Продолжительность анимации.
     */
    public float duration = 0.0f;
    /**
     * Продолжительность задержки анимации.
     */
    public float delay = 0.0f;
    /**
     * Частота обновления кадров анимации.
     */
    public int fps = 0;
    /**
     * Масштабирование времени.
     */
    public float timeScale = 1.0f;
    /**
     * Эффект анимации.
     */
    public Easing easing = Easing.LINEAR;
    /**
     * Обработчик событий анимации.
     */
    public AnimationListener listener = null;
    /**
     * Триггеры событий.
     */
    public int triggers = AnimationEvent.COMPLETE;
    /**
     * Максимальное количество повторений.
     */
    public int repeat = 1;
    /**
     * Задержка между повторами анимации.
     */
    public float repeatDelay = 0.0f;
    /**
     * Режим времени анимации.
     */
    public int timeMode = AnimationConstants.MILLISECONDS;
    /**
     * Режим работы анимации.
     */
    public int playMode = AnimationConstants.FORWARD;

    /**
     * Стандартный конструктор.
     */
    public AnimationOptions() {
    }

    /**
     * Конструктор, устанавливающий продолжительность анимации.
     *
     * @param duration продолжительность анимации.
     */
    public AnimationOptions(float duration) {
        this.duration = duration;
    }

    /**
     * Конструктор, устанавливающий продолжительность анимации и эффект
     * анимации.
     *
     * @param duration продолжительность анимации.
     * @param easing   эффект аниамции.
     */
    public AnimationOptions(float duration, Easing easing) {
        if (easing == null) throw new NullPointerException("easing == null");

        this.duration = duration;
        this.easing = easing;
    }

    /**
     * Конструктор, устанавливающий продолжительность анимации, эффект анимации
     * и обработчик событий.
     *
     * @param duration продолжительность анимации.
     * @param easing   эффект аниамции.
     * @param listener обработчик событий.
     */
    public AnimationOptions(float duration, Easing easing, AnimationListener listener) {
        if (easing == null) throw new NullPointerException("easing == null");
        //if (listener == null) throw new NullPointerException("listener == null");

        this.duration = duration;
        this.easing = easing;
        this.listener = listener;
    }

    /**
     * Конструктор, устанавливающий продолжительность анимации и обработчик
     * событий.
     *
     * @param duration продолжительность анимации.
     * @param listener обработчик событий.
     */
    public AnimationOptions(float duration, AnimationListener listener) {
        //if (listener == null) throw new NullPointerException("listener == null");

        this.duration = duration;
        this.listener = listener;
    }

    /**
     * Конструктор, устанавливающий обработчик событий анимации.
     *
     * @param listener обработчик событий.
     */
    public AnimationOptions(AnimationListener listener) {
        //if (listener == null) throw new NullPointerException("listener == null");

        this.listener = listener;
    }

    /**
     * Конструктор, копирующий значения полей из другого объекта.
     *
     * @param options опции.
     */
    public AnimationOptions(AnimationOptions options) {
        if (options == null) throw new NullPointerException("options == null");

        this.duration = options.duration;
        this.delay = options.delay;
        this.fps = options.fps;
        this.timeScale = options.timeScale;
        this.easing = options.easing;
        this.listener = options.listener;
        this.triggers = options.triggers;
        this.repeat = options.repeat;
        this.repeatDelay = options.repeatDelay;
        this.timeMode = options.timeMode;
        this.playMode = options.playMode;
    }

    /**
     * Конструктор, копирующий значения полей из объекта параметров анимации.
     *
     * @param animation опции.
     */
    public AnimationOptions(ControllableAnimation animation) {
        if (animation == null) throw new NullPointerException("animation == null");

        this.duration = animation.duration;
        this.delay = animation.delay;
        this.fps = animation.fps;
        this.timeScale = animation.timeScale;
        this.easing = animation.easing;
        this.listener = animation.listener;
        this.triggers = animation.triggers;
        this.repeat = animation.repeat;
        this.repeatDelay = animation.repeatDelay;
        this.timeMode = animation.timeMode;
        this.playMode = animation.playMode;
    }

    /**
     * Получает продолжительность анимации.
     *
     * @return продолжительность анимации.
     * @see AnimationConstants#DURATION_INFINITE
     */
    public float getDuration() {
        return this.duration;
    }

    /**
     * Устанавливает продолжительность анимации.
     *
     * @param duration продолжительность анимации.
     * @see AnimationConstants#DURATION_INFINITE
     */
    public void setDuration(float duration) {
        this.duration = duration;
    }

    /**
     * Получает продолжительность задержки анимации.
     *
     * @return продолжительность задержки анимации.
     * @see AnimationConstants#DURATION_INFINITE
     */
    public float getDelay() {
        return this.delay;
    }

    /**
     * Устанавливает продолжительность задержки анимации.
     *
     * @param delay продолжительность задержки анимации.
     * @see AnimationConstants#DURATION_INFINITE
     */
    public void setDelay(float delay) {
        this.delay = delay;
    }

    /**
     * Получает частоту обновления кадров анимации.
     *
     * @return частоту обновления кадров анимации.
     */
    public int getFps() {
        return this.fps;
    }

    /**
     * Устанавливает частоту обновления кадров анимации.
     *
     * @param fps частота обновления кадров анимации.
     */
    public void setFps(int fps) {
        this.fps = fps;
    }

    /**
     * Получает масштабирование времени.
     *
     * @return масштабирование времени.
     */
    public float getTimeScale() {
        return this.timeScale;
    }

    /**
     * Устанавливает масштабирование времени.
     *
     * @param timeScale масштабирование времени.
     */
    public void setTimeScale(float timeScale) {
        this.timeScale = timeScale;
    }

    /**
     * Получает эффект аниамции.
     *
     * @return эффект аниамции.
     */
    public Easing getEasing() {
        return this.easing;
    }

    /**
     * Устанавливает эффект анимации.
     *
     * @param easing эффект аниамции.
     */
    public void setEasing(Easing easing) {
        if (easing == null) throw new NullPointerException("easing == null");
        this.easing = easing;
    }

    /**
     * Получает обработчик событий анимации.
     *
     * @return обработчик событий анимации.
     */
    public AnimationListener getListener() {
        return this.listener;
    }

    /**
     * Устанавливает обработчик событий анимации.
     *
     * @param listener обработчик событий анимации.
     */
    public void setListener(AnimationListener listener) {
        if (listener == null) throw new NullPointerException("listener == null");
        this.listener = listener;
    }

    /**
     * Получает триггеры событий.
     *
     * @return триггеры событий.
     */
    public int getTriggers() {
        return this.triggers;
    }

    /**
     * Устанавливает триггеры событий.
     *
     * @param triggers триггеры событий.
     */
    public void setTriggers(int triggers) {
        this.triggers = triggers;
    }

    /**
     * Устанавливает триггер.
     *
     * @param trigger триггер.
     */
    public void setTrigger(int trigger) {
        this.triggers |= trigger;
    }

    /**
     * Получает количество повторов анимации.
     *
     * @return количество повторов анимации.
     * @see AnimationConstants#REPEAT_INFINITE
     */
    public int getRepeat() {
        return this.repeat;
    }

    /**
     * Устанавливает количество повторов анимации.
     *
     * @param repeat количество повторов анимации.
     * @see AnimationConstants#REPEAT_INFINITE
     */
    public void setRepeat(int repeat) {
        this.repeat = repeat;
    }

    /**
     * Получает продолжительность задержки анимации между ее повторами.
     *
     * @return продолжительность задержки между повторами анимации.
     * @see AnimationConstants#DURATION_INFINITE
     */
    public float getRepeatDelay() {
        return this.repeatDelay;
    }

    /**
     * Устанавливает продолжительность задержки между повторами анимации.
     *
     * @param repeatDelay продолжительность задержки между повторами анимации.
     * @see AnimationConstants#DURATION_INFINITE
     */
    public void setRepeatDelay(float repeatDelay) {
        this.repeatDelay = repeatDelay;
    }

    /**
     * Получает режим времени анимации.
     *
     * @return режим времени.
     * @see AnimationConstants#MILLISECONDS
     * @see AnimationConstants#SECONDS
     * @see AnimationConstants#FRAMES
     */
    public int getTimeMode() {
        return this.timeMode;
    }

    /**
     * Устанавливает режим времени анимации.
     *
     * @param timeMode режим времени.
     * @see AnimationConstants#MILLISECONDS
     * @see AnimationConstants#SECONDS
     * @see AnimationConstants#FRAMES
     */
    public void setTimeMode(int timeMode) {
        this.timeMode = timeMode;
    }

    /**
     * Получает режим работы анимации.
     *
     * @return режим работы.
     * @see AnimationConstants#FORWARD
     * @see AnimationConstants#BACKWARD
     */
    public int getPlayMode() {
        return this.playMode;
    }

    /**
     * Устанавливает режим работы анимации.
     *
     * @param playMode режим работы.
     * @see AnimationConstants#FORWARD
     * @see AnimationConstants#BACKWARD
     */
    public void setPlayMode(int playMode) {
        this.playMode = playMode;
    }

    /**
     * Копирует значения полей из другого объекта опций.
     *
     * @param options опции.
     */
    public void copy(AnimationOptions options) {
        if (options == null) throw new NullPointerException("options == null");

        this.duration = options.duration;
        this.delay = options.delay;
        this.fps = options.fps;
        this.timeScale = options.timeScale;
        this.easing = options.easing;
        this.listener = options.listener;
        this.triggers = options.triggers;
        this.repeat = options.repeat;
        this.repeatDelay = options.repeatDelay;
        this.timeMode = options.timeMode;
        this.playMode = options.playMode;
    }

    @Override
    public AnimationOptions clone() {
        AnimationOptions options = new AnimationOptions();
        options.duration = this.duration;
        options.delay = this.delay;
        options.fps = this.fps;
        options.timeScale = this.timeScale;
        options.easing = this.easing;
        options.listener = this.listener;
        options.triggers = this.triggers;
        options.repeat = this.repeat;
        options.repeatDelay = this.repeatDelay;
        options.timeMode = this.timeMode;
        options.playMode = this.playMode;
        return options;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("AnimationOptions [");
        builder.append("duration=").append(this.duration);
        builder.append(", delay=").append(this.delay);
        builder.append(", fps=").append(this.fps);
        builder.append(", timeScale=").append(this.timeScale);
        builder.append(", easing=").append(this.easing);
        builder.append(", listener=").append(this.listener);
        builder.append(", trigger=").append(this.triggers);
        builder.append(", repeat=").append(this.repeat);
        builder.append(", repeatDelay=").append(this.repeatDelay);
        builder.append(", timeMode=").append(this.timeMode);
        builder.append(", playMode=").append(this.playMode);
        builder.append("]");
        return builder.toString();
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Float.floatToIntBits(this.delay);
        result = prime * result + Float.floatToIntBits(this.duration);
        result = prime * result + (this.easing == null ? 0 : this.easing.hashCode());
        result = prime * result + this.fps;
        result = prime * result + (this.listener == null ? 0 : this.listener.hashCode());
        result = prime * result + this.triggers;
        result = prime * result + this.playMode;
        result = prime * result + this.repeat;
        result = prime * result + Float.floatToIntBits(this.repeatDelay);
        result = prime * result + this.timeMode;
        result = prime * result + Float.floatToIntBits(this.timeScale);
        return result;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (obj instanceof AnimationOptions) {
            AnimationOptions other = (AnimationOptions) obj;
            if (this.duration != other.duration) return false;
            if (this.delay != other.delay) return false;
            if (this.easing == null) {
                if (other.easing != null) {
                    return false;
                }
            } else if (!this.easing.equals(other.easing)) {
                return false;
            }
            if (this.fps != other.fps) return false;
            if (this.listener == null) {
                if (other.listener != null) {
                    return false;
                }
            } else if (!this.listener.equals(other.listener)) {
                return false;
            }
            if (this.triggers != other.triggers) return false;
            if (this.playMode != other.playMode) return false;
            if (this.repeat != other.repeat) return false;
            if (this.repeatDelay != other.repeatDelay) return false;
            if (this.timeMode != other.timeMode) return false;
            if (this.timeScale != other.timeScale) return false;

            return true;
        } else {
            return false;
        }
    }

}
