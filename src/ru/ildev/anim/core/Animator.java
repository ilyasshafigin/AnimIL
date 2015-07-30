/*
 * Copyright (C) 2013-2014 Shafigin Ilyas
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.

 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.

 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package ru.ildev.anim.core;

import ru.ildev.anim.events.AnimatorListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

/**
 * Класс аниматор. Контролирует все анимации.
 *
 * @author Shafigin Ilyas (Шафигин Ильяс)
 * @version 0.13.44
 */
public class Animator implements Runnable {

    /**
     * Регистратор.
     */
    public static final Logger LOGGER = Logger.getLogger(Animator.class.getName());

    /**
     * Стандартный объект аниматора.
     */
    private static Animator instance = null;
    /**
     * Объединение потоков, исполняющих анимации.
     */
    protected static ExecutorService executor = null;

    private static void createExecutor() {
        executor = Executors.newFixedThreadPool(3);
    }

    /**
     * Устанавливает свой исполнитель.
     *
     * @param exec кастомный исполнитель.
     */
    public static void setExecutor(ExecutorService exec) {
        executor = exec;
    }

    /**
     * Перечисление состояний аниматора.
     *
     * @author Ilyas74
     * @version 0.0.0
     */
    public enum State {

        /**
         * Готов к запуску.
         */
        READY,
        /**
         * Запущен.
         */
        START,
        /**
         * Приостановлен.
         */
        PAUSE,
        /**
         * Остановлен.
         */
        STOP

    }

    /**
     * Текущее состояние аниматора.
     */
    private State state = State.READY;
    /**
     * Частота работы аниматора.
     */
    private int fps = AnimationConstants.DEFAULT_FPS;
    /**
     * Список анимаций.
     */
    private final List<Animation> animations = new ArrayList<>();
    /**
     * Список добавляемых анимаций.
     */
    private final List<Animation> added = new ArrayList<>();
    /**
     * Список удаляемых анимаций.
     */
    private final List<Animation> removed = new ArrayList<>();
    /**
     * Обработчик событий аниматора.
     */
    private final AnimatorListener listener = new AnimatorListener() {

        @Override
        public void onStart() {
            Animator.this.listeners.forEach(AnimatorListener::onStart);
        }

        @Override
        public void onStop() {
            Animator.this.listeners.forEach(AnimatorListener::onStop);
        }

        @Override
        public void onPause() {
            Animator.this.listeners.forEach(AnimatorListener::onPause);
        }

        @Override
        public void onStep() {
            Animator.this.listeners.forEach(AnimatorListener::onStep);
        }

        @Override
        public void onResume() {
            Animator.this.listeners.forEach(AnimatorListener::onResume);
        }

        @Override
        public void onAnimate(Animation animation) {
            Animator.this.listeners.forEach(listener -> listener.onAnimate(animation));
        }

    };
    /**
     * Обработчики событий аниматора.
     */
    private List<AnimatorListener> listeners = new ArrayList<>();
    /**
     * Время прошлого шага.
     */
    private long last;
    /**
     * Время следующего шага.
     */
    private long next;

    /**
     * Стандартный конструктор.
     */
    public Animator() {
    }

    /**
     * Конструктор, устанавливающий частоту кадров аниматору.
     *
     * @param fps частота кадров.
     */
    public Animator(int fps) {
        this.setFps(fps);
    }

    /**
     * Получает стандартный объект аниматора.
     *
     * @return стандартный объект аниматора.
     */
    public static Animator getInstance() {
        if (instance == null) {
            instance = new Animator();
        }
        return instance;
    }

    /**
     * Плучает состояние аниматора.
     *
     * @return состояние аниматора.
     */
    public State getState() {
        return this.state;
    }

    /**
     * Получает частоту обновления аниматора.
     *
     * @return частоту обновления аниматора.
     */
    public int getFps() {
        return this.fps;
    }

    /**
     * Устанавливает частоту обновления аниматора.
     *
     * @param fps частота обновления аниматора.
     */
    public void setFps(int fps) {
        if (fps <= 0) throw new IllegalArgumentException("fps <= 0");
        this.fps = fps;
    }

    /**
     * Получает количество добавленных в аниматор анимаций.
     *
     * @return количестко анимаций.
     */
    public int getAnimationCount() {
        return this.animations.size();
    }

    /**
     * Добавляет обработчик событий аниматора.
     *
     * @param listener обработчик событий аниматора.
     */
    public void addAnimatorListener(AnimatorListener listener) {
        if (listener == null) return;
        this.listeners.add(listener);
    }

    /**
     * Удаляет обработчик событий аниматора.
     *
     * @param listener обработчик событий аниматора.
     */
    public void removeAnimatorListener(AnimatorListener listener) {
        if (listener == null) return;
        this.listeners.remove(listener);
    }

    /**
     * Анимирует объект анимации, т.е. добывляет ее в список и запускает.
     *
     * @param animation анимация.
     */
    public void animate(Animation animation) {
        if (animation == null) throw new NullPointerException("animation == null");

        // Добавляем анимацию в список.
        this.added.add(animation);
        // Запускаем событие.
        this.listener.onAnimate(animation);
        // Если данная анимация является контролируемой.
        if (animation instanceof ControllableAnimation) {
            ControllableAnimation controllableAnimation = (ControllableAnimation) animation;
            // Запускаем ее.
            controllableAnimation.start();
        }
    }

    /**
     * @param task
     * @param time
     */
    public void interval(Runnable task, float time) {
        this.animate(new TimeInterval(task, time));
    }

    /**
     * @param task
     * @param time
     */
    public void timeout(Runnable task, float time) {
        this.animate(new Timeout(task, time));
    }

