/*
 *
 */
package ru.ildev.anim.skeletal;

import ru.ildev.anim.core.AnimationOptions;
import ru.ildev.anim.core.ControllableAnimation;
import ru.ildev.anim.easings.Easing;
import ru.ildev.math.MoreMath;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс анимации скелета.
 *
 * @author Shafigin Ilyas (Шафигин Ильяс) <Ilyas74>
 * @version 0.1.7
 */
public class SkeletonAnimation extends ControllableAnimation {

    /**
     * Название анимации.
     */
    protected String name = null;
    /**
     * Скелет.
     */
    protected Skeleton skeleton = null;
    /**
     * Список ключевых кадров.
     */
    protected List<SkeletonAnimationKeyFrame> keyFrames = null;

    /**
     * Конструктор, создающий пустой объект анимации.
     *
     * @param name название анимации.
     */
    public SkeletonAnimation(String name) {
        super();

        if (name == null) throw new NullPointerException("name == null");

        this.name = name;
        this.keyFrames = new ArrayList<>();
    }

    /**
     * Конструктор, создающий объект анимации с заданными кадрами.
     *
     * @param name      название анимации.
     * @param keyFrames спиок кадров скелетной анимации.
     */
    public SkeletonAnimation(String name, List<SkeletonAnimationKeyFrame> keyFrames) {
        super();

        if (name == null) throw new NullPointerException("name == null");
        if (keyFrames == null) throw new NullPointerException("keyFrames == null");

        this.name = name;
        this.keyFrames = keyFrames;
    }

    /**
     * Конструктор, создающий пустой объект анимации с заданными опциями.
     *
     * @param name    название анимации.
     * @param options опции анимации.
     */
    public SkeletonAnimation(String name, AnimationOptions options) {
        super();

        if (name == null) throw new NullPointerException("name == null");
        if (options == null) throw new NullPointerException("options == null");

        this.name = name;
        this.keyFrames = new ArrayList<>();
        this.copy(null, options);
    }

    /**
     * Конструктор, создающий пустой объект анимации с заданными кадрами и
     * опциями.
     *
     * @param name      название анимации.
     * @param keyFrames спиок кадров скелетной анимации.
     * @param options
     */
    public SkeletonAnimation(String name, List<SkeletonAnimationKeyFrame> keyFrames,
                             AnimationOptions options) {
        super();

        if (name == null) throw new NullPointerException("name == null");
        if (keyFrames == null) throw new NullPointerException("keyFrames == null");
        if (options == null) throw new NullPointerException("options == null");

        this.name = name;
        this.keyFrames = keyFrames;
        this.copy(null, options);
    }

    /**
     * Получает название анимации.
     *
     * @return название анимации.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Устаналивает имя анимации.
     *
     * @param name имя.
     */
    public void setName(String name) {
        if (name == null) throw new NullPointerException("name == null");

        this.name = name;
    }

    /**
     * Получает список ключевых кадров.
     *
     * @return список ключевых кадров.
     */
    public List<SkeletonAnimationKeyFrame> getKeyFrames() {
        return this.keyFrames;
    }

    /**
     * Получает количество ключевых кадров в анимации.
     *
     * @return количество ключевых кадров.
     */
    public int getKeyFramesCount() {
        return this.keyFrames.size();
    }

    /**
     * Получает ключевой кадр анимации под индексом.
     *
     * @param index индекс.
     * @return ключевой кадр.
     */
    public SkeletonAnimationKeyFrame getKeyFrame(int index) {
        return this.keyFrames.get(index);
    }

    /**
     * Добавляет ключевой кадр в анимацию.
     *
     * @param keyFrame ключевой кадр.
     */
    public void addKeyFrame(SkeletonAnimationKeyFrame keyFrame) {
        if (keyFrame == null) return;
        this.keyFrames.add(keyFrame);
    }

    /**
     * Удаляет ключевой кадр анимации.
     *
     * @param index индекс кадра.
     * @return удаленный ключевой кадр.
     */
    public SkeletonAnimationKeyFrame removeKeyFrame(int index) {
        return this.keyFrames.remove(index);
    }

