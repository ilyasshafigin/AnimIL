/*
 *
 */
package ru.ildev.anim.core;

import ru.ildev.anim.easings.Easing;
import ru.ildev.anim.events.AnimationAdapter;
import ru.ildev.anim.events.AnimationEvent;
import ru.ildev.anim.events.AnimationListener;
import ru.ildev.anim.plugins.AnimationPlugin;
import ru.ildev.anim.plugins.PluginList;
import ru.ildev.utils.Builder;

import java.util.*;

/**
 * Класс анимации, которая реализует очередь элементов. Элементами могут быть
 * анимации, задержки, события. У каждого анимируемого объекта есть своя очередь.
 *
 * @author Shafigin Ilyas (Шафигин Ильяс)
 * @version 0.11.17
 */
public class QueueAnimation extends ControllableAnimation {

    /**
     * Анимируемый объект.
     */
    protected Object target = null;
    /**
     * Очередь.
     */
    protected Queue queue = null;
    /**
     * Флаг, отвечающий за состояние анимирования элементов по одному.
     */
    protected boolean one = false;

    /**
     * Конструктор, создающий анимацию с заданными параметрами.
     *
     * @param queue   очередь.
     * @param options опции анимации.
     * @param oneRun
     */
    protected QueueAnimation(Queue queue, AnimationOptions options,
                             boolean oneRun) {
        super();

        if (queue == null) throw new NullPointerException("queue == null");
        if (options == null) throw new NullPointerException("options == null");

        this.queue = queue;
        this.one = oneRun;
        this.parameters.copy(options);
        this.parameters.duration = this.parameters.getTimeFromTimeMode(queue.getDuration());
    }

    /**
     * Конструктор, создающий анимацию с пустой очередью и стандартными
     * параметрами.
     */
    public QueueAnimation() {
        super();

        this.queue = new Queue();
    }

    /**
     * Конструктор, создающий анимацию с пустой очередью и заданными
     * параметрами.
     *
     * @param options опции анимации.
     */
    public QueueAnimation(AnimationOptions options) {
        super();

        if (options == null) throw new NullPointerException("options == null");

        this.queue = new Queue();
        this.parameters.copy(options);
        this.parameters.duration = 0.0f;
    }

    /**
     * Конструктор, создающий анимацию с пустой очередью и стандартными
     * параметрами.
     *
     * @param oneRun
     */
    public QueueAnimation(boolean oneRun) {
        super();

        this.queue = new Queue();
        this.one = oneRun;
    }

    /**
     * Конструктор, создающий анимацию с пустой очередью и заданными
     * параметрами.
     *
     * @param options опции анимации.
     * @param oneRun
     */
    public QueueAnimation(AnimationOptions options, boolean oneRun) {
        super();

        if (options == null) throw new NullPointerException("options == null");

        this.queue = new Queue();
        this.one = oneRun;
        this.parameters.copy(options);
        this.parameters.duration = 0.0f;
    }

    /**
     * Конструктор, создающий анимацию заданными параметрами.
     *
     * @param target  ссылка на объект.
     * @param queue   очередь.
     * @param options опции анимации.
     * @param oneRun
     */
    protected QueueAnimation(Object target, Queue queue,
                             AnimationOptions options, boolean oneRun) {
        super();

        if (target == null) throw new NullPointerException("target == null");
        if (queue == null) throw new NullPointerException("queue == null");
        if (options == null) throw new NullPointerException("options == null");

        this.target = target;
        this.queue = queue;
        this.one = oneRun;
        this.parameters.copy(target, options);
        this.parameters.duration = this.parameters.getTimeFromTimeMode(queue.getDuration());
    }

    /**
     * Конструктор, создающий анимацию с пустой очередью и стандартными
     * параметрами.
     *
     * @param target ссылка на объект.
     */
    public QueueAnimation(Object target) {
        super();

        if (target == null) throw new NullPointerException("target == null");

        this.target = target;
        this.queue = new Queue();
        this.parameters.target = target;
    }

    /**
     * Конструктор, создающий анимацию с пустой очередью и заданными
     * параметрами.
     *
     * @param target  ссылка на объект.
     * @param options опции анимации.
     */
    public QueueAnimation(Object target, AnimationOptions options) {
        super();

        if (target == null) throw new NullPointerException("target == null");
        if (options == null) throw new NullPointerException("options == null");

        this.target = target;
        this.queue = new Queue();
        this.parameters.copy(target, options);
        this.parameters.duration = 0.0f;
    }

    /**
     * Конструктор, создающий анимацию с пустой очередью и стандартными
     * параметрами.
     *
     * @param target ссылка на объект.
     * @param oneRun
     */
    public QueueAnimation(Object target, boolean oneRun) {
        super();

        if (target == null) throw new NullPointerException("target == null");

        this.target = target;
        this.queue = new Queue();
        this.one = oneRun;
        this.parameters.target = target;
    }

    /**
     * Конструктор, создающий анимацию с пустой очередью и заданными
     * параметрами.
     *
     * @param target  ссылка на объект.
     * @param options опции анимации.
     * @param oneRun
     */
    public QueueAnimation(Object target, AnimationOptions options,
                          boolean oneRun) {
        super();

        if (target == null) throw new NullPointerException("target == null");
        if (options == null) throw new NullPointerException("options == null");

        this.target = target;
        this.queue = new Queue();
        this.one = oneRun;
        this.parameters.copy(target, options);
        this.parameters.duration = 0.0f;
    }

    /**
     * Определяет, включено ли анимирование по одному.
     *
     * @return {@code true}, если анимирование по одному включено.
     */
    public boolean isOne() {
        return this.one;
    }

    /**
     * Изменяет состояние анимирования по очереди.
     *
     * @param flag флаг.
     */
    public void setOne(boolean flag) {
        this.one = flag;
    }

    /**
     * Получает активный элемент очереди анимации.
     *
     * @return активный элемент очереди анимации.
     */
    public Element getCurrentQueueElement() {
        return this.queue.current();
    }

    /**
     * Добавляет новый элемент в очередь.
     *
     * @param plugin   плагин.
     * @param duration продолжительность анимации.
     */
    public void insert(AnimationPlugin plugin, float duration) {
        // Создаем список плагинов с добавленным в него плагином.
        PluginList plugins = new PluginList(plugin);
        // Создаем объект опций анимации.
        AnimationOptions options = new AnimationOptions(duration);
        // Добавляем элемент в очередь.
        this.insert(plugins, options);
    }

    /**
     * Добавляет новый элемент в очередь.
     *
     * @param plugin   плагин.
     * @param duration продолжительность анимации.
     * @param easing   эффект анимации.
     */
    public void insert(AnimationPlugin plugin, float duration, Easing easing) {
        // Создаем список плагинов с добавленным в него плагином.
        PluginList plugins = new PluginList(plugin);
        // Создаем объект опций анимации.
        AnimationOptions options = new AnimationOptions(duration, easing);
        // Добавляем элемент в очередь.
        this.insert(plugins, options);
    }

    /**
     * Добавляет новый элемент в очередь.
     *
     * @param plugin   плагин.
     * @param duration продолжительность анимации.
     * @param easing   эффект анимации.
     * @param listener обработчик событий.
     */
    public void insert(AnimationPlugin plugin, float duration, Easing easing,
                       AnimationListener listener) {
        // Создаем список плагинов с добавленным в него плагином.
        PluginList plugins = new PluginList(plugin);
        // Создаем объект опций анимации.
        AnimationOptions options = new AnimationOptions(duration, easing, listener);
        // Добавляем элемент в очередь.
        this.insert(plugins, options);
    }

    /**
     * Добавляет новый элемент в очередь.
     *
     * @param plugin   плагин.
     * @param duration продолжительность анимации.
     * @param listener обработчик событий.
     */
    public void insert(AnimationPlugin plugin, float duration,
                       AnimationListener listener) {
        // Создаем список плагинов с добавленным в него плагином.
        PluginList plugins = new PluginList(plugin);
        // Создаем объект опций анимации.
        AnimationOptions options = new AnimationOptions(duration, listener);
        // Добавляем элемент в очередь.
        this.insert(plugins, options);
    }

    /**
     * Добавляет новый элемент в очередь.
     *
     * @param plugin  плагин.
     * @param options опции.
     */
    public void insert(AnimationPlugin plugin, AnimationOptions options) {
        // Создаем список плагинов с добавленным в него плагином.
        PluginList plugins = new PluginList(plugin);
        // Добавляем элемент в очередь.
        this.insert(plugins, options);
    }

    /**
     * Добавляет новый элемент в очередь.
     *
     * @param plugins  список плагинов.
     * @param duration продолжительность анимации.
     */
    public void insert(PluginList plugins, float duration) {
        // Создаем объект опций анимации.
        AnimationOptions options = new AnimationOptions(duration);
        // Добавляем элемент в очередь.
        this.insert(plugins, options);
    }

    /**
     * Добавляет новый элемент в очередь.
     *
     * @param plugins  список плагинов.
     * @param duration продолжительность анимации.
     * @param easing   эффект анимации.
     */
    public void insert(PluginList plugins, float duration, Easing easing) {
        // Создаем объект опций анимации.
        AnimationOptions options = new AnimationOptions(duration, easing);
        // Добавляем элемент в очередь.
        this.insert(plugins, options);
    }

    /**
     * Добавляет новый элемент в очередь.
     *
     * @param plugins  список плагинов.
     * @param duration продолжительность анимации.
     * @param easing   эффект анимации.
     * @param listener обработчик событий.
     */
    public void insert(PluginList plugins, float duration, Easing easing,
                       AnimationListener listener) {
        // Создаем объект опций анимации.
        AnimationOptions options = new AnimationOptions(duration, easing, listener);
        // Добавляем элемент в очередь.
        this.insert(plugins, options);
    }

