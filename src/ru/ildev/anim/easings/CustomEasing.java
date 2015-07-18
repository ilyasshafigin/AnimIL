/*
 *
 */
package ru.ildev.anim.easings;

import ru.ildev.math.MoreMath;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс кастомного эффекта анимации.
 *
 * @author Shafigin Ilyas (Шафигин Ильяс) <Ilyas74>
 * @version 1.1.0
 */
public abstract class CustomEasing extends Easing {

    /**
     * Класс кастомного эффекта анимации. Эффект получается линейной
     * интерполяцией точек.
     */
    public static class Linear extends CustomEasing {

        /**
         * Точки.
         */
        private float[] points;

        /**
         * Конструктор, устанавливающий точки. Точки в строке записываются через
         * запятую, т.е. в таком виде: "p0, p1, p2, ...".
         *
         * @param string строка.
         */
        public Linear(String string) {
            if (string == null) throw new NullPointerException("string == null");

            String[] array = string.split(",");
            this.points = new float[array.length];

            for (int i = 0; i < array.length; i++) {
                this.points[i] = Float.parseFloat(array[i].trim());
            }
        }

        /**
         * Конструктор, устанавливающий точки.
         *
         * @param points точки.
         */
        public Linear(float... points) {
            if (points == null) throw new NullPointerException("points == null");
            this.points = points;
        }

        /**
         * Получает масив точки.
         *
         * @return массив точки.
         */
        public float[] getPoints() {
            return this.points;
        }

        @Override
        public float easeIn(float state, float now, float firstNum,
                            float lastNum, float duration) {
            return this.easeLinear(state, now, firstNum, lastNum, duration);
        }

        @Override
        public float easeOut(float state, float now, float firstNum,
                             float lastNum, float duration) {
            return this.easeLinear(state, now, firstNum, lastNum, duration);
        }

        @Override
        public float easeInOut(float state, float now, float firstNum,
                               float lastNum, float duration) {
            return this.easeLinear(state, now, firstNum, lastNum, duration);
        }

        private float easeLinear(float state, float now, float firstNum,
                                 float lastNum, float duration) {
            float index = (this.points.length - 1) * state;
            int intIndex = (int) index;
            float weight = index - intIndex;

            float p0 = this.points[intIndex];
            float p1 = this.points[intIndex > this.points.length - 2 ? intIndex : intIndex + 1];

            return lastNum * this.interpolate(weight, p0, p1) + firstNum;
        }

        /**
         * Функция интерполяции.
         *
         * @param t
         * @param p0
         * @param p1
         * @return
         */
        private float interpolate(float t, float p0, float p1) {
            return (p1 - p0) * t + p0;
        }

    }

    /**
     * Класс кастомного эффекта анимации. Эффект получается кубической
     * интерполяцией точек.
     */
    public static class Cubic extends CustomEasing {

        /**
         * Точки.
         */
        private float[] points;

        /**
         * Конструктор, устанавливающий точки. Точки в строке записываются через
         * запятую, т.е. в таком виде: "p0, p1, p2, ...".
         *
         * @param string строка.
         */
        public Cubic(String string) {
            if (string == null) throw new NullPointerException("string == null");

            String[] array = string.split(",");
            this.points = new float[array.length];

            for (int i = 0; i < array.length; i++) {
                this.points[i] = Float.parseFloat(array[i].trim());
            }
        }

        /**
         * Конструктор, устанавливающий точки.
         *
         * @param points массив точек.
         */
        public Cubic(float... points) {
            if (points == null) throw new NullPointerException("points == null");
            this.points = points;
        }

        /**
         * Получает точки.
         *
         * @return точки.
         */
        public float[] getPoints() {
            return this.points;
        }

        @Override
        public float easeIn(float state, float now, float firstNum,
                            float lastNum, float duration) {
            return this.easeCubic(state, now, firstNum, lastNum, duration);
        }

        @Override
        public float easeOut(float state, float now, float firstNum,
                             float lastNum, float duration) {
            return this.easeCubic(state, now, firstNum, lastNum, duration);
        }

        @Override
        public float easeInOut(float state, float now, float firstNum,
                               float lastNum, float duration) {
            return this.easeCubic(state, now, firstNum, lastNum, duration);
        }

        private float easeCubic(float state, float now, float firstNum,
                                float lastNum, float duration) {
            float index = (this.points.length - 1) * state;
            int intIndex = (int) index;
            float weight = index - intIndex;

            float p0 = this.points[intIndex == 0 ? intIndex : intIndex - 1];
            float p1 = this.points[intIndex];
            float p2 = this.points[intIndex > this.points.length - 2 ? intIndex : intIndex + 1];
            float p3 = this.points[intIndex > this.points.length - 3 ? intIndex : intIndex + 2];

            return lastNum * this.interpolate(weight, p0, p1, p2, p3) + firstNum;
        }

        /**
         * Функция интерполяции.
         *
         * @param t
         * @param p0
         * @param p1
         * @param p2
         * @param p3
         * @return
         */
        private float interpolate(float t, float p0, float p1, float p2, float p3) {
            return p0 * (1 - t) * (1 - t) * (1 - t) + 3 * p1 * (1 - t) * (1 - t) * t + 3 * p2 * (1 - t) * t * t + p3 * t * t * t;
        }

    }

