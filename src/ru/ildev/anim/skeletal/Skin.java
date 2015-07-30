/*
 *
 */
package ru.ildev.anim.skeletal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс скина скелета.
 *
 * @author Shafigin Ilyas (Шафигин Ильяс) <Ilyas74>
 * @version 0.2.2
 */
public class Skin {

    /**
     * Карта участков скина.
     */
    protected Map<String, SkinPatch> map = new HashMap<>();

    /**
     * Конструктор, создающий пустой скин.
     */
    public Skin() {
    }

    /**
     * Получает карту участков скина.
     *
     * @return карту участков скина.
     */
    public Map<String, SkinPatch> getMap() {
        return this.map;
    }

    /**
     * Добавляет участок скина.
     *
     * @param patch участок скина.
     */
    public void addPatch(SkinPatch patch) {
        if (patch == null) return;

        if (this.map.containsKey(patch.boneName)) {
            this.map.remove(patch.boneName);
        }
        this.map.put(patch.boneName, patch);
    }

    /**
     * Добавляет участок скина.
     *
     * @param boneName    имя кости.
     * @param textureName имя текстуры.
     */
    public void addPatch(String boneName, String textureName) {
        SkinPatch patch = new SkinPatch(boneName, textureName);

        if (this.map.containsKey(boneName)) {
            this.map.remove(boneName);
        }

        this.map.put(boneName, patch);
    }

    /**
     * Удаляет участок скина.
     *
     * @param patch
     */
    public void removePatch(SkinPatch patch) {
        if (patch == null) return;
        this.map.remove(patch.boneName);
    }

    /**
     * Удаляет участок скина.
     *
     * @param bone кость, к которому привязан участок.
     */
    public void removePatch(Bone bone) {
        if (bone == null) return;
        List<Bone> bones = bone.toList();
        for (Bone bone1 : bones) {
            this.map.remove(bone1.name);
        }
    }

    /**
     * Получает количество участков скина.
     *
     * @return количество участков скина.
     */
    public int getPatchesCount() {
        return this.map.size();
    }

    /**
     * Получает участок скина по имени кости.
     *
     * @param boneName имя кости.
     * @return участок скина.
     */
    public SkinPatch getPatch(String boneName) {
        if (boneName == null) return null;
        return this.map.get(boneName);
    }

    /**
     * Получает участок скина по кости, привязанной к ней.
     *
     * @param bone кость.
     * @return участок скина.
     */
    public SkinPatch getPatch(Bone bone) {
        if (bone == null) return null;
        return this.map.get(bone.name);
    }

    /**
     * Получает участок скина по прикрепленной к нему текстуре.
     *
     * @param texture текстура.
     * @return участок скина.
     */
    public SkinPatch getPatch(Texture texture) {
        if (texture == null) return null;
        return this.map.values().stream().filter((patch) -> patch.textureName.equals(texture.name)).findFirst().get();
    }

    /**
     * Получает количество добавленных частей скина.
     *
     * @return количество частей скина.
     */
    public int size() {
        return this.map.size();
    }

}