    /**
     * Добавляет новый элемент в очередь.
     *
     * @param plugins  список плагинов.
     * @param duration продолжительность анимации.
     * @param listener обработчик событий.
     */
    public void insert(PluginList plugins, float duration,
                       AnimationListener listener) {
        // Создаем объект опций анимации.
        AnimationOptions options = new AnimationOptions(duration, listener);
        // Добавляем элемент в очередь.
        this.insert(plugins, options);
    }

    /**
     * Добавляет новый элемент в очередь.
     *
     * @param plugins список плагинов.
     * @param options опции.
     */
    public void insert(PluginList plugins, AnimationOptions options) {
        if (plugins == null) throw new NullPointerException("plugins == null");
        if (options == null) throw new NullPointerException("options == null");

        // Если анимация будет повторяться бесконечно.
        if (options.repeat == REPEAT_INFINITE) {
            // Заносим сообщение в регистратор.
            Animator.LOGGER.warning("options.repeat == REPEAT_INFINITE");
            // Выходим.
            return;
        }

        // Создаем объект параметров анимации.
        AnimationParameters parameters = new AnimationParameters(this.target, options);
        // Создаем новый элемент очереди анимаций.
        Element element = new Element(plugins, parameters);
        // Добавляем ее в конец списка.
        this.queue.add(element);

        // Обновляем продолжительность анимации очереди.
        this.parameters.duration += this.parameters.getTime(parameters.getTotalDuration(), parameters);
    }

    /**
     * Добавляет новый элемент в очередь.
     *
     * @param index    индекс элемента.
     * @param plugin   плагин.
     * @param duration продолжительность анимации.
     */
    public void insert(int index, AnimationPlugin plugin, float duration) {
        // Создаем список плагинов с добавленным в него плагином.
        PluginList plugins = new PluginList(plugin);
        // Создаем объект опций анимации.
        AnimationOptions options = new AnimationOptions(duration);
        // Добавляем элемент в очередь.
        this.insert(plugins, options);
    }

    /**
     * Добавляет новый элемент в очередь.
     *
     * @param index    индекс элемента.
     * @param plugin   плагин.
     * @param duration продолжительность анимации.
     * @param easing   эффект анимации.
     */
    public void insert(int index, AnimationPlugin plugin, float duration,
                       Easing easing) {
        // Создаем список плагинов с добавленным в него плагином.
        PluginList plugins = new PluginList(plugin);
        // Создаем объект опций анимации.
        AnimationOptions options = new AnimationOptions(duration, easing);
        // Добавляем элемент в очередь.
        this.insert(plugins, options);
    }

    /**
     * Добавляет новый элемент в очередь.
     *
     * @param index    индекс элемента.
     * @param plugin   плагин.
     * @param duration продолжительность анимации.
     * @param easing   эффект анимации.
     * @param listener обработчик событий.
     */
    public void insert(int index, AnimationPlugin plugin, float duration,
                       Easing easing, AnimationListener listener) {
        // Создаем список плагинов с добавленным в него плагином.
        PluginList plugins = new PluginList(plugin);
        // Создаем объект опций анимации.
        AnimationOptions options = new AnimationOptions(duration, easing, listener);
        // Добавляем элемент в очередь.
        this.insert(plugins, options);
    }

    /**
     * Добавляет новый элемент в очередь.
     *
     * @param index    индекс элемента.
     * @param plugin   плагин.
     * @param duration продолжительность анимации.
     * @param listener обработчик событий.
     */
    public void insert(int index, AnimationPlugin plugin, float duration,
                       AnimationListener listener) {
        // Создаем список плагинов с добавленным в него плагином.
        PluginList plugins = new PluginList(plugin);
        // Создаем объект опций анимации.
        AnimationOptions options = new AnimationOptions(duration, listener);
        // Добавляем элемент в очередь.
        this.insert(plugins, options);
    }

    /**
     * Добавляет новый элемент в очередь.
     *
     * @param index   индекс элемента.
     * @param plugin  плагин.
     * @param options опции.
     */
    public void insert(int index, AnimationPlugin plugin, AnimationOptions options) {
        // Создаем список плагинов с добавленным в него плагином.
        PluginList plugins = new PluginList(plugin);
        // Добавляем элемент в очередь.
        this.insert(plugins, options);
    }

    /**
     * Добавляет новый элемент в очередь.
     *
     * @param index    индекс элемента.
     * @param plugins  список плагинов.
     * @param duration продолжительность анимации.
     */
    public void insert(int index, PluginList plugins, float duration) {
        // Создаем объект опций анимации.
        AnimationOptions options = new AnimationOptions(duration);
        // Добавляем элемент в очередь.
        this.insert(plugins, options);
    }

    /**
     * Добавляет новый элемент в очередь.
     *
     * @param index    индекс элемента.
     * @param plugins  список плагинов.
     * @param duration продолжительность анимации.
     * @param easing   эффект анимации.
     */
    public void insert(int index, PluginList plugins, float duration,
                       Easing easing) {
        // Создаем объект опций анимации.
        AnimationOptions options = new AnimationOptions(duration, easing);
        // Добавляем элемент в очередь.
        this.insert(plugins, options);
    }

    /**
     * Добавляет новый элемент в очередь.
     *
     * @param index    индекс элемента.
     * @param plugins  список плагинов.
     * @param duration продолжительность анимации.
     * @param easing   эффект анимации.
     * @param listener обработчик событий.
     */
    public void insert(int index, PluginList plugins, float duration,
                       Easing easing, AnimationListener listener) {
        // Создаем объект опций анимации.
        AnimationOptions options = new AnimationOptions(duration, easing, listener);
        // Добавляем элемент в очередь.
        this.insert(plugins, options);
    }

    /**
     * Добавляет новый элемент в очередь.
     *
     * @param index    индекс элемента.
     * @param plugins  список плагинов.
     * @param duration продолжительность анимации.
     * @param listener обработчик событий.
     */
    public void insert(int index, PluginList plugins, float duration,
                       AnimationListener listener) {
        // Создаем объект опций анимации.
        AnimationOptions options = new AnimationOptions(duration, listener);
        // Добавляем элемент в очередь.
        this.insert(plugins, options);
    }

    /**
     * Добавляет новый элемент в очередь.
     *
     * @param index   индекс элемента.
     * @param plugins список плагинов.
     * @param options опции.
     */
    public void insert(int index, PluginList plugins, AnimationOptions options) {
        if (index < 0) throw new IndexOutOfBoundsException("index < 0");
        if (index >= this.queue.size()) throw new IndexOutOfBoundsException("index >= queue.size");
        if (plugins == null) throw new NullPointerException("plugins == null");
        if (options == null) throw new NullPointerException("options == null");

        // Если анимация будет повторяться бесконечно.
        if (options.repeat == REPEAT_INFINITE) {
            // Заносим сообщение в регистратор.
            Animator.LOGGER.warning("options.repeat == REPEAT_INFINITE");
            // Выходим.
            return;
        }

        // Создаем объект параметров анимации.
        AnimationParameters parameters = new AnimationParameters(this.target, options);
        // Создаем новый элемент очереди анимаций.
        Element element = new Element(plugins, parameters);
        // Добавляем ее в конец списка.
        this.queue.add(index, element);

        // Обновляем продолжительность анимации очереди.
        this.parameters.duration += this.parameters.getTime(parameters.getTotalDuration(), parameters);
    }

    /**
     * Добавляет новый элемент в очередь.
     *
     * @param plugin   плагин.
     * @param duration продолжительность анимации.
     * @param name     имя элемента.
     */
    public void insert(String name, AnimationPlugin plugin, float duration) {
        // Создаем список плагинов с добавленным в него плагином.
        PluginList plugins = new PluginList(plugin);
        // Создаем объект опций анимации.
        AnimationOptions options = new AnimationOptions(duration);
        // Добавляем элемент в очередь.
        this.insert(name, plugins, options);
    }

    /**
     * Добавляет новый элемент в очередь.
     *
     * @param plugin   плагин.
     * @param duration продолжительность анимации.
     * @param easing   эффект анимации.
     * @param name     имя элемента.
     */
    public void insert(String name, AnimationPlugin plugin, float duration,
                       Easing easing) {
        // Создаем список плагинов с добавленным в него плагином.
        PluginList plugins = new PluginList(plugin);
        // Создаем объект опций анимации.
        AnimationOptions options = new AnimationOptions(duration, easing);
        // Добавляем элемент в очередь.
        this.insert(name, plugins, options);
    }

    /**
     * Добавляет новый элемент в очередь.
     *
     * @param plugin   плагин.
     * @param duration продолжительность анимации.
     * @param easing   эффект анимации.
     * @param listener обработчик событий.
     * @param name     имя элемента.
     */
    public void insert(String name, AnimationPlugin plugin, float duration,
                       Easing easing, AnimationListener listener) {
        // Создаем список плагинов с добавленным в него плагином.
        PluginList plugins = new PluginList(plugin);
        // Создаем объект опций анимации.
        AnimationOptions options = new AnimationOptions(duration, easing, listener);
        // Добавляем элемент в очередь.
        this.insert(name, plugins, options);
    }

    /**
     * Добавляет новый элемент в очередь.
     *
     * @param plugin   плагин.
     * @param duration продолжительность анимации.
     * @param listener обработчик событий.
     * @param name     имя элемента.
     */
    public void insert(String name, AnimationPlugin plugin, float duration,
                       AnimationListener listener) {
        // Создаем список плагинов с добавленным в него плагином.
        PluginList plugins = new PluginList(plugin);
        // Создаем объект опций анимации.
        AnimationOptions options = new AnimationOptions(duration, listener);
        // Добавляем элемент в очередь.
        this.insert(name, plugins, options);
    }

    /**
     * Добавляет новый элемент в очередь.
     *
     * @param plugin  плагин.
     * @param options опции.
     * @param name    имя элемента.
     */
    public void insert(String name, AnimationPlugin plugin,
                       AnimationOptions options) {
        // Создаем список плагинов с добавленным в него плагином.
        PluginList plugins = new PluginList(plugin);
        // Добавляем элемент в очередь.
        this.insert(name, plugins, options);
    }

