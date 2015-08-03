/*
 *
 */
package ru.ildev.anim.core;

import ru.ildev.anim.events.AnimationEvent;
import ru.ildev.anim.events.AnimationListener;
import ru.ildev.anim.utils.Builder;

import java.util.*;

/**
 * Класс анимации, которая объединяет сразу несколько анимаций, которые будут
 * запускаться последовательно.
 * Длительность анимации определяется количеством и длительностью дочерних анимаций.
 *
 * @todo Задержки данной анимации
 * @author Shafigin Ilyas (Шафигин Ильяс)
 * @version 0.2.5
 */
public class SequenceAnimation extends ControllableAnimation {

    /**
     * Очередь.
     */
    protected Queue queue = null;
    /**
     * Флаг, отвечающий за состояние анимирования элементов по одному.
     */
    protected boolean oneRun = false;

    /**
     * Конструктор, устанавливающий очередь и опции анимации.
     *
     * @param oneRun  анимировать по одному элементу?
     * @param options опции анимации.
     * @param queue   очередь.
     */
    protected SequenceAnimation(Queue queue, AnimationOptions options, boolean oneRun) {
        super();

        if (queue == null) throw new NullPointerException("queue == null");
        if (options == null) throw new NullPointerException("options == null");

        this.queue = queue;
        this.oneRun = oneRun;
        this.copy(options);
        this.duration = 0.0f;
    }

    /**
     * Стандартный конструктор.
     */
    public SequenceAnimation() {
        super();

        this.queue = new Queue();
    }

    /**
     * Конструктор, устанавливающий опции анимации.
     *
     * @param options опции анимации.
     */
    public SequenceAnimation(AnimationOptions options) {
        super();

        if (options == null) throw new NullPointerException("options == null");

        this.queue = new Queue();
        this.copy(options);
        this.duration = 0.0f;
    }

    /**
     * Конструктор, устанавливающий опции анимации.
     *
     * @param options опции анимации.
     * @param oneRun  анимировать по одному элементу?
     */
    public SequenceAnimation(AnimationOptions options, boolean oneRun) {
        super();

        if (options == null) throw new NullPointerException("options == null");
        this.queue = new Queue();
        this.oneRun = oneRun;
        this.copy(options);
        this.duration = 0.0f;
    }

    /**
     * Определяет, включено ли анимирование по одному.
     *
     * @return {@code true}, если анимирование по одному включено.
     */
    public boolean isOneRun() {
        return this.oneRun;
    }

    /**
     * Изменяет состояние анимирования по очереди.
     *
     * @param flag флаг.
     */
    public void setOneRun(boolean flag) {
        this.oneRun = flag;
    }

    /**
     * Получает активный элемент очереди анимации.
     *
     * @return активный элемент очереди анимации.
     */
    public Element getCurrentQueueElement() {
        return this.queue.current();
    }

    /**
     * Добавляет анимацию в очередь.
     *
     * @param animation анимация.
     */
    public void insert(ControllableAnimation animation) {
        if (animation == null) throw new NullPointerException("animation == null");

        // Если анимация будет повторяться бесконечно.
        if (animation.repeat == REPEAT_INFINITE) {
            // Заносим сообщение в регистратор.
            Animator.LOGGER.warning("repeat == REPEAT_INFINITE");
            // Выходим.
            return;
        }

        // Добавляем ее в конец списка.
        this.queue.add(new Element(animation));
        // Обновляем продолжительность анимации очереди.
        this.duration += this.getTimeFromTimeMode(animation.getTotalDurationInMilliseconds());
    }

    /**
     * Добавляет анимацию в очередь.
     *
     * @param index     индекс элемента.
     * @param animation анимация.
     */
    public void insert(int index, ControllableAnimation animation) {
        if (index < 0) throw new IndexOutOfBoundsException("index < 0");
        if (index >= this.queue.size()) throw new IndexOutOfBoundsException("index >= queue.size");
        if (animation == null) throw new NullPointerException("animation == null");

        // Если анимация будет повторяться бесконечно.
        if (animation.repeat == REPEAT_INFINITE) {
            // Заносим сообщение в регистратор.
            Animator.LOGGER.warning("repeat == REPEAT_INFINITE");
            // Выходим.
            return;
        }

        // Добавляем ее в конец списка.
        this.queue.add(index, new Element(animation));
        // Обновляем продолжительность анимации очереди.
        this.duration += this.getTimeFromTimeMode(animation.getTotalDurationInMilliseconds());
    }

