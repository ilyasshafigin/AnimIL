package ru.ildev.anim.tween;

import ru.ildev.anim.core.AnimationOptions;
import ru.ildev.anim.core.Animator;
import ru.ildev.anim.core.ControllableAnimation;
import ru.ildev.anim.easings.Easing;
import ru.ildev.anim.events.AnimationListener;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ilyas Shafigin
 * @since 24.07.15
 */
public class Tween extends ControllableAnimation {

    private static int combinedAttrsLimit = 4;
    private static final Map<Class<?>, TweenAccessor<?>> registeredAccessors = new HashMap<>();

    /**
     *
     */
    public static void setCombinedAttributesLimit(int limit) {
        combinedAttrsLimit = limit;
    }

    /**
     * @param someClass
     * @param defaultAccessor
     */
    public static void registerAccessor(Class<?> someClass, TweenAccessor<?> defaultAccessor) {
        registeredAccessors.put(someClass, defaultAccessor);
    }

    /**
     * @param someClass
     */
    public static TweenAccessor<?> getRegisteredAccessor(Class<?> someClass) {
        return registeredAccessors.get(someClass);
    }

    // --------------------------

    /**
     * @param target
     * @param tweenType
     * @param duration
     * @return
     */
    public static Tween to(Object target, int tweenType, float duration) {
        Tween tween = new Tween();
        tween.setup(target, tweenType, duration);
        tween.ease(Easing.QUAD_IN_OUT);
        return tween;
    }

    /**
     * @param target
     * @param tweenType
     * @param duration
     * @return
     */
    public static Tween from(Object target, int tweenType, float duration) {
        Tween tween = new Tween();
        tween.setup(target, tweenType, duration);
        tween.ease(Easing.QUAD_IN_OUT);
        tween.options.setPlayMode(BACKWARD);
        return tween;
    }

    /**
     * @param target
     * @param tweenType
     * @return
     */
    public static Tween set(Object target, int tweenType) {
        Tween tween = new Tween();
        tween.setup(target, tweenType, 0);
        tween.ease(Easing.QUAD_IN_OUT);
        return tween;
    }

    /**
     * @param callback
     * @return
     */
    public static Tween call(AnimationListener callback) {
        Tween tween = new Tween();
        tween.setup(null, -1, 0);
        tween.options.setListener(callback);
        //tween.setCallbackTriggers(TweenCallback.START);
        return tween;
    }

    /**
     * @return
     */
    public static Tween mark() {
        Tween tween = new Tween();
        tween.setup(null, -1, 0);
        return tween;
    }

    // ---------------------------

    private Object target;
    private Class<?> targetClass;
    private TweenAccessor<Object> accessor;
    private int type;
    private AnimationOptions options;

    private int combinedAttrsCount;
    private final float[] startValues;
    private final float[] targetValues;
    private float[] accessorBuffer;

    /**
     *
     */
    public Tween() {
        this.target = null;
        this.targetClass = null;
        this.accessor = null;
        this.type = -1;
        this.options = new AnimationOptions();
        this.combinedAttrsCount = 0;

        this.startValues = new float[combinedAttrsLimit];
        this.targetValues = new float[combinedAttrsLimit];
        this.accessorBuffer = new float[combinedAttrsLimit];
    }

    private void setup(Object target, int tweenType, float duration) {
        if (duration < 0) throw new RuntimeException("Duration can't be negative");

        this.target = target;
        this.targetClass = target != null ? findTargetClass() : null;
        this.type = tweenType;
        this.options.setDuration(duration);
    }

    private Class<?> findTargetClass() {
        if (registeredAccessors.containsKey(this.target.getClass())) return this.target.getClass();
        if (this.target instanceof TweenAccessor) return this.target.getClass();

        Class<?> parentClass = this.target.getClass().getSuperclass();
        while (parentClass != null && !registeredAccessors.containsKey(parentClass))
            parentClass = parentClass.getSuperclass();

        return parentClass;
    }

    // ------------------------------

    /**
     *
     */
    public Object getTarget() {
        return this.target;
    }

    /**
     *
     */
    public int getType() {
        return this.type;
    }

    /**
     *
     */
    public float[] getTargetValues() {
        return this.targetValues;
    }

