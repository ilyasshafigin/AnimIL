/*
 *
 */
package ru.ildev.anim.core;

import ru.ildev.anim.easings.Easing;
import ru.ildev.anim.events.AnimationListener;
import ru.ildev.anim.plugins.AnimationPlugin;
import ru.ildev.anim.plugins.PluginList;
import ru.ildev.utils.Builder;

import java.util.Collection;

/**
 * Класс стандартной анимации. Содержит один список плагинов.
 *
 * @author Shafigin Ilyas (Шафигин Ильяс)
 * @version 0.6.14
 */
public class BasicAnimation extends ControllableAnimation {

    /**
     * Список плагинов.
     */
    protected PluginList plugins = null;

    /**
     * Конструктор, создающий объект стандартной анимации с заданными
     * параметрами.
     *
     * @param plugin   плагин.
     * @param duration продолжительность анимации.
     */
    public BasicAnimation(AnimationPlugin plugin, float duration) {
        super();

        // Создаем список плагинов с добавленным в него плагином.
        PluginList plugins = new PluginList(plugin);
        // Создаем объект опций анимации.
        AnimationOptions options = new AnimationOptions(duration);
        // Запускаем инициализацию.
        this.initialize(plugins, options);
    }

    /**
     * Конструктор, создающий объект стандартной анимации с заданными
     * параметрами.
     *
     * @param plugin   плагин.
     * @param duration продолжительность анимации.
     * @param easing   эффект анимации.
     */
    public BasicAnimation(AnimationPlugin plugin, float duration, Easing easing) {
        super();

        // Создаем список плагинов с добавленным в него плагином.
        PluginList plugins = new PluginList(plugin);
        // Создаем объект опций анимации.
        AnimationOptions options = new AnimationOptions(duration, easing);
        // Запускаем инициализацию.
        this.initialize(plugins, options);
    }

    /**
     * Конструктор, создающий объект стандартной анимации с заданными
     * параметрами.
     *
     * @param plugin   плагин.
     * @param duration продолжительность анимации.
     * @param easing   эффект анимации.
     * @param listener обработчик событий.
     */
    public BasicAnimation(AnimationPlugin plugin, float duration, Easing easing, AnimationListener listener) {
        super();

        // Создаем список плагинов с добавленным в него плагином.
        PluginList plugins = new PluginList(plugin);
        // Создаем объект опций анимации.
        AnimationOptions options = new AnimationOptions(duration, easing,
                listener);
        // Запускаем инициализацию.
        this.initialize(plugins, options);
    }

    /**
     * Конструктор, создающий объект стандартной анимации с заданными
     * параметрами.
     *
     * @param plugin   плагин.
     * @param duration продолжительность анимации.
     * @param listener обработчик событий.
     */
    public BasicAnimation(AnimationPlugin plugin, float duration, AnimationListener listener) {
        super();

        // Создаем список плагинов с добавленным в него плагином.
        PluginList plugins = new PluginList(plugin);
        // Создаем объект опций анимации.
        AnimationOptions options = new AnimationOptions(duration, listener);
        // Запускаем инициализацию.
        this.initialize(plugins, options);
    }

    /**
     * Конструктор, создающий объект стандартной анимации с заданными
     * параметрами.
     *
     * @param plugin  плагин.
     * @param options опции.
     */
    public BasicAnimation(AnimationPlugin plugin, AnimationOptions options) {
        super();

        // Создаем список плагинов с добавленным в него плагином.
        PluginList plugins = new PluginList(plugin);
        // Запускаем инициализацию.
        this.initialize(plugins, options);
    }

    /**
     * Конструктор, создающий объект стандартной анимации с заданными
     * параметрами.
     *
     * @param plugins  список плагинов.
     * @param duration продолжительность анимации.
     */
    public BasicAnimation(PluginList plugins, float duration) {
        super();

        // Создаем объект опций анимации.
        AnimationOptions options = new AnimationOptions(duration);
        // Запускаем инициализацию.
        this.initialize(plugins, options);
    }

