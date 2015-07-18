/*
 *
 */
package ru.ildev.anim.easings;

import ru.ildev.anim.core.AnimationConstants;

import java.util.HashMap;
import java.util.Map;

import static ru.ildev.anim.core.AnimationConstants.*;

/**
 * ����������� ����� ������� ��������.
 *
 * @author Ilyas74
 * @version 2.5.5
 */
public abstract class Easing {

    // ����������� �������.
    /**  */
    public static final Easing LINEAR = new Linear();
    /**  */
    public static final Easing QUAD_IN = new Quad(IN);
    /**  */
    public static final Easing QUAD_OUT = new Quad(OUT);
    /**  */
    public static final Easing QUAD_IN_OUT = new Quad(IN_OUT);
    /**  */
    public static final Easing CUBIC_IN = new Cubic(IN);
    /**  */
    public static final Easing CUBIC_OUT = new Cubic(OUT);
    /**  */
    public static final Easing CUBIC_IN_OUT = new Cubic(IN_OUT);
    /**  */
    public static final Easing QUART_IN = new Quart(IN);
    /**  */
    public static final Easing QUART_OUT = new Quart(OUT);
    /**  */
    public static final Easing QUART_IN_OUT = new Quart(IN_OUT);
    /**  */
    public static final Easing QUINT_IN = new Quint(IN);
    /**  */
    public static final Easing QUINT_OUT = new Quint(OUT);
    /**  */
    public static final Easing QUINT_IN_OUT = new Quint(IN_OUT);
    /**  */
    public static final Easing SINE_IN = new Sine(IN);
    /**  */
    public static final Easing SINE_OUT = new Sine(OUT);
    /**  */
    public static final Easing SINE_IN_OUT = new Sine(IN_OUT);
    /**  */
    public static final Easing CIRCLE_IN = new Circle(IN);
    /**  */
    public static final Easing CIRCLE_OUT = new Circle(OUT);
    /**  */
    public static final Easing CIRCLE_IN_OUT = new Circle(IN_OUT);
    /**  */
    public static final Easing EXPO_IN = new Expo(IN);
    /**  */
    public static final Easing EXPO_OUT = new Expo(OUT);
    /**  */
    public static final Easing EXPO_IN_OUT = new Expo(IN_OUT);
    /**  */
    public static final Easing BACK_IN = new Back(IN);
    /**  */
    public static final Easing BACK_OUT = new Back(OUT);
    /**  */
    public static final Easing BACK_IN_OUT = new Back(IN_OUT);
    /**  */
    public static final Easing BOUNCE_IN = new Bounce(IN);
    /**  */
    public static final Easing BOUNCE_OUT = new Bounce(OUT);
    /**  */
    public static final Easing BOUNCE_IN_OUT = new Bounce(IN_OUT);
    /**  */
    public static final Easing ELASTIC_IN = new Elastic(IN);
    /**  */
    public static final Easing ELASTIC_OUT = new Elastic(OUT);
    /**  */
    public static final Easing ELASTIC_IN_OUT = new Elastic(IN_OUT);

    /**
     * ��� ������� ��������.
     */
    protected String name = "";
    /**
     * ����������� ������� ��������.
     */
    protected int mode = OUT;

    /**
     * �����.
     */
    private static Map<String, Easing> easings = null;

    /**
     * ����������� �����������.
     */
    public Easing() {
    }

    /**
     * ����������� �����������.
     *
     * @param mode �����������.
     * @see ru.ildev.anim.core.AnimationConstants#IN
     * @see ru.ildev.anim.core.AnimationConstants#OUT
     * @see ru.ildev.anim.core.AnimationConstants#IN_OUT
     */
    public Easing(int mode) {
        this.mode = mode;
    }

    @Override
    public String toString() {
        return this.getCompoundName();
    }

    /**
     * �������� ��� ������� ��������.
     *
     * @return ��� ������� ��������.
     */
    public String getName() {
        return this.name;
    }

    /**
     * �������� ��������� ��� ������� ��������. � ���� ������ ��� ������� �
     * ��� ��� �����������.
     *
     * @return ��������� ���.
     */
    public String getCompoundName() {
        String modeName = "";
        if (this.mode == AnimationConstants.IN) {
            modeName = "In";
        } else if (this.mode == AnimationConstants.OUT) {
            modeName = "Out";
        } else if (this.mode == AnimationConstants.IN_OUT) {
            modeName = "InOut";
        }
        return this.name + modeName;
    }

