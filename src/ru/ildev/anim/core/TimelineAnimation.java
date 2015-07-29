/*
 *
 */
package ru.ildev.anim.core;

import ru.ildev.anim.easings.Easing;
import ru.ildev.anim.events.AnimationListener;
import ru.ildev.math.MoreMath;
import ru.ildev.utils.Builder;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс анимации в виде временной шкалы. Элементами этой шкалы являются другие
 * объекты анимации.
 *
 * @author Shafigin Ilyas (Шафигин Ильяс)
 * @version 0.1.3
 */
public class TimelineAnimation extends ControllableAnimation {

    /**
     * Класс элемента временной шкалы.
     */
    static class Time {

        /**
         * Имя.
         */
        String name;
        /**
         * Время начала анимации
         */
        float startTime;
        /**
         * Объект анимации.
         */
        ControllableAnimation animation;

        /**
         * Конструктор.
         *
         * @param name      имя.
         * @param startTime время начала.
         * @param animation объект анимации.
         */
        Time(String name, float startTime, ControllableAnimation animation) {
            this.name = name;
            this.startTime = startTime;
            this.animation = animation;
        }

    }

    /**
     * Список элементов временной шкалы.
     */
    protected List<Time> timeline = null;

    /**
     * Внутренний конструктор.
     *
     * @param timeline список элементов.
     * @param options  опции анимации.
     */
    protected TimelineAnimation(List<Time> timeline, AnimationOptions options) {
        super();

        if (timeline == null) throw new NullPointerException("timeline == null");
        if (options == null) throw new NullPointerException("options == null");

        this.timeline = timeline;
        this.copy(options);
    }

    /**
     * Конструктор.
     */
    public TimelineAnimation() {
        super();

        this.timeline = new ArrayList<>();
    }

    /**
     * Конструктор с параметрами.
     *
     * @param options мапаметры анимации.
     */
    public TimelineAnimation(AnimationOptions options) {
        super();

        this.timeline = new ArrayList<>();
        this.copy(options);
    }

    /**
     * Добавляет на временную шкалу анимацию.
     * @param startTime время начала анимации.
     * @param animation анимация.
     */
    public void append(float startTime, ControllableAnimation animation) {
        if (animation == null) throw new NullPointerException("animation == null");
        //
        String name = String.valueOf(this.timeline.size());
        //
        this.timeline.add(new Time(name, startTime, animation));
    }

    /**
     * Добавляет на временную шкалу анимацию.
     * @param name название анимации.
     * @param startTime время начала анимации.
     * @param animation анимация.
     */
    public void append(String name, float startTime, ControllableAnimation animation) {
        if (name == null) throw new NullPointerException("name == null");
        if (animation == null) throw new NullPointerException("animation == null");

        //
        this.timeline.add(new Time(name, startTime, animation));
    }

    /**
     * Устанавливает текущее время во временной шкале.
     *
     * @param currentTime текущее время.
     */
    public void setTime(float currentTime) {
        if (currentTime < 0.0f) throw new IllegalArgumentException("currentTime < 0");

        this.setTotalElapsedTime(currentTime);
        for (Time time : this.timeline) {
            time.animation.setTotalElapsedTime(
                    time.animation.getTime(MoreMath.max(0.0f, currentTime - time.startTime), this));
        }
    }

    /**
     * Запускает анимацию, начиная ее с заданного момента.
     *
     * @param startTime время начала анимации.
     */
    public void start(float startTime) {
        if (startTime < 0.0f) throw new IllegalArgumentException("startTime < 0");
        // Если анимацию нужно удалить, то выходим.
        if (this.state == State.REMOVE) return;
        // Устанавливаем время.
        this.setTime(startTime);
        // Запускаем анимацию.
        this.start();
    }

    @Override
    protected void repeat() {
        super.repeat();

        for (Time time : this.timeline) {
            time.animation.reset();
        }
    }

    @Override
    public void update() {
        for (Time time : this.timeline) {
            float t = time.animation.getTime(this.elapsedTime - time.startTime, this);

            if (time.animation.isInsidePlayingTime(t)) {
                if (time.animation.isStop()) {
                    time.animation.start();
                }

                time.animation.setTotalElapsedTime(t);
                time.animation.step(0.0f);
            }
        }
    }

