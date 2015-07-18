/**
 *
 */
package ru.ildev.anim.skeletal.render;

import ru.ildev.anim.skeletal.*;
import ru.ildev.game.render.Context2D;
import ru.ildev.geom.Transform2;
import ru.ildev.math.MoreMath;

/**
 * @author Shafigin Ilyas <ilyas174@gmail.com>
 * @version 0.0.0
 */
public class SkeletonRendererContext2D extends SkeletonRenderer {

    /**  */
    private Context2D ctx = null;

    /**
     * @param ctx
     */
    public void initialize(Context2D ctx) {
        this.ctx = ctx;
    }

    /**
     * @param ctx
     * @param skeleton
     * @param skin
     * @param textures
     */
    public void draw(Context2D ctx, Skeleton skeleton, Skin skin,
                     Textures textures) {
        this.initialize(ctx);
        this.draw(skeleton, skin, textures);
    }

    @Override
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

        // this.save();

        this.ctx.saveState();

        this.ctx.translate(boneX, boneY);
        this.ctx.rotate(boneAngle);

        this.ctx.saveState();
        this.ctx.rotate(patchAngle);
        this.ctx.translate(patchX, patchY);

        // TODO Рендеринг текстур с заданной трансформацией не проверен!
        float[] tx0 = this.ctx.getTransform();
        if (textureTransform != null) {
            float[] tx1 = textureTransform.toArray();
            this.ctx.transform(tx1[0], tx1[1], tx1[2], tx1[3], tx1[4], tx1[5]);
        }

        this.draw(bone, patch, texture);

        if (textureTransform != null) {
            this.ctx.setTransform(tx0[0], tx0[1], tx0[2], tx0[3], tx0[4],
                    tx0[5]);
        }

        this.ctx.restoreState();
        this.ctx.translate(boneLength, 0.0f);

        for (Bone child : bone) {
            this.draw(child, skin, textures);
        }

        this.ctx.restore();
    }

    @Override
    protected void draw(Bone bone, SkinPatch patch, Texture texture) {
        if (texture == null) return;

        this.draw(texture);
    }

    @Override
    protected void draw(Texture texture) {
        if (texture instanceof ImageTexture) {
            ImageTexture itex = (ImageTexture) texture;

            int w = (int) itex.getWidth();
            int h = (int) itex.getHeight();

            this.ctx.image(itex.getImage(), 0, 0, w, h);
        }
    }

    @Override
    protected float[] getTransform() {
        return this.ctx.getTransform();
    }

    @Override
    protected void setTransform(float[] tx) {
        // this.ctx.setTransform(tx[0], tx[1], tx[2], tx[3], tx[4], tx[5]);
    }

    @Override
    protected void translate(float dx, float dy) {
        // this.ctx.translate(dx, dy);
    }

    @Override
    protected void rotate(float theta) {
        // this.ctx.rotate(theta);
    }

    @Override
    protected void transform(float[] tx) {
        // this.ctx.transform(tx[0], tx[1], tx[2], tx[3], tx[4], tx[5]);
    }

    @Override
    protected void save() {
        // this.ctx.saveState();
    }

    @Override
    protected void restore() {
        // this.ctx.restore();
    }

}
