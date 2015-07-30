package ru.ildev.anim.tween;

import ru.ildev.math.MoreMath;

/**
 * @author Ilyas Shafigin
 * @since 30.07.15
 */
public interface TweenPath {

    /**
     * @param t
     * @param points
     * @param pointsCount
     * @return
     */
    float compute(float t, float[] points, int pointsCount);

    /**
     *
     */
    TweenPath LINEAR = (t, points, pointsCount) -> {
        int segment = MoreMath.floor((pointsCount - 1) * t);
        segment = MoreMath.max(segment, 0);
        segment = MoreMath.min(segment, pointsCount - 2);

        t = t * (pointsCount - 1) - segment;

        return points[segment] + t * (points[segment + 1] - points[segment]);
    };

    /**
     *
     */
    TweenPath CATMULL_ROM = new TweenPath() {

        @Override
        public float compute(float t, float[] points, int pointsCount) {
            int segment = MoreMath.floor((pointsCount - 1) * t);
            segment = MoreMath.max(segment, 0);
            segment = MoreMath.min(segment, pointsCount - 2);

            t = t * (pointsCount - 1) - segment;

            if (segment == 0) {
                return this.catmullRomSpline(points[0], points[0], points[1], points[2], t);
            }

            if (segment == pointsCount - 2) {
                return this.catmullRomSpline(points[pointsCount - 3], points[pointsCount - 2],
                        points[pointsCount - 1], points[pointsCount - 1], t);
            }

            return catmullRomSpline(points[segment - 1], points[segment], points[segment + 1], points[segment + 2], t);
        }

        private float catmullRomSpline(float a, float b, float c, float d, float t) {
            float t1 = (c - a) * 0.5f;
            float t2 = (d - b) * 0.5f;

            float tt = t * t;
            float ttt = tt * t;

            float h1 = +2 * ttt - 3 * tt + 1;
            float h2 = -2 * ttt + 3 * tt;
            float h3 = ttt - 2 * tt + t;
            float h4 = ttt - tt;

            return b * h1 + c * h2 + t1 * h3 + t2 * h4;
        }
    };

}
