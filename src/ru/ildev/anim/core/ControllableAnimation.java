/*
 *
 */
package ru.ildev.anim.core;

import ru.ildev.anim.easings.Easing;
import ru.ildev.anim.events.AnimationEvent;
import ru.ildev.anim.events.AnimationListener;
import ru.ildev.math.MoreMath;

/**
 * Класс контролируемой анимации, т.е. анимации, которую можно остановить,
 * запустить, поставить на паузу.
 *
 * @author Shafigin Ilyas (Шафигин Ильяс)
 * @version 0.4.8
 */
public class ControllableAnimation implements Animation, AnimationConstants {

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
     * Состояние анимации.
     */
    protected State state = State.STOP;
    /**
     * Ссылка на анимируемый объект.
     */
    protected Object target;
    /**
     * Продолжительность анимации.
     */
    protected float duration;
    /**
     * Продолжительность задержки анимациии. Задержка производится перед началом
     * анимации.
     */
    protected float delay;
    /**
     * Частота обновления кадров анимации.
     */
    protected int fps;
    /**
     * Масштабирование времени.
     */
    protected float timeScale;
    /**
     * Эффект анимации.
     */
    protected Easing easing;
    /**
     * Обработчик событий анимации.
     */
    protected AnimationListener listener;
    /**
     * Триггеры для событий.
     */
    protected int triggers;
    /**
     * Количество повторов анимации.
     */
    protected int repeat;
    /**
     * Задержки между циклами анимации.
     */
    protected float repeatDelay;
    /**
     * Режим времени анимации.
     */
    protected int timeMode;
    /**
     * Режим работы анимации.
     */
    protected int playMode;

    /**
     * Пройденное время после начала цикла анимации. В это время входит задержка перед анимацией.
     */
    protected float elapsedTime;
    /**
     * Текущее время анимации между задержками.
     */
    protected float currentTime;
    /**
     * Законченное количество повторов анимации.
     */
    protected int completedRepeat;
    /**
     * Определяет, начался ли цикл анимации.
     */
    protected boolean isBegin;

    /**
     * Создает объект контролируемой анимации.
     */
    public ControllableAnimation() {
        this.target = null;
        this.duration = 0.0f;
        this.delay = 0.0f;
        this.fps = 0;
        this.timeScale = 1.0f;
        this.easing = Easing.LINEAR;
        this.listener = null;
        this.triggers = AnimationEvent.COMPLETE;
        this.repeat = 1;
        this.repeatDelay = 0.0f;
        this.timeMode = MILLISECONDS;
        this.playMode = FORWARD;

        this.elapsedTime = 0.0f;
        this.currentTime = 0.0f;
        this.completedRepeat = 0;
        this.isBegin = false;
    }