    /**
     * Добавляет анимацию в очередь.
     *
     * @param animation анимация.
     * @param name      имя анимации.
     */
    public void insert(String name, ControllableAnimation animation) {
        if (name == null) throw new NullPointerException("name == null");
        if (animation == null) throw new NullPointerException("animation == null");

        // Если анимация будет повторяться бесконечно.
        if (animation.repeat == REPEAT_INFINITE) {
            // Заносим сообщение в регистратор.
            Animator.LOGGER.warning("repeat == REPEAT_INFINITE");
            // Выходим.
            return;
        }

        // Добавляем ее в конец списка.
        this.queue.add(new Element(name, animation));
        // Обновляем продолжительность анимации очереди.
        this.duration += this.getTimeFromTimeMode(animation.getTotalDurationInMilliseconds());
    }

    /**
     * Добавляет анимацию в очередь.
     *
     * @param index     индекс элемента.
     * @param animation анимация.
     * @param name      имя анимации.
     */
    public void insert(int index, String name, ControllableAnimation animation) {
        if (name == null) throw new NullPointerException("name == null");
        if (animation == null) throw new NullPointerException("animation == null");

        // Если анимация будет повторяться бесконечно.
        if (animation.repeat == REPEAT_INFINITE) {
            // Заносим сообщение в регистратор.
            Animator.LOGGER.warning("repeat == REPEAT_INFINITE");
            // Выходим.
            return;
        }

        // Добавляем ее в конец списка.
        this.queue.add(index, new Element(name, animation));
        // Обновляем продолжительность анимации очереди.
        this.duration += this.getTimeFromTimeMode(animation.getTotalDurationInMilliseconds());
    }

    /**
     * Удаляет элемент под индексом из очереди анимации. При этом анимация не
     * будет закончена.
     *
     * @param index индекс.
     */
    public void remove(int index) {
        this.remove(index, false);
    }

    /**
     * Удаляет элемент под индексом из очереди анимации.
     *
     * @param index   индекс.
     * @param gotoEnd флаг, определяющий, заканчивать ли анимацию удаляемого
     *                элемента.
     */
    public void remove(int index, boolean gotoEnd) {
        if (index < 0) throw new IndexOutOfBoundsException("index < 0");
        if (index >= this.queue.size()) throw new IndexOutOfBoundsException("index >= queue.size");

        // Находим индекс активного элемента.
        int currentIndex = this.queue.currentIndex;
        // Удаляем и получаем элемент.
        Element removed = this.queue.remove(index);
        // Останавливаем анимацию.
        removed.animation.stop(gotoEnd);

        float duration = this.getTimeFromTimeMode(removed.animation.getTotalDurationInMilliseconds());
        // Обновляем пройденное время и продолжительность анимации очереди.
        if (currentIndex >= index) {
            this.elapsedTime -= duration;
        }
        this.duration -= duration;

        // Если удаляем активный элемент,
        if (currentIndex == index) {
            // то переходим к следующему.
            //this.next();
        }
    }

    /**
     * Удаляет элемент под именем из очереди анимации. При этом анимация не
     * будет закончена.
     *
     * @param name имя элемента.
     */
    public void remove(String name) {
        this.remove(name, false);
    }