    /**
     * Добавляет новый элемент в очередь.
     *
     * @param plugins  список плагинов.
     * @param duration продолжительность анимации.
     * @param name     имя элемента.
     */
    public void insert(String name, PluginList plugins, float duration) {
        // Создаем объект опций анимации.
        AnimationOptions options = new AnimationOptions(duration);
        // Добавляем элемент в очередь.
        this.insert(name, plugins, options);
    }

    /**
     * Добавляет новый элемент в очередь.
     *
     * @param plugins  список плагинов.
     * @param duration продолжительность анимации.
     * @param easing   эффект анимации.
     * @param name     имя элемента.
     */
    public void insert(String name, PluginList plugins, float duration,
                       Easing easing) {
        // Создаем объект опций анимации.
        AnimationOptions options = new AnimationOptions(duration, easing);
        // Добавляем элемент в очередь.
        this.insert(name, plugins, options);
    }

    /**
     * Добавляет новый элемент в очередь.
     *
     * @param plugins  список плагинов.
     * @param duration продолжительность анимации.
     * @param easing   эффект анимации.
     * @param listener обработчик событий.
     * @param name     имя элемента.
     */
    public void insert(String name, PluginList plugins, float duration,
                       Easing easing, AnimationListener listener) {
        // Создаем объект опций анимации.
        AnimationOptions options = new AnimationOptions(duration, easing, listener);
        // Добавляем элемент в очередь.
        this.insert(name, plugins, options);
    }

    /**
     * Добавляет новый элемент в очередь.
     *
     * @param plugins  список плагинов.
     * @param duration продолжительность анимации.
     * @param listener обработчик событий.
     * @param name     имя элемента.
     */
    public void insert(String name, PluginList plugins, float duration,
                       AnimationListener listener) {
        // Создаем объект опций анимации.
        AnimationOptions options = new AnimationOptions(duration, listener);
        // Добавляем элемент в очередь.
        this.insert(name, plugins, options);
    }

    /**
     * Добавляет новый элемент в очередь.
     *
     * @param plugins список плагинов.
     * @param options опции.
     * @param name    имя элемента.
     */
    public void insert(String name, PluginList plugins,
                       AnimationOptions options) {
        if (name == null) throw new NullPointerException("name == null");
        if (plugins == null) throw new NullPointerException("plugins == null");
        if (options == null) throw new NullPointerException("options == null");

        // Если анимация будет повторяться бесконечно.
        if (options.repeat == REPEAT_INFINITE) {
            // Заносим сообщение в регистратор.
            Animator.LOGGER.warning("options.repeat == REPEAT_INFINITE");
            // Выходим.
            return;
        }

        // Создаем объект параметров анимации.
        AnimationParameters parameters = new AnimationParameters(this.target, options);
        // Создаем новый элемент очереди анимаций.
        Element element = new Element(name, plugins, parameters);
        // Добавляем ее в конец списка.
        this.queue.add(element);

        // Обновляем продолжительность анимации очереди.
        this.parameters.duration += this.parameters.getTime(parameters.getTotalDuration(), parameters);
    }

    /**
     * Добавляет новый элемент в очередь.
     *
     * @param index    индекс элемента.
     * @param plugin   плагин.
     * @param duration продолжительность анимации.
     * @param name     имя элемента.
     */
    public void insert(int index, String name, AnimationPlugin plugin,
                       float duration) {
        // Создаем список плагинов с добавленным в него плагином.
        PluginList plugins = new PluginList(plugin);
        // Создаем объект опций анимации.
        AnimationOptions options = new AnimationOptions(duration);
        // Добавляем элемент в очередь.
        this.insert(index, name, plugins, options);
    }

    /**
     * Добавляет новый элемент в очередь.
     *
     * @param index    индекс элемента.
     * @param plugin   плагин.
     * @param duration продолжительность анимации.
     * @param easing   эффект анимации.
     * @param name     имя элемента.
     */
    public void insert(int index, String name, AnimationPlugin plugin,
                       float duration, Easing easing) {
        // Создаем список плагинов с добавленным в него плагином.
        PluginList plugins = new PluginList(plugin);
        // Создаем объект опций анимации.
        AnimationOptions options = new AnimationOptions(duration, easing);
        // Добавляем элемент в очередь.
        this.insert(index, name, plugins, options);
    }

    /**
     * Добавляет новый элемент в очередь.
     *
     * @param index    индекс элемента.
     * @param plugin   плагин.
     * @param duration продолжительность анимации.
     * @param easing   эффект анимации.
     * @param listener обработчик событий.
     * @param name     имя элемента.
     */
    public void insert(int index, String name, AnimationPlugin plugin,
                       float duration, Easing easing, AnimationListener listener) {
        // Создаем список плагинов с добавленным в него плагином.
        PluginList plugins = new PluginList(plugin);
        // Создаем объект опций анимации.
        AnimationOptions options = new AnimationOptions(duration, easing, listener);
        // Добавляем элемент в очередь.
        this.insert(index, name, plugins, options);
    }

    /**
     * Добавляет новый элемент в очередь.
     *
     * @param index    индекс элемента.
     * @param plugin   плагин.
     * @param duration продолжительность анимации.
     * @param listener обработчик событий.
     * @param name     имя элемента.
     */
    public void insert(int index, String name, AnimationPlugin plugin,
                       float duration, AnimationListener listener) {
        // Создаем список плагинов с добавленным в него плагином.
        PluginList plugins = new PluginList(plugin);
        // Создаем объект опций анимации.
        AnimationOptions options = new AnimationOptions(duration, listener);
        // Добавляем элемент в очередь.
        this.insert(index, name, plugins, options);
    }

    /**
     * Добавляет новый элемент в очередь.
     *
     * @param index   индекс элемента.
     * @param plugin  плагин.
     * @param options опции.
     * @param name    имя элемента.
     */
    public void insert(int index, String name, AnimationPlugin plugin,
                       AnimationOptions options) {
        // Создаем список плагинов с добавленным в него плагином.
        PluginList plugins = new PluginList(plugin);
        // Добавляем элемент в очередь.
        this.insert(index, name, plugins, options);
    }

    /**
     * Добавляет новый элемент в очередь.
     *
     * @param index    индекс элемента.
     * @param plugins  список плагинов.
     * @param duration продолжительность анимации.
     * @param name     имя элемента.
     */
    public void insert(int index, String name, PluginList plugins,
                       float duration) {
        // Создаем объект опций анимации.
        AnimationOptions options = new AnimationOptions(duration);
        // Добавляем элемент в очередь.
        this.insert(index, name, plugins, options);
    }

    /**
     * Добавляет новый элемент в очередь.
     *
     * @param index    индекс элемента.
     * @param plugins  список плагинов.
     * @param duration продолжительность анимации.
     * @param easing   эффект анимации.
     * @param name     имя элемента.
     */
    public void insert(int index, String name, PluginList plugins,
                       float duration, Easing easing) {
        // Создаем объект опций анимации.
        AnimationOptions options = new AnimationOptions(duration, easing);
        // Добавляем элемент в очередь.
        this.insert(index, name, plugins, options);
    }

    /**
     * Добавляет новый элемент в очередь.
     *
     * @param index    индекс элемента.
     * @param plugins  список плагинов.
     * @param duration продолжительность анимации.
     * @param easing   эффект анимации.
     * @param listener обработчик событий.
     * @param name     имя элемента.
     */
    public void insert(int index, String name, PluginList plugins,
                       float duration, Easing easing, AnimationListener listener) {
        // Создаем объект опций анимации.
        AnimationOptions options = new AnimationOptions(duration, easing, listener);
        // Добавляем элемент в очередь.
        this.insert(index, name, plugins, options);
    }

    /**
     * Добавляет новый элемент в очередь.
     *
     * @param index    индекс элемента.
     * @param plugins  список плагинов.
     * @param duration продолжительность анимации.
     * @param listener обработчик событий.
     * @param name     имя элемента.
     */
    public void insert(int index, String name, PluginList plugins,
                       float duration, AnimationListener listener) {
        // Создаем объект опций анимации.
        AnimationOptions options = new AnimationOptions(duration, listener);
        // Добавляем элемент в очередь.
        this.insert(index, name, plugins, options);
    }

    /**
     * Добавляет новый элемент в очередь.
     *
     * @param index   индекс элемента.
     * @param plugins список плагинов.
     * @param options опции.
     * @param name    имя элемента.
     */
    public void insert(int index, String name, PluginList plugins,
                       AnimationOptions options) {
        if (index < 0) throw new IndexOutOfBoundsException("index < 0");
        if (index >= this.queue.size()) throw new IndexOutOfBoundsException("index >= queue.size");
        if (name == null) throw new NullPointerException("name == null");
        if (plugins == null) throw new NullPointerException("plugins == null");
        if (options == null) throw new NullPointerException("options == null");

        // Если анимация будет повторяться бесконечно.
        if (options.repeat == REPEAT_INFINITE) {
            // Заносим сообщение в регистратор.
            Animator.LOGGER.warning("options.repeat == REPEAT_INFINITE");
            // Выходим.
            return;
        }

        // Создаем объект параметров анимации.
        AnimationParameters parameters = new AnimationParameters(this.target, options);
        // Создаем новый элемент очереди анимаций.
        Element element = new Element(name, plugins, parameters);
        // Добавляем ее в конец списка.
        this.queue.add(index, element);

        // Обновляем продолжительность анимации очереди.
        this.parameters.duration += this.parameters.getTime(parameters.getTotalDuration(), parameters);
    }

    /**
     * Добавляет в очередь задержку.
     *
     * @param duration продолжительность задержки анимации.
     */
    public void insert(float duration) {
        // Создаем объект опций анимации.
        AnimationOptions options = new AnimationOptions(duration);
        // Добавляем в очередь задержку.
        this.insert(options);
    }

    /**
     * Добавляет в очередь задержку.
     *
     * @param duration продолжительность задержки анимации.
     * @param listener обработчик событий.
     */
    public void insert(float duration, AnimationListener listener) {
        // Создаем объект опций анимации.
        AnimationOptions options = new AnimationOptions(duration, listener);
        // Добавляем в очередь задержку.
        this.insert(options);
    }