    /**
     * Удаляет объект анимации из аниматора.
     *
     * @param animation объект анимации.
     */
    public void remove(Animation animation) {
        //if(animation == null) throw new NullPointerException("animation == null");
        if (animation == null) return;
        if (animation instanceof ControllableAnimation) {
            ControllableAnimation controllableAnimation = (ControllableAnimation) animation;
            controllableAnimation.remove();
        } else {
            // Добавляем в список удаляемых анимаций.
            this.removed.add(animation);
        }
    }

    /**
     * Определяет, готов ли аниматор к запуску.
     *
     * @return {@code true}, если аниматор готов к запуску.
     */
    public boolean isReady() {
        return this.state == State.READY;
    }

    /**
     * Определяет, запущен ли аниматор.
     *
     * @return {@code true}, если аниматор запущен.
     */
    public boolean isStart() {
        return this.state == State.START;
    }

    /**
     * Определяет, приостановлена ли работа анимотора.
     *
     * @return {@code true}, если аниматор приостановлен.
     */
    public boolean isPause() {
        return this.state == State.PAUSE;
    }

    /**
     * Определяет, остановлен ли анимотор.
     *
     * @return {@code true}, если аниматор остановлен.
     */
    public boolean isStop() {
        return this.state == State.STOP;
    }

    /**
     * Запускает аниматор, при этом он не начинает работать.
     *
     * @see #stop()
     */
    public void start() {
        // Если аниматор уже запущен, то выходим.
        if (this.state == State.START) return;
        if (this.state == State.PAUSE) {
            this.resume();
            return;
        }
        //if(this.state != State.READY && this.state != State.STOP) return;

        // Устанавливаем состояние.
        this.state = State.START;
        // Запускаем событие запуска аниматора.
        this.listener.onStart();

        // Создаем и запускаем таймер.
        //Thread timer = new Thread(this, this.getClass().getName() + ".thread");
        //timer.setDaemon(true);
        //timer.start();
        if (executor == null) {
            createExecutor();
        }
        executor.submit(this);
    }

    /**
     * Приостанавливает работу аниматора.
     *
     * @see #resume()
     */
    public void pause() {
        // Если аниматор уже приостановлен, то выходим.
        if (this.state == State.PAUSE) return;
        if (this.state != State.START) return;

        // Устанавливаем состояние.
        this.state = State.PAUSE;
        // Запускаем событие.
        this.listener.onPause();
    }

    /**
     * Возобновляет работу аниматора.
     *
     * @see #pause()
     */
    public void resume() {
        //Если аниматор не приостановлен.
        if (this.state != State.PAUSE) return;

        // Устанавливаем состояние.
        this.state = State.START;
        // Запускаем событие.
        this.listener.onResume();

        this.last = System.nanoTime();
        this.next = System.nanoTime();
    }

    /**
     * Останавливает аниматор, удаляет таймер, очищает список объектов.
     *
     * @see #start()
     */
    public void stop() {
        // Если аниматор уже остановлен, то выходим.
        if (this.state == State.STOP) return;
        if (this.state == State.READY) return;

        // Устанавливаем состояние.
        this.state = State.STOP;
        // Запускаем событие.
        this.listener.onStop();
    }

    /**
     * Очищает все списки с анимациями. Действие может выполниться лишь в том
     * случае, если аниматор еще не был запущен или уже остановлен.
     */
    public void clear() {
        if (this.state == State.START || this.state == State.PAUSE) return;

        this.added.clear();
        // TODO Делать вызов событий для анимаций в added или нет?
        this.removed.clear();
        this.animations.clear();
        // TODO Нужно сделать вызов событий анимаций.
    }

    /**
     * Шаг аниматора. Запускается таймером.
     *
     * @param elapsedTime пройденное время в миллисекундах.
     */
    private void step(float elapsedTime) {
        // Запускаем событие.
        this.listener.onStep();

        // Если список пуст, то выходим.
        if (this.animations.isEmpty()) return;
        // Проходим по всем анимациям и запускаем их методы шага анимации.
        /*for (int i = 0; i < this.animations.size(); i++) {
            // Получаем анимацию.
            Animation animation = this.animations.get(i);
            // Запускаем шаг анимации.
            if (animation.step(elapsedTime)) {
                // Добавляет анимацию в список удаляемых объектов.
                this.removed.add(animation);
            }
        }*/
        // Upgrade for Java 8
        this.animations.stream()
                .filter(animation -> animation != null && animation.step(elapsedTime))
                .forEach(this.removed::add);
    }

    @Override
    public void run() {
        try {
            long curr, next, sleep;
            float dt;

            while (this.state != State.STOP) {
                // Находим текущее время в наносекундах.
                curr = System.nanoTime();
                // Находим пройденное время от прошлого кадра и переводим его в миллисекунды.
                dt = (curr - this.last) / 1000000.0f;
                // Устанавливает текущее время.
                this.last = curr;

                // Запускаем шаг.
                if (this.state != State.PAUSE) {
                    this.step(dt);
                }

                // Добавляем анимации.
                if (!this.added.isEmpty()) {
                    this.animations.addAll(this.added);
                    this.added.clear();
                }
                // Удаляем анимации.
                if (!this.removed.isEmpty()) {
                    this.animations.removeAll(this.removed);
                    this.removed.clear();
                }

                // Синхроризация в соответствии с частотой кадров.
                if (this.fps > 0) {
                    next = Math.max(1000000000L / this.fps + this.next, curr);
                    sleep = (next - curr) / 1000000L;
                    if (sleep > 0) {
                        Thread.sleep(sleep);
                    }
                    this.next = next;
                }
            }

            // Очищаем список.
            this.animations.clear();
            this.removed.clear();
        } catch (Throwable throwable) {
            //Animator.LOGGER.throwing(this.getClass().getName(), "run", throwable);
            throwable.printStackTrace();
        }
    }

}
