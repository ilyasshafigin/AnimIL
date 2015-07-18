/*
 *
 */
package ru.ildev.anim.core;

import ru.ildev.anim.easings.Easing;
import ru.ildev.anim.events.AnimationAdapter;
import ru.ildev.anim.events.AnimationEvent;
import ru.ildev.anim.events.AnimationListener;
import ru.ildev.math.MoreMath;

/**
 * Объект параметров анимации. Содержит все необходимые параметры для контроля
 * анимации.
 *
 * @author Shafigin Ilyas (Шафигин Ильяс)
 * @version 0.9.20
 */
public class AnimationParameters implements AnimationConstants {

    /**
     * Ссылка на анимируемый объект.
     */
    protected Object target = null;

    /**
     * Продолжительность анимации.
     */
    protected float duration = 0.0f;
    /**
     * Продолжительность задержки анимациии. Задержка производится перед началом
     * анимации.
     */
    protected float delay = 0.0f;
    /**
     * Частота обновления кадров анимации.
     */
    protected int fps = 0;
    /**
     * Масштабирование времени.
     */
    protected float timeScale = 1.0f;
    /**
     * Эффект анимации.
     */
    protected Easing easing = Easing.LINEAR;
    /**
     * Обработчик событий анимации.
     */
    protected AnimationListener listener = new AnimationAdapter();
    /**
     * Количество повторов анимации.
     */
    protected int repeat = 1;
    /**
     * Задержки между повторами анимации.
     */
    protected float repeatDelay = 0.0f;
    /**
     * Режим времени анимации.
     */
    protected int timeMode = MILLISECONDS;
    /**
     * Режим работы анимации.
     */
    protected int playMode = FORWARD;

    /**
     * Пройденное время после начала анимации.
     */
    protected float elapsedTime = 0.0f;
    /**
     * Текущее время анимации между задержками.
     */
    protected float currentTime = 0.0f;
    /**
     * Законченное количество повторов анимации.
     */
    protected int completedRepeat = 0;

    /**
     * Стандартный конструктор.
     */
    public AnimationParameters() {
    }

    /**
     * Конструктор, копирующий значения полей из опций.
     *
     * @param options опции.
     */
    public AnimationParameters(AnimationOptions options) {
        if (options == null) throw new NullPointerException("options == null");

        this.duration = options.duration;
        this.delay = options.delay;
        this.fps = options.fps;
        this.timeScale = options.timeScale;
        this.easing = options.easing;
        this.listener = options.listener;
        this.repeat = options.repeat;
        this.repeatDelay = options.repeatDelay;
        this.timeMode = options.timeMode;
        this.playMode = options.playMode;
    }

