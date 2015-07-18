package ru.ildev.anim.core;

/**
 * @author Shafigin Ilyas (Шафигин Ильяс)
 * @version 0.0.0
 */
public class TimeInterval implements Animation {

    /**  */
    protected Runnable task;
    /**  */
    protected float interval;

    private float elapsedTime = 0.0f;
    private boolean close = false;

    /**
     * @param task
     * @param interval
     */
    public TimeInterval(Runnable task, float interval) {
        if (task == null) throw new NullPointerException("task == null");
        if (interval <= 0.0f) throw new IllegalArgumentException("interval <= 0");

        this.task = task;
        this.interval = interval;
    }

    @Override
    public boolean step(float elapsedTime) {
        if (this.close) return true;

        this.elapsedTime += elapsedTime;
        if (this.elapsedTime >= this.interval) {
            Animator.executor.submit(this.task);
            this.elapsedTime -= this.interval;
        }
        return this.close;
    }

    /**
     *
     */
    public void close() {
        this.close = true;
    }

}
