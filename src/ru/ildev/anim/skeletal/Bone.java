/*
 *
 */
package ru.ildev.anim.skeletal;

import ru.ildev.geom.Vector2;
import ru.ildev.math.MoreMath;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Класс кости на плоскости.
 * Особенности:
 * <ul>
 * <li>Кости можно задать позицию и длину.</li>
 * <li>Началом кости является ее позиция, концом - позиция с учетом ее длины.</li>
 * <li>Позиции дочерних костей отсчитываются от конца родительской кости.</li>
 * </ul>
 *
 * @author Shafigin Ilyas (Шафигин Ильяс)
 * @version 0.1.5
 */
public class Bone implements Cloneable, Iterable<Bone> {

    /**
     * Имя кости.
     */
    protected String name = "bone";

    /**
     * Локальная позиция, т.е позиция относительно родительской кости.
     */
    protected Vector2 position = new Vector2();
    /** Абсолютная позиция. */
    //protected Vector2 absolutePosition = new Vector2();

    /**
     * Локальный угол поворота кости в градусах.
     */
    protected float angle = 0.0f;
    /** Абсолютный угол поворота кости в градусах. */
    //protected float absoluteAngle = 0.0f;
    /** Максимальный угол поворота кости в градусах. */
    //protected float maxAngle = 0.0f;
    /** Минимальный угол поворота кости в градусах. */
    //protected float minAngle = 0.0f;

    /**
     * Длина кости.
     */
    protected float length = 0.0f;

    /**
     * Родительская кость.
     */
    protected Bone parent = null;
    /**
     * Дочерние кости.
     */
    protected List<Bone> children = new ArrayList<>();

    /**
     * Конструктор, создающий пустую кость.
     */
    public Bone() {
    }

    /**
     * Конструктор, создающий объект кости с заданным именем.
     *
     * @param name имя кости.
     */
    public Bone(String name) {
        if (name == null) throw new NullPointerException("name == null");

        this.name = name;
    }

    /**
     * Конструктор, создающий объект кости с заданным именем, уголом поворота и
     * длиной.
     *
     * @param name   имя кости.
     * @param angle  угол поворота кости в градусах.
     * @param length длина кости.
     */
    public Bone(String name, float angle, float length) {
        if (name == null) throw new NullPointerException("name == null");

        this.name = name;
        this.angle = angle;
        this.length = length;
    }

    /**
     * Конструктор, создающий объект кости с заданным именем, уголом поворота,
     * длиной и позицией.
     *
     * @param name     имя.
     * @param position позиция.
     * @param angle    угол поворота.
     * @param length   длина.
     */
    public Bone(String name, Vector2 position, float angle, float length) {
        if (name == null) throw new NullPointerException("name == null");
        if (position == null) throw new NullPointerException("position == null");

        this.name = name;
        this.position.copy(position);
        this.angle = angle;
        this.length = length;
    }

    /**
     * Конструктор копирования.
     *
     * @param bone кость.
     */
    public Bone(Bone bone) {
        if (bone == null) throw new NullPointerException("bone == null");
        this.name = bone.name;
        this.position.copy(bone.position);
        this.angle = bone.angle;
        //this.minAngle = bone.minAngle;
        //this.maxAngle = bone.maxAngle;
        this.length = bone.length;
        this.parent = bone.parent;
        for (Bone child : bone.children) {
            this.children.add(child.clone());
        }
    }

    @Override
    public Bone clone() {
        Bone clone = new Bone();
        clone.name = this.name;
        clone.position.copy(this.position);
        clone.angle = this.angle;
        //this.minAngle = bone.minAngle;
        //this.maxAngle = bone.maxAngle;
        clone.length = this.length;
        clone.parent = this.parent;
        for (Bone child : this.children) {
            clone.children.add(child.clone());
        }
        return clone;
    }

    /**
     * Получает имя кости.
     *
     * @return имя кости.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Устанавливает имя кости.
     *
     * @param name имя.
     */
    public void setName(String name) {
        if (name == null) throw new NullPointerException("name == null");

        this.name = name;
    }

    /**
     * Получает позицию кости относительно ее родителя.
     *
     * @return позицию кости.
     */
    public Vector2 getPosition() {
        return this.position;
    }

