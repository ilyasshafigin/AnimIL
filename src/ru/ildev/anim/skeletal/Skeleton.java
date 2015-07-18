/*
 *
 */
package ru.ildev.anim.skeletal;

import java.util.Iterator;

/**
 * Класс скелета.
 *
 * @author Shafigin Ilyas (Шафигин Ильяс) <Ilyas74>
 * @version 0.1.2
 */
public class Skeleton implements Iterable<Bone> {

    /**
     * Корневая кость.
     */
    protected Bone root = new Bone("root");

    /**
     * Конструктор, создающий объект пустого скелета.
     */
    public Skeleton() {
    }

    /**
     * Конструктор, созающий объект скелета с заданной корневой костью
     *
     * @param root корневая кость.
     */
    public Skeleton(Bone root) {
        if (root == null) throw new NullPointerException("root == null");

        this.root = root;
    }

    /**
     * Получает корневую кость.
     *
     * @return корневую кость.
     */
    public Bone getRoot() {
        return this.root;
    }

    /**
     * Устанавливает корневую кость.
     *
     * @param root корневая кость.
     */
    public void setRoot(Bone root) {
        if (root == null) throw new NullPointerException("root == null");

        this.root = root;
    }

    /**
     * Удаляет кость скелета.
     *
     * @param name имя кости.
     */
    public void remove(String name) {
        Bone bone = this.root.getChild(name);
        bone.remove();
    }

    /**
     * Удаляет кость скелета.
     *
     * @param bone кость.
     */
    public void remove(Bone bone) {
        bone.remove();
    }

    /**
     * Находит кость по ее имени.
     *
     * @param name имя кости.
     * @return кость.
     */
    public Bone find(String name) {
        return this.root.getChild(name);
    }

    /**
     * Обновляет скелет.
     */
    public void update() {
        this.root.update();
    }

    @Override
    public String toString() {
        if (this.root == null) {
            return "Skleton[root=null]";
        } else {
            return "Skeleton[root=" + this.root.name + "]";
        }
    }

    @Override
    public Iterator<Bone> iterator() {
        return this.root.toList().iterator();
    }

}
