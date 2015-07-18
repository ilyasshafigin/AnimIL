/**
 *
 */
package ru.ildev.anim.skeletal.render;

import ru.ildev.anim.skeletal.*;
import ru.ildev.geom.Transform2;
import ru.ildev.math.MoreMath;

import java.util.Stack;

/**
 * Интерфейс рендера скелетов.
 *
 * @author Shafigin Ilyas <ilyas174@gmail.com>
 * @version 0.1.0
 */
public abstract class SkeletonRenderer {

    /**
     * Стек трансформаций.
     */
    private Stack<float[]> states = new Stack<>();

    /**
     * Рисует скелет.
     *
     * @param skeleton скелет.
     * @param skin     скин скелета.
     * @param textures текстуры скина.
     */
    public void draw(Skeleton skeleton, Skin skin, Textures textures) {
        this.draw(skeleton.getRoot(), skin, textures);
    }

    /**
     * Рисует кость вместе с ее дочерними костями.
     *
     * @param bone     кость.
     * @param skin     скин.
     * @param textures текстуры.
     */
    protected void draw(Bone bone, Skin skin, Textures textures) {
        SkinPatch patch = skin != null ? skin.getPatch(bone) : null;
        Texture texture = textures != null && patch != null ? textures
                .getTexture(patch.getTextureName()) : null;

        float boneX = bone.getX();
        float boneY = bone.getY();
        float boneAngle = MoreMath.radians(bone.getAngle());
        float boneLength = bone.getLength();

        float textureWidth = texture != null ? texture.getWidth() : 0.0f;
        float textureHeight = texture != null ? texture.getHeight() : 0.0f;

        Transform2 textureTransform = texture != null ? texture.getTransform()
                : null;

        float patchX = patch != null ? (patch.getCenterX() - 0.5f)
                * textureWidth : 0.5f;
        float patchY = patch != null ? (patch.getCenterY() - 0.5f)
                * textureHeight : 0.5f;
        float patchAngle = patch != null ? MoreMath.radians(patch.getAngle())
                : 0.0f;

        //this.save();

        this.translate(boneX, boneY);
        this.rotate(boneAngle);
        this.rotate(patchAngle);
        this.translate(patchX, patchY);

        // TODO Рендеринг текстур с заданной трансформацией не проверен!
        float[] tx0 = this.getTransform();
        if (textureTransform != null) {
            float[] tx1 = textureTransform.toArray();
            this.transform(tx1);
        }

        this.draw(bone, patch, texture);

        if (textureTransform != null) {
            this.setTransform(tx0);
        }

        this.translate(-patchX, -patchY);
        this.rotate(-patchAngle);
        this.translate(boneLength, 0.0f);

        for (Bone child : bone) {
            this.draw(child, skin, textures);
        }

        this.translate(-boneLength, 0.0f);
        this.rotate(-boneAngle);
        this.translate(-boneX, -boneY);

        //this.restore();
    }

    /**
     * Рисует кость.
     *
     * @param bone    кость.
     * @param patch   участок скина.
     * @param texture текстура.
     */
    protected abstract void draw(Bone bone, SkinPatch patch, Texture texture);

    /**
     * Рисует текстуру.
     *
     * @param texture текстура.
     */
    protected abstract void draw(Texture texture);

    /**
     * Получает текущую трансформацию.
     *
     * @return текущую трансформацию.
     */
    protected abstract float[] getTransform();

    /**
     * Устанавливает текущую трансформацию.
     *
     * @param transform трансформация.
     */
    protected abstract void setTransform(float[] transform);

    /**
     * @param dx
     * @param dy
     */
    protected abstract void translate(float dx, float dy);

    /**
     * @param theta
     */
    protected abstract void rotate(float theta);

    /**
     * Трансформирует
     *
     * @param transform трансформация.
     */
    protected abstract void transform(float[] transform);

    /**
     * Сохраняет текущую трансформацию.
     */
    protected void save() {
        float[] transform = this.getTransform();
        this.states.push(transform);
    }

    /**
     * Восстанавливает прошлую трансформация.
     */
    protected void restore() {
        if (!this.states.isEmpty()) {
            this.setTransform(this.states.pop());
        }
    }

}