    /**
     * Конструктор, создающий объект стандартной анимации с заданными
     * параметрами.
     *
     * @param plugins  список плагинов.
     * @param duration продолжительность анимации.
     * @param easing   эффект анимации.
     */
    public BasicAnimation(PluginList plugins, float duration, Easing easing) {
        super();

        // Создаем объект опций анимации.
        AnimationOptions options = new AnimationOptions(duration, easing);
        // Запускаем инициализацию.
        this.initialize(plugins, options);
    }

    /**
     * Конструктор, создающий объект стандартной анимации с заданными
     * параметрами.
     *
     * @param plugins  список плагинов.
     * @param duration продолжительность анимации.
     * @param easing   эффект анимации.
     * @param listener обработчик событий.
     */
    public BasicAnimation(PluginList plugins, float duration, Easing easing, AnimationListener listener) {
        super();

        // Создаем объект опций анимации.
        AnimationOptions options = new AnimationOptions(duration, easing,
                listener);
        // Запускаем инициализацию.
        this.initialize(plugins, options);
    }

    /**
     * Конструктор, создающий объект стандартной анимации с заданными
     * параметрами.
     *
     * @param plugins  список плагинов.
     * @param duration продолжительность анимации.
     * @param listener обработчик событий.
     */
    public BasicAnimation(PluginList plugins, float duration, AnimationListener listener) {
        super();

        // Создаем объект опций анимации.
        AnimationOptions options = new AnimationOptions(duration, listener);
        // Запускаем инициализацию.
        this.initialize(plugins, options);
    }

    /**
     * Конструктор, создающий объект стандартной анимации с заданными
     * параметрами.
     *
     * @param plugins список плагинов.
     * @param options опции.
     */
    public BasicAnimation(PluginList plugins, AnimationOptions options) {
        super();

        // Запускаем инициализацию.
        this.initialize(plugins, options);
    }

    /**
     * Конструктор, создающий объект стандартной анимации с заданными
     * параметрами.
     *
     * @param target   ссылка на объект.
     * @param plugin   плагин.
     * @param duration продолжительность анимации.
     */
    public BasicAnimation(Object target, AnimationPlugin plugin, float duration) {
        super();

        // Создаем список плагинов с добавленным в него плагином.
        PluginList plugins = new PluginList(plugin);
        // Создаем объект опций анимации.
        AnimationOptions options = new AnimationOptions(duration);
        // Запускаем инициализацию.
        this.initialize(target, plugins, options);
    }

    /**
     * Конструктор, создающий объект стандартной анимации с заданными
     * параметрами.
     *
     * @param target   ссылка на объект.
     * @param plugin   плагин.
     * @param duration продолжительность анимации.
     * @param easing   эффект анимации.
     */
    public BasicAnimation(Object target, AnimationPlugin plugin, float duration, Easing easing) {
        super();

        // Создаем список плагинов с добавленным в него плагином.
        PluginList plugins = new PluginList(plugin);
        // Создаем объект опций анимации.
        AnimationOptions options = new AnimationOptions(duration, easing);
        // Запускаем инициализацию.
        this.initialize(target, plugins, options);
    }

    /**
     * Конструктор, создающий объект стандартной анимации с заданными
     * параметрами.
     *
     * @param target   ссылка на объект.
     * @param plugin   плагин.
     * @param duration продолжительность анимации.
     * @param easing   эффект анимации.
     * @param listener обработчик событий.
     */
    public BasicAnimation(Object target, AnimationPlugin plugin, float duration, Easing easing, AnimationListener listener) {
        super();

        // Создаем список плагинов с добавленным в него плагином.
        PluginList plugins = new PluginList(plugin);
        // Создаем объект опций анимации.
        AnimationOptions options = new AnimationOptions(duration, easing, listener);
        // Запускаем инициализацию.
        this.initialize(target, plugins, options);
    }