    /**
     * Удаляет ключевой кадр анимации.
     *
     * @param keyFrame ключевой кадр.
     */
    public void removeKeyFrame(SkeletonAnimationKeyFrame keyFrame) {
        this.keyFrames.remove(keyFrame);
    }

    /**
     * Получает текущий анимируемый ключевой кадр.
     *
     * @return текущий ключевой кадр.
     */
    public SkeletonAnimationKeyFrame getCurrentKeyFrame() {
        float duration = this.duration;
        float time = this.elapsedTime - this.delay - this.repeatDelay;

        int fSize = this.keyFrames.size();
        int index = 0;
        for (int i = 0; i < fSize; i++) {
            SkeletonAnimationKeyFrame keyFrame = this.keyFrames.get(i);
            if (time >= (keyFrame.time * duration)) {
                index = i;
            }
        }

        SkeletonAnimationKeyFrame keyFrame = this.keyFrames.get(index);

        return keyFrame;
    }

    /**
     * Инициализирует анимацию.
     *
     * @param skeleton скелет.
     */
    public void initialize(Skeleton skeleton) {
        if (skeleton == null) throw new NullPointerException("skeleton == null");
        this.skeleton = skeleton;
    }

    @Override
    public void start() {
        super.start();

        this.update();
    }

    @Override
    public boolean stop(boolean gotoEnd) {
        this.update();

        return super.stop(gotoEnd);
    }

    @Override
    public void setPosition(float position) {
        super.setPosition(position);

        this.update();
    }

    /**
     * Обновляет скелет.
     */
    @Override
    protected void update() {
        if (this.skeleton == null) return;

        float duration = this.duration;
        float time = this.elapsedTime - this.delay - this.repeatDelay;

        int fSize = this.keyFrames.size();
        int index = 0;
        for (int i = 0; i < fSize; i++) {
            SkeletonAnimationKeyFrame key = this.keyFrames.get(i);
            if (time >= (key.time * duration)) index = i;
        }

        SkeletonAnimationKeyFrame keyFrame1 = this.keyFrames.get(index);
        SkeletonAnimationKeyFrame keyFrame2 = this.keyFrames
                .get(index > fSize - 2 ? index : index + 1);

        Easing easing = keyFrame1.easing != null ? keyFrame1.easing : this.easing;

        float diff = (keyFrame2.time - keyFrame1.time) * duration;
        float now = time - keyFrame1.time * duration;
        float progress = MoreMath.clamp(now / diff, 0.0f, 1.0f);
        float position = MoreMath.clamp(easing.ease(progress, now, 0.0f, 1.0f, diff), 0.0f, 1.0f);

        // Проходим по именам костей первого кадра.
        for (String boneName : keyFrame1.boneKeyFrames.keySet()) {
            // Если имени кости нет во втором кадре, то проходим дальше.
            if (!keyFrame2.boneKeyFrames.containsKey(boneName)) continue;

            // Находим кость в скелете по ее имени.
            Bone bone = this.skeleton.find(boneName);
            // Если кость не нашли, то проходим дальше.
            if (bone == null) continue;

            // Находим начальное и конечное значения.
            float[] begin = keyFrame1.boneKeyFrames.get(boneName);
            float[] end = keyFrame2.boneKeyFrames.get(boneName);

            int size = MoreMath.min(begin.length, end.length);

            // Находим текущее значение.
            float[] value = new float[size];
            for (int i = 0; i < size; i++) {
                value[i] = begin[i] + position * (end[i] - begin[i]);
            }

            // Применяем кости скелета текущее значение.
            if (size >= 1) bone.setX(value[0]);
            if (size >= 2) bone.setY(value[1]);
            if (size >= 3) bone.setAngle(value[2]);
            if (size >= 4) bone.setLength(value[3]);
        }
    }

    @Override
    public String toString() {
        return "SkeletonAnimation[name=" + this.name + "]";
    }

}
