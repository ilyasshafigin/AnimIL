/*
 *
 */
package ru.ildev.anim.skeletal;

import ru.ildev.anim.easings.Easing;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс ключевого кадра скелетной анимации.
 *
 * @author Shafigin Ilyas (Шафигин Ильяс)
 * @version 0.3.3
 */
public class SkeletonAnimationKeyFrame {

    /**
     * Имя ключевого кадра.
     */
    protected String name;
    /**
     * Время ключевого кадра.
     */
    protected float time;
    /**
     * Эффект анимации.
     */
    protected Easing easing = null;
    /**
     * Карта ключевых кадров костей. Ключом является имя кости, значением -
     * массив, содержащий положение и ориентацию кости: [x-координата,
     * y-координата, угол поворота, длина].
     */
    protected Map<String, float[]> boneKeyFrames;

    /**
     * Конструктор, создающий ключевой кадр скелетной анимации.
     *
     * @param name          имя кадра.
     * @param time          время кадра.
     * @param boneKeyFrames
     */
    public SkeletonAnimationKeyFrame(String name, float time,
                                     Map<String, float[]> boneKeyFrames) {
        this(name, time, null, boneKeyFrames);
    }

    /**
     * Конструктор, создающий ключевой кадр скелетной анимации.
     *
     * @param name          имя кадра.
     * @param time          время кадра.
     * @param easing        эффект анимации.
     * @param boneKeyFrames карта ключевых значений костей.
     */
    public SkeletonAnimationKeyFrame(String name, float time, Easing easing,
                                     Map<String, float[]> boneKeyFrames) {
        if (name == null) throw new NullPointerException("name == null");
        if (boneKeyFrames == null) throw new NullPointerException("boneKeyFrames == null");

        this.name = name;
        this.time = time;
        this.easing = easing;
        this.boneKeyFrames = boneKeyFrames;
    }

    /**
     * Конструктор, создающий ключевой кадр скелетной анимации.
     *
     * @param name     имя кадра.
     * @param time     время кадра.
     * @param skeleton скелет.
     */
    public SkeletonAnimationKeyFrame(String name, float time, Skeleton skeleton) {
        this(name, time, null, skeleton);
    }

    /**
     * Конструктор, создающий ключевой кадр скелетной анимации.
     *
     * @param name     имя кадра.
     * @param time     время кадра.
     * @param easing   эффект анимации.
     * @param skeleton скелет.
     */
    public SkeletonAnimationKeyFrame(String name, float time, Easing easing,
                                     Skeleton skeleton) {
        if (name == null) throw new NullPointerException("name == null");
        if (skeleton == null) throw new NullPointerException("skeleton == null");

        HashMap<String, float[]> boneKeyFrames = new HashMap<>();
        List<Bone> boneList = skeleton.getRoot().toList();

        for (Bone bone : boneList) {
            float[] values = new float[4];
            values[0] = bone.getPosition().x;
            values[1] = bone.getPosition().y;
            values[2] = bone.getAngle();
            values[3] = bone.getLength();
            boneKeyFrames.put(bone.getName(), values);
        }

        this.name = name;
        this.time = time;
        this.easing = easing;
        this.boneKeyFrames = boneKeyFrames;
    }

    /**
     * Получаем имя ключевого кадра анимации.
     *
     * @return имя ключевого кадра.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Устанавливает имя ключевому кадру.
     *
     * @param name имя кадра.
     */
    public void setName(String name) {
        if (name == null) throw new NullPointerException("name == null");
        this.name = name;
    }

    /**
     * Получает время ключевого кадра.
     *
     * @return время ключевого кадра.
     */
    public float getTime() {
        return this.time;
    }

    /**
     * Устанавливает время ключевого кадра.
     *
     * @param time вреия кадра.
     */
    public void setTime(float time) {
        this.time = time;
    }

    /**
     * Получает эффект анимации.
     *
     * @return эффект анимации.
     */
    public Easing getEasing() {
        return this.easing;
    }

    /**
     * Устанавливает эффект анимации.
     *
     * @param easing эффект анимации.
     */
    public void setEasing(Easing easing) {
        this.easing = easing;
    }

    /**
     * Получает таблицу кадров костей.
     *
     * @return таблицу кадров костей.
     */
    public Map<String, float[]> getBoneKeyFrames() {
        return this.boneKeyFrames;
    }

    /**
     * Устанавливает таблицу кадров костей.
     *
     * @param boneKeyFrames таблицу кадров костей.
     */
    public void setBoneKeyFrames(Map<String, float[]> boneKeyFrames) {
        if (boneKeyFrames == null) throw new NullPointerException("boneKeyFrames == null");
        this.boneKeyFrames = boneKeyFrames;
    }

    /**
     * Получает значения кадра для кости с заданным имнем.
     *
     * @param boneName имя кости.
     * @return кадр кости.
     */
    public float[] getBoneKeyFrame(String boneName) {
        return this.boneKeyFrames.get(boneName);
    }

    /**
     * Определяет, содержится ли в кадре анимации значения для кости с заданным
     * именем.
     *
     * @param boneName имя кости.
     * @return {@code true}, если...
     */
    public boolean containsKeyFrameForBone(String boneName) {
        return this.boneKeyFrames.containsKey(boneName);
    }

    @Override
    public String toString() {
        return "SkeletonAnimationKeyFrame[name=" + this.name + "]";
    }

}