    /**
     * Добавляет в очередь задержку.
     *
     * @param options опции.
     */
    public void insert(AnimationOptions options) {
        if (options == null) throw new NullPointerException("options == null");

        // Если анимация будет повторяться бесконечно.
        if (options.repeat == REPEAT_INFINITE) {
            // Заносим сообщение в регистратор.
            Animator.LOGGER.warning("options.repeat == REPEAT_INFINITE");
            // Выходим.
            return;
        }

        // Создаем объект параметров анимации.
        AnimationParameters parameters = new AnimationParameters(this.target, options);
        // Создаем новую очередь анимаций.
        Element queue = new DelayElement(parameters);
        // Добавляем ее в конец списка.
        this.queue.add(queue);

        // Обновляем продолжительность анимации очереди.
        this.parameters.duration += this.parameters.getTime(parameters.getTotalDuration(), parameters);
    }

    /**
     * Добавляет в очередь задержку.
     *
     * @param index    индекс элемента.
     * @param duration продолжительность задержки анимации.
     */
    public void insert(int index, float duration) {
        // Создаем объект опций анимации.
        AnimationOptions options = new AnimationOptions(duration);
        // Добавляем в очередь задержку.
        this.insert(options);
    }

    /**
     * Добавляет в очередь задержку.
     *
     * @param index    индекс элемента.
     * @param duration продолжительность задержки анимации.
     * @param listener обработчик событий.
     */
    public void insert(int index, float duration, AnimationListener listener) {
        // Создаем объект опций анимации.
        AnimationOptions options = new AnimationOptions(duration, listener);
        // Добавляем в очередь задержку.
        this.insert(options);
    }

    /**
     * Добавляет в очередь задержку.
     *
     * @param index   индекс элемента.
     * @param options опции.
     */
    public void insert(int index, AnimationOptions options) {
        if (index < 0) throw new IndexOutOfBoundsException("index < 0");
        if (index >= this.queue.size()) throw new IndexOutOfBoundsException("index >= queue.size");
        if (options == null) throw new NullPointerException("options == null");

        // Если анимация будет повторяться бесконечно.
        if (options.repeat == REPEAT_INFINITE) {
            // Заносим сообщение в регистратор.
            Animator.LOGGER.warning("options.repeat == REPEAT_INFINITE");
            // Выходим.
            return;
        }

        // Создаем объект параметров анимации.
        AnimationParameters parameters = new AnimationParameters(this.target, options);
        // Создаем новую очередь анимаций.
        Element queue = new DelayElement(parameters);
        // Добавляем ее в конец списка.
        this.queue.add(index, queue);

        // Обновляем продолжительность анимации очереди.
        this.parameters.duration += this.parameters.getTime(parameters.getTotalDuration(), parameters);
    }

    /**
     * Добавляет в очередь задержку.
     *
     * @param duration продолжительность задержки анимации.
     * @param name     имя элемента.
     */
    public void insert(String name, float duration) {
        // Создаем объект опций анимации.
        AnimationOptions options = new AnimationOptions(duration);
        // Добавляем в очередь задержку.
        this.insert(name, options);
    }

    /**
     * Добавляет в очередь задержку.
     *
     * @param duration продолжительность задержки анимации.
     * @param listener обработчик событий.
     * @param name     имя элемента.
     */
    public void insert(String name, float duration, AnimationListener listener) {
        // Создаем объект опций анимации.
        AnimationOptions options = new AnimationOptions(duration, listener);
        // Добавляем в очередь задержку.
        this.insert(name, options);
    }

    /**
     * Добавляет в очередь задержку.
     *
     * @param options опции.
     * @param name    имя элемента.
     */
    public void insert(String name, AnimationOptions options) {
        if (name == null) throw new NullPointerException("name == null");
        if (options == null) throw new NullPointerException("options == null");

        // Если анимация будет повторяться бесконечно.
        if (options.repeat == REPEAT_INFINITE) {
            // Заносим сообщение в регистратор.
            Animator.LOGGER.warning("options.repeat == REPEAT_INFINITE");
            // Выходим.
            return;
        }

        // Создаем объект параметров анимации.
        AnimationParameters parameters = new AnimationParameters(this.target, options);
        // Создаем новую очередь анимаций.
        Element queue = new DelayElement(name, parameters);
        // Добавляем ее в конец списка.
        this.queue.add(queue);

        // Обновляем продолжительность анимации очереди.
        this.parameters.duration += this.parameters.getTime(parameters.getTotalDuration(), parameters);
    }

    /**
     * Добавляет в очередь задержку.
     *
     * @param index    индекс элемента.
     * @param duration продолжительность задержки анимации.
     * @param name     имя элемента.
     */
    public void insert(int index, String name, float duration) {
        // Создаем объект опций анимации.
        AnimationOptions options = new AnimationOptions(duration);
        // Добавляем в очередь задержку.
        this.insert(name, options);
    }

    /**
     * Добавляет в очередь задержку.
     *
     * @param index    индекс элемента.
     * @param duration продолжительность задержки анимации.
     * @param listener обработчик событий.
     * @param name     имя элемента.
     */
    public void insert(int index, String name, float duration,
                       AnimationListener listener) {
        // Создаем объект опций анимации.
        AnimationOptions options = new AnimationOptions(duration, listener);
        // Добавляем в очередь задержку.
        this.insert(name, options);
    }

    /**
     * Добавляет в очередь задержку.
     *
     * @param index   индекс элемента.
     * @param options опции.
     * @param name    имя элемента.
     */
    public void insert(int index, String name, AnimationOptions options) {
        if (name == null) throw new NullPointerException("name == null");
        if (options == null) throw new NullPointerException("options == null");

        // Если анимация будет повторяться бесконечно.
        if (options.repeat == REPEAT_INFINITE) {
            // Заносим сообщение в регистратор.
            Animator.LOGGER.warning("options.repeat == REPEAT_INFINITE");
            // Выходим.
            return;
        }

        // Создаем объект параметров анимации.
        AnimationParameters parameters = new AnimationParameters(this.target, options);
        // Создаем новую очередь анимаций.
        Element queue = new DelayElement(name, parameters);
        // Добавляем ее в конец списка.
        this.queue.add(queue);

        // Обновляем продолжительность анимации очереди.
        this.parameters.duration += this.parameters.getTime(parameters.getTotalDuration(), parameters);
    }

    /**
     * Добавляет в очередь обработчик событий.
     *
     * @param listener обработчик событий.
     */
    public void insert(AnimationListener listener) {
        if (listener == null) throw new NullPointerException("listener == null");

        // Создаем новую очередь анимаций.
        Element queue = new ListenerElement(new AnimationOptions(listener));
        // Добавляем ее в конец списка.
        this.queue.add(queue);
    }

    /**
     * Добавляет в очередь обработчик событий.
     *
     * @param listener обработчик событий.
     * @param name     имя элемента.
     */
    public void insert(String name, AnimationListener listener) {
        if (name == null) throw new NullPointerException("name == null");
        if (listener == null) throw new NullPointerException("listener == null");

        // Создаем новую очередь анимаций.
        Element queue = new ListenerElement(name, new AnimationOptions(listener));
        // Добавляем ее в конец списка.
        this.queue.add(queue);
    }

    /**
     * Добавляет в очередь обработчик событий.
     *
     * @param index    индекс элемента.
     * @param listener обработчик событий.
     */
    public void insert(int index, AnimationListener listener) {
        if (index < 0) throw new IndexOutOfBoundsException("index < 0");
        if (index >= this.queue.size()) throw new IndexOutOfBoundsException("index >= queue.size");
        if (listener == null) throw new NullPointerException("listener == null");

        // Создаем новую очередь анимаций.
        Element queue = new ListenerElement(new AnimationOptions(listener));
        // Добавляем ее в конец списка.
        this.queue.add(index, queue);
    }

    /**
     * Добавляет в очередь обработчик событий.
     *
     * @param index    индекс элемента.
     * @param name     имя элемента.
     * @param listener обработчик событий.
     */
    public void insert(int index, String name, AnimationListener listener) {
        if (index < 0) throw new IndexOutOfBoundsException("index < 0");
        if (index >= this.queue.size()) throw new IndexOutOfBoundsException("index >= queue.size");
        if (name == null) throw new NullPointerException("name == null");
        if (listener == null) throw new NullPointerException("listener == null");

        // Создаем новую очередь анимаций.
        Element queue = new ListenerElement(name, new AnimationOptions(listener));
        // Добавляем ее в конец списка.
        this.queue.add(index, queue);
    }

    /**
     * Удаляет элемент под индексом из очереди анимации. При этом анимация не
     * будет закончена.
     *
     * @param index индекс.
     */
    public void remove(int index) {
        this.remove(index, false);
    }

    /**
     * Удаляет элемент под индексом из очереди анимации.
     *
     * @param index   индекс.
     * @param gotoEnd флаг, определяющий, заканчивать ли анимацию удаляемого
     *                элемента.
     */
    public void remove(int index, boolean gotoEnd) {
        if (index < 0) throw new IndexOutOfBoundsException("index < 0");
        if (index >= this.queue.size()) throw new IndexOutOfBoundsException("index >= queue.size");

        // Находим индекс активного элемента.
        int currentIndex = this.queue.currentIndex;
        // Удаляем и получаем элемент.
        Element removed = this.queue.remove(index);
        // Получаем параметры удаляемого элемента.
        AnimationParameters rparameters = removed.parameters;
        // Запускаем событие останоки анимации.
        rparameters.fireEvent(AnimationEvent.Type.STOP, this);
        // Если нужно закончить анимацию элемента.
        if (gotoEnd) {
            rparameters.end();
            removed.plugins.update(rparameters);
        }
        //
        removed.plugins.end(rparameters);

        float rduration = this.parameters.getTime(rparameters.getTotalDuration(), rparameters);
        // Обновляем пройденное время и продолжительность анимации очереди.
        if (currentIndex >= index) {
            this.parameters.elapsedTime -= rduration;
        }
        this.parameters.duration -= rduration;

        // Если удаляем активный элемент,
        if (currentIndex == index) {
            // то переходим к следующему.
            //this.next();
        }
    }