    /**
     * Конструктор, создающий объект стандартной анимации с заданными
     * параметрами.
     *
     * @param target   ссылка на объект.
     * @param plugin   плагин.
     * @param duration продолжительность анимации.
     * @param listener обработчик событий.
     */
    public BasicAnimation(Object target, AnimationPlugin plugin, float duration, AnimationListener listener) {
        super();

        // Создаем список плагинов с добавленным в него плагином.
        PluginList plugins = new PluginList(plugin);
        // Создаем объект опций анимации.
        AnimationOptions options = new AnimationOptions(duration, listener);
        // Запускаем инициализацию.
        this.initialize(target, plugins, options);
    }

    /**
     * Конструктор, создающий объект стандартной анимации с заданными
     * параметрами.
     *
     * @param target  ссылка на объект.
     * @param plugin  плагин.
     * @param options опции.
     */
    public BasicAnimation(Object target, AnimationPlugin plugin, AnimationOptions options) {
        super();

        // Создаем список плагинов с добавленным в него плагином.
        PluginList plugins = new PluginList(plugin);
        // Запускаем инициализацию.
        this.initialize(target, plugins, options);
    }

    /**
     * Конструктор, создающий объект стандартной анимации с заданными
     * параметрами.
     *
     * @param target   ссылка на объект.
     * @param plugins  список плагинов.
     * @param duration продолжительность анимации.
     */
    public BasicAnimation(Object target, PluginList plugins, float duration) {
        super();

        // Создаем объект опций анимации.
        AnimationOptions options = new AnimationOptions(duration);
        // Запускаем инициализацию.
        this.initialize(target, plugins, options);
    }

    /**
     * Конструктор, создающий объект стандартной анимации с заданными
     * параметрами.
     *
     * @param target   ссылка на объект.
     * @param plugins  список плагинов.
     * @param duration продолжительность анимации.
     * @param easing   эффект анимации.
     */
    public BasicAnimation(Object target, PluginList plugins, float duration, Easing easing) {
        super();

        // Создаем объект опций анимации.
        AnimationOptions options = new AnimationOptions(duration, easing);
        // Запускаем инициализацию.
        this.initialize(target, plugins, options);
    }

    /**
     * Конструктор, создающий объект стандартной анимации с заданными
     * параметрами.
     *
     * @param target   ссылка на объект.
     * @param plugins  список плагинов.
     * @param duration продолжительность анимации.
     * @param easing   эффект анимации.
     * @param listener обработчик событий.
     */
    public BasicAnimation(Object target, PluginList plugins, float duration, Easing easing, AnimationListener listener) {
        super();

        // Создаем объект опций анимации.
        AnimationOptions options = new AnimationOptions(duration, easing, listener);
        // Запускаем инициализацию.
        this.initialize(target, plugins, options);
    }

    /**
     * Конструктор, создающий объект стандартной анимации с заданными
     * параметрами.
     *
     * @param target   ссылка на объект.
     * @param plugins  список плагинов.
     * @param duration продолжительность анимации.
     * @param listener обработчик событий.
     */
    public BasicAnimation(Object target, PluginList plugins, float duration, AnimationListener listener) {
        super();

        // Создаем объект опций анимации.
        AnimationOptions options = new AnimationOptions(duration, listener);
        // Запускаем инициализацию.
        this.initialize(target, plugins, options);
    }

    /**
     * Конструктор, создающий объект стандартной анимации с заданными
     * параметрами.
     *
     * @param target  ссылка на объект.
     * @param plugins список плагинов.
     * @param options опции.
     */
    public BasicAnimation(Object target, PluginList plugins, AnimationOptions options) {
        super();

        // Запускаем инициализацию.
        this.initialize(target, plugins, options);
    }

    /**
     * Получает массив анимируемых плагинов.
     *
     * @return массив анимируемых плагинов.
     */
    public PluginList getPlugins() {
        return this.plugins;
    }

    /**
     * Инициализирует анимацию.
     *
     * @param plugins свойства.
     * @param options опции.
     */
    private void initialize(PluginList plugins, AnimationOptions options) {
        if (plugins == null) throw new NullPointerException("plugins == null");
        if (options == null) throw new NullPointerException("options == null");

        // Устанавливаем значения.
        this.plugins = plugins;
        this.copy(null, options);
        this.plugins.initialize(this);
    }

