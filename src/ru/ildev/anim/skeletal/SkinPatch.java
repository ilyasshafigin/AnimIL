/*
 *
 */
package ru.ildev.anim.skeletal;

import ru.ildev.geom.Vector2;

/**
 * Класс участка скина скелета.
 *
 * @author Shafigin Ilyas (Шафигин Ильяс) <Ilyas74>
 * @version 0.1.2
 */
public class SkinPatch {

    /**
     * Имя кости, к которой прикреплен данный участок.
     */
    protected String boneName;
    /**
     * Имя текстуры, которая прикперлена к данному участку.
     */
    protected String textureName;

    /**
     * Угол поворота участва в радианах.
     */
    protected float angle;
    /**
     * Центр участка скина. Координаты задаются от левого верхнего угла, от 0
     * до 1.
     */
    protected Vector2 center = new Vector2(0.5f, 0.5f);

    /**
     * Конструктор, создающий участок скина с заданными именами кости и текстуры.
     *
     * @param boneName    имя кости.
     * @param textureName имя текстуры.
     */
    public SkinPatch(String boneName, String textureName) {
        if (boneName == null) throw new NullPointerException("boneName == null");
        if (textureName == null) throw new NullPointerException("textureName == null");

        this.boneName = boneName;
        this.textureName = textureName;
    }

    /**
     * Получает имя кости.
     *
     * @return имя кости.
     */
    public String getBoneName() {
        return this.boneName;
    }

    /**
     * Устанавливает имя кости.
     *
     * @param boneName имя кости.
     */
    public void setBoneName(String boneName) {
        if (boneName == null) throw new NullPointerException("boneName == null");

        this.boneName = boneName;
    }

    /**
     * Получает имя текстуры.
     *
     * @return имя текстуры.
     */
    public String getTextureName() {
        return this.textureName;
    }

    /**
     * Устанавливает имя текстуры.
     *
     * @param textureName имя текстуры.
     */
    public void setTextureName(String textureName) {
        if (textureName == null) throw new NullPointerException("textureName == null");

        this.textureName = textureName;
    }

    /**
     * Получает угол поворота участка скина.
     *
     * @return угол поворота участка скина.
     */
    public float getAngle() {
        return this.angle;
    }

    /**
     * Устанавливает угол поворота участка скина.
     *
     * @param angle угол поворота в радианах.
     */
    public void setAngle(float angle) {
        this.angle = angle;
    }

    /**
     * Получает центр участка скина.
     *
     * @return центр участка скина.
     */
    public Vector2 getCenter() {
        return this.center;
    }

    /**
     * Устанавливает центр участка скина.
     *
     * @param center центр участка скина.
     */
    public void setCenter(Vector2 center) {
        if (center == null) throw new NullPointerException("center == null");

        this.center.copy(center);
    }

    /**
     * Устанавливает центр участка скина.
     *
     * @param x x-координата центра.
     * @param y y-координата центра.
     */
    public void setCenter(float x, float y) {
        this.center.set(x, y);
    }

    /**
     * Получает x-координату центра участка скина.
     *
     * @return x-координату центра участка скина.
     */
    public float getCenterX() {
        return this.center.x;
    }

    /**
     * Получает y-координату центра участка скина.
     *
     * @return y-координату центра участка скина.
     */
    public float getCenterY() {
        return this.center.y;
    }

    /**
     * Устанавливает x-координате центра участка скина новое значение.
     *
     * @param x x-координата центра участка скина.
     */
    public void setCenterX(float x) {
        this.center.x = x;
    }

    /**
     * Устанавливает y-координате центра участка скина новое значение.
     *
     * @param y y-координата центра участка скина.
     */
    public void setCenterY(float y) {
        this.center.y = y;
    }

    @Override
    public String toString() {
        return "SkinPatch[bone=" + this.boneName + ", texture=" + this.textureName + "]";
    }

}