    /**
     * Удаляет элемент под именем из очереди анимации. При этом анимация не
     * будет закончена.
     *
     * @param name имя элемента.
     */
    public void remove(String name) {
        this.remove(name, false);
    }

    /**
     * Удаляет элемент под именем из очереди анимации.
     *
     * @param name    имя элемента.
     * @param gotoEnd флаг, определяющий, заканчивать ли анимацию удаляемого
     *                элемента.
     */
    public void remove(String name, boolean gotoEnd) {
        if (name == null) throw new NullPointerException("name == null");

        // Находим индекс удаляемого элемента.
        int index = this.queue.indexOf(name);
        // Если такого элемента нет в очереди, то выходим.
        if (index == -1) return;
        // Находим индекс активного элемента.
        int currentIndex = this.queue.currentIndex;
        // Удаляем и получаем элемент.
        Element removed = this.queue.remove(name);
        // Получаем параметры удаляемого элемента.
        AnimationParameters rparameters = removed.parameters;
        // Запускаем событие останоки анимации.
        rparameters.fireEvent(AnimationEvent.Type.STOP, this);
        // Если нужно закончить анимацию элемента.
        if (gotoEnd) {
            rparameters.end();
            removed.plugins.update(rparameters);
        }
        //
        removed.plugins.end(rparameters);

        float rduration = this.parameters.getTime(rparameters.getTotalDuration(), rparameters);
        // Обновляем пройденное время и продолжительность анимации очереди.
        if (currentIndex >= index) {
            this.parameters.elapsedTime -= rduration;
        }
        this.parameters.duration -= rduration;

        // Если удаляем активный элемент,
        if (currentIndex == index) {
            // то переходим к следующему.
            //this.next();
        }
    }

    /**
     * Запускает анимацию очереди, начиная с элемента под заданным индексом.
     *
     * @param index индекс.
     */
    public void start(int index) {
        if (index < 0) throw new IndexOutOfBoundsException("index < 0");
        if (index >= this.queue.size()) throw new IndexOutOfBoundsException("index >= queue.size");

        // Если анимацию нужно удалить, то выходим.
        if (this.state == State.REMOVE) return;
        // Если анимация уже запущена или приостановлена.
        if (this.state != State.STOP) {
            this.stop(true, false);
        }

        // Находим элемент.
        Element started = this.queue.get(index);
        // Получаем параметры элемента.
        AnimationParameters sparameters = started.parameters;
        //
        sparameters.begin();
//        // Если его нет, то останавливаем анимацию.
//        if(started == null) {
//            // Устанавливаем состояние.
//            this.state = State.STOP;
//            // Выходим.
//            return;
//        }
        // Устанавливаем состояние.
        this.state = State.START;
        // Устанавливаем индекс активного элемента.
        this.queue.currentIndex = index;
        // Запускаем событие.
        sparameters.fireEvent(AnimationEvent.Type.START, this);
        //
        started.plugins.begin(started.parameters);

        // Обновляем пройденное время анимации очереди.
        this.parameters.begin();
        this.parameters.elapsedTime = 0.0f;
        for (int i = 0; i < index; i++) {
            Element element = this.queue.get(i);
            AnimationParameters eparameters = element.parameters;
            this.parameters.elapsedTime += this.parameters.getTime(eparameters.getTotalDuration(), eparameters);
        }
    }

    /**
     * Запускает анимацию очереди, начиная с элемента под заданным именем.
     *
     * @param name имя элемента.
     */
    public void start(String name) {
        if (name == null) throw new NullPointerException("name == null");

        // Если анимацию нужно удалить, то выходим.
        if (this.state == State.REMOVE) return;
        // Если анимация уже запущена или приостановлена.
        if (this.state != State.STOP) {
            this.stop(true, false);
        }

        // Находим элемент.
        Element started = this.queue.get(name);
        // Получаем параметры элемента.
        AnimationParameters sparameters = started.parameters;
        // Получаем индекс элемента в очереди.
        int index = this.queue.indexOf(started);
//        // Если его нет, то останавливаем анимацию.
//        if(started == null) {
//            // Устанавливаем состояние.
//            this.state = State.STOP;
//            // Выходим.
//            return;
//        }
        // Устанавливаем состояние.
        this.state = State.START;
        // Устанавливаем индекс активного элемента.
        this.queue.currentIndex = index;
        // Запускаем событие.
        sparameters.fireEvent(AnimationEvent.Type.START, this);
        //
        started.plugins.begin(started.parameters);

        // Обновляем пройденное время анимации очереди.
        this.parameters.begin();
        this.parameters.elapsedTime = 0.0f;
        for (int i = 0; i < index; i++) {
            Element element = this.queue.get(i);
            AnimationParameters eparameters = element.parameters;
            this.parameters.elapsedTime += this.parameters.getTime(eparameters.getTotalDuration(), eparameters);
        }
    }

    @Override
    public void start() {
        // Если анимация уже запущена, то выходим.
        if (this.state == State.START) return;
        // Если анимацию нужно удалить, то выходим.
        if (this.state == State.REMOVE) return;

        // Если очередь пуста, то останавливаем анимацию и выходим.
        if (this.queue.size() == 0) {
            this.state = State.STOP;
            return;
        }

        // Устанавливаем состояние.
        this.state = State.START;

        // Находим активный элемент.
        Element current = this.queue.current();
        // Получаем параметры элемента.
        AnimationParameters сparameters = current.parameters;
        // Запускаем событие.
        сparameters.fireEvent(AnimationEvent.Type.START, this);
        //
        current.plugins.begin(current.parameters);

        this.parameters.elapsedTime = 0.0f;
        for (int i = 0; i < this.queue.currentIndex; i++) {
            Element element = this.queue.get(i);
            AnimationParameters eparameters = element.parameters;
            this.parameters.elapsedTime += this.parameters.getTime(eparameters.getTotalDuration(), eparameters);
        }
    }

    @Override
    public void repeat() {
        // Если анимация не запущена, то выходим.
        if (this.state != State.START) return;
        // Если анимацию нужно удалить, то выходим.
        if (this.state == State.REMOVE) return;

        // Находим активный элемент.
        Element current = this.queue.current();
        // Получаем параметры элемента.
        AnimationParameters parameters = current.parameters;
        //
        parameters.repeat();
        // Запускаем событие.
        parameters.fireEvent(AnimationEvent.Type.REPEAT, this);
    }

    @Override
    public boolean step(float elapsedTime) {
        // Если анимацию нужно удалить.
        if (this.state == State.REMOVE) return true;
        // Если анимация остановлена.
        if (this.state == State.STOP) return false;
        // Если на паузе.
        if (this.state == State.PAUSE) return false;

        // Если пройденное время некорректно, то выходим.
        //if(elapsedTime <= 0.0f) return false;
        //if(elapsedTime == 0.0f) return false;

        // Находим активный элемент.
        Element current = this.queue.current();
        // Получаем параметры элемента.
        AnimationParameters cparameters = current.parameters;
        // Обновляем время в параметрах.
        if (!cparameters.update(cparameters.getTimeFromTimeMode(elapsedTime))) return false;
        // Запускаем событие кадра анимации.
        cparameters.fireEvent(AnimationEvent.Type.STEP, this);
        // Обновляем плагины.
        current.plugins.update(cparameters);

        // Обновляем пройденное время анимации очереди.
        this.parameters.elapsedTime = this.parameters.getTime(cparameters.getTotalElapsedTime(), cparameters);
        for (int i = 0; i < this.queue.currentIndex; i++) {
            Element element = this.queue.get(i);
            AnimationParameters eparameters = element.parameters;
            this.parameters.elapsedTime += this.parameters.getTime(eparameters.getTotalDuration(), eparameters);
        }

        // Если анимация закончилась.
        if (cparameters.isEnded()) this.next();
        //
        return false;
    }

    /**
     * Метод перехода на следующий цикл повторений анимации или на следующий
     * элемент очереди.
     */
    private void next() {
        this.repeat();

        // Находим активный элемент.
        Element current = this.queue.current();
        // Получаем параметры элемента.
        AnimationParameters cparameters = current.parameters;

        // Если нет возможности повторить анимацию.
        if (!cparameters.canRepeat()) {
            // Запускаем событие останоки анимации.
            cparameters.fireEvent(AnimationEvent.Type.STOP, this);
            //
            current.plugins.end(cparameters);

            // Если включено анимирование по очереди.
            if (!this.one) {
                // Если есть возможность перехода следующий элемент.
                if (this.queue.canNext()) {
                    // Переходим на следующий элемент.
                    current = this.queue.next();
                    cparameters = current.parameters;
                    //
                    current.plugins.begin(cparameters);
                } else {
                    //
                    this.parameters.repeat();
                    // Если есть возможность повторить анимацию.
                    if (this.parameters.canRepeat()) {
                        // Запускаем событие.
                        this.parameters.fireEvent(AnimationEvent.Type.REPEAT, this);
                        // Сбрасываем параметры очереди.
                        this.queue.reset();
                    } else {
                        // Устанавливаем состояние.
                        this.state = State.STOP;
                    }
                }
            } else {
                // Устанавливаем состояние.
                this.state = State.STOP;
            }
        }
    }

    @Override
    public void pause() {
        // Если анимация уже на паузе, то выходим.
        if (this.state == State.PAUSE) return;
        // Если анимацию нужно удалить, то выходим.
        if (this.state == State.REMOVE) return;

        // Устанавливаем состояние.
        this.state = State.PAUSE;

        // Находим активный элемент.
        Element current = this.queue.current();
        // Получаем параметры элемента.
        AnimationParameters cparameters = current.parameters;
        // Запускаем событие.
        cparameters.fireEvent(AnimationEvent.Type.PAUSE, this);
    }