    /**
     * Инициализирует анимацию.
     *
     * @param target  ссылка на объект.
     * @param plugins свойства.
     * @param options опции.
     */
    private void initialize(Object target, PluginList plugins, AnimationOptions options) {
        if (target == null) throw new NullPointerException("target == null");
        if (plugins == null) throw new NullPointerException("plugins == null");
        if (options == null) throw new NullPointerException("options == null");

        // Устанавливаем значения.
        this.plugins = plugins;
        this.copy(target, options);
        this.plugins.initialize(this);
    }

    @Override
    public void start() {
        super.start();
        //
        this.plugins.begin(this);
    }

    @Override
    public void update() {
        // Обновляем плагины.
        this.plugins.update(this);
    }

    @Override
    public boolean stop(boolean gotoEnd) {
        if (!super.stop(gotoEnd)) return false;

        if (gotoEnd) {
            this.plugins.update(this);
        }
        this.plugins.end(this);

        return true;
    }

    /**
     * Создает и возвращает новый объект построителя стандартной анимации.
     *
     * @return построитель стандартной анимации.
     */
    public static BasicAnimationBuilder builder() {
        return new BasicAnimationBuilder();
    }

    /**
     * Создает и возвращает новый объект построителя стандартной анимации.
     *
     * @param target анимируемый объект.
     * @return построитель стандартной анимации.
     */
    public static BasicAnimationBuilder builder(Object target) {
        return new BasicAnimationBuilder(target);
    }

    /**
     * Класс построителя стандартной анимации.
     *
     * @author Ilyas74
     * @version 0.2.4
     */
    public static class BasicAnimationBuilder implements Builder<BasicAnimation> {

        /**
         * Анимируемый объект.
         */
        private Object target;
        /**
         * Анимируемые плагины.
         */
        private PluginList plugins;
        /**
         * Опции анимации.
         */
        private AnimationOptions options;

        /**
         * Конструктор, создающий построитель анимации.
         */
        protected BasicAnimationBuilder() {
            this.target = null;
            this.plugins = new PluginList();
            this.options = new AnimationOptions();
        }

        /**
         * Конструктор, создающий построитель анимации с установленным
         * анимируемым объектом.
         *
         * @param target анимируемый объект.
         */
        protected BasicAnimationBuilder(Object target) {
            if (target == null) throw new NullPointerException("target == null");

            this.target = target;
            this.plugins = new PluginList();
            this.options = new AnimationOptions();
        }

        /**
         * Устанавливает анимируемый объект.
         *
         * @param target анимируемый объект.
         * @return данный построитель.
         */
        public BasicAnimationBuilder target(Object target) {
            if (target == null) throw new NullPointerException("target == null");

            this.target = target;
            return this;
        }

        /**
         * Добавляет анимируемый плагин в анимацию.
         *
         * @param plugin свойство.
         * @return данный построитель.
         */
        public BasicAnimationBuilder plugin(AnimationPlugin plugin) {
            if (plugin != null) {
                this.plugins.add(plugin);
            }
            return this;
        }

        /**
         * Добавляет анимируемые плагины в анимацию.
         *
         * @param plugins массив свойств.
         * @return данный построитель.
         */
        public BasicAnimationBuilder plugin(AnimationPlugin... plugins) {
            if (plugins != null) {
                for (AnimationPlugin plugin : plugins) {
                    if (plugin != null) {
                        this.plugins.add(plugin);
                    }
                }
            }
            return this;
        }

        /**
         * Добавляет анимируемые плагины в анимацию.
         *
         * @param plugins коллекция свойств.
         * @return данный построитель.
         */
        public BasicAnimationBuilder plugin(Collection<? extends AnimationPlugin> plugins) {
            if (plugins != null) {
                this.plugins.addAll(plugins);
            }
            return this;
        }