    /**
     * Класс кастомного эффекта анимации. Эффект получается интерполяцией точек
     * сплайнами.
     */
    public static class Spline extends CustomEasing {

        /**
         * Точки.
         */
        private float[] points;

        /**
         * Конструктор, устанавливающий точки. Точки в строке записываются через
         * запятую, т.е. в таком виде: "p0, p1, p2, ...".
         *
         * @param string строка.
         */
        public Spline(String string) {
            if (string == null) throw new NullPointerException("string == null");

            String[] array = string.split(",");
            this.points = new float[array.length];

            for (int i = 0; i < array.length; i++) {
                this.points[i] = Float.parseFloat(array[i].trim());
            }
        }

        /**
         * Конструктор, устанавливающий точки.
         *
         * @param points массив точек.
         */
        public Spline(float... points) {
            if (points == null) throw new NullPointerException("points == null");
            this.points = points;
        }

        /**
         * Получает точки.
         *
         * @return точки.
         */
        public float[] getPoints() {
            return this.points;
        }

        @Override
        public float easeIn(float state, float now, float firstNum,
                            float lastNum, float duration) {
            return this.easeSpline(state, now, firstNum, lastNum, duration);
        }

        @Override
        public float easeOut(float state, float now, float firstNum,
                             float lastNum, float duration) {
            return this.easeSpline(state, now, firstNum, lastNum, duration);
        }

        @Override
        public float easeInOut(float state, float now, float firstNum,
                               float lastNum, float duration) {
            return this.easeSpline(state, now, firstNum, lastNum, duration);
        }

        private float easeSpline(float state, float now, float firstNum,
                                 float lastNum, float duration) {
            float index = (this.points.length - 1) * state;
            int intIndex = (int) index;
            float weight = index - intIndex;

            float p0 = this.points[intIndex == 0 ? intIndex : intIndex - 1];
            float p1 = this.points[intIndex];
            float p2 = this.points[intIndex > this.points.length - 2 ? intIndex : intIndex + 1];
            float p3 = this.points[intIndex > this.points.length - 3 ? intIndex : intIndex + 2];

            return lastNum * this.interpolate(weight, p0, p1, p2, p3) + firstNum;
        }

        /**
         * Функция интерполяции.
         *
         * @param t
         * @param p0
         * @param p1
         * @param p2
         * @param p3
         * @return
         */
        private float interpolate(float t, float p0, float p1, float p2, float p3) {
            return p1 + 0.5f * t * (p2 - p0 + t * (2.0f * p0 - 5.0f * p1 + 4.0f * p2 - p3 + t * (3.0f * (p1 - p2) + p3 - p0)));
        }

    }

    /**
     * Класс кастомного эффекта анимации. Эффект получается интерполяцией точек
     * кривыми Безье.
     */
    public static class Bezier extends CustomEasing {

        /**  */
        private final List<Segment> segments = new ArrayList<>();

        /**
         * Конструктор, устанавливающий точки. Точки записываются в строке в
         * таком виде: "s1,c1,e1; s2,c2,e2", где s - значение начальной точки,
         * c - значение контрольной точки, e - значение конечной точки.
         *
         * @param string строка.
         */
        public Bezier(String string) {
            Pattern regex = Pattern.compile("([\\d\\s\\.]+,[\\d\\s\\.]+,[\\d\\s\\.]+);");
            Matcher matcher = regex.matcher(string);

            while (matcher.find()) {
                String segmentString = matcher.group();
                String[] segmentProperties = segmentString.replaceAll(";", "").split(",");
                this.segments.add(new Segment(Float.parseFloat(segmentProperties[0].trim()),
                        Float.parseFloat(segmentProperties[1].trim()),
                        Float.parseFloat(segmentProperties[2].trim())));
            }
        }

        @Override
        public float easeIn(float state, float now, float firstNum,
                            float lastNum, float duration) {
            return this.easeBezier(state, now, firstNum, lastNum, duration);
        }

        @Override
        public float easeOut(float state, float now, float firstNum,
                             float lastNum, float duration) {
            return this.easeBezier(state, now, firstNum, lastNum, duration);
        }

        @Override
        public float easeInOut(float state, float now, float firstNum,
                               float lastNum, float duration) {
            return this.easeBezier(state, now, firstNum, lastNum, duration);
        }

        private float easeBezier(float state, float now, float firstNum,
                                 float lastNum, float duration) {
            int size = this.segments.size();
            int i = MoreMath.min((int) (size * state), size - 1);
            float time = (state - (i * (1.0f / size))) * size;

            Segment s = this.segments.get(i);
            return firstNum + lastNum * (s.start + time * (2.0f * (1.0f - time) * (s.control - s.start) + time * (s.end - s.start)));
        }

        /**
         *
         */
        private class Segment {

            /**
             * Начальная точка.
             */
            float start;
            /**
             * Контрольная точка.
             */
            float control;
            /**
             * Конечная точка.
             */
            float end;

            /**
             * Конструктор.
             *
             * @param start   начальная точка.
             * @param control контрольная точка.
             * @param end     конечная точка.
             */
            Segment(float start, float control, float end) {
                this.start = start;
                this.control = control;
                this.end = end;
            }

        }

    }

}
