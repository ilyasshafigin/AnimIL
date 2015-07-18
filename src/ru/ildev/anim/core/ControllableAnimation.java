/*
 *
 */
package ru.ildev.anim.core;

import ru.ildev.anim.easings.Easing;
import ru.ildev.anim.events.AnimationEvent;
import ru.ildev.anim.events.AnimationListener;

/**
 * Класс контролируемой анимации, т.е. анимации, которую можно остановить,
 * запустить, поставить на паузу.
 *
 * @author Shafigin Ilyas (Шафигин Ильяс)
 * @version 0.4.8
 */
public abstract class ControllableAnimation implements Animation, AnimationConstants {

    /**
     * Перечисление состояний анимации.
     *
     * @author Ilyas74
     * @version 0.1.0
     */
    public enum State {

        /**
         * Запущена.
         */
        START,
        /**
         * Приостановлена.
         */
        PAUSE,
        /**
         * Остановлена.
         */
        STOP,
        /**
         * Удаляемая.
         */
        REMOVE

    }

    /**
     * Параметры анимации.
     */
    protected AnimationParameters parameters;
    /**
     * Состояние анимации.
     */
    protected State state = State.STOP;

    /**
     * Создает объект контролируемой анимации.
     */
    public ControllableAnimation() {
        this.parameters = new AnimationParameters();
    }

    /**
     * Получает параметры анимации.
     *
     * @return параметры анимации.
     */
    public AnimationParameters getParameters() {
        return this.parameters;
    }

    /**
     * Получает объект параметров анимации.
     *
     * @return объект параметров анимации.
     */
    public AnimationOptions getOptions() {
        return new AnimationOptions(this.parameters);
    }

    /**
     * Получает состояние анимации.
     *
     * @return состояние анимации.
     */
    public State getState() {
        return this.state;
    }

    /**
     * Определяет, запущена ли анимация.
     *
     * @return {@code true}, если анимация запущена.
     */
    public boolean isStart() {
        return this.state == State.START;
    }

    /**
     * Определяет, приостановлена ли анимация.
     *
     * @return {@code true}, если анимация приостановлена.
     */
    public boolean isPause() {
        return this.state == State.PAUSE;
    }

    /**
     * Определяет, остановлена ли анимация.
     *
     * @return {@code true}, если анимация остановлена.
     */
    public boolean isStop() {
        return this.state == State.STOP;
    }

    /**
     * Определяет, будет ли удалена анимаци..
     *
     * @return {@code true}, если анимация будет удалена.
     */
    public boolean isRemoved() {
        return this.state == State.REMOVE;
    }

    /**
     * Запускает анимацию.
     */
    public void start() {
        // Если анимация уже запущена, то выходим.
        if (this.state == State.START) return;
        // Если анимация будет удалена, то выходим.
        if (this.state == State.REMOVE) return;

        // Устанавливаем состояние.
        this.state = State.START;
        // Запускаем событие.
        this.parameters.fireEvent(AnimationEvent.Type.START, this);
    }

    /**
     * Цикл повторений анимации.
     */
    public void repeat() {
        //
        this.parameters.repeat();
        // Запускаем событие.
        this.parameters.fireEvent(AnimationEvent.Type.REPEAT, this);
    }

    /**
     * Приостанавливает анимации.
     */
    public void pause() {
        // Если анимация уже на паузе, то выходим.
        if (this.state == State.PAUSE) return;
        // Если анимация будет удалена, то выходим.
        if (this.state == State.REMOVE) return;

        // Устанавливаем состояние.
        this.state = State.PAUSE;
        // Запускаем событие.
        this.parameters.fireEvent(AnimationEvent.Type.PAUSE, this);
    }

    /**
     * Останавливает анимацию, при это не заканчивает ее.
     */
    public void stop() {
        this.stop(false);
    }

    /**
     * Останавливает анимацию.
     *
     * @param gotoEnd флаг установли конечных значенй всем свойствам, т.е.
     *                заканчивает анимацию.
     */
    public void stop(boolean gotoEnd) {
        // Если анимация уже остановлена, то выходим.
        if (this.state == State.STOP) return;
        // Если анимация будет удалена, то выходим.
        if (this.state == State.REMOVE) return;

        // Устанавливаем состояние.
        this.state = State.STOP;
        // Запускаем событие.
        this.parameters.fireEvent(AnimationEvent.Type.STOP, this);
        // Если нужно закончить анимацию элемента.
        if (gotoEnd) this.parameters.end();
    }