    /**
     * @return
     */
    public int getCombinedAttributesCount() {
        return this.combinedAttrsCount;
    }

    /**
     *
     */
    public TweenAccessor<?> getAccessor() {
        return this.accessor;
    }

    /**
     *
     */
    public Class<?> getTargetClass() {
        return this.targetClass;
    }

    /**
     * @param easing
     * @return
     */
    public Tween ease(Easing easing) {
        this.options.setEasing(easing);
        return this;
    }

    /**
     * @param targetClass
     * @return
     */
    public Tween cast(Class<?> targetClass) {
        this.targetClass = targetClass;
        return this;
    }

    /**
     * @param targetValue
     * @return
     */
    public Tween target(float targetValue) {
        this.targetValues[0] = targetValue;
        return this;
    }

    /**
     * @param targetValue1
     * @param targetValue2
     * @return
     */
    public Tween target(float targetValue1, float targetValue2) {
        this.targetValues[0] = targetValue1;
        this.targetValues[1] = targetValue2;
        return this;
    }

    /**
     * @param targetValue1
     * @param targetValue2
     * @param targetValue3
     * @return
     */
    public Tween target(float targetValue1, float targetValue2, float targetValue3) {
        this.targetValues[0] = targetValue1;
        this.targetValues[1] = targetValue2;
        this.targetValues[2] = targetValue3;
        return this;
    }

    /**
     * @param targetValues
     * @return
     */
    public Tween target(float... targetValues) {
        if (targetValues.length > combinedAttrsLimit) this.throwCombinedAttrsLimitReached();
        System.arraycopy(targetValues, 0, this.targetValues, 0, targetValues.length);
        return this;
    }

    /**
     * @param delay
     * @return
     */
    public Tween delay(float delay) {
        this.options.delay += delay;
        return this;
    }

    /**
     * @param count
     * @param delay
     * @return
     */
    public Tween repeat(int count, float delay) {
        this.options.setRepeat(count);
        this.options.setRepeatDelay(delay >= 0 ? delay : 0);
        return this;
    }

    /**
     * @param count
     * @param delay
     * @return
     */
    public Tween repeatYoyo(int count, float delay) {
        this.options.setRepeat(count);
        this.options.setRepeatDelay(delay >= 0 ? delay : 0);
        this.options.setPlayMode(YOYO);
        return this;
    }

    /**
     * @param callback
     * @return
     */
    public Tween setCallback(AnimationListener callback) {
        this.options.setListener(callback);
        return this;
    }

    /**
     * @param animator
     * @return
     */
    public Tween start(Animator animator) {
        animator.animate(this);
        this.initialize();
        return this;
    }

    protected Tween initialize() {
        if (this.target == null) return this;

        this.accessor = (TweenAccessor<Object>) registeredAccessors.get(this.targetClass);
        if (this.accessor == null && target instanceof TweenAccessor)
            this.accessor = (TweenAccessor<Object>) this.target;
        if (this.combinedAttrsCount > combinedAttrsLimit) this.throwCombinedAttrsLimitReached();

        this.copy(this.target, this.options);
        return this;
    }

    @Override
    public void start() {
        super.start();

        if (this.accessor != null)
            this.combinedAttrsCount = this.accessor.get(this.target, this.type, this.startValues);
        else throw new RuntimeException("No TweenAccessor was found for the target");
    }

    protected void free() {

    }

    // ------- Animation override

    @Override
    protected boolean update(float elapsedTime) {
        float position = this.getPosition(this.easing);
        for (int i = 0; i < this.combinedAttrsCount; i++) {
            this.accessorBuffer[i] = this.startValues[i] + position * (this.targetValues[i] - this.startValues[i]);
        }
        this.accessor.set(this.target, this.type, this.accessorBuffer);

        return true;
    }

    // --------------

    private void throwCombinedAttrsLimitReached() {
        String msg = "You cannot combine more than " + combinedAttrsLimit + " "
                + "attributes in a tween. You can raise this limit with "
                + "Tween.setCombinedAttributesLimit(), which should be called once "
                + "in application initialization code.";
        throw new RuntimeException(msg);
    }

}