    @Override
    public void stop() {
        this.stop(false, false);
    }

    @Override
    public void stop(boolean gotoEnd) {
        this.stop(gotoEnd, false);
    }

    /**
     * Останавливает анимацию.
     *
     * @param clear   флаг очистки всей очереди.
     * @param gotoEnd флаг установли конечных значений всем свойствам первого
     *                элемента очереди.
     */
    public void stop(boolean gotoEnd, boolean clear) {
        // Если анимацию нужно удалить, то выходим.
        if (this.state == State.REMOVE) return;
        // Если анимация уже остановлена.
        if (this.state == State.STOP) {
            // Если нужно очистить очередь.
            if (clear) {
                // Сбрасываем параметры анимации.
                this.parameters.begin();
                this.parameters.duration = 0.0f;
                // Очищаем очередь.
                this.queue.clear();
            }
            // Выходим.
            return;
        }

        // Устанавливаем состояние.
        this.state = State.STOP;

        // Находим активный элемент.
        Element current = this.queue.current();
        // Получаем параметры элемента.
        AnimationParameters cparameters = current.parameters;
        // Запускаем событие останоки анимации.
        cparameters.fireEvent(AnimationEvent.Type.STOP, this);

        // Если нужно завершить анимацию активного элемента очереди.
        if (gotoEnd) {
            //
            cparameters.end();
            // Обновляем продолжительность анимации очереди.
            this.parameters.elapsedTime += this.parameters.getTime(cparameters.getTotalElapsedTime(), cparameters);
            //
            current.plugins.update(cparameters);

            // Если не нужно очищать очередь,
            if (!clear) {
                // то переходим к следующему элементу очереди.
                this.next();
                // Сбрасываем параметры анимации.
                this.parameters.begin();
                this.parameters.duration = 0.0f;
                // Выходим.
                return;
            }
        }

        //
        current.plugins.end(cparameters);

        // Если нужно очистить очередь.
        if (clear) {
            // Сбрасываем параметры анимации.
            this.parameters.begin();
            this.parameters.duration = 0.0f;
            // Очищаем очередь.
            this.queue.clear();
        }
    }

    @Override
    public void restart() {
        super.restart();
        // Сбрасываем параметры очереди.
        this.queue.reset();
        // Сбрасываем параметры анимации.
        this.parameters.begin();
        this.parameters.duration = 0.0f;
        // Проходим по элементам очереди.
        int qSize = this.queue.size();
        for (int i = 0; i < qSize; i++) {
            // Получаем элемент очереди.
            Element element = this.queue.get(i);
            // Сбрасываем параметры элемента очереди.
            element.parameters.begin();
        }
    }

    /**
     * Получает анимируемый объект.
     *
     * @return анимируемый объект.
     */
    @Override
    public Object getTarget() {
        return this.target;
    }

    /**
     * Устанавливает прогресс анимации всей очереди.
     *
     * @param position прогресс от 0 до 1.
     */
    @Override
    public void setPosition(float position) {
        super.setPosition(position);

        float d1 = this.getDuration();
        float d2 = 0.0f;
        int qSize = this.queue.size();
        for (int i = 0; i < qSize; i++) {
            Element element = this.queue.get(i);
            AnimationParameters eparameters = element.parameters;

            float d3 = eparameters.getTimeInMillisecond(eparameters.getTotalDuration());
            d2 += d3;

            if (d2 / d1 >= position) {
                this.queue.currentIndex = i;
                float d4 = d1 * position - d3;
                float d5 = eparameters.getTimeInMillisecond(eparameters.duration);
                eparameters.completedRepeat = (int) (d4 / d5);
                eparameters.elapsedTime = eparameters.getTimeFromTimeMode(d4 % d5);
                break;
            }
        }
    }

    /**
     * Очищает очередь анимации.
     */
    public void clear() {
        // Если анимацию нужно удалить, то выходим.
        if (this.state == State.REMOVE) return;
        // Устанавливаем состояние.
        this.state = State.STOP;
        // Сбрасываем параметры анимации.
        this.parameters.begin();
        this.parameters.duration = 0.0f;
        // Очищаем очередь.
        this.queue.clear();
    }

    /**
     * Создает и возвращает новый объект построителя очереди анимации.
     *
     * @return построитель анимации.
     */
    public static QueueAnimationBuilder builder() {
        return new QueueAnimationBuilder();
    }

    /**
     * Создает и возвращает новый объект построителя очереди анимации.
     *
     * @param target анимируемый объект.
     * @return построитель анимации.
     */
    public static QueueAnimationBuilder builder(Object target) {
        return new QueueAnimationBuilder(target);
    }

    /**
     * Класс элемента очереди.
     *
     * @author Ilyas74
     * @version 0.1.4
     */
    public static class Element {

        /**
         * Имя элемента.
         */
        protected String name = null;
        /**
         * Опции элемента.
         */
        protected AnimationParameters parameters = null;
        /**
         * Список анимируемых плагинов.
         */
        protected PluginList plugins = null;

        /**
         * Конструктор, создающий пустой элемент очереди анимации.
         */
        protected Element() {
        }

        /**
         * Конструктор, создающий элемент очереди анимации с заданными
         * параметрами.
         *
         * @param plugins    список плагинов.
         * @param parameters опции.
         */
        public Element(PluginList plugins, AnimationParameters parameters) {
            this.parameters = parameters;
            this.plugins = plugins;
            // Инициализируем свойства.
            this.plugins.initialize(this.parameters);
        }

        /**
         * Конструктор, создающий элемент очереди анимации с заданными
         * параметрами.
         *
         * @param name       имя.
         * @param plugins    список плагинов.
         * @param parameters опции.
         */
        public Element(String name, PluginList plugins,
                       AnimationParameters parameters) {
            this.name = name;
            this.parameters = parameters;
            this.plugins = plugins;
            // Инициализируем свойства.
            this.plugins.initialize(this.parameters);
        }

        /**
         * Получает имя элемента.
         *
         * @return имя элемента.
         */
        public String getName() {
            return this.name;
        }

        /**
         * Получает параметры элемента.
         *
         * @return параметры элемента.
         */
        public AnimationParameters getParameters() {
            return this.parameters;
        }

        /**
         * Получает список плагинов элемента.
         *
         * @return список плагинов элемента.
         */
        public PluginList getPlugins() {
            return this.plugins;
        }

    }

    /**
     * Класс элемента очереди, который задерживает анимацию.
     *
     * @author Ilyas74
     * @version 0.1.0
     */
    public static class DelayElement extends Element {

        /**
         * Конструктор, создающий пустой элемент очереди анимации.
         */
        public DelayElement() {
            this.plugins = new PluginList();
        }

        /**
         * Конструктор, создающий элемент очереди анимации с заданными
         * параметрами.
         *
         * @param parameters опции.
         */
        public DelayElement(AnimationParameters parameters) {
            this.parameters = parameters;
            this.plugins = new PluginList();
        }

        /**
         * Конструктор, создающий элемент очереди анимации с заданными
         * параметрами.
         *
         * @param name       имя элемента.
         * @param parameters опции.
         */
        public DelayElement(String name, AnimationParameters parameters) {
            this.name = name;
            this.parameters = parameters;
            this.plugins = new PluginList();
        }

    }

    /**
     * Класс элемента очереди, который содержит обработчик событий.
     *
     * @author Ilyas74
     * @version 0.1.0
     */
    public static class ListenerElement extends Element {

        /**
         * Конструктор, создающий пустой элемент очереди анимации.
         */
        public ListenerElement() {
            this.plugins = new PluginList();
        }

        /**
         * Конструктор, создающий элемент очереди анимации с заданными
         * параметрами.
         *
         * @param options опции.
         */
        public ListenerElement(AnimationOptions options) {
            options.duration = 0.0f;
            options.fps = 0;

            this.parameters = new AnimationParameters(null, options);
            this.plugins = new PluginList();
        }

        /**
         * Конструктор, создающий элемент очереди анимации с заданными
         * параметрами.
         *
         * @param name    имя элемента.
         * @param options опции.
         */
        public ListenerElement(String name, AnimationOptions options) {
            options.duration = 0.0f;
            options.fps = 0;

            this.name = name;
            this.parameters = new AnimationParameters(null, options);
            this.plugins = new PluginList();
        }

    }

    /**
     * Класс очереди анимации.
     *
     * @author Ilyas74
     * @version 1.1.0
     */
    protected static class Queue {

        /**
         * Список элементов.
         */
        List<Element> list = new ArrayList<>();
        /**
         * Таблица элементов.
         */
        Map<String, Element> map = new HashMap<>();
        /**
         * Индекс активного элемента очереди.
         */
        int currentIndex = 0;

        /**
         * Конструктор, создающий пустую очередь.
         */
        Queue() {
        }

        /**
         * Получает элемент под индексом.
         *
         * @param index индес.
         * @return элемент очереди.
         */
        Element get(int index) {
            return this.list.get(index);
        }

        /**
         * Получает элемент и заданным именем.
         *
         * @param name имя.
         * @return элемент очереди.
         */
        Element get(String name) {
            return this.map.get(name);
        }

        /**
         * Получает индекс элемента очереди.
         *
         * @param name имя элемента.
         * @return индекс элемента.
         */
        int indexOf(String name) {
            Element element = this.map.get(name);
            if (element == null) return -1;
            return this.list.indexOf(element);
        }

        /**
         * Получает индекс элемента очереди.
         *
         * @param element элемент.
         * @return индекс элемента.
         */
        int indexOf(Element element) {
            return this.list.indexOf(element);
        }

        /**
         * Добавляет элемент в очередь.
         *
         * @param element элемент.
         */
        void add(Element element) {
            if (element.name != null) {
                if (this.map.containsKey(element.name)) {
                    this.map.remove(element.name);
                    this.list.remove(element);
                }
            } else {
                element.name = String.valueOf(this.list.size());
            }

            this.list.add(element);
            this.map.put(element.name, element);
        }

