package ru.ildev.anim.tween;

/**
 * @author Ilyas Shafigin
 * @since 29.07.15
 */
public class MutableInteger extends Number implements TweenAccessor<MutableInteger> {

    private int value;

    public MutableInteger(int value) {
        this.value = value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public int intValue() {
        return value;
    }

    @Override
    public long longValue() {
        return (long) value;
    }

    @Override
    public float floatValue() {
        return (float) value;
    }

    @Override
    public double doubleValue() {
        return (double) value;
    }

    @Override
    public int get(MutableInteger target, int tweenType, float[] returnValues) {
        returnValues[0] = target.value;
        return 1;
    }

    @Override
    public void set(MutableInteger target, int tweenType, float[] newValues) {
        target.value = (int) newValues[0];
    }

}