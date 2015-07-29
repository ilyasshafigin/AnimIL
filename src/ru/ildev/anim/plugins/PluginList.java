/*
 *
 */
package ru.ildev.anim.plugins;

import ru.ildev.anim.core.ControllableAnimation;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Класс списка плагинов.
 *
 * @author Shafigin Ilyas (Шафигин Ильяс) <Ilyas74>
 * @version 1.0.1
 */
@SuppressWarnings("serial")
public class PluginList extends ArrayList<AnimationPlugin> {

    /**
     * Конструктор, создающий пустой список плагинов.
     */
    public PluginList() {
    }

    /**
     * Конструктор, создающий список плагинов с добавленным в него одним
     * плагином.
     *
     * @param plugin плагин.
     */
    public PluginList(AnimationPlugin plugin) {
        this.add(plugin);
    }

    /**
     * Конструктор, создающий список плагинов с добавленными в него плагтнами.
     *
     * @param plugins массив плагинов.
     */
    public PluginList(AnimationPlugin... plugins) {
        this.addAll(Arrays.<AnimationPlugin>asList(plugins));
    }

    /**
     * Инициализирует добавленные плагины.
     *
     * @param animation анимация.
     */
    public void initialize(ControllableAnimation animation) {
        int size = this.size();
        // Если список пуст, то выходим.
        if (size == 0) return;
        // Проходим по плагинам и инициализируем их
        this.forEach(plugin -> plugin.initialize(animation));
    }

    /**
     * @param animation анимация.
     */
    public void begin(ControllableAnimation animation) {
        int size = this.size();
        // Если список пуст, то выходим.
        if (size == 0) return;
        // Проходим по плагинам.
        this.forEach(plugin -> plugin.begin(animation));
    }

    /**
     * @param animation анимация.
     */
    public void end(ControllableAnimation animation) {
        int size = this.size();
        // Если список пуст, то выходим.
        if (size == 0) return;
        // Проходим по плагинам.
        this.forEach(plugin -> plugin.end(animation));
    }

    /**
     * Обновляет добавленные плагины..
     *
     * @param animation анимация.
     */
    public void update(ControllableAnimation animation) {
        int size = this.size();
        // Если список пуст, то выходим.
        if (size == 0) return;
        // Проходим по плагинам.
        this.forEach(plugin -> {
            // Если плагин был инициализирован,
            if (plugin.hasState(AnimationPlugin.INITIALIZE)) {
                // то обновляем его.
                plugin.update(animation);
            }
        });
    }

}