    /**
     * Удаляет элемент под именем из очереди анимации.
     *
     * @param name    имя элемента.
     * @param gotoEnd флаг, определяющий, заканчивать ли анимацию удаляемого
     *                элемента.
     */
    public void remove(String name, boolean gotoEnd) {
        if (name == null) throw new NullPointerException("name == null");

        // Находим индекс удаляемого элемента.
        int index = this.queue.indexOf(name);
        // Если такого элемента нет в очереди, то выходим.
        if (index == -1) return;
        // Находим индекс активного элемента.
        int currentIndex = this.queue.currentIndex;
        // Удаляем и получаем элемент.
        Element removed = this.queue.remove(name);
        // Останавливаем анимацию.
        removed.animation.stop(gotoEnd);

        float duration = this.getTimeFromTimeMode(removed.animation.getTotalDurationInMilliseconds());
        // Обновляем пройденное время и продолжительность анимации очереди.
        if (currentIndex >= index) {
            this.elapsedTime -= duration;
        }
        this.duration -= duration;


        // Если удаляем активный элемент,
        if (currentIndex == index) {
            // то переходим к следующему.
            //this.next();
        }
    }

    /**
     * Запускает анимацию очереди, начиная с элемента под заданным индексом.
     *
     * @param index индекс.
     */
    public void start(int index) {
        if (index < 0) throw new IndexOutOfBoundsException("index < 0");
        if (index >= this.queue.size()) throw new IndexOutOfBoundsException("index >= queue.size");

        // Если анимацию нужно удалить, то выходим.
        if (this.state == State.REMOVE) return;
        // Если анимация уже запущена или на приостановлена.
        if (this.state != State.STOP) {
            // Получаем активный элемент.
            Element current = this.queue.current();
            // Останавливаем анимацию.
            current.animation.stop(true);
            // Запускаем событие
            this.fireEvent(AnimationEvent.STOP);
        }

        // Находим элемент.
        Element started = this.queue.get(index);
        // Если его нет, то останавливаем анимацию.
        if (started == null) {
            // Устанавливаем состояние.
            this.state = State.STOP;
            // Выходим.
            return;
        }
        // Устанавливаем состояние.
        this.state = State.START;
        // Устанавливаем индекс активного элемента.
        this.queue.currentIndex = index;
        // Обновляем пройденное время анимации очереди.
        this.reset();
        for (int i = 0; i < index; i++) {
            Element element = this.queue.get(i);
            this.elapsedTime += this.getTimeFromTimeMode(element.animation.getTotalDurationInMilliseconds());
        }

        // Запускаем анимацию.
        //started.animation.reset();
        started.animation.start();
    }

    /**
     * Запускает анимацию очереди, начиная с элемента под заданным именем.
     *
     * @param name имя элемента.
     */
    public void start(String name) {
        if (name == null) throw new NullPointerException("name == null");

        // Если анимацию нужно удалить, то выходим.
        if (this.state == State.REMOVE) return;
        // Если анимация уже запущена или на приостановлена.
        if (this.state != State.STOP) {
            // Получаем активный элемент.
            Element current = this.queue.current();
            // Останавливаем анимацию.
            current.animation.stop(true);
            // Запускаем событие
            this.fireEvent(AnimationEvent.STOP);
        }

        // Находим элемент.
        Element started = this.queue.get(name);
        // Если его нет, то останавливаем анимацию.
        if (started == null) {
            // Устанавливаем состояние.
            this.state = State.STOP;
            // Выходим.
            return;
        }
        // Устанавливаем состояние.
        this.state = State.START;
        // Учтанавливаем индекс активного элемента.
        this.queue.currentIndex = this.queue.indexOf(started);
        // Обновляем пройденное время анимации очереди.
        this.reset();
        //for (int i = 0; i < this.queue.currentIndex; i++) {
        //    Element element = this.queue.get(i);
        //    this.elapsedTime += this.getTimeFromTimeMode(element.animation.getTotalDurationInMilliseconds());
        //}

        // Запускаем анимацию.
        //started.animation.reset();
        started.animation.start();
    }

    @Override
    protected boolean canRemove() {
        return this.state == State.REMOVE;
    }

    @Override
    public void start() {
        // Если анимация уже запущена, то выходим.
        if (this.state == State.START) return;
        // Если анимация будет удалена, то выходим.
        if (this.state == State.REMOVE) return;

        // Если очередь пуста, то останавливаем анимацию и выходим.
        if (this.queue.size() == 0) {
            return;
        }

        // Устанавливаем состояние.
        this.state = State.START;
        // Находим активный элемент.
        Element current = this.queue.current();
        // Сбрасываем состояние
        this.reset();
        //for (int i = 0; i < this.queue.currentIndex; i++) {
        //    Element element = this.queue.get(i);
        //    this.elapsedTime += this.getTimeFromTimeMode(element.animation.getTotalDurationInMilliseconds());
        //}

        // Перезапускаем активную анимацию.
        //current.animation.reset();
        current.animation.start();
    }

