/**
 *
 */
package ru.ildev.anim.skeletal.render;

import ru.ildev.anim.skeletal.*;

import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * @author Shafigin Ilyas <ilyas174@gmail.com>
 */
public class SkeletonRendererGraphics2D extends SkeletonRenderer {

    /**  */
    protected Graphics2D g = null;

    /**
     * @param g
     */
    public void initialize(Graphics2D g) {
        this.g = g;
    }

    /**
     * @param g
     * @param skeleton
     * @param skin
     * @param textures
     */
    public void draw(Graphics2D g, Skeleton skeleton, Skin skin,
                     Textures textures) {
        this.initialize(g);
        this.draw(skeleton, skin, textures);
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

            this.g.drawImage(itex.getImage(), 0, 0, w, h, null);
        }
    }

    @Override
    protected void translate(float dx, float dy) {
        this.g.translate(dx, dy);
    }

    @Override
    protected void rotate(float theta) {
        this.g.rotate(theta);
    }

    @Override
    protected void transform(float[] transform) {
        AffineTransform tx = new AffineTransform(transform[0], transform[2],
                transform[1], transform[3], transform[4], transform[5]);
        this.g.transform(tx);
    }

    @Override
    protected float[] getTransform() {
        AffineTransform tx = this.g.getTransform();
        return new float[]{(float) tx.getScaleX(), (float) tx.getShearX(),
                (float) tx.getTranslateX(), (float) tx.getShearY(),
                (float) tx.getScaleY(), (float) tx.getTranslateY()};
    }

    @Override
    protected void setTransform(float[] transform) {
        AffineTransform tx = new AffineTransform(transform[0], transform[2],
                transform[1], transform[3], transform[4], transform[5]);
        this.g.setTransform(tx);
    }

}
