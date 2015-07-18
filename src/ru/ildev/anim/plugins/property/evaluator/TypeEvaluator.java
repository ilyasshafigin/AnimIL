/**
 *
 */
package ru.ildev.anim.plugins.property.evaluator;


/**
 * @author Shafigin Ilyas (Шафигин Ильяс)
 * @version 0.0.0
 */
public interface TypeEvaluator<T> {

    /**  */
    TypeEvaluator<Integer> INTEGER = new NumberEvaluator.Integer();
    /**  */
    TypeEvaluator<Float> FLOAT = new NumberEvaluator.Float();
    /**  */
    TypeEvaluator<Integer> ARGB = new NumberEvaluator.ARGB();

    /**
     * @param position
     * @param from
     * @param to
     * @return
     */
    T evaluate(float position, T from, T to);

    /**
     * @param operation
     * @param from
     * @param to
     * @return
     */
    T calculate(String operation, T from, T to);

}