    @Override
    protected boolean update(float elapsedTime) {
        // Находим активный элемент.
        Element current = this.queue.current();
        // Запускаем шаг активной анимации.
        current.animation.step(current.animation.getTimeFromTimeMode(elapsedTime));

        // Обновляем пройденное время анимации очереди.
        //this.elapsedTime = this.getTimeFromTimeMode(current.animation.getTotalElapsedTimeInMilliseconds());
        //for (int i = 0; i < this.queue.currentIndex; i++) {
        //    Element element = this.queue.get(i);
        //    this.elapsedTime += this.getTimeFromTimeMode(element.animation.getTotalDurationInMilliseconds());
        //}

        // Если анимация еще не закончилась.
        if (!current.animation.isStop()) return true;

        // Если включено анимирование по очереди.
        if (!this.oneRun) {
            // Если есть возможность перехода следующий элемент.
            if (this.queue.canNext()) {
                // Переходим на следующий элемент.
                current = this.queue.next();
                // Запускаем его.
                current.animation.start();
            } else {
                // Повтор
                this.repeat();
                // Если есть возможность повторить анимацию.
                if (this.canRepeat()) {
                    // Запускаем событие.
                    this.fireEvent(AnimationEvent.END);
                    // Сбрасываем параметры
                    this.reset();
                    // Переходим на следующий элемент.
                    current = this.queue.current();
                    // Запускаем его.
                    current.animation.start();
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }

        return true;
    }

    @Override
    public void pause() {
        // Если анимация уже на паузе, то выходим.
        if (this.state == State.PAUSE) return;
        // Если анимацию нужно удалить, то выходим.
        if (this.state == State.REMOVE) return;

        // Устанавливаем состояние.
        this.state = State.PAUSE;

        // Находим активный элемент.
        Element current = this.queue.current();
        // Приостаналиваем ее.
        current.animation.pause();
    }

    @Override
    public void resume() {
        // Если анимация не на паузе, то выходим.
        if (this.state != State.PAUSE) return;

        // Устанавливаем состояние.
        this.state = State.START;

        // Находим активный элемент.
        Element current = this.queue.current();
        // Приостаналиваем ее.
        current.animation.resume();
    }

    @Override
    public void stop() {
        this.stop(false, false);
    }

    @Override
    public boolean stop(boolean gotoEnd) {
        return this.stop(gotoEnd, false);
    }

    /**
     * Останавливает анимацию.
     *
     * @param clear   флаг очистки всей очереди.
     * @param gotoEnd флаг установли конечных значений всем свойствам первого
     *                элемента очереди.
     */
    public boolean stop(boolean gotoEnd, boolean clear) {
        // Если анимацию нужно удалить, то выходим.
        if (this.state == State.REMOVE) return false;
        // Если анимация уже остановлена.
        if (this.state == State.STOP) {
            // Если нужно очистить очередь.
            if (clear) {
                this.clear();
            }
            // Выходим.
            return true;
        }

        // Устанавливает состояние.
        this.state = State.STOP;

        // Находим активный элемент.
        Element current = this.queue.current();
        // Останавливаем ее.
        current.animation.stop(gotoEnd);

        // Если нужно завершить анимацию активного элемента очереди.
        if (gotoEnd) {
            //
            current.animation.end();
            // Обновляем продолжительность анимации очереди.
            this.elapsedTime += this.getTimeFromTimeMode(current.animation.getTotalElapsedTimeInMilliseconds());
            // Если не нужно очищать очередь,
            if (!clear) {
                // то переходим к следующему элементу очереди.
                // Если есть возможность перехода следующий элемент.
                if (this.queue.canNext()) {
                    // Переходим на следующий элемент.
                    current = this.queue.next();
                } else {
                    // Запускаем событие.
                    this.fireEvent(AnimationEvent.END);
                    //
                    this.repeat();
                    // Если есть возможность повторить анимацию.
                    if (this.canRepeat()) {
                        // Сбрасываем параметры
                        this.reset();
                    } else {
                        // Запускаем событие
                        this.fireEvent(AnimationEvent.COMPLETE);
                        return true;
                    }
                }
                // Выходим.
                return true;
            }
        }

        // Если нужно очистить очередь.
        if (clear) {
            this.clear();
        }

        return true;
    }

    @Override
    public void restart() {
        super.restart();
        // Сбрасываем параметры анимации.
        this.reset();
    }

    @Override
    protected void reset() {
        super.reset();
        // Сбрасываем очередь.
        this.queue.reset();
    }

    /**
     * Устанавливает прогресс анимации всей очереди.
     *
     * @param position прогресс от 0 до 1.
     */
    @Override
    public void setPosition(float position) {
        super.setPosition(position);

        float d1 = this.getDurationInMilliseconds();
        float d2 = 0.0f;
        int qSize = this.queue.size();
        for (int i = 0; i < qSize; i++) {
            Element element = this.queue.get(i);

            float d3 = element.animation.getTotalDurationInMilliseconds();
            d2 += d3;

            if (d2 / d1 >= position) {
                this.queue.currentIndex = i;
                float d4 = d1 * position - d3;
                float d5 = element.animation.getDurationInMilliseconds();
                element.animation.completedRepeat = (int) (d4 / d5);
                element.animation.elapsedTime = element.animation.getTimeFromTimeMode(d4 % d5);
                break;
            }
        }
    }

    /**
     * Очищает очередь анимации.
     */
    public void clear() {
        // Устанавливаем состояние.
        this.state = State.STOP;
        // Сбрасываем параметры анимации.
        this.reset();
        this.duration = 0.0f;
        // Очищаем очередь.
        this.queue.clear();
    }

    /**
     * Создает и возвращает новый объект построителя анимации.
     *
     * @return построитель анимации.
     */
    public static SequenceAnimationBuilder builder() {
        return new SequenceAnimationBuilder();
    }

    /**
     * Класс элемента очереди.
     *
     * @author Ilyas74
     * @version 0.1.4
     */
    public static class Element {

        /**
         * Имя элемента.
         */
        protected String name = null;
        /**
         * Анимация.
         */
        protected ControllableAnimation animation = null;

        /**
         * Конструктор, устанавливающий анимацию
         *
         * @param animation анимация.
         */
        public Element(ControllableAnimation animation) {
            this.animation = animation;
        }

        /**
         * Конструктор, устанавливающий анимацию и имя элементу.
         *
         * @param name      имя.
         * @param animation анимация.
         */
        public Element(String name, ControllableAnimation animation) {
            this.name = name;
            this.animation = animation;
        }

        /**
         * Получает имя элемента.
         *
         * @return имя элемента.
         */
        public String getName() {
            return this.name;
        }

        /**
         * Получает анимацию элемента.
         *
         * @return анимацию элемента.
         */
        public ControllableAnimation getAnimation() {
            return this.animation;
        }

    }

    /**
     * Класс очереди анимации.
     *
     * @author Ilyas74
     * @version 0.0.0
     */
    static class Queue implements Iterable<Element> {

        /**
         * Список анимаций очереди.
         */
        List<Element> list = new ArrayList<>();
        /**
         * Таблица элементов.
         */
        Map<String, Element> map = new HashMap<>();
        /**
         * Индекс активной анимации.
         */
        int currentIndex = 0;

        /**
         * Конструктор, создающий пустую очередь.
         */
        Queue() {
        }

        /**
         * Получает анимацию под индекслм.
         *
         * @param index индес.
         * @return анимацию очереди.
         */
        Element get(int index) {
            return this.list.get(index);
        }

        /**
         * Получает элемент и заданным именем.
         *
         * @param name имя.
         * @return элемент очереди.
         */
        Element get(String name) {
            return this.map.get(name);
        }

        /**
         * Получает индекс элемента очереди.
         *
         * @param name имя элемента.
         * @return индекс элемента.
         */
        int indexOf(String name) {
            Element element = this.map.get(name);
            if (element == null) return -1;
            return this.list.indexOf(element);
        }

        /**
         * Получает индекс элемента очереди.
         *
         * @param element элемент.
         * @return индекс элемента.
         */
        int indexOf(Element element) {
            return this.list.indexOf(element);
        }

        /**
         * Добавляет элемент в очередь.
         *
         * @param element элемент.
         */
        void add(Element element) {
            if (element.name != null) {
                if (this.map.containsKey(element.name)) {
                    this.map.remove(element.name);
                    this.list.remove(element);
                }
            } else {
                element.name = String.valueOf(this.list.size());
            }

            this.list.add(element);
            this.map.put(element.name, element);
        }

        /**
         * Добавляет элемент в очередь.
         *
         * @param index   индекс элемента.
         * @param element элемент.
         */
        void add(int index, Element element) {
            if (element.name != null) {
                if (this.map.containsKey(element.name)) {
                    this.map.remove(element.name);
                    this.list.remove(element);
                }
            } else {
                element.name = String.valueOf(this.list.size());
            }

            this.list.add(index, element);
            this.map.put(element.name, element);
        }

        /**
         * Переходит к слкдующему элементу очереди.
         *
         * @return следующий элемент очереди.
         */
        Element next() {
            this.currentIndex++;
            if (this.currentIndex < 0) throw new IndexOutOfBoundsException("index < 0");
            if (this.currentIndex >= this.list.size()) throw new IndexOutOfBoundsException("index >= list.size");
            return this.list.get(this.currentIndex);
        }

        /**
         * Получает текущий активный элемент очереди.
         *
         * @return текущий элемент очереди.
         */
        Element current() {
            return this.list.get(this.currentIndex);
        }

        /**
         * Проверяет, возможен ли переход к следующему элементу очереди.
         *
         * @return {@code true}, если переход возможен.
         */
        boolean canNext() {
            return this.currentIndex + 1 < this.list.size();
        }

        /**
         * Удаляет активный элемент очереди.
         *
         * @return активный элемент очереди.
         */
        Element remove() {
            Element element = this.list.remove(this.currentIndex);
            if (element != null) this.map.remove(element.name);
            return element;
        }

        /**
         * Удаляет элемент очереди под индексом.
         *
         * @param index индекс.
         * @return удаленный элемент.
         */
        Element remove(int index) {
            Element element = this.list.remove(index);
            if (element != null) this.map.remove(element.name);
            //
            if (this.currentIndex > index) this.currentIndex--;
            return element;
        }

        /**
         * Удаляет элемент очереди под именем.
         *
         * @param name имя.
         * @return удаленный элемент.
         */
        Element remove(String name) {
            Element element = this.map.get(name);
            int index = this.list.indexOf(element);
            if (element != null) this.list.remove(index);
            //
            if (this.currentIndex > index) this.currentIndex--;
            return element;
        }

        /**
         * Сбрасывает текущий индекс.
         */
        void reset() {
            this.currentIndex = 0;
            for (Element element : this.list) {
                element.animation.reset();
            }
        }

        /**
         * Очищает очередь.
         */
        void clear() {
            this.currentIndex = 0;
            this.list.clear();
            this.map.clear();
        }

        /**
         * Получает размер списка очереди.
         *
         * @return количество элементов очереди.
         */
        int size() {
            return this.list.size();
        }

        @Override
        public Iterator<Element> iterator() {
            return this.list.iterator();
        }
    }

    /**
     * Класс построителя анимации.
     *
     * @author Ilyas74
     * @version 0.0.4
     */
    public static class SequenceAnimationBuilder implements Builder<SequenceAnimation> {

        /**
         * Очередь.
         */
        protected Queue queue = null;
        /**
         * Опции анимации.
         */
        protected AnimationOptions options = null;
        /**  */
        protected boolean oneRun = false;

        /**
         * Конструктор.
         */
        protected SequenceAnimationBuilder() {
            this.queue = new Queue();
            this.options = new AnimationOptions();
        }

        /**
         * Изменяет состояние анимирования по очереди.
         *
         * @param flag флаг.
         * @return данный построитель.
         */
        public SequenceAnimationBuilder oneRun(boolean flag) {
            this.oneRun = flag;
            return this;
        }

        /**
         * Добавляет в очередь анимации новый элемент.
         *
         * @param animation элемент.
         * @return данный построитель.
         */
        public SequenceAnimationBuilder element(ControllableAnimation animation) {
            if (animation == null) return this;
            this.queue.add(new Element(animation));
            return this;
        }

        /**
         * Добавляет в очередь анимации новый элемент.
         *
         * @param index
         * @param animation элемент.
         * @return данный построитель.
         */
        public SequenceAnimationBuilder element(int index, ControllableAnimation animation) {
            if (animation == null) return this;
            if (index < 0 || index > this.queue.size()) return this;
            this.queue.add(index, new Element(animation));
            return this;
        }

        /**
         * Добавляет в очередь анимации новый элемент.
         *
         * @param name      имя элемента очереди.
         * @param animation элемент.
         * @return данный построитель.
         */
        public SequenceAnimationBuilder element(String name, ControllableAnimation animation) {
            if (animation == null) return this;
            this.queue.add(new Element(name, animation));
            return this;
        }

        /**
         * Добавляет в очередь анимации новый элемент.
         *
         * @param index
         * @param name
         * @param animation элемент.
         * @return данный построитель.
         */
        public SequenceAnimationBuilder element(int index, String name, ControllableAnimation animation) {
            if (animation == null) return this;
            if (index < 0 || index > this.queue.size()) return this;
            this.queue.add(index, new Element(name, animation));
            return this;
        }

        /**
         * Устанавливает обработчик событий анимации.
         *
         * @param listener обработчик событий анимации.
         * @return данный построитель.
         */
        public SequenceAnimationBuilder listener(AnimationListener listener) {
            this.options.setListener(listener);
            return this;
        }

        /**
         * Устанавливает продолжительность задержки анимации.
         *
         * @param delay продолжительность задержки анимации.
         * @return данный построитель.
         */
        public SequenceAnimationBuilder delay(float delay) {
            this.options.setDelay(delay);
            return this;
        }

        /**
         * Устанавливает количество повторов анимации.
         *
         * @param repeat количество повторов анимации.
         * @return данный построитель.
         */
        public SequenceAnimationBuilder repeat(int repeat) {
            this.options.setRepeat(repeat);
            return this;
        }

        /**
         * Устанавливает продолжительность задержки между повторами анимации.
         *
         * @param repeatDelay продолжительность задержки между повторами анимации.
         * @return данный построитель.
         */
        public SequenceAnimationBuilder repeatDelay(float repeatDelay) {
            this.options.setRepeatDelay(repeatDelay);
            return this;
        }

        /**
         * Устанавливает режим времени анимации.
         *
         * @param timemode режим времени.
         * @return данный построитель.
         * @see AnimationConstants#MILLISECONDS
         * @see AnimationConstants#SECONDS
         * @see AnimationConstants#FRAMES
         */
        public SequenceAnimationBuilder timemode(int timemode) {
            this.options.setTimeMode(timemode);
            return this;
        }

        /**
         * Устанавливает режим работы анимации.
         *
         * @param playmode режим работы.
         * @return данный построитель.
         * @see AnimationConstants#FORWARD
         * @see AnimationConstants#BACKWARD
         */
        public SequenceAnimationBuilder playmode(int playmode) {
            this.options.setPlayMode(playmode);
            return this;
        }

        /**
         * Устанавливает опции анимации.
         *
         * @param options опции.
         * @return данный построитель.
         */
        public SequenceAnimationBuilder options(AnimationOptions options) {
            if (options == null) return this;
            this.options.copy(options);
            return this;
        }

        /**
         * Сбрасывает данный построитель.
         *
         * @return данный построитель.
         */
        public SequenceAnimationBuilder reset() {
            this.queue = new Queue();
            this.options = new AnimationOptions();
            return this;
        }

        @Override
        public SequenceAnimation build() {
            SequenceAnimation animation = new SequenceAnimation(this.queue, this.options, this.oneRun);
            this.reset();
            return animation;
        }

    }

}