        /**
         * Добавляет элемент в очередь.
         *
         * @param index   индекс элемента.
         * @param element элемент.
         */
        void add(int index, Element element) {
            if (element.name != null) {
                if (this.map.containsKey(element.name)) {
                    this.map.remove(element.name);
                    this.list.remove(element);
                }
            } else {
                element.name = String.valueOf(this.list.size());
            }

            this.list.add(index, element);
            this.map.put(element.name, element);
        }

        /**
         * Переходит к слкдующему элементу очереди.
         *
         * @return следующий элемент очереди.
         */
        Element next() {
            if (++this.currentIndex >= this.list.size())
                throw new IllegalStateException("currentSize + 1 >= list.size()");
            return this.list.get(this.currentIndex);
        }

        /**
         * Получает текущий активный элемент очереди.
         *
         * @return текущий элемент очереди.
         */
        Element current() {
            return this.list.get(this.currentIndex);
        }

        /**
         * Проверяет, возможен ли переход к следующему элементу очереди.
         *
         * @return {@code true}, если переход возможен.
         */
        boolean canNext() {
            return this.currentIndex + 1 < this.list.size();
        }

        /**
         * Удаляет активный элемент очереди.
         *
         * @return активный элемент очереди.
         */
        Element remove() {
            Element element = this.list.remove(this.currentIndex);
            if (element != null) this.map.remove(element.name);
            return element;
        }

        /**
         * Удаляет элемент очереди под индексом.
         *
         * @param index индекс.
         * @return удаленный элемент.
         */
        Element remove(int index) {
            Element element = this.list.remove(index);
            if (element != null) this.map.remove(element.name);
            //
            if (this.currentIndex > index) this.currentIndex--;
            return element;
        }

        /**
         * Удаляет элемент очереди под именем.
         *
         * @param name имя.
         * @return удаленный элемент.
         */
        Element remove(String name) {
            Element element = this.map.get(name);
            int index = this.list.indexOf(element);
            if (element != null) this.list.remove(index);
            //
            if (this.currentIndex > index) this.currentIndex--;
            return element;
        }

        /**
         * Сбрасывает текущий индекс.
         */
        void reset() {
            this.currentIndex = 0;
        }

        /**
         * Очищает очередь.
         */
        void clear() {
            this.currentIndex = 0;
            this.list.clear();
            this.map.clear();
        }

        /**
         * Получает размер списка очереди.
         *
         * @return количество элементов очереди.
         */
        int size() {
            return this.list.size();
        }

        /**
         * @return
         */
        float getDuration() {
            float duration = 0.0f;
            int size = this.list.size();
            for (int i = 0; i < size; i++) {
                Element element = this.list.get(i);
                AnimationParameters eparameters = element.parameters;
                duration += eparameters.getTimeInMillisecond(eparameters.getTotalDuration());
            }
            return duration;
        }

    }

    /**
     * Класс построителя очереди анимации.
     *
     * @author Ilyas74
     * @version 0.2.5
     */
    public static class QueueAnimationBuilder implements Builder<QueueAnimation> {

        /**
         * Анимируемый объект.
         */
        protected Object target = null;
        /**
         * Очередь.
         */
        protected Queue queue = null;
        /**
         * Опции анимации.
         */
        protected AnimationOptions options = null;
        /**  */
        protected boolean oneRun = false;

        /**
         * Конструктор, создающий построитель анимации.
         */
        protected QueueAnimationBuilder() {
            this.queue = new Queue();
            this.options = new AnimationOptions();
        }

        /**
         * Конструктор, создающий построитель анимации.
         *
         * @param target анимируемый объект.
         */
        protected QueueAnimationBuilder(Object target) {
            if (target == null) throw new NullPointerException("target == null");
            this.target = target;
            this.queue = new Queue();
            this.options = new AnimationOptions();
        }

        /**
         * Устанавливает анимируемый объект.
         *
         * @param target анимируемый объект.
         * @return данный построитель.
         */
        public QueueAnimationBuilder target(Object target) {
            if (target == null) throw new NullPointerException("target == null");

            this.target = target;
            return this;
        }

        /**
         * Изменяет состояние анимирования по очереди.
         *
         * @param flag флаг.
         * @return данный построитель.
         */
        public QueueAnimationBuilder oneRun(boolean flag) {
            this.oneRun = flag;
            return this;
        }

        /**
         * Создает и возвращает построитель элемента очереди анимации.
         *
         * @return построитель элемента очереди анимации.
         */
        public QueueElementBuilder element() {
            return new QueueElementBuilder(this);
        }

        /**
         * Создает и возвращает построитель элемента очереди анимации.
         *
         * @param index
         * @return построитель элемента очереди анимации.
         */
        public QueueElementBuilder element(int index) {
            return new QueueElementBuilder(this, index);
        }

        /**
         * Создает и возвращает построитель элемента очереди анимации.
         *
         * @param name имя элемента очереди.
         * @return построитель элемента очереди анимации.
         */
        public QueueElementBuilder element(String name) {
            return new QueueElementBuilder(this, name);
        }

        /**
         * Создает и возвращает построитель элемента очереди анимации.
         *
         * @param index
         * @param name  имя элемента очереди.
         * @return построитель элемента очереди анимации.
         */
        public QueueElementBuilder element(int index, String name) {
            return new QueueElementBuilder(this, index, name);
        }

        /**
         * Добавляет в очередь анимации задержку.
         *
         * @param duration продолжительность задержки.
         * @return данный построитель.
         */
        public QueueAnimationBuilder element(float duration) {
            this.queue.add(new DelayElement(new AnimationParameters(this.target, new AnimationOptions(duration))));
            return this;
        }

        /**
         * Добавляет в очередь анимации задержку.
         *
         * @param index
         * @param duration продолжительность задержки.
         * @return данный построитель.
         */
        public QueueAnimationBuilder element(int index, float duration) {
            if (index < 0 || index > this.queue.size()) {
                this.queue.add(new DelayElement(new AnimationParameters(this.target, new AnimationOptions(duration))));
            } else {
                this.queue.add(index, new DelayElement(new AnimationParameters(this.target, new AnimationOptions(duration))));
            }
            return this;
        }

        /**
         * Добавляет в очередь анимации задержку.
         *
         * @param name
         * @param duration продолжительность задержки.
         * @return данный построитель.
         */
        public QueueAnimationBuilder element(String name, float duration) {
            this.queue.add(new DelayElement(name, new AnimationParameters(this.target, new AnimationOptions(duration))));
            return this;
        }

        /**
         * Добавляет в очередь анимации задержку.
         *
         * @param index
         * @param name
         * @param duration продолжительность задержки.
         * @return данный построитель.
         */
        public QueueAnimationBuilder element(int index, String name, float duration) {
            if (index < 0 || index > this.queue.size()) {
                this.queue.add(new DelayElement(name, new AnimationParameters(this.target, new AnimationOptions(duration))));
            } else {
                this.queue.add(index, new DelayElement(name, new AnimationParameters(this.target, new AnimationOptions(duration))));
            }
            return this;
        }

        /**
         * Добавляет в очередь анимации задержку.
         *
         * @param duration продолжительность задержки.
         * @param listener обработчик событий.
         * @return данный построитель.
         */
        public QueueAnimationBuilder element(float duration, AnimationListener listener) {
            this.queue.add(new DelayElement(new AnimationParameters(this.target, new AnimationOptions(duration, listener == null ? new AnimationAdapter() : listener))));
            return this;
        }

        /**
         * Добавляет в очередь анимации задержку.
         *
         * @param index
         * @param duration продолжительность задержки.
         * @param listener
         * @return данный построитель.
         */
        public QueueAnimationBuilder element(int index, float duration, AnimationListener listener) {
            if (index < 0 || index > this.queue.size()) {
                this.queue.add(new DelayElement(new AnimationParameters(this.target, new AnimationOptions(duration, listener == null ? new AnimationAdapter() : listener))));
            } else {
                this.queue.add(index, new DelayElement(new AnimationParameters(this.target, new AnimationOptions(duration, listener == null ? new AnimationAdapter() : listener))));
            }
            return this;
        }

        /**
         * Добавляет в очередь анимации задержку.
         *
         * @param name
         * @param duration продолжительность задержки.
         * @param listener
         * @return данный построитель.
         */
        public QueueAnimationBuilder element(String name, float duration, AnimationListener listener) {
            this.queue.add(new DelayElement(name, new AnimationParameters(this.target, new AnimationOptions(duration, listener == null ? new AnimationAdapter() : listener))));
            return this;
        }

        /**
         * Добавляет в очередь анимации задержку.
         *
         * @param index
         * @param name
         * @param duration продолжительность задержки.
         * @param listener
         * @return данный построитель.
         */
        public QueueAnimationBuilder element(int index, String name, float duration, AnimationListener listener) {
            if (index < 0 || index > this.queue.size()) {
                this.queue.add(new DelayElement(name, new AnimationParameters(this.target, new AnimationOptions(duration, listener == null ? new AnimationAdapter() : listener))));
            } else {
                this.queue.add(index, new DelayElement(name, new AnimationParameters(this.target, new AnimationOptions(duration, listener == null ? new AnimationAdapter() : listener))));
            }
            return this;
        }

        /**
         * Добавляет в очередь обработчик событий.
         *
         * @param listener обработчик событий.
         * @return данный построитель.
         */
        public QueueAnimationBuilder element(AnimationListener listener) {
            this.queue.add(new ListenerElement(new AnimationOptions(listener == null ? new AnimationAdapter() : listener)));
            return this;
        }

        /**
         * Добавляет в очередь обработчик событий.
         *
         * @param index
         * @param listener обработчик событий.
         * @return данный построитель.
         */
        public QueueAnimationBuilder element(int index, AnimationListener listener) {
            if (index < 0 || index > this.queue.size()) {
                this.queue.add(new ListenerElement(new AnimationOptions(listener == null ? new AnimationAdapter() : listener)));
            } else {
                this.queue.add(index, new ListenerElement(new AnimationOptions(listener == null ? new AnimationAdapter() : listener)));
            }
            return this;
        }