    /**
     * Устанавливает позицию кости.
     *
     * @param position позиция относительно родительской кости.
     */
    public void setPosition(Vector2 position) {
        if (position == null) throw new NullPointerException("position == null");

        this.position.copy(position);
    }

    /**
     * Устанавливает позицию кости.
     *
     * @param x x-координата позиции кости относительно ее родителя.
     * @param y y-координата позиции кости относительно ее родителя.
     */
    public void setPosition(float x, float y) {
        this.position.set(x, y);
    }

    /**
     * Получает x-координату позиции кости относительно ее родителя.
     *
     * @return x-координату позиции кости.
     */
    public float getX() {
        return this.position.x;
    }

    /**
     * Получает y-координату позиции кости относительно ее родителя.
     *
     * @return y-координату позиции кости.
     */
    public float getY() {
        return this.position.y;
    }

    /**
     * Устанавливает x-координате позиции кости новое значение.
     *
     * @param x x-координата позиции кости относительно ее родителя.
     */
    public void setX(float x) {
        this.position.x = x;
    }

    /**
     * Устанавливает y-координате позиции кости новое значение.
     *
     * @param y y-координата позиции кости относительно ее родителя.
     */
    public void setY(float y) {
        this.position.y = y;
    }

    /**
     * Получает абсолютную позицию кости.
     *
     * @return абсолютную позицию кости.
     */
    //public Vector2 getAbsolutePosition() {
    //    //return this.absolutePosition;
    //    Vector2 p = new Vector2();
    //    p.set(this.position);
    //    if(this.parent != null) {
    //        p.rotate(this.parent.angle);
    //        p.add(this.parent.getAbsolutePosition());
    //    }
    //   return p;
    //}

    /**
     * Получает угол поворота кости.
     *
     * @return угол поворота кости в градусах.
     */
    public float getAngle() {
        return this.angle;
    }

    /**
     * Устанавливает угол поворота кости.
     *
     * @param angle угол поворота в градусах.
     */
    public void setAngle(float angle) {
        this.angle = angle;
    }

    /**
     * Получает абсолютный угол поворота кости.
     *
     * @return абсолютный угол поворота кости.
     */
    //public float getAbsoluteAngle() {
    //    return this.absoluteAngle;
    //}

    ///**
    // * Получает максимальный угол поворота кости.
    // *
    // * @return максимальный угол поворота кости в градусах.
    // */
    //public float getMaxAngle() {
    //    return this.maxAngle;
    //}

    ///**
    // * Устанавливает максимальный угол поворота кости.
    // *
    // * @param maxAngle максимальный угол поворота в градусах.
    // */
    //public void setMaxAngle(float maxAngle) {
    //    this.maxAngle = maxAngle;
    //}

    ///**
    // * Получает минимальный угол поворота кости.
    // *
    // * @return минимальный угол поворота кости в градусах.
    // */
    //public float getMinAngle() {
    //    return this.minAngle;
    //}

    ///**
    // * Устанавливает минимальный угол поворота кости.
    // *
    // * @param minAngle минимальный угол поворота кости в градусах.
    // */
    //public void setMinAngle(float minAngle) {
    //    this.minAngle = minAngle;
    //}

    /**
     * Получает длину кости.
     *
     * @return длину кости.
     */
    public float getLength() {
        return this.length;
    }

    /**
     * Устанавливает длину кости.
     *
     * @param length длина кости.
     */
    public void setLength(float length) {
        this.length = length;
    }

    /**
     * Получает родительскую кость.
     *
     * @return родительскую кость.
     */
    public Bone getParent() {
        return this.parent;
    }

    /**
     * Получает список дочерних костей.
     *
     * @return список дочерних костей.
     */
    public List<Bone> getChildren() {
        return this.children;
    }

    /**
     * Получает количество дочерних костей.
     *
     * @return количество дочерних костей.
     */
    public int getChildrenCount() {
        return this.children.size();
    }

    ///**
    // * Получает трнасформацию данной кости.
    // *
    // * @return трнасформацию данной кости.
    // */
    //public Transform2 getTransform() {
    //    Transform2 transform = new Transform2();
    //    transform.translateLocal(this.absolutePosition);
    //    transform.rotate(this.absoluteAngle);
    //    return transform;
    //}

