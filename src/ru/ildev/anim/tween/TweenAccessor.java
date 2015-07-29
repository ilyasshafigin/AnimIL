package ru.ildev.anim.tween;

/**
 * @author Ilyas Shafigin
 * @since 18.07.15
 */
public interface TweenAccessor<T> {

    /**
     * @param target
     * @param type
     * @param returnValues
     * @return
     */
    int get(T target, int type, float[] returnValues);

    /**
     * @param target
     * @param type
     * @param values
     */
    void set(T target, int type, float[] values);

}
