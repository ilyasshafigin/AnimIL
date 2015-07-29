package ru.ildev.anim.tween;

/**
 * @author Ilyas Shafigin
 * @since 28.07.15
 */
public class MutableFloat extends Number implements TweenAccessor<MutableFloat> {

    private float value;

    public MutableFloat(float value) {
        this.value = value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    @Override
    public int intValue() {
        return (int) value;
    }

    @Override
    public long longValue() {
        return (long) value;
    }

    @Override
    public float floatValue() {
        return value;
    }

    @Override
    public double doubleValue() {
        return (double) value;
    }

    @Override
    public int get(MutableFloat target, int tweenType, float[] returnValues) {
        returnValues[0] = target.value;
        return 1;
    }

    @Override
    public void set(MutableFloat target, int tweenType, float[] newValues) {
        target.value = newValues[0];
    }

}