        /**
         * Добавляет в очередь обработчик событий.
         *
         * @param name
         * @param listener обработчик событий.
         * @return данный построитель.
         */
        public QueueAnimationBuilder element(String name, AnimationListener listener) {
            this.queue.add(new ListenerElement(name, new AnimationOptions(listener == null ? new AnimationAdapter() : listener)));
            return this;
        }

        /**
         * Добавляет в очередь обработчик событий.
         *
         * @param index
         * @param name
         * @param listener обработчик событий.
         * @return данный построитель.
         */
        public QueueAnimationBuilder element(int index, String name, AnimationListener listener) {
            if (index < 0 || index > this.queue.size()) {
                this.queue.add(new ListenerElement(name, new AnimationOptions(listener == null ? new AnimationAdapter() : listener)));
            } else {
                this.queue.add(index, new ListenerElement(name, new AnimationOptions(listener == null ? new AnimationAdapter() : listener)));
            }
            return this;
        }

        /**
         * Устанавливает обработчик событий анимации.
         *
         * @param listener обработчик событий анимации.
         * @return данный построитель.
         */
        public QueueAnimationBuilder listener(AnimationListener listener) {
            this.options.setListener(listener);
            return this;
        }

        /**
         * Устанавливает продолжительность задержки анимации.
         *
         * @param delay продолжительность задержки анимации.
         * @return данный построитель.
         */
        public QueueAnimationBuilder delay(float delay) {
            this.options.setDelay(delay);
            return this;
        }

        /**
         * Устанавливает количество повторов анимации.
         *
         * @param repeat количество повторов анимации.
         * @return данный построитель.
         */
        public QueueAnimationBuilder repeat(int repeat) {
            this.options.setRepeat(repeat);
            return this;
        }

        /**
         * Устанавливает продолжительность задержки между повторами анимации.
         *
         * @param repeatDelay продолжительность задержки между повторами анимации.
         * @return данный построитель.
         */
        public QueueAnimationBuilder repeatDelay(float repeatDelay) {
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
        public QueueAnimationBuilder timemode(int timemode) {
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
        public QueueAnimationBuilder playmode(int playmode) {
            this.options.setPlayMode(playmode);
            return this;
        }

        /**
         * Устанавливает опции анимации.
         *
         * @param options опции.
         * @return данный построитель.
         */
        public QueueAnimationBuilder options(AnimationOptions options) {
            if (options == null) return this;
            this.options.copy(options);
            return this;
        }

        /**
         * Сбрасывает данный построитель.
         *
         * @return данный построитель.
         */
        public QueueAnimationBuilder reset() {
            this.target = null;
            this.queue = new Queue();
            this.options = new AnimationOptions();
            return this;
        }

        @Override
        public QueueAnimation build() {
            if (this.target == null) {
                QueueAnimation animation = new QueueAnimation(this.queue, this.options, this.oneRun);
                this.reset();
                return animation;
            } else {
                QueueAnimation animation = new QueueAnimation(this.target, this.queue, this.options, this.oneRun);
                this.reset();
                return animation;
            }
        }

    }

    /**
     * Класс построителя элемента очереди анимации.
     *
     * @author Ilyas74
     * @version 0.3.3
     */
    public static class QueueElementBuilder {

        /**
         * Построитель очереди анимации.
         */
        protected QueueAnimationBuilder animationBuiler = null;
        /**
         * Имя элемента очереди.
         */
        protected String name = null;
        /**
         * Индекс элемента в очереди.
         */
        protected int index = -1;
        /**
         * Анимируемые плагины.
         */
        protected PluginList plugins = null;
        /**
         * Опции анимации.
         */
        protected AnimationOptions options = null;

        /**
         * Конструктор, создающий объект построителя элемента очереди анимации.
         *
         * @param queueAnimationBuiler построитель очереди анимации.
         */
        protected QueueElementBuilder(QueueAnimationBuilder queueAnimationBuiler) {
            this.animationBuiler = queueAnimationBuiler;
            this.plugins = new PluginList();
            this.options = new AnimationOptions();
        }

        /**
         * Конструктор, создающий объект построителя элемента очереди анимации.
         *
         * @param queueAnimationBuiler построитель очереди анимации.
         * @param index
         */
        protected QueueElementBuilder(QueueAnimationBuilder queueAnimationBuiler, int index) {
            this.animationBuiler = queueAnimationBuiler;
            this.index = index;
            this.plugins = new PluginList();
            this.options = new AnimationOptions();
        }

        /**
         * Конструктор, создающий объект построителя элемента очереди анимации.
         *
         * @param queueAnimationBuiler построитель очереди анимации.
         * @param name                 имя элемента.
         */
        protected QueueElementBuilder(QueueAnimationBuilder queueAnimationBuiler, String name) {
            this.animationBuiler = queueAnimationBuiler;
            this.name = name;
            this.plugins = new PluginList();
            this.options = new AnimationOptions();
        }

        /**
         * Конструктор, создающий объект построителя элемента очереди анимации.
         *
         * @param queueAnimationBuiler построитель очереди анимации.
         * @param name                 имя элемента.
         * @param index
         */
        protected QueueElementBuilder(QueueAnimationBuilder queueAnimationBuiler, int index, String name) {
            this.animationBuiler = queueAnimationBuiler;
            this.name = name;
            this.index = -1;
            this.plugins = new PluginList();
            this.options = new AnimationOptions();
        }

        /**
         * Устанавливает имя элементу.
         *
         * @param name имя.
         * @return данный построитель.
         */
        public QueueElementBuilder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * Устанавливает индекс элементу в очереди.
         *
         * @param index индекс элемента.
         * @return данный построитель.
         */
        public QueueElementBuilder index(int index) {
            this.index = index;
            return this;
        }

        /**
         * Добавляет анимируемый плагин в анимацию.
         *
         * @param plugin свойство.
         * @return данный построитель.
         */
        public QueueElementBuilder plugin(AnimationPlugin plugin) {
            if (plugin != null) {
                this.plugins.add(plugin);
            }
            return this;
        }

        /**
         * Добавляет анимируемые плагины в анимацию.
         *
         * @param plugins массив плагинов.
         * @return данный построитель.
         */
        public QueueElementBuilder plugin(AnimationPlugin... plugins) {
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
         * @param plugins коллекция плагинов.
         * @return данный построитель.
         */
        public QueueElementBuilder plugin(Collection<AnimationPlugin> plugins) {
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
        public QueueElementBuilder duration(float duration) {
            this.options.setDuration(duration);
            return this;
        }

        /**
         * Устанавливает продолжительность задержки анимации.
         *
         * @param delay продолжительность задержки анимации.
         * @return данный построитель.
         */
        public QueueElementBuilder delay(float delay) {
            this.options.setDelay(delay);
            return this;
        }

        /**
         * Устанавливает частоту обновления кадров анимации.
         *
         * @param fps частота обновления кадров анимации.
         * @return данный построитель.
         */
        public QueueElementBuilder fps(int fps) {
            this.options.setFps(fps);
            return this;
        }

        /**
         * Устанавливает масштабирование времени анимации.
         *
         * @param timescale масштабирование времени.
         * @return данный построитель.
         */
        public QueueElementBuilder timescale(float timescale) {
            this.options.setTimeScale(timescale);
            return this;
        }

        /**
         * Устанавливает эффект аниамции.
         *
         * @param easing эффект аниамции.
         * @return данный построитель.
         */
        public QueueElementBuilder easing(Easing easing) {
            this.options.setEasing(easing);
            return this;
        }

        /**
         * Устанавливает обработчик событий анимации.
         *
         * @param listener обработчик событий анимации.
         * @return данный построитель.
         */
        public QueueElementBuilder listener(AnimationListener listener) {
            this.options.setListener(listener);
            return this;
        }

        /**
         * Устанавливает количество повторов анимации.
         *
         * @param repeat количество повторов анимации.
         * @return данный построитель.
         */
        public QueueElementBuilder repeat(int repeat) {
            this.options.setRepeat(repeat);
            return this;
        }

        /**
         * Устанавливает продолжительность задержки между повторами анимации.
         *
         * @param repeatDelay продолжительность задержки между повторами анимации.
         * @return данный построитель.
         */
        public QueueElementBuilder repeatDelay(float repeatDelay) {
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
        public QueueElementBuilder timemode(int timemode) {
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
        public QueueElementBuilder playmode(int playmode) {
            this.options.setPlayMode(playmode);
            return this;
        }

        /**
         * Устанавливает опции элементу очереди.
         *
         * @param options опции.
         * @return данный построитель.
         */
        public QueueElementBuilder options(AnimationOptions options) {
            if (options == null) return this;
            this.options.copy(options);
            return this;
        }

        /**
         * Сбрасывает данный построитель.
         *
         * @return данный построитель.
         */
        public QueueElementBuilder reset() {
            this.plugins = new PluginList();
            this.options = new AnimationOptions();
            return this;
        }

        /**
         * Создает элемент очереди анимации, добавляет его в очередь и
         * возвращает построитель очереди анимации.
         *
         * @return построитель очереди анимации.
         */
        public QueueAnimationBuilder build() {
            if (this.animationBuiler.target == null) {
                if (this.index < 0 || this.index > this.animationBuiler.queue.size()) {
                    this.animationBuiler.queue.add(new Element(this.name, this.plugins,
                            new AnimationParameters(this.options)));
                } else {
                    this.animationBuiler.queue.add(this.index, new Element(this.name, this.plugins,
                            new AnimationParameters(this.options)));
                }
            } else {
                if (this.index < 0 || this.index > this.animationBuiler.queue.size()) {
                    this.animationBuiler.queue.add(new Element(this.name, this.plugins,
                            new AnimationParameters(this.animationBuiler.target, this.options)));
                } else {
                    this.animationBuiler.queue.add(this.index, new Element(this.name, this.plugins,
                            new AnimationParameters(this.animationBuiler.target, this.options)));
                }
            }
            return this.animationBuiler;
        }

    }

}