    /**
     * Конструктор, копирующий значения полей из опций.
     *
     * @param target  анимируемый объект.
     * @param options опции.
     */
    public AnimationParameters(Object target, AnimationOptions options) {
        // if(target == null) throw new NullPointerException("target == null");
        if (options == null) throw new NullPointerException("options == null");

        this.target = target;
        this.duration = options.duration;
        this.delay = options.delay;
        this.fps = options.fps;
        this.timeScale = options.timeScale;
        this.easing = options.easing;
        this.listener = options.listener;
        this.repeat = options.repeat;
        this.repeatDelay = options.repeatDelay;
        this.timeMode = options.timeMode;
        this.playMode = options.playMode;
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
            return this.delay + this.repeat * (this.duration + this.repeatDelay) - this.repeatDelay;
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
     * Устанавливает пройденное время анимации.
     *
     * @param elapsedTime пройденное время.
     */
    public void setElapsedTime(float elapsedTime) {
        if (elapsedTime < 0.0f) throw new IllegalArgumentException("elapsedTime < 0");

        //
        elapsedTime *= this.timeScale;
        // Если пройденное время некорректно.
        if (elapsedTime <= 0.0f) return;

        this.elapsedTime = elapsedTime;
        this.currentTime = 0.0f;
    }

    /**
     * Получает пройденое время анимации в миллисекундах, включая повторы.
     *
     * @return полное пройденое время.
     */
    public float getTotalElapsedTime() {
        // Если установлена бесконечная продолжительность.
        if (this.duration == DURATION_INFINITE) {
            return this.elapsedTime;
        } else {
            // Расчитываем и возвращаем пройденное время.
            if (this.completedRepeat == 0) {
                return this.elapsedTime;
            } else {
                return this.delay + this.elapsedTime + (this.duration + this.repeatDelay) *
                        this.completedRepeat - this.repeatDelay;
            }
        }
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
            // dt = delay + elapsedTime + (duration + repeatDelay) * completedRepeat - repeatDelay
            //
            float t = totalElapsedTime - (this.duration + this.delay);
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
            if (this.elapsedTime <= this.delay) return 0.0f;
            // Если пройденное время больше суммы продолжительности и задержки,
            // то возвращаем 1.
            if (this.elapsedTime >= this.duration + this.delay) return 1.0f;
            // Расчитываем и возвращаем прогресс анимации.
            return (this.elapsedTime - this.delay) / this.duration;
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
            return easing.ease(position, this.elapsedTime - this.delay, 0.0f, 1.0f, this.duration);
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
            this.elapsedTime += this.delay;
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
    public boolean isInsidePlayingTime(float time) {
        return time >= 0.0f && this.duration != DURATION_INFINITE && time < this.getTotalDuration();
    }

    /**
     * Копирует значения полей из объекта опций.
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
        this.repeat = options.repeat;
        this.repeatDelay = options.repeatDelay;
        this.timeMode = options.timeMode;
        this.playMode = options.playMode;
    }

    /**
     * Копирует значения полей из объекта опций и устанавливает ссылку на
     * анимируемый объект.
     *
     * @param target  анимируемый объект.
     * @param options опции.
     */
    public void copy(Object target, AnimationOptions options) {
        // if(target == null) throw new NullPointerException("target == null");
        if (options == null) throw new NullPointerException("options == null");

        this.target = target;
        this.duration = options.duration;
        this.delay = options.delay;
        this.fps = options.fps;
        this.timeScale = options.timeScale;
        this.easing = options.easing;
        this.listener = options.listener;
        this.repeat = options.repeat;
        this.repeatDelay = options.repeatDelay;
        this.timeMode = options.timeMode;
        this.playMode = options.playMode;
    }

    /**
     * Метод повтора анимации.
     */
    public void repeat() {
        this.completedRepeat++;
        // Сбрасываем пройденное время.
        // this.elapsedTime = 0.0f;
        this.elapsedTime -= this.duration;
        if (this.completedRepeat != 1) {
            this.elapsedTime -= this.repeatDelay;
        } else {
            this.elapsedTime -= this.delay;
        }
    }

    /**
     * Определяет, можно ли повторить анимацию.
     *
     * @return {@code true}, если можно повторить анимацию.
     */
    public boolean canRepeat() {
        // Если количество повторений бесконечно, то возвращаем истину.
        if (this.repeat == REPEAT_INFINITE) return true;
        //
        return this.completedRepeat < this.repeat;
    }

    /**
     * Начинает анимацию.
     */
    public void begin() {
        // Сбрасываем пройденное время.
        this.elapsedTime = 0.0f;
        this.currentTime = 0.0f;
        this.completedRepeat = 0;
    }

    /**
     * Заканчивает анимацию.
     */
    public void end() {
        this.elapsedTime = 0.0f;
        // Если установлена бесконечная продолжительность.
        if (this.duration == DURATION_INFINITE) return;

        this.elapsedTime += this.duration;
        if (this.completedRepeat == 0) {
            this.elapsedTime += this.delay;
        } else {
            this.elapsedTime += this.repeatDelay;
        }
    }

    /**
     * Определяет, закончилась ли анимация.
     *
     * @return {@code true}, если анимация закончилась.
     */
    public boolean isEnded() {
        // Если продолжительность анимации бесконечна, то возвращаем ложь.
        if (this.duration == DURATION_INFINITE) return false;
        // Если продолжительность некорректна, то возвращаем истину.
        if (this.duration <= 0.0f) return true;
        // Если пройденное время меньше нуля, то возвращаем истину.
        // if(this.elapsedTime < 0.0f) return true;

        // Определяем, закончилась ли анимация.
        if (this.completedRepeat == 0) {
            return this.elapsedTime >= this.duration + this.delay;
        } else {
            return this.elapsedTime >= this.duration + this.repeatDelay;
        }
    }

    /**
     * Обновляет время.
     *
     * @param elapsedTime пройденное время.
     * @return {@code true}, если анимация продолжается.
     */
    public boolean update(float elapsedTime) {
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

        return true;
    }

    /**
     * Запускает событие анимации.
     *
     * @param type      тип события.
     * @param animation объект анимации.
     */
    public void fireEvent(AnimationEvent.Type type, ControllableAnimation animation) {
        switch (type) {
            default:break;
            case START: this.listener.onStart(new AnimationEvent(animation, this, AnimationEvent.Type.START));
            case STOP: this.listener.onStop(new AnimationEvent(animation, this, AnimationEvent.Type.STOP));
            case RESTART: this.listener.onRestart(new AnimationEvent(animation, this, AnimationEvent.Type.RESTART));
            case PAUSE: this.listener.onPause(new AnimationEvent(animation, this, AnimationEvent.Type.PAUSE));
            case REPEAT: this.listener.onRepeat(new AnimationEvent(animation, this, AnimationEvent.Type.REPEAT));
            case STEP: this.listener.onStep(new AnimationEvent(animation, this, AnimationEvent.Type.STEP));
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
                return time / this.fps;
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
                return this.fps / time;
            default:
                return 0.0f;
        }
    }

    /**
     * @param time
     * @param parameters параметры.
     * @return в заданном режиме.
     */
    protected float getTime(float time, AnimationParameters parameters) {
        return this.getTimeFromTimeMode(parameters.getTimeInMillisecond(time));
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("AnimationParameters [");
        builder.append("target=").append(this.target);
        builder.append(", duration=").append(this.duration);
        builder.append(", delay=").append(this.delay);
        builder.append(", fps=").append(this.fps);
        builder.append(", timeScale=").append(this.timeScale);
        builder.append(", easing=").append(this.easing);
        builder.append(", listener=").append(this.listener);
        builder.append(", repeat=").append(this.repeat);
        builder.append(", repeatDelay=").append(this.repeatDelay);
        builder.append(", timeMode=").append(this.timeMode);
        builder.append(", playMode=").append(this.playMode);
        builder.append("]");
        return builder.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + (this.target == null ? 0 : this.target.hashCode());
        result = prime * result + Float.floatToIntBits(this.delay);
        result = prime * result + Float.floatToIntBits(this.duration);
        result = prime * result
                + (this.easing == null ? 0 : this.easing.hashCode());
        result = prime * result + this.fps;
        result = prime * result
                + (this.listener == null ? 0 : this.listener.hashCode());
        result = prime * result + this.playMode;
        result = prime * result + this.repeat;
        result = prime * result + Float.floatToIntBits(this.repeatDelay);
        result = prime * result + this.timeMode;
        result = prime * result + Float.floatToIntBits(this.timeScale);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof AnimationParameters)) return false;

        AnimationParameters other = (AnimationParameters) obj;
        if (this.target == null) {
            if (other.target != null) return false;
        } else if (!this.target.equals(other.target)) {
            return false;
        }
        if (this.duration != other.duration) return false;
        if (this.delay != other.delay) return false;
        if (this.easing == null) {
            if (other.easing != null) return false;
        } else if (!this.easing.equals(other.easing)) {
            return false;
        }
        if (this.fps != other.fps) return false;
        if (this.listener == null) {
            if (other.listener != null) return false;
        } else if (!this.listener.equals(other.listener)) {
            return false;
        }
        if (this.playMode != other.playMode) return false;
        if (this.repeat != other.repeat) return false;
        if (this.repeatDelay != other.repeatDelay) return false;
        if (this.timeMode != other.timeMode) return false;
        if (this.timeScale != other.timeScale) return false;

        return true;
    }

}