    /**
     * Получает дочернюю кость данной кости по её имени. Если имя
     * {@code childName} совпадает с именем данное кости, то возвращает данную
     * кость.
     *
     * @param childName иля дочерней кости.
     * @return дочернюю кость.
     */
    public Bone getChild(String childName) {
        // Если имя кости совпадает с именем данной кости,
        if (this.name.equals(childName)) {
            // то возвращаем данную кость.
            return this;
        }
        // Проходим по дочерним костям.
        for (Bone child : this.children) {
            // Находим дочернюю кость следующего уровня.
            Bone subChild = child.getChild(childName);
            // Если нашли,
            if (subChild != null) {
                // то возвращаем ее.
                return subChild;
            }
        }
        //
        return null;
    }

    /**
     * Получает дочернюю кость данной кости по индексу в списке.
     *
     * @param index индекс.
     * @return дочернюю кость.
     */
    public Bone getChild(int index) {
        return this.children.get(index);
    }

    /**
     * Добавляет кость в список дочерних костей данной кости.
     *
     * @param child кость.
     */
    public void add(Bone child) {
        // Если кость не задана, то выходим.
        if (child == null) return;
        // Если добавляемый объект и данный объект одно и тоже.
        if (child == this) return;
        // Если добавляемый объект уже есть в списке дочерних объектов.
        if (this.children.contains(child)) return;
        // Если у добавляемого объекта есть родитель,
        if (child.parent != null) {
            // то его из родителя.
            child.parent.remove(child);
        }

        // Устанавляем родителя добавляемому объекту.
        child.parent = this;
        // Добавляем объект в данный объект.
        this.children.add(child);
    }

    /**
     * Удаляет дочернюю кость из данной кости.
     *
     * @param child дочерняя кость.
     */
    public void remove(Bone child) {
        // Если кость не задана, то выходим.
        if (child == null) return;
        // Если удаляемый объект есть в списке,
        if (this.children.contains(child)) {
            // то удаляем его родимеля
            child.parent = null;
            // и удаляем его из данного объекта.
            this.children.remove(child);
        }
    }

    /**
     * Удаляет дочернюю кость из данной кости.
     *
     * @param childName имя дочерней кости.
     * @return удаленную дочернюю кость.
     */
    public Bone remove(String childName) {
        // Если имя кости не задано, то выходим.
        if (childName == null) return null;
        // Ищем удаляемый объект.
        for (Bone child : this.children) {
            if (child.name.equals(childName)) {
                // Удаляем родителя удаляемого объекта,
                child.parent = null;
                // удаляем его из данного объекта,
                this.children.remove(child);
                // и возвращаем его.
                return child;
            }
        }
        return null;
    }

    /**
     * Удаляет из данной кости все дочерние кости.
     */
    public void removeAll() {
        // Проходим по дочерним костям.
        for (Bone child : this.children) {
            // Удаляем родителя удаляемого объекта.
            child.parent = null;
            // Удаляем его из списка.
            // this.children.remove(child);
        }
        // Очищаем список дочерних костей.
        this.children.clear();
    }

    /**
     * Удаляет данную кость из родителя.
     */
    public void remove() {
        // Если есть родительская кость.
        if (this.parent != null) {
            // Удаляем из родительской кости данную кость.
            this.parent.remove(this);
            // Удаляем родителя.
            this.parent = null;
        }
    }

    /**
     * Обновляет данную кость.
     */
    public void update() {
        //this.angle = MoreMath.clamp(this.angle, this.minAngle, this.maxAngle);
        /*
        this.absoluteAngle = this.angle;
        this.absolutePosition.set(this.position);

        if(this.parent != null) {
            this.absoluteAngle += this.parent.absoluteAngle;
            this.absolutePosition.add(this.parent.absolutePosition);
            this.absolutePosition.add(this.parent.length, 0.0);
        }
        */
        // Проходим по дочерним костям.
        for (Bone child : this.children) {
            // Обновляем дочернюю кость.
            child.update();
        }
    }

    /**
     * Расчитывает точку, координаты которой заданы относительно данной кости.
     *
     * @param point точка с абсолютными координатами.
     * @return точку.
     */
    public Vector2 getRelative(Vector2 point) {
        Vector2 result = new Vector2(point);

        if (this.parent != null) {
            this.parent.getRelative(result, result);
            result.subtract(this.parent.length, 0.0f);
        }

        result.subtract(this.position);
        result.rotate(-MoreMath.radians(this.angle));

        return result;
    }

