/**
 *
 */
package ru.ildev.anim.plugins.property;

import ru.ildev.anim.core.ControllableAnimation;
import ru.ildev.anim.plugins.property.evaluator.TypeEvaluator;
import ru.ildev.anim.plugins.property.evaluator.VectorEvaluator;
import ru.ildev.curve.Curve;
import ru.ildev.geom.Vector2;
import ru.ildev.geom.Vector3;
import ru.ildev.math.MoreMath;

/**
 * @author Shafigin Ilyas <ilyas174@gmail.com>
 * @version 1.0.0
 */
public interface PathProperty<T> extends Property<T> {

    /**
     * @param curve
     * @param property
     * @return
     */
    static PathProperty<Float> of1D(Curve.D1 curve, Property<Float> property) {
        return new D1(curve, property);
    }

    /**
     * @param curve
     * @param property
     * @return
     */
    static PathProperty<Vector2> of2D(Curve.D2 curve, Property<Vector2> property) {
        return new D2(curve, property);
    }

    /**
     * @param curve
     * @param property
     * @return
     */
    static PathProperty<Vector3> of3D(Curve.D3 curve, Property<Vector3> property) {
        return new D3(curve, property);
    }

    /**
     * Абстрактный класс свойства, содержащего объекты кривых, по которым
     * устанавливаются значения.
     *
     * @author Shafigin Ilyas (Шафигин Ильяс)
     * @version 1.0.0
     */
    class D1 extends AbstractProperty<Float> implements PathProperty<Float> {

        /**
         * Объект одномерной кривой.
         */
        protected Curve.D1 curve;
        /**  */
        protected Property<Float> property;

        /**
         * Стандартный констурктор.
         */
        public D1() {
        }

        /**
         * @param property
         */
        public D1(Property<Float> property) {
            if (property == null) throw new NullPointerException("property == null");
            this.property = property;
            this.name = property.getName();
            this.easing = property.getEasing();
            this.evaluator = TypeEvaluator.FLOAT;
        }

        /**
         * @param property
         * @param curve    кривая.
         */
        public D1(Curve.D1 curve, Property<Float> property) {
            this(property);
            if (curve == null) throw new NullPointerException("curve == null");
            this.curve = curve;
            // Устанавливаем состояние.
            this.setState(SETUP, true);
        }

        /**
         * Получает объект кривой.
         *
         * @return объект кривой.
         */
        public Curve.D1 getCurve() {
            return this.curve;
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
        }

        @Override
        public void end(ControllableAnimation animation) {
            this.property.end(animation);
        }

        @Override
        public void update(ControllableAnimation animation) {
            if (!this.property.hasState(SETUP)) return;
            float position = MoreMath.clamp(animation.getPosition(this.easing), 0.0f, 1.0f);
            this.property.set(this.curve.getPointAt(position));
        }

        @Override
        public Float get() {
            return this.property.get();
        }

        @Override
        public void set(Float value) {
            this.property.set(value);
        }

    }

    /**
     * Абстрактный класс свойства, содержащего объекты кривых, по которым
     * устанавливаются значения.
     *
     * @author Shafigin Ilyas (Шафигин Ильяс)
     * @version 1.0.0
     */
    class D2 extends AbstractProperty<Vector2>
            implements PathProperty<Vector2> {

        /**
         * Объект двухмерной кривой.
         */
        protected Curve.D2 curve;
        /**  */
        protected Property<Vector2> property;

        /**
         * Стандартный констурктор.
         */
        public D2() {
        }

        /**
         * @param property
         */
        public D2(Property<Vector2> property) {
            if (property == null) throw new NullPointerException("property == null");
            this.property = property;
            this.name = property.getName();
            this.easing = property.getEasing();
            this.evaluator = VectorEvaluator.V2;
        }

        /**
         * @param property
         * @param curve    кривая.
         */
        public D2(Curve.D2 curve, Property<Vector2> property) {
            this(property);
            if (curve == null) throw new NullPointerException("curve == null");
            this.curve = curve;
            // Устанавливаем состояние.
            this.setState(SETUP, true);
        }

        /**
         * Получает объект кривой.
         *
         * @return объект кривой.
         */
        public Curve.D2 getCurve() {
            return this.curve;
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
        }

        @Override
        public void end(ControllableAnimation animation) {
            this.property.end(animation);
        }

        @Override
        public void update(ControllableAnimation animation) {
            if (!this.property.hasState(SETUP)) return;
            float position = MoreMath.clamp(animation.getPosition(this.easing), 0.0f, 1.0f);
            this.property.set(this.curve.getPointAt(position));
        }

        @Override
        public Vector2 get() {
            return this.property.get();
        }

        @Override
        public void set(Vector2 value) {
            this.property.set(value);
        }

    }

    /**
     * Абстрактный класс свойства, содержащего объекты кривых, по которым
     * устанавливаются значения.
     *
     * @author Shafigin Ilyas (Шафигин Ильяс) <Ilyas74>
     * @version 1.0.0
     */
    class D3 extends AbstractProperty<Vector3>
            implements PathProperty<Vector3> {

        /**
         * Объект трехмерной кривой.
         */
        protected Curve.D3 curve;
        /**  */
        protected Property<Vector3> property;

        /**
         * Стандартный констурктор.
         */
        public D3() {
        }

        /**
         * @param property
         */
        public D3(Property<Vector3> property) {
            if (property == null) throw new NullPointerException("property == null");
            this.property = property;
            this.name = property.getName();
            this.easing = property.getEasing();
            this.evaluator = VectorEvaluator.V3;
        }

        /**
         * @param property
         * @param curve    кривая.
         */
        public D3(Curve.D3 curve, Property<Vector3> property) {
            this(property);
            if (curve == null) throw new NullPointerException("curve == null");
            this.curve = curve;
            // Устанавливаем состояние.
            this.setState(SETUP, true);
        }

        /**
         * Получает объект кривой.
         *
         * @return объект кривой.
         */
        public Curve.D3 getCurve() {
            return this.curve;
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
        }

        @Override
        public void end(ControllableAnimation animation) {
            this.property.end(animation);
        }

        @Override
        public void update(ControllableAnimation animation) {
            if (!this.property.hasState(SETUP)) return;
            float position = MoreMath.clamp(animation.getPosition(this.easing), 0.0f, 1.0f);
            this.property.set(this.curve.getPointAt(position));
        }

        @Override
        public Vector3 get() {
            return this.property.get();
        }

        @Override
        public void set(Vector3 value) {
            this.property.set(value);
        }

    }

}