        /**
         * Устанавливает продолжительность анимации.
         *
         * @param duration продолжительность анимации.
         * @return данный построитель.
         */
        public BasicAnimationBuilder duration(float duration) {
            this.options.setDuration(duration);
            return this;
        }

        /**
         * Устанавливает продолжительность задержки анимации.
         *
         * @param delay продолжительность задержки анимации.
         * @return данный построитель.
         */
        public BasicAnimationBuilder delay(float delay) {
            this.options.setDelay(delay);
            return this;
        }

        /**
         * Устанавливает частоту обновления кадров анимации.
         *
         * @param fps частота обновления кадров анимации.
         * @return данный построитель.
         */
        public BasicAnimationBuilder fps(int fps) {
            this.options.setFps(fps);
            return this;
        }

        /**
         * Устанавливает масштабирование времени анимации.
         *
         * @param timescale масштабирование времени.
         * @return данный построитель.
         */
        public BasicAnimationBuilder timescale(float timescale) {
            this.options.setTimeScale(timescale);
            return this;
        }

        /**
         * Устанавливает эффект аниамции.
         *
         * @param easing эффект аниамции.
         * @return данный построитель.
         */
        public BasicAnimationBuilder easing(Easing easing) {
            this.options.setEasing(easing);
            return this;
        }

        /**
         * Устанавливает обработчик событий анимации.
         *
         * @param listener обработчик событий анимации.
         * @return данный построитель.
         */
        public BasicAnimationBuilder listener(AnimationListener listener) {
            this.options.setListener(listener);
            return this;
        }

        /**
         * Устанавливает триггеры событий.
         * @param triggers триггеры
         * @return данный построитель.
         */
        public BasicAnimationBuilder triggers(int triggers) {
            this.options.setTriggers(triggers);
            return this;
        }

        /**
         * Устанавливает количество повторов анимации.
         *
         * @param repeat количество повторов анимации.
         * @return данный построитель.
         */
        public BasicAnimationBuilder repeat(int repeat) {
            this.options.setRepeat(repeat);
            return this;
        }

        /**
         * Устанавливает продолжительность задержки между повторами анимации.
         *
         * @param repeatDelay продолжительность задержки между повторами
         *                    анимации.
         * @return данный построитель.
         */
        public BasicAnimationBuilder repeatDelay(float repeatDelay) {
            this.options.setRepeatDelay(repeatDelay);
            return this;
        }

        /**
         * Устанавливает режим времени анимации.
         *
         * @param timeMode режим времени.
         * @return данный построитель.
         * @see AnimationConstants#MILLISECONDS
         * @see AnimationConstants#SECONDS
         * @see AnimationConstants#FRAMES
         */
        public BasicAnimationBuilder timeMode(int timeMode) {
            this.options.setTimeMode(timeMode);
            return this;
        }

        /**
         * Устанавливает режим работы анимации.
         *
         * @param playMode режим работы.
         * @return данный построитель.
         * @see AnimationConstants#FORWARD
         * @see AnimationConstants#BACKWARD
         */
        public BasicAnimationBuilder playMode(int playMode) {
            this.options.setPlayMode(playMode);
            return this;
        }

        /**
         * Устанавливаем опции анимации.
         *
         * @param options объект опций.
         * @return данный построитель.
         */
        public BasicAnimationBuilder options(AnimationOptions options) {
            if (options == null) return this;
            this.options.copy(options);
            return this;
        }

        /**
         * Сбрасывает данный построитель.
         *
         * @return данный построитель.
         */
        public BasicAnimationBuilder reset() {
            this.target = null;
            this.plugins = new PluginList();
            this.options = new AnimationOptions();
            return this;
        }

        @Override
        public BasicAnimation build() {
            if (this.target == null) {
                BasicAnimation animation = new BasicAnimation(this.plugins, this.options);
                this.reset();
                return animation;
            } else {
                BasicAnimation animation = new BasicAnimation(this.target, this.plugins, this.options);
                this.reset();
                return animation;
            }
        }

    }

}