    @Override
    public boolean stop(boolean gotoEnd) {
        if (super.stop(gotoEnd)) {
            for (Time time : this.timeline) {
                time.animation.stop(gotoEnd);
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void setPosition(float position) {
        super.setPosition(position);

        for (Time time : this.timeline) {
            float t = time.animation.getTime(this.elapsedTime - time.startTime, this);

            if (time.animation.isInsidePlayingTime(t)) {
                if (time.animation.isStop()) {
                    time.animation.start();
                }

                time.animation.setTotalElapsedTime(t);
            }
        }
    }

    /**
     * Расчитывает полную продолжительность анимации временной шкалы.
     *
     * @return полную продолжительность анимации.
     */
    public float getTimelineDuration() {
        float duration = 0.0f;
        for (Time time : this.timeline) {
            ControllableAnimation tanimation = time.animation;
            float tduration = tanimation.getTotalDurationInMilliseconds();

            if (tduration == AnimationConstants.DURATION_INFINITE) {
                return AnimationConstants.DURATION_INFINITE;
            }

            duration += tduration;
        }
        return duration;
    }

    /**
     * Пересчитывает продолжительность всей анимации.
     */
    public void recalculateTimelineDuration() {
        this.duration = this.getTimelineDuration();
    }

    /**
     * Создает и возвращает новый объект построителя анимации.
     *
     * @return построитель анимации.
     */
    public static TimelineAnimationBuilder builder() {
        return new TimelineAnimationBuilder();
    }

    /**
     * Класс построителя анимации.
     *
     * @author Ilyas74
     * @version 0.0.0
     */
    public static class TimelineAnimationBuilder implements Builder<TimelineAnimation> {

        /**
         * Очередь.
         */
        protected List<Time> timeline = null;
        /**
         * Опции анимации.
         */
        protected AnimationOptions options = null;
        /**  */
        protected boolean oneRun = false;

        /**
         * Конструктор.
         */
        protected TimelineAnimationBuilder() {
            this.timeline = new ArrayList<>();
            this.options = new AnimationOptions();
        }

        /**
         * Добавляет на временную шкалу анимацию.
         *
         * @param startTime вреня запуска анимации.
         * @param animation анимация.
         * @return данный построитель.
         */
        public TimelineAnimationBuilder time(float startTime, ControllableAnimation animation) {
            if (animation == null) return this;
            this.timeline.add(new Time(String.valueOf(this.timeline.size()), startTime, animation));
            return this;
        }

        /**
         * Добавляет на временную шкалу анимацию.
         *
         * @param name      имя анимации на временной шкале.
         * @param startTime вреня запуска анимации.
         * @param animation анимация.
         * @return данный построитель.
         */
        public TimelineAnimationBuilder time(String name, float startTime, ControllableAnimation animation) {
            if (animation == null) return this;
            this.timeline.add(new Time(name, startTime, animation));
            return this;
        }

        /**
         * Устанавливает продолжительность анимации.
         *
         * @param duration продолжительность анимации.
         * @return данный построитель.
         */
        public TimelineAnimationBuilder duration(float duration) {
            this.options.setDuration(duration);
            return this;
        }

        /**
         * Устанавливает продолжительность задержки анимации.
         *
         * @param delay продолжительность задержки анимации.
         * @return данный построитель.
         */
        public TimelineAnimationBuilder delay(float delay) {
            this.options.setDelay(delay);
            return this;
        }

        /**
         * Устанавливает частоту обновления кадров анимации.
         *
         * @param fps частота обновления кадров анимации.
         * @return данный построитель.
         */
        public TimelineAnimationBuilder fps(int fps) {
            this.options.setFps(fps);
            return this;
        }

        /**
         * Устанавливает масштабирование времени анимации.
         *
         * @param timescale масштабирование времени.
         * @return данный построитель.
         */
        public TimelineAnimationBuilder timescale(float timescale) {
            this.options.setTimeScale(timescale);
            return this;
        }

        /**
         * Устанавливает эффект аниамции.
         *
         * @param easing эффект аниамции.
         * @return данный построитель.
         */
        public TimelineAnimationBuilder easing(Easing easing) {
            this.options.setEasing(easing);
            return this;
        }

        /**
         * Устанавливает обработчик событий анимации.
         *
         * @param listener обработчик событий анимации.
         * @return данный построитель.
         */
        public TimelineAnimationBuilder listener(AnimationListener listener) {
            this.options.setListener(listener);
            return this;
        }

        /**
         * Устанавливает количество повторов анимации.
         *
         * @param repeat количество повторов анимации.
         * @return данный построитель.
         */
        public TimelineAnimationBuilder repeat(int repeat) {
            this.options.setRepeat(repeat);
            return this;
        }

        /**
         * Устанавливает продолжительность задержки между повторами анимации.
         *
         * @param repeatDelay продолжительность задержки между повторами анимации.
         * @return данный построитель.
         */
        public TimelineAnimationBuilder repeatDelay(float repeatDelay) {
            this.options.setRepeatDelay(repeatDelay);
            return this;
        }

        /**
         * Устанавливает режим времени анимации.
         *
         * @param timemode режим времени.
         * @return данный построитель.
         * @see AnimationConstants#MILLISECONDS
         * @see AnimationConstants#SECONDS
         * @see AnimationConstants#FRAMES
         */
        public TimelineAnimationBuilder timemode(int timemode) {
            this.options.setTimeMode(timemode);
            return this;
        }

        /**
         * Устанавливает режим работы анимации.
         *
         * @param playmode режим работы.
         * @return данный построитель.
         * @see AnimationConstants#FORWARD
         * @see AnimationConstants#BACKWARD
         */
        public TimelineAnimationBuilder playmode(int playmode) {
            this.options.setPlayMode(playmode);
            return this;
        }

        /**
         * Устанавливает опции анимации.
         *
         * @param options опции.
         * @return данный построитель.
         */
        public TimelineAnimationBuilder options(AnimationOptions options) {
            if (options == null) return this;
            this.options.copy(options);
            return this;
        }

        /**
         * Сбрасывает данный построитель.
         *
         * @return данный построитель.
         */
        public TimelineAnimationBuilder reset() {
            this.timeline = new ArrayList<>();
            this.options = new AnimationOptions();
            return this;
        }

        @Override
        public TimelineAnimation build() {
            TimelineAnimation animation = new TimelineAnimation(this.timeline, this.options);
            this.reset();
            return animation;
        }

    }

}
