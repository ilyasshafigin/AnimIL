/**
 *
 */
package ru.ildev.anim.plugins.property.evaluator;

import ru.ildev.geom.Vector2;
import ru.ildev.geom.Vector3;
import ru.ildev.geom.Vector4;

import static ru.ildev.anim.core.AnimationConstants.*;


/**
 * @author Shafigin Ilyas <ilyas174@gmail.com>
 */
public interface VectorEvaluator<T> extends TypeEvaluator<T> {

    /**  */
    VectorEvaluator<Vector2> V2 = new Vector2Evaluator();
    /**  */
    VectorEvaluator<Vector3> V3 = new Vector3Evaluator();
    /**  */
    VectorEvaluator<Vector4> V4 = new Vector4Evaluator();

    /**
     * @author Shafigin Ilyas <ilyas174@gmail.com>
     */
    class Vector2Evaluator implements VectorEvaluator<Vector2> {

        @Override
        public Vector2 evaluate(float position, Vector2 from, Vector2 to) {
            Vector2 v = new Vector2();
            v.x = from.x + position * (to.x - from.x);
            v.y = from.y + position * (to.y - from.y);
            return v;
        }

        @Override
        public Vector2 calculate(String operation, Vector2 from, Vector2 to) {
            switch (operation) {
                case ADD:
                    return new Vector2(from.x + to.x, from.y + to.y);
                case SUB:
                    return new Vector2(from.x - to.x, from.y - to.y);
                case MUL:
                    return new Vector2(from.x * to.x, from.y * to.y);
                case DIV:
                    return new Vector2(from.x / to.x, from.y / to.y);
                case MOD:
                    return new Vector2(from.x % to.x, from.y % to.y);
                case NONE:
                default:
                    return to;
            }
        }

    }

    /**
     * @author Shafigin Ilyas <ilyas174@gmail.com>
     */
    class Vector3Evaluator implements VectorEvaluator<Vector3> {

        @Override
        public Vector3 evaluate(float position, Vector3 from, Vector3 to) {
            Vector3 v = new Vector3();
            v.x = from.x + position * (to.x - from.x);
            v.y = from.y + position * (to.y - from.y);
            v.z = from.z + position * (to.z - from.z);
            return v;
        }

        @Override
        public Vector3 calculate(String operation, Vector3 from, Vector3 to) {
            switch (operation) {
                case ADD:
                    return new Vector3(from.x + to.x, from.y + to.y, from.z + to.z);
                case SUB:
                    return new Vector3(from.x - to.x, from.y - to.y, from.z - to.z);
                case MUL:
                    return new Vector3(from.x * to.x, from.y * to.y, from.z * to.z);
                case DIV:
                    return new Vector3(from.x / to.x, from.y / to.y, from.z / to.z);
                case MOD:
                    return new Vector3(from.x % to.x, from.y % to.y, from.z % to.z);
                case NONE:
                default:
                    return to;
            }
        }

    }

    /**
     * @author Shafigin Ilyas <ilyas174@gmail.com>
     */
    class Vector4Evaluator implements VectorEvaluator<Vector4> {

        @Override
        public Vector4 evaluate(float position, Vector4 from, Vector4 to) {
            Vector4 v = new Vector4();
            v.x = from.x + position * (to.x - from.x);
            v.y = from.y + position * (to.y - from.y);
            v.z = from.z + position * (to.z - from.z);
            v.w = from.w + position * (to.w - from.w);
            return v;
        }

        @Override
        public Vector4 calculate(String operation, Vector4 from, Vector4 to) {
            switch (operation) {
                case ADD:
                    return new Vector4(from.x + to.x, from.y + to.y, from.z + to.z, from.w + to.w);
                case SUB:
                    return new Vector4(from.x - to.x, from.y - to.y, from.z - to.z, from.w - to.w);
                case MUL:
                    return new Vector4(from.x * to.x, from.y * to.y, from.z * to.z, from.w * to.w);
                case DIV:
                    return new Vector4(from.x / to.x, from.y / to.y, from.z / to.z, from.w / to.w);
                case MOD:
                    return new Vector4(from.x % to.x, from.y % to.y, from.z % to.z, from.w % to.w);
                case NONE:
                default:
                    return to;
            }
        }

    }

}
