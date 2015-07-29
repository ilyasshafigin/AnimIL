/*
 *
 */
package ru.ildev.anim.plugins.property;

import ru.ildev.anim.core.ControllableAnimation;
import ru.ildev.anim.easings.Easing;
import ru.ildev.math.MoreMath;

/**
 * Абстрактный класс свойства, содержащего ключевые кадры.
 *
 * @param <T> тип свойства.
 * @author Shafigin Ilyas (Шафигин Ильяс) <Ilyas74>
 * @version 1.1.2
 */
public class AbstractKeyFrameProperty<T> extends AbstractProperty<T> implements KeyFrameProperty<T> {

    /**
     * Ключевые кадры.
     */
    protected KeyFrame<T>[] keys;
    /**  */
    protected Property<T> property;

    /**
     * Стандартный конструктор.
     */
    public AbstractKeyFrameProperty() {
    }

    /**
     * Конструктор, устанавливающий имя свойству.
     *
     * @param property свойство.
     */
    public AbstractKeyFrameProperty(Property<T> property) {
        if (property == null) throw new NullPointerException("property == null");
        this.name = property.getName();
        this.property = property;
        this.easing = property.getEasing();
        this.evaluator = property.getEvaluator();
    }

    /**
     * Конструктор, устанавливающий имя свойства и конечное значение.
     *
     * @param keys     ключевые кадры.
     * @param property свойство.
     */
    public AbstractKeyFrameProperty(KeyFrame<T>[] keys, Property<T> property) {
        this(property);
        if (keys == null) throw new NullPointerException("keys == null");
        if (keys.length == 0) throw new IllegalArgumentException(
                "keys.length == 0");
        this.keys = keys;
    }

    /**
     * Получает ключевые кадры.
     *
     * @return ключевые кадры.
     */
    public KeyFrame<T>[] getKeys() {
        return this.keys;
    }

    @Override
    public void initialize(ControllableAnimation animation) {
        super.initialize(animation);
        this.property.initialize(animation);
        this.setState(SETUP, true);
    }

    @Override
    public void begin(ControllableAnimation animation) {
        this.property.begin(animation);
        super.begin(animation);
    }

    @Override
    public void end(ControllableAnimation animation) {
        this.property.end(animation);
        super.end(animation);
    }

    @Override
    public void update(ControllableAnimation animation) {
        if (!this.property.hasState(SETUP)) return;

        float duration = animation.getDuration();
        float time = animation.getElapsedTime();

        int index = 0;
        for (int i = 0; i < this.keys.length; i++)
            if (time >= this.keys[i].time * duration) index = i;

        KeyFrame<T> key1 = this.keys[index];
        KeyFrame<T> key2 = this.keys[index > this.keys.length - 2 ? index
                : index + 1];

        Easing easing = key1.easing != null ? key1.easing : this.easing;

        float diff = (key2.time - key1.time) * duration;
        float now = time - key1.time * duration;
        float progress = MoreMath.clamp(now / diff, 0.0f, 1.0f);
        float position = MoreMath.clamp(
                easing.ease(progress, now, 0.0f, 1.0f, diff), 0.0f, 1.0f);

        this.begin = key1.value;
        this.end = key2.value;
        this.set(this.evaluator.evaluate(position, this.begin, this.end));
    }

    @Override
    public T get() {
        return this.property.get();
    }

    @Override
    public void set(T value) {
        this.property.set(value);
    }

}