    /**
     * Получает объект параметров анимации.
     *
     * @return объект параметров анимации.
     */
    public AnimationOptions getOptions() {
        return new AnimationOptions(this);
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
     * Получает анимируемый объект.
     *
     * @return анимируемый объект.
     */
    public Object getTarget() {
        return this.target;
    }

    /**
     * Получает продолжительность анимации.
     *
     * @return продолжительность анимации.
     */
    public float getDuration() {
        return this.duration;
    }

    /**
     * Получает продолжительность анимации.
     *
     * @return продолжительность анимации в миллисекундах.
     */
    public float getDurationInMilliseconds() {
        if (this.duration == DURATION_INFINITE) {
            return DURATION_INFINITE;
        } else {
            return this.getTimeInMillisecond(this.duration);
        }
    }

    /**
     * Получает продолжительность анимации включая ее повторы и задержку.
     *
     * @return полную продолжительность анимации.
     */
    public float getTotalDuration() {
        // Если установлено бесконечное количество повторов,
        if (this.repeat == REPEAT_INFINITE) {
            // то возвращаем бесконечную продолжительность.
            return DURATION_INFINITE;
        } else {
            // Расчитываем и возвращаем продолжительность.
            return this.delay + this.repeat * (this.duration + this.repeatDelay);
        }
    }

    /**
     * Получает полную продолжительность анимации.
     *
     * @return полную продолжительность анимации в миллисекундах.
     */
    public float getTotalDurationInMilliseconds() {
        float totalDuration = this.getTotalDuration();
        if (totalDuration == DURATION_INFINITE) {
            return DURATION_INFINITE;
        } else {
            return this.getTimeInMillisecond(totalDuration);
        }
    }

    /**
     * Получает продолжительность задержки анимации.
     *
     * @return продолжительность задержки анимации.
     */
    public float getDelay() {
        return this.delay;
    }

    /**
     * Получает продолжительность задержки анимации.
     *
     * @return продолжительность задержки анимации в миллисекундах.
     */
    public float getDelayInMilliseconds() {
        return this.getTimeInMillisecond(this.delay);
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
     * Получает масштабирование времени.
     *
     * @return масштабирование времени.
     */
    public float getTimeScale() {
        return this.timeScale;
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
     * Получает обработчик событий анимации.
     *
     * @return обработчик событий анимации.
     */
    public AnimationListener getListener() {
        return this.listener;
    }

    /**
     * Получает триггеры событий анимации.
     *
     * @return триггеры событий.
     */
    public int getTriggers() {
        return this.triggers;
    }

    /**
     * Получает количество повторов анимации.
     *
     * @return количество повторов анимации.
     */
    public int getRepeat() {
        return this.repeat;
    }

    /**
     * Получает продолжительность задержки между повторами анимации.
     *
     * @return продолжительность задержки между повторами анимации.
     */
    public float getRepeatDelay() {
        return this.repeatDelay;
    }

    /**
     * Получает продолжительность задержки между повторами анимации.
     *
     * @return продолжительность задержки между повторами анимации в миллисекундах.
     */
    public float getRepeatDelayInMilliseconds() {
        return this.getTimeInMillisecond(this.repeatDelay);
    }

    /**
     * Получает законченное количество повторов анимации.
     *
     * @return законченное количество повторов анимации.
     */
    public int getCompletedRepeat() {
        return this.completedRepeat;
    }

    /**
     * Получает режим времени анимации.
     *
     * @return режим времени.
     */
    public int getTimeMode() {
        return this.timeMode;
    }

    /**
     * Получает режим работы анимации.
     *
     * @return режим работы.
     */
    public int getPlayMode() {
        return this.playMode;
    }

    /**
     * Получает пройденое время анимации.
     *
     * @return пройденое время.
     */
    public float getElapsedTime() {
        return this.elapsedTime;
    }

    /**
     * Получает пройденое время анимации.
     *
     * @return пройденое время анимации в миллисекундах.
     */
    public float getElapsedTimeInMilliseconds() {
        return this.getTimeInMillisecond(this.elapsedTime);
    }

    /**
     * Устанавливает пройденное время анимации.
     *
     * @param elapsedTime пройденное время.
     */
    public void setElapsedTime(float elapsedTime) {
        if (elapsedTime < 0.0f) throw new IllegalArgumentException("elapsedTime < 0");

        this.elapsedTime = 0.0f;
        this.currentTime = 0.0f;

        //
        elapsedTime *= this.timeScale;
        // Если пройденное время некорректно.
        if (elapsedTime <= 0.0f) return;

        this.elapsedTime = elapsedTime;
    }

    /**
     * Получает полное пройденое время анимации.
     *
     * @return полное пройденое время.
     */
    public float getTotalElapsedTime() {
        // Если установлена бесконечная продолжительность.
        if (this.duration == DURATION_INFINITE) {
            return this.elapsedTime;
        } else {
            // Расчитываем и возвращаем пройденное время.
            return this.elapsedTime + (this.duration + this.repeatDelay) * this.completedRepeat;
        }
    }

    /**
     * Получает пройденое время анимации в миллисекундах, включая повторы.
     *
     * @return полное пройденое время анимации в миллисекундах.
     */
    public float getTotalElapsedTimeInMilliseconds() {
        return this.getTimeInMillisecond(this.getTotalElapsedTime());
    }

    /**
     * Устанавливает полное пройденное время анимации.
     *
     * @param totalElapsedTime пройденное время.
     */
    public void setTotalElapsedTime(float totalElapsedTime) {
        if (totalElapsedTime < 0.0f) throw new IllegalArgumentException("totalElapsedTime < 0");

        //
        totalElapsedTime *= this.timeScale;
        // Если пройденное время некорректно.
        if (totalElapsedTime <= 0.0f) return;

        // Если установлена бесконечная продолжительность.
        if (this.duration == DURATION_INFINITE) {
            this.completedRepeat = 0;
            this.elapsedTime = totalElapsedTime;
        } else {
            // dt = elapsedTime + (duration + repeatDelay) * completedRepeat
            //
            float t = totalElapsedTime - (this.duration + this.delay + this.repeatDelay);
            if (t <= 0.0f) {
                this.completedRepeat = 0;
                this.elapsedTime = totalElapsedTime;
            } else {
                float d = this.duration + this.repeatDelay;
                this.completedRepeat = (int) (t / d) + 1;
                this.elapsedTime = t % d;
            }
        }
        this.currentTime = 0.0f;
    }

    /**
     * Получает прогресс анимации.
     *
     * @return прогресс анимации.
     */
    public float getPosition() {
        // Если продолжительность бесконечна, то возвращаем 0.
        if (this.duration == DURATION_INFINITE) return 0.0f;
        // Если продолжительность некорректна, то возвращаем 1.
        if (this.duration <= 0.0f) return 1.0f;

        if (this.completedRepeat == 0) {
            // Если пройденное время меньше задержки, то возвращаем 0.
            if (this.elapsedTime <= this.delay + this.repeatDelay) return 0.0f;
            // Если пройденное время больше суммы продолжительности и задержки, то возвращаем 1.
            if (this.elapsedTime >= this.duration + this.delay + this.repeatDelay) return 1.0f;
            // Расчитываем и возвращаем прогресс анимации.
            return (this.elapsedTime - this.delay - this.repeatDelay) / this.duration;
        } else {
            // Если пройденное время меньше задержки, то возвращаем 0.
            if (this.elapsedTime <= this.repeatDelay) return 0.0f;
            // Если пройденное время больше суммы продолжительности и задержки,
            // то возвращаем 1.
            if (this.elapsedTime >= this.duration + this.repeatDelay) return 1.0f;
            // Расчитываем и возвращаем прогресс анимации.
            return (this.elapsedTime - this.repeatDelay) / this.duration;
        }
    }

    /**
     * Получает интерполируемый прогресс анимации.
     *
     * @param easing интерполяция.
     * @return прогресс анимации.
     */
    public float getPosition(Easing easing) {
        float position = this.getPosition();

        if (this.playMode == FORWARD) {
            // position = position;
        } else if (this.playMode == BACKWARD) {
            position = 1.0f - position;
        } else if (this.playMode == YOYO) {
            if (position <= 0.5f) {
                position = position * 2.0f;
            } else {
                position = (1.0f - position) * 2.0f;
            }
        }

        if (this.completedRepeat == 0) {
            return easing.ease(position, this.elapsedTime - this.delay - this.repeatDelay, 0.0f, 1.0f, this.duration);
        } else {
            return easing.ease(position, this.elapsedTime - this.repeatDelay, 0.0f, 1.0f, this.duration);
        }
    }

    /**
     * Устанавливает прогресс анимации.
     *
     * @param position прогресс анимации, от 0 до 1.
     */
    public void setPosition(float position) {
        this.elapsedTime = 0.0f;
        this.currentTime = 0.0f;

        // Если установлена бесконечная продолжительность.
        if (this.duration == DURATION_INFINITE) return;
        this.elapsedTime += MoreMath.clamp(position, 0.0f, 1.0f) * this.duration;
        if (this.completedRepeat == 0) {
            this.elapsedTime += this.delay + this.repeatDelay;
        } else {
            this.elapsedTime += this.repeatDelay;
        }
    }

    /**
     * Определяет, входит ли время {@code time} во время анимирования.
     *
     * @param time время.
     * @return {@code true}, если {@code time} входит во время анимирования.
     */
    protected boolean isInsidePlayingTime(float time) {
        return time >= 0.0f && this.duration != DURATION_INFINITE && time < this.getTotalDuration();
    }

    /**
     * Определяет, можно ли повторить анимацию.
     *
     * @return {@code true}, если можно повторить анимацию.
     */
    protected boolean canRepeat() {
        // Если количество повторений бесконечно, то возвращаем истину.
        if (this.repeat == REPEAT_INFINITE) return true;
        //
        return this.completedRepeat < this.repeat;
    }

    /**
     * Определяет, можно ли удалить анимацию из аниматора.
     * @return {@code true}, если можно удалить анимацию.
     */
    protected boolean canRemove() {
        return this.state == State.REMOVE || this.state == State.STOP;
    }

    /**
     * Определяет, начался ли цикл анимации.
     *
     * @return {@code true}, если начался цикл анимации.
     */
    protected boolean isBegin() {
        if (this.completedRepeat == 0) {
            return this.elapsedTime >= this.delay + this.repeatDelay;
        } else {
            return this.elapsedTime >= this.repeatDelay;
        }
    }

    /**
     * Определяет, закончился ли цикл анимации.
     *
     * @return {@code true}, если цикл анимации закончился.
     */
    protected boolean isEnd() {
        // Если продолжительность анимации бесконечна, то возвращаем ложь.
        if (this.duration == DURATION_INFINITE) return false;
        // Если продолжительность некорректна, то возвращаем истину.
        if (this.duration <= 0.0f) return true;
        // Если пройденное время меньше нуля, то возвращаем истину.
        // if(this.elapsedTime < 0.0f) return true;

        // Определяем, закончилась ли анимация.
        if (this.completedRepeat == 0) {
            return this.elapsedTime >= this.duration + this.delay + this.repeatDelay;
        } else {
            return this.elapsedTime >= this.duration + this.repeatDelay;
        }
    }

    /**
     * Копирует значения полей из объекта опций.
     *
     * @param options опции.
     */
    public ControllableAnimation copy(AnimationOptions options) {
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

        this.target = null;
        this.elapsedTime = 0.0f;
        this.currentTime = 0.0f;
        this.completedRepeat = 0;

        return this;
    }

    /**
     * Копирует значения полей из объекта опций и устанавливает ссылку на
     * анимируемый объект.
     *
     * @param target  анимируемый объект.
     * @param options опции.
     */
    public ControllableAnimation copy(Object target, AnimationOptions options) {
        // if(target == null) throw new NullPointerException("target == null");
        if (options == null) throw new NullPointerException("options == null");

        this.target = target;
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

        this.elapsedTime = 0.0f;
        this.currentTime = 0.0f;
        this.completedRepeat = 0;

        return this;
    }

    /**
     * Запускает анимацию.
     */
    public void start() {
        // Если анимация уже запущена, то выходим.
        if (this.state == State.START) return;
        // Если анимация будет удалена, то выходим.
        if (this.state == State.REMOVE) return;

        // Сбрасываем текущее состояние.
        this.reset();
        // Устанавливаем состояние.
        this.state = State.START;
    }

    @Override
    public boolean step(float elapsedTime) {
        // Если анимацию нужно удалить.
        if (this.canRemove()) return true;
        // Если на паузе.
        if (this.state == State.PAUSE) return false;

        // Если пройденное время некорректно, то выходим.
        //if(elapsedTime <= 0.0f) return false;
        // Если плагинов нет.
        //if(this.plugins.isEmpty()) return true;
        // Обновляем время в опциях.
        if (!this.updateTime(this.getTimeFromTimeMode(elapsedTime))) return false;

        // Если анимация началась, то вызываем события
        if (!this.isBegin && this.isBegin()) {
            this.isBegin = true;
            if (this.completedRepeat == 0) {
                this.fireEvent(AnimationEvent.BEGIN);
            }
            this.fireEvent(AnimationEvent.START);
        }

        // Обновляем
        boolean isEnd = !this.update(elapsedTime);

        // Запускаем событие
        this.fireEvent(AnimationEvent.STEP);

        // Если анимация закончилась.
        if (isEnd || this.isEnd()) {
            // Запускаем событие.
            this.fireEvent(AnimationEvent.END);

            this.repeat();

            // Если нет возможности продолжать анимацию.
            if (!this.canRepeat()) {
                // Запускаем событие
                this.fireEvent(AnimationEvent.COMPLETE);
                // Устанавливаем состояние.
                this.state = State.STOP;
                return true;
            }
        }
        //
        return false;
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
        this.fireEvent(AnimationEvent.PAUSE);
    }

    /**
     * Прододжает анимациюпосле приостановки.
     */
    public void resume() {
        // Если анимация не была приостановлена, то выходим.
        if (this.state != State.PAUSE) return;

        // Устанавливаем состояние.
        this.state = State.START;
        // Запускаем событие.
        this.fireEvent(AnimationEvent.RESUME);
    }

    /**
     * Перезапускает анимацию.
     */
    public void restart() {
        // Запускаем событие.
        this.fireEvent(AnimationEvent.RESTART);
        // Сбрасываем время.
        this.reset();
        // Устанавливаем состояние.
        this.state = State.START;
    }

    /**
     * Цикл повторений анимации.
     */
    protected void repeat() {
        this.isBegin = false;
        // Увеличиваем количество повторов.
        this.completedRepeat++;
        // Сбрасываем пройденное время.
        if (this.completedRepeat != 1) {
            this.elapsedTime -= this.duration + this.repeatDelay;
        } else {
            this.elapsedTime -= this.duration + this.delay + this.repeatDelay;
        }
    }

    /**
     * Заканчивает анимацию.
     */
    public void end() {
        this.elapsedTime = 0.0f;
        // Если установлена бесконечная продолжительность.
        if (this.duration == DURATION_INFINITE) return;
        // Изменяем пройденной время
        if (this.completedRepeat == 0) {
            this.elapsedTime = this.duration + this.delay + this.repeatDelay;
        } else {
            this.elapsedTime = this.duration + this.repeatDelay;
        }
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
     * @return {@code true}, если анимация была остановлена.
     */
    public boolean stop(boolean gotoEnd) {
        // Если анимация уже остановлена, то выходим.
        if (this.state == State.STOP) return false;
        // Если анимация будет удалена, то выходим.
        if (this.state == State.REMOVE) return false;

        // Устанавливаем состояние.
        this.state = State.STOP;
        // Запускаем событие.
        this.fireEvent(AnimationEvent.STOP);
        // Если нужно закончить анимацию элемента.
        if (gotoEnd) {
            this.end();
        }

        return true;
    }

    /**
     * Удаляет данную анимацию из аниматора.
     */
    public void remove() {
        // Устанавливаем состояние.
        this.state = State.REMOVE;
    }

    /**
     * Начинает анимацию.
     */
    protected void reset() {
        // Сбрасываем пройденное время.
        this.elapsedTime = 0.0f;
        this.currentTime = 0.0f;
        this.completedRepeat = 0;
        this.isBegin = false;
    }

    /**
     * Абстрактный метод обновления.
     * @param elapsedTime пройденное время в миллисекундах
     * @return {@code false}, если необходимо остановить анимацию.
     */
    protected boolean update(float elapsedTime) {
        return true;
    }

    /**
     * Обновляет время.
     *
     * @param elapsedTime пройденное время.
     * @return {@code true}, если анимация продолжается.
     */
    protected boolean updateTime(float elapsedTime) {
        //
        elapsedTime *= this.timeScale;
        // Если пройденное время некорректно.
        // if(time <= 0.0f) return false;

        // Если установлен режим в кадрах.
        if (this.timeMode == FRAMES) {
            // Прибавляем к суммарному времени, т.е. суммируем кадры.
            this.currentTime += elapsedTime;
            // Если сумма кадров больше одного кадра.
            if (this.currentTime >= 1.0f) {
                // Убавляем суммарное время.
                this.currentTime -= 1.0f;
                // Добавляем время задержки.
                this.elapsedTime += 1.0f;
            } else {
                return false;
            }
        } else {
            // Если частота обновления корректна.
            if (this.fps > 0) {
                // Прибавляем к суммарному времени.
                this.currentTime += elapsedTime;
                // Находим задержку.
                float delay = this.getTimeFromTimeMode(1000.0f) / this.fps;
                // Если суммарное время больше задержки.
                if (this.currentTime >= delay) {
                    // Убавляем суммарное время.
                    this.currentTime -= delay;
                    // Добавляем время задержки.
                    this.elapsedTime += delay;
                } else {
                    return false;
                }
            } else {
                // Добавляем пройденное время
                this.elapsedTime += elapsedTime;
            }
        }

        // Запускаем событие кадра анимации.
        this.fireEvent(AnimationEvent.STEP);

        return true;
    }

    /**
     * Запускает событие анимации.
     *
     * @param type      тип события.
     */
    protected void fireEvent(int type) {
        if (this.listener != null && (this.triggers & type) > 0) {
            this.listener.onEvent(new AnimationEvent(this, type));
        }
    }

    /**
     * @param time время в миллисекундах.
     * @return время в заданном режиме.
     */
    protected float getTimeFromTimeMode(float time) {
        switch (this.timeMode) {
            case MILLISECONDS:
                return time;
            case SECONDS:
                return time * 0.001f;
            case FRAMES:
                return time * 0.001f / this.fps;
            default:
                return 0.0f;
        }
    }

    /**
     * @param time
     * @return время в миллисекундах.
     */
    protected float getTimeInMillisecond(float time) {
        switch (this.timeMode) {
            case MILLISECONDS:
                return time;
            case SECONDS:
                return time * 1000.0f;
            case FRAMES:
                return this.fps * 1000.0f / time;
            default:
                return 0.0f;
        }
    }

    /**
     * @param time
     * @param animation
     * @return в заданном режиме.
     */
    protected float getTime(float time, ControllableAnimation animation) {
        return this.getTimeFromTimeMode(animation.getTimeInMillisecond(time));
    }

}
