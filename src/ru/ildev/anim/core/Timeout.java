package ru.ildev.anim.core;

/**
 * @author Shafigin Ilyas <ilyas174@gmail.com>
 */
public class Timeout implements Animation {

    /**  */
    protected Runnable task;
    /**  */
    protected float time;

    private float elapsedTime = 0.0f;
    private boolean close = false;

    /**
     * @param task
     * @param time
     */
    public Timeout(Runnable task, float time) {
        if (task == null) throw new NullPointerException("task == null");
        if (time < 0.0f) throw new IllegalArgumentException("time < 0");

        this.task = task;
        this.time = time;
    }

    @Override
    public boolean step(float elapsedTime) {
        if (this.close) return true;

        this.elapsedTime += elapsedTime;
        if (this.elapsedTime >= this.time) {
            Animator.executor.submit(this.task);
            return true;
        }
        return false;
    }

    /**
     *
     */
    public void close() {
        this.close = true;
    }

}
