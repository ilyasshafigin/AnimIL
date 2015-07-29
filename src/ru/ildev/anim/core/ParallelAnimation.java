/*
 *
 */
package ru.ildev.anim.core;

import ru.ildev.anim.events.AnimationListener;
import ru.ildev.utils.Builder;

import java.util.*;

/**
 * Класс анимации, которая параллельно запускаем дочерние анимации. Такой
 * объект позволяет управлять сразу несколькими анимациями.
 *
 * @author Shafigin Ilyas (Шафигин Ильяс)
 * @version 0.0.4
 */
public class ParallelAnimation extends ControllableAnimation {

    /**
     * Класс дочернего элемента.
     *
     * @author Ilyas74
     * @version 0.1.4
     */
    public static class Child {

        /**
         * Имя элемента.
         */
        protected String name = null;
        /**
         * Анимация.
         */
        protected ControllableAnimation animation = null;

        /**
         * Стандартный конструктор.
         */
        protected Child() {
        }

        /**
         * Конструктор, устанавливающий анимацию
         *
         * @param animation анимация.
         */
        public Child(ControllableAnimation animation) {
            this.animation = animation;
        }

        /**
         * Конструктор, устанавливающий анимацию и имя элементу.
         *
         * @param name      имя.
         * @param animation анимация.
         */
        public Child(String name, ControllableAnimation animation) {
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
     * Класс, объединяющий дочерние элементы.
     *
     * @author Ilyas74
     * @version 0.0.0
     */
    protected static class Children implements Iterable<Child> {

        /**
         * Список элементов.
         */
        List<Child> list = new ArrayList<>();
        /**
         * Таблица элементов.
         */
        Map<String, Child> map = new HashMap<>();

        /**
         * Конструктор, создающий пустую объект.
         */
        Children() {
        }

        /**
         * Получает элемент под индексом.
         *
         * @param index индес.
         * @return элемент.
         */
        Child get(int index) {
            return this.list.get(index);
        }

        /**
         * Получает элемент под заданным именем.
         *
         * @param name имя.
         * @return элемент.
         */
        Child get(String name) {
            return this.map.get(name);
        }

        /**
         * Получает индекс элемента под заданным именем.
         *
         * @param name имя элемента.
         * @return индекс элемента.
         */
        int indexOf(String name) {
            Child child = this.map.get(name);
            if (child == null) return -1;
            return this.list.indexOf(child);
        }

        /**
         * Получает индекс элемента.
         *
         * @param child элемент.
         * @return индекс элемента.
         */
        int indexOf(Child child) {
            return this.list.indexOf(child);
        }

        /**
         * Добавляет элемент.
         *
         * @param child элемент.
         */
        void add(Child child) {
            if (child.name != null) {
                if (this.map.containsKey(child.name)) {
                    this.map.remove(child.name);
                    this.list.remove(child);
                }
            } else {
                child.name = String.valueOf(this.list.size());
            }

            this.list.add(child);
            this.map.put(child.name, child);
        }

        /**
         * Добавляет элемент.
         *
         * @param index индекс.
         * @param child элемент.
         */
        void add(int index, Child child) {
            if (child.name != null) {
                if (this.map.containsKey(child.name)) {
                    this.map.remove(child.name);
                    this.list.remove(child);
                }
            } else {
                child.name = String.valueOf(this.list.size());
            }

            this.list.add(index, child);
            this.map.put(child.name, child);
        }

        /**
         * Удаляет элемент под индексом.
         *
         * @param index индекс.
         * @return удаленный элемент.
         */
        Child remove(int index) {
            Child child = this.list.remove(index);
            if (child != null) this.map.remove(child.name);
            return child;
        }

        /**
         * Удаляет элемент под именем.
         *
         * @param name имя.
         * @return удаленный элемент.
         */
        Child remove(String name) {
            Child child = this.map.get(name);
            int index = this.list.indexOf(child);
            if (child != null) this.list.remove(index);
            return child;
        }

        /**
         * Очищает объект.
         */
        void clear() {
            this.list.clear();
            this.map.clear();
        }

        /**
         * Получает размер списка элементов.
         *
         * @return количество элементов.
         */
        int size() {
            return this.list.size();
        }

        @Override
        public Iterator<Child> iterator() {
            return this.list.iterator();
        }

    }

    /**
     * Список дочерних элеменотов.
     */
    protected Children children = null;

    /**
     * Конструктор, создающий анимацию с заданными дочерними анимациями и
     * опциями.
     *
     * @param children дочерние элементы.
     * @param options  опции.
     */
    protected ParallelAnimation(Children children, AnimationOptions options) {
        super();

        if (children == null) throw new NullPointerException("children == null");
        if (options == null) throw new NullPointerException("options == null");

        this.children = children;
        this.copy(options);
    }

    /**
     * Конструктор, создающий пустую анимацию.
     */
    public ParallelAnimation() {
        super();

        this.children = new Children();
    }

    /**
     * Конструктор, создающий анимацию с заданными опциями.
     *
     * @param options опции.
     */
    public ParallelAnimation(AnimationOptions options) {
        super();

        if (options == null) throw new NullPointerException("options == null");

        this.children = new Children();
        this.copy(options);
    }

    /**
     * Получает дочернюю анимацию под индексом.
     *
     * @param index индекс.
     * @return дочернюю анимацию.
     */
    public Child getChild(int index) {
        if (index < 0) throw new IndexOutOfBoundsException("index < 0");
        if (index >= this.children.size()) throw new IndexOutOfBoundsException("index >= children.size");
        return this.children.get(index);
    }

    /**
     * Получает дочернюю анимацию под заданным именем.
     *
     * @param name имя анимации.
     * @return дочернюю анимацию.
     */
    public Child getChild(String name) {
        if (name == null) throw new NullPointerException("name == null");
        return this.children.get(name);
    }

    /**
     * Добавляет анимацию в конец списка.
     *
     * @param animation анимация.
     */
    public void addChild(ControllableAnimation animation) {
        if (animation == null) throw new NullPointerException("animation == null");
        this.children.add(new Child(animation));
    }

    /**
     * Добавляет анимацию в список на заданое место.
     *
     * @param index     индекс.
     * @param animation анимация.
     */
    public void addChild(int index, ControllableAnimation animation) {
        if (animation == null) throw new NullPointerException("animation == null");
        if (index < 0) throw new IndexOutOfBoundsException("index < 0");
        if (index >= this.children.size()) throw new IndexOutOfBoundsException("index >= children.size");
        this.children.add(index, new Child(animation));
    }

    /**
     * Добавляет анимацию в конец списка и задает ей имя.
     *
     * @param name      имя анимации.
     * @param animation анимация.
     */
    public void addChild(String name, ControllableAnimation animation) {
        if (animation == null) throw new NullPointerException("animation == null");
        if (name == null) throw new NullPointerException("name == null");
        this.children.add(new Child(name, animation));
    }

    /**
     * Добавляет анимацию в список на заданое место и задает ей имя.
     *
     * @param index     индекс.
     * @param name      имя анимации.
     * @param animation анимация.
     */
    public void addChild(int index, String name, ControllableAnimation animation) {
        if (animation == null) throw new NullPointerException("animation == null");
        if (name == null) throw new NullPointerException("name == null");
        if (index < 0) throw new IndexOutOfBoundsException("index < 0");
        if (index >= this.children.size()) throw new IndexOutOfBoundsException("index >= children.size");
        this.children.add(index, new Child(name, animation));
    }

    /**
     * Удаляет дочернюю анимацию.
     *
     * @param animation анимация.
     * @return удаленный элемент.
     */
    public Child removeChild(ControllableAnimation animation) {
        int cSize = this.children.size();
        for (int i = 0; i < cSize; i++) {
            Child child = this.children.get(i);
            if (child.animation == animation) {
                this.children.remove(i);
                return child;
            }
        }
        return null;
    }

    /**
     * Удаляет дочернюю анимацию.
     *
     * @param index индекс анимации.
     * @return удаленный элемент.
     */
    public Child removeChild(int index) {
        if (index >= 0 && index < this.children.size()) return null;
        return this.children.remove(index);
    }

    /**
     * Удаляет дочернюю анимацию.
     *
     * @param name
     * @return удаленный элемент.
     */
    public Child removeChild(String name) {
        if (name == null) return null;
        return this.children.remove(name);
    }

    @Override
    public boolean step(float elapsedTime) {
        // Если анимацию нужно удалить.
        if (this.state == State.REMOVE) return true;
        // Если анимация остановлена.
        if (this.state == State.STOP) return true;
        // Если на паузе.
        if (this.state == State.PAUSE) return false;

        // Если пройденное время некорректно, то выходим.
        //if(elapsedTime <= 0.0f) return false;

        boolean stopped = true;
        // Проходим по дочерним элементам.
        for (Child child : this.children) {
            stopped &= child.animation.step(child.animation.getTime(elapsedTime, this));
        }

        // Возвращаем ...
        return false;//stopped;
    }

    /**
     * Очищает очередь анимации.
     */
    public void clear() {
        // Устанавливаем состояние.
        this.state = State.STOP;
        // Очищаем список дочерних элементов.
        this.children.clear();
    }

    /**
     * Создает и возвращает новый объект построителя анимации.
     *
     * @return построитель анимации.
     */
    public static ParallelAnimationBuilder builder() {
        return new ParallelAnimationBuilder();
    }

    /**
     * Класс построителя анимации.
     *
     * @author Ilyas74
     * @version 0.0.1
     */
    public static class ParallelAnimationBuilder implements Builder<ParallelAnimation> {

        /**
         * Список дочерних элементов.
         */
        protected Children children;
        /**
         * Опции анимации.
         */
        protected AnimationOptions options;

        /**
         * Конструктор.
         */
        protected ParallelAnimationBuilder() {
            this.children = new Children();
            this.options = new AnimationOptions();
        }

        /**
         * Добавляет в очередь анимации новый дочерний элемент.
         *
         * @param animation анимация.
         * @return данный построитель.
         */
        public ParallelAnimationBuilder child(ControllableAnimation animation) {
            if (animation == null) return this;
            this.children.add(new Child(animation));
            return this;
        }

        /**
         * Добавляет в очередь анимации новый дочерний элемент.
         *
         * @param index     индекс элемента в списке.
         * @param animation анимация.
         * @return данный построитель.
         */
        public ParallelAnimationBuilder child(int index, ControllableAnimation animation) {
            if (index < 0 || index > this.children.size()) return this;
            if (animation == null) return this;
            this.children.add(new Child(animation));
            return this;
        }

        /**
         * Добавляет в очередь анимации новый дочерний элемент.
         *
         * @param name      имя элемента очереди.
         * @param animation анимация.
         * @return данный построитель.
         */
        public ParallelAnimationBuilder child(String name, ControllableAnimation animation) {
            if (animation == null) return this;
            this.children.add(new Child(name, animation));
            return this;
        }

        /**
         * Добавляет в очередь анимации новый дочерний элемент.
         *
         * @param index     индекс элемента в списке.
         * @param name      имя элемента очереди.
         * @param animation анимация.
         * @return данный построитель.
         */
        public ParallelAnimationBuilder child(int index, String name, ControllableAnimation animation) {
            if (index < 0 || index > this.children.size()) return this;
            if (animation == null) return this;
            this.children.add(new Child(name, animation));
            return this;
        }

        /**
         * Устанавливает обработчик событий анимации.
         *
         * @param listener обработчик событий анимации.
         * @return данный построитель.
         */
        public ParallelAnimationBuilder listener(AnimationListener listener) {
            this.options.setListener(listener);
            return this;
        }

        /**
         * Устанавливает опции анимации.
         *
         * @param options опции.
         * @return данный построитель.
         */
        public ParallelAnimationBuilder options(AnimationOptions options) {
            if (options == null) return this;
            this.options.copy(options);
            return this;
        }

        /**
         * Сбрасывает данный построитель.
         *
         * @return данный построитель.
         */
        public ParallelAnimationBuilder reset() {
            this.children = new Children();
            this.options = new AnimationOptions();
            return this;
        }

        @Override
        public ParallelAnimation build() {
            ParallelAnimation animation = new ParallelAnimation(this.children, this.options);
            this.reset();
            return animation;
        }

    }

}