    /**
     * Расчитывает точку, координаты которой заданы относительно данной кости.
     *
     * @param point  точка с абсолютными координатами.
     * @param result результат.
     * @return точку.
     */
    public Vector2 getRelative(Vector2 point, Vector2 result) {
        result.set(point);

        if (this.parent != null) {
            this.parent.getRelative(result, result);
            result.subtract(this.parent.length, 0.0f);
        }

        result.subtract(this.position);
        result.rotate(-MoreMath.radians(this.angle));

        return result;
    }

    /**
     * Расчитывает точку, координаты которой заданы относительно конца
     * родительской кости.
     *
     * @param point точка с абсолютными координатами.
     * @return точку.
     */
    public Vector2 getRelativeFromParent(Vector2 point) {
        Vector2 result = new Vector2(point);

        if (this.parent != null) {
            this.parent.getRelative(result, result);
            result.subtract(this.parent.length, 0.0f);
        }

        return result;
    }

    /**
     * Расчитывает точку, координаты которой заданы относительно конца
     * родительской кости.
     *
     * @param point  точка с абсолютными координатами.
     * @param result результат.
     * @return точку.
     */
    public Vector2 getRelativeFromParent(Vector2 point, Vector2 result) {
        result.set(point);

        if (this.parent != null) {
            this.parent.getRelative(result, result);
            result.subtract(this.parent.length, 0.0f);
        }

        return result;
    }

    /**
     * Расчитывает точку, координаты которой заданы относительно самого
     * верхнего предка данной кости.
     *
     * @param point точка, координаты которой заданы относительно данной кости.
     * @return точку.
     */
    public Vector2 getAbsolute(Vector2 point) {
        Vector2 result = new Vector2(point);

        result.rotate(MoreMath.radians(this.angle));
        result.add(this.position);

        if (this.parent == null) return result;

        result.add(this.parent.length, 0.0f);
        return this.parent.getAbsolute(result, result);
    }

    /**
     * Расчитывает точку, координаты которой заданы относительно самого
     * верхнего предка данной кости.
     *
     * @param point  точка, координаты которой заданы относительно конца
     *               родительской кости.
     * @param result результат.
     * @return точку.
     */
    public Vector2 getAbsoluteFromParent(Vector2 point, Vector2 result) {
        result.set(point);

        if (this.parent == null) return result;

        result.add(this.parent.length, 0.0f);
        return this.parent.getAbsolute(result, result);
    }

    /**
     * Расчитывает точку, координаты которой заданы относительно самого
     * верхнего предка данной кости.
     *
     * @param point точка, координаты которой заданы относительно конца
     *              родительской кости.
     * @return точку.
     */
    public Vector2 getAbsoluteFromParent(Vector2 point) {
        Vector2 result = new Vector2(point);

        if (this.parent == null) return result;

        result.add(this.parent.length, 0.0f);
        return this.parent.getAbsolute(result, result);
    }

    /**
     * Расчитывает точку, координаты которой заданы относительно самого
     * верхнего предка данной кости.
     *
     * @param point  точка, координаты которой заданы относительно данной кости.
     * @param result результат.
     * @return точку.
     */
    public Vector2 getAbsolute(Vector2 point, Vector2 result) {
        result.set(point);

        result.rotate(MoreMath.radians(this.angle));
        result.add(this.position);

        if (this.parent == null) return result;

        result.add(this.parent.length, 0.0f);
        return this.parent.getAbsolute(result, result);
    }

    /**
     * Получает список всех костей, начания с данной.
     *
     * @return список костей.
     */
    public List<Bone> toList() {
        List<Bone> bones = new ArrayList<>();
        this.toList(bones);
        return bones;
    }

    /**
     * Заносит в список все кости, начания с данной.
     *
     * @param bones список костей.
     * @return список костей.
     */
    public List<Bone> toList(List<Bone> bones) {
        bones.add(this);
        for (Bone child : this.children) {
            child.toList(bones);
        }
        return bones;
    }

    @Override
    public String toString() {
        return "Bone[name=" + this.name + "]";
    }

    @Override
    public Iterator<Bone> iterator() {
        return this.children.iterator();
    }

}