    /**
     * �������� ����������� ������� ��������.
     *
     * @return �����������.
     * @see ru.ildev.anim.core.AnimationConstants#IN
     * @see ru.ildev.anim.core.AnimationConstants#OUT
     * @see ru.ildev.anim.core.AnimationConstants#IN_OUT
     */
    public int getMode() {
        return this.mode;
    }

    /**
     * ������������� �����������.
     *
     * @param mode �����������.
     * @see ru.ildev.anim.core.AnimationConstants#IN
     * @see ru.ildev.anim.core.AnimationConstants#OUT
     * @see ru.ildev.anim.core.AnimationConstants#IN_OUT
     */
    public void setMode(int mode) {
        this.mode = mode;
    }

    /**
     * ��������� �������� ������ ��������.
     *
     * @param state    ������� �������� ��������� ��������.
     * @param now      ������� ����������������� ��������.
     * @param first    ��������� ��������.
     * @param last     �������� ��������.
     * @param duration ����������������� ��������.
     * @return ���������� �������� ���������.
     */
    public abstract float easeIn(float state, float now, float first,
                                 float last, float duration);

    /**
     * ��������� ��������� ������ ��������.
     *
     * @param state    ������� �������� ��������� ��������.
     * @param now      ������� ����������������� ��������.
     * @param first    ��������� ��������.
     * @param last     �������� ��������.
     * @param duration ����������������� ��������.
     * @return ���������� �������� ���������.
     */
    public abstract float easeOut(float state, float now, float first,
                                  float last, float duration);

    /**
     * ��������� �������� � ��������� ������ ��������.
     *
     * @param state    ������� �������� ��������� ��������.
     * @param now      ������� ����������������� ��������.
     * @param first    ��������� ��������.
     * @param last     �������� �������� ���������.
     * @param duration ����������������� ��������.
     * @return ���������� �������� ���������.
     */
    public abstract float easeInOut(float state, float now, float first,
                                    float last, float duration);

    /**
     * ��������� ������ ��������.
     *
     * @param state    ������� ��������� ��������� ��������.
     * @param now      ������� ����������������� ��������.
     * @param first    ��������� ��������.
     * @param last     �������� ��������.
     * @param duration ����������������� ��������.
     * @return ���������� �������� ���������.
     */
    public float ease(float state, float now, float first, float last,
                      float duration) {
        switch (this.mode) {
            case IN:
                return this.easeIn(state, now, first, last, duration);

            default:
            case OUT:
                return this.easeOut(state, now, first, last, duration);

            case IN_OUT:
                return this.easeInOut(state, now, first, last, duration);
        }
    }

    /**
     * <p>�������� ������ ������� �������� �� ��� �����.</p>
     * <p>����������� �������� - {@code LINEAR}.</p>
     * <p>���� {@code name == null} ��� {@code name} ��� � ������ ������������
     * ��������, �� ���������� ����������� ��������.</p>
     * <p>���� {@code name == "null"}, �� ���������� {@code null}.</p>
     *
     * @param name ��� ������� ��������.
     * @return ������ ������� ��������.
     */
    public static Easing getEasing(String name) {
        if (Easing.easings == null) Easing.initEasings();
        if (name == null) return Easing.LINEAR;
        if (name.equals("null")) return null;
        Easing easing = Easing.easings.get(name.toLowerCase());
        if (easing == null) return Easing.LINEAR;
        return easing;
    }

    private static void initEasings() {
        Easing[] array = {Easing.LINEAR, Easing.QUAD_IN, Easing.QUAD_OUT,
                Easing.QUAD_IN_OUT, Easing.CUBIC_IN, Easing.CUBIC_IN_OUT,
                Easing.CUBIC_OUT, Easing.QUART_IN, Easing.QUART_OUT,
                Easing.QUART_IN_OUT, Easing.QUINT_IN, Easing.QUINT_OUT,
                Easing.QUINT_IN_OUT, Easing.SINE_IN, Easing.SINE_OUT,
                Easing.SINE_IN_OUT, Easing.CIRCLE_IN, Easing.CIRCLE_OUT,
                Easing.CIRCLE_IN_OUT, Easing.EXPO_IN, Easing.EXPO_OUT,
                Easing.EXPO_IN_OUT, Easing.BACK_IN, Easing.BACK_OUT,
                Easing.BACK_IN_OUT, Easing.BOUNCE_IN, Easing.BOUNCE_OUT,
                Easing.BOUNCE_IN_OUT, Easing.ELASTIC_IN, Easing.ELASTIC_OUT,
                Easing.ELASTIC_IN_OUT};

        Map<String, Easing> map = new HashMap<>(array.length);
        for (Easing easing : array) {
            String name = easing.getCompoundName().toLowerCase();
            map.put(name, easing);
        }
        Easing.easings = map;
    }

}
