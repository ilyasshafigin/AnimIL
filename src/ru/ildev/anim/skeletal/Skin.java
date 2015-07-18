/*
 *
 */
package ru.ildev.anim.skeletal;

import java.util.*;
import java.util.function.Consumer;

/**
 * Класс скина скелета.
 *
 * @author Shafigin Ilyas (Шафигин Ильяс) <Ilyas74>
 * @version 0.2.2
 */
public class Skin implements Iterable<SkinPatch> {

    /**
     * Карта участков скина.
     */
    protected Map<String, SkinPatch> map = new HashMap<>();
    /**
     * Список участков скина.
     */
    protected List<SkinPatch> list = new ArrayList<>();

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
     * Получает список участков скина.
     *
     * @return список участков скина.
     */
    public List<SkinPatch> getList() {
        return this.list;
    }

    /**
     * Добавляет участок скина.
     *
     * @param patch участок скина.
     */
    public void addPatch(SkinPatch patch) {
        if (patch == null) return;

        if (this.map.containsKey(patch.boneName)) {
            SkinPatch previousPatch = this.map.remove(patch.boneName);
            this.list.remove(previousPatch);
        }

        this.map.put(patch.boneName, patch);
        this.list.add(patch);
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
            SkinPatch previousPatch = this.map.remove(boneName);
            this.list.remove(previousPatch);
        }

        this.map.put(boneName, patch);
        this.list.add(patch);
    }

    /**
     * Удаляет участок скина.
     *
     * @param patch
     */
    public void removePatch(SkinPatch patch) {
        if (patch == null) return;
        this.map.remove(patch.boneName);
        this.list.remove(patch);
    }

    /**
     * Удаляет участок скина.
     *
     * @param bone кость, к которому привязан участок.
     */
    public void removePatch(Bone bone) {
        if (bone == null) return;
        List<Bone> bones = bone.toList();
        for (int i = 0; i < bones.size(); i++) {
            SkinPatch patch = this.map.remove(bones.get(i).name);
            this.list.remove(patch);
        }
    }

    /**
     * Получает количество участков скина.
     *
     * @return количество участков скина.
     */
    public int getPatchesCount() {
        return this.list.size();
    }

    /**
     * Получает участок скина по его индексу в списке.
     *
     * @param index индекс.
     * @return участок скина.
     */
    public SkinPatch getPatch(int index) {
        return this.list.get(index);
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
        for (SkinPatch patch : this.list) {
            if (patch.textureName.equals(texture.name)) {
                return patch;
            }
        }
        return null;
    }

    /**
     * Получает количество добавленных частей скина.
     *
     * @return количество частей скина.
     */
    public int size() {
        return this.list.size();
    }

    @Override
    public Iterator<SkinPatch> iterator() {
        return this.list.iterator();
    }

    @Override
    public void forEach(Consumer<? super SkinPatch> consumer) {
        this.list.forEach(consumer);
    }

    @Override
    public Spliterator<SkinPatch> spliterator() {
        return this.list.spliterator();
    }

}