    /**
     * Перезапускает анимацию.
     */
    public void restart() {
        // Сбрасываем параметры.
        this.parameters.begin();
        // Устанавливаем состояние.
        this.state = State.START;
        // Запускаем событие.
        this.parameters.fireEvent(AnimationEvent.Type.RESTART, this);
    }

    /**
     * Удаляет данную анимацию из аниматора.
     */
    public void remove() {
        // Устанавливаем состояние.
        this.state = State.REMOVE;
    }

    /**
     * Получает анимируемый объект.
     *
     * @return анимируемый объект.
     */
    public Object getTarget() {
        return this.parameters.target;
    }

    /**
     * Получает продолжительность анимации.
     *
     * @return продолжительность анимации в миллисекундах.
     */
    public float getDuration() {
        if (this.parameters.duration == DURATION_INFINITE) {
            return DURATION_INFINITE;
        } else {
            return this.parameters.getTimeInMillisecond(this.parameters.duration);
        }
    }

    /**
     * Получает полную продолжительность анимации.
     *
     * @return полную продолжительность анимации в миллисекундах.
     */
    public float getTotalDuration() {
        float totalDuration = this.parameters.getTotalDuration();
        if (totalDuration == DURATION_INFINITE) {
            return DURATION_INFINITE;
        } else {
            return this.parameters.getTimeInMillisecond(totalDuration);
        }
    }

    /**
     * Получает продолжительность задержки анимации.
     *
     * @return продолжительность задержки анимации в миллисекундах.
     */
    public float getDelay() {
        return this.parameters.getTimeInMillisecond(this.parameters.delay);
    }

    /**
     * Получает частоту обновления кадров анимации.
     *
     * @return частоту обновления кадров анимации.
     */
    public int getFps() {
        return this.parameters.fps;
    }

    /**
     * Получает масштабирование времени.
     *
     * @return масштабирование времени.
     */
    public float getTimeScale() {
        return this.parameters.timeScale;
    }

    /**
     * Получает эффект анимации.
     *
     * @return эффект анимации.
     */
    public Easing getEasing() {
        return this.parameters.easing;
    }

    /**
     * Получает обработчик событий анимации.
     *
     * @return обработчик событий анимации.
     */
    public AnimationListener getListener() {
        return this.parameters.listener;
    }

    /**
     * Получает количество повторов анимации.
     *
     * @return количество повторов анимации.
     */
    public int getRepeat() {
        return this.parameters.repeat;
    }

    /**
     * Получает законченное количество повторов анимации.
     *
     * @return законченное количество повторов анимации.
     */
    public int getCompletedRepeat() {
        return this.parameters.completedRepeat;
    }

    /**
     * Получает продолжительность задержки между повторами анимации.
     *
     * @return продолжительность задержки между повторами анимации в миллисекундах.
     */
    public float getRepearDelay() {
        return this.parameters.getTimeInMillisecond(this.parameters.repeatDelay);
    }

    /**
     * Получает режим времени анимации.
     *
     * @return режим времени.
     */
    public int getTimeMode() {
        return this.parameters.timeMode;
    }

    /**
     * Получает режим работы анимации.
     *
     * @return режим работы.
     */
    public int getPlayMode() {
        return this.parameters.playMode;
    }

    /**
     * Получает пройденое время анимации.
     *
     * @return пройденое время анимации в миллисекундах.
     */
    public float getElapsedTime() {
        return this.parameters.getTimeInMillisecond(this.parameters.elapsedTime);
    }

    /**
     * Получает полное пройденое время анимации.
     *
     * @return полное пройденое время анимации в миллисекундах.
     */
    public float getTotalElapsedTime() {
        float totalElapsedTime = this.parameters.getTotalElapsedTime();
        if (totalElapsedTime == DURATION_INFINITE) {
            return DURATION_INFINITE;
        } else {
            return this.parameters.getTimeInMillisecond(totalElapsedTime);
        }
    }

    /**
     * Получает прогресс анимации.
     *
     * @return прогресс анимации от 0 до 1.
     */
    public float getPosition() {
        return this.parameters.getPosition();
    }

    /**
     * Устанавливает прогресс анимации.
     *
     * @param position прогресс, от 0 до 1.
     */
    public void setPosition(float position) {
        this.parameters.setPosition(position);
    }

}
