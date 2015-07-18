/**
 *
 */
package ru.ildev.anim.skeletal.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import ru.ildev.anim.core.AnimationOptions;
import ru.ildev.anim.core.Animator;
import ru.ildev.anim.skeletal.*;
import ru.ildev.anim.skeletal.json.*;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс утилит скелетной анимации.
 *
 * @author Shafigin Ilyas <ilyas174@gmail.com>
 * @version 0.3.2
 */
public final class SkeletalUtils {

    /**
     * Загружает скелет из файла.
     *
     * @param path путь к файлу.
     * @return объект скелета.
     * @throws JsonIOException       при ошибках ввода/вывода в JSON.
     * @throws FileNotFoundException если нет файла.
     * @throws JsonSyntaxException   при ошибках в синтаксисе JSON.
     */
    public static Skeleton loadSkeleton(String path) throws JsonIOException,
            JsonSyntaxException, FileNotFoundException {
        return SkeletalUtils.loadSkeleton(new File(path));
    }

    /**
     * Загружает скелет из файла.
     *
     * @param file файл.
     * @return объект скелета.
     * @throws JsonIOException       при ошибках ввода/вывода в JSON.
     * @throws FileNotFoundException если нет файла.
     * @throws JsonSyntaxException   при ошибках в синтаксисе JSON.
     */
    public static Skeleton loadSkeleton(File file) throws JsonIOException,
            JsonSyntaxException, FileNotFoundException {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Bone.class, new BoneJsonDeserializer())
                .registerTypeAdapter(Skeleton.class,
                        new SkeletonJsonDeserializer()).setPrettyPrinting()
                .create();
        return gson.<Skeleton>fromJson(new FileReader(file), Skeleton.class);
    }

    /**
     * Сохраняет скелет в файл.
     *
     * @param path     путь к файлу.
     * @param skeleton скелет.
     * @return {@code true}, если скелет сохранился.
     */
    public static boolean saveSkeleton(String path, Skeleton skeleton) {
        return SkeletalUtils.saveSkeleton(new File(path), skeleton);
    }

    /**
     * Сохраняет скелет в файл.
     *
     * @param file     файл.
     * @param skeleton скелет.
     * @return {@code true}, если скелет сохранился.
     */
    public static boolean saveSkeleton(File file, Skeleton skeleton) {
        try {
            if (!file.exists()) {
                if (!file.createNewFile()) {
                    return false;
                }
            }
            FileWriter writer = new FileWriter(file);
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Bone.class, new BoneJsonSerializer())
                    .registerTypeAdapter(Skeleton.class,
                            new SkeletonJsonSerializer()).setPrettyPrinting()
                    .create();
            gson.toJson(skeleton, writer);

            writer.flush();
            writer.close();

            return true;
        } catch (IOException exception) {
            Animator.LOGGER.throwing(SkeletalUtils.class.getSimpleName(),
                    "saveSkeleton", exception);
            return false;
        }
    }

    /**
     * Загружает список анимаций скелета.
     *
     * @param path путь к файлу.
     * @return список анимаций.
     * @throws JsonIOException       при ошибках ввода/вывода в JSON.
     * @throws FileNotFoundException если нет файла.
     * @throws JsonSyntaxException   при ошибках в синтаксисе JSON.
     */
    public static List<SkeletonAnimation> loadAnimations(String path)
            throws JsonIOException, JsonSyntaxException, FileNotFoundException {
        return SkeletalUtils.loadAnimations(new File(path));
    }

    /**
     * Загружает список анимаций скелета.
     *
     * @param file файл.
     * @return список анимаций.
     * @throws JsonIOException       при ошибках ввода/вывода в JSON.
     * @throws FileNotFoundException если нет файла.
     * @throws JsonSyntaxException   при ошибках в синтаксисе JSON.
     */
    public static List<SkeletonAnimation> loadAnimations(File file)
            throws JsonIOException, JsonSyntaxException, FileNotFoundException {
        Type animationsListType = new TypeToken<List<SkeletonAnimation>>() {
        }
                .getType();
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(SkeletonAnimation.class,
                        new SkeletonAnimationJsonDeserializer())
                .registerTypeAdapter(AnimationOptions.class,
                        new SkeletonAnimationOptionsJsonDeserializer())
                .registerTypeAdapter(SkeletonAnimationKeyFrame.class,
                        new SkeletonAnimationKeyFrameJsonDeserializer())
                .setPrettyPrinting().create();
        return gson.<List<SkeletonAnimation>>fromJson(new FileReader(file),
                animationsListType);
    }

    /**
     * Сохраняет анимацию скелета в файл.
     *
     * @param path               путь к файлу.
     * @param skeletonAnimations список анимаций.
     * @return {@code true}, если анимации сохранились.
     */
    public static boolean saveAnimations(String path,
                                         List<SkeletonAnimation> skeletonAnimations) {
        return SkeletalUtils.saveAnimations(new File(path), skeletonAnimations);
    }

    /**
     * Сохраняет анимацию скелета в файл.
     *
     * @param file               файл.
     * @param skeletonAnimations список анимаций.
     * @return {@code true}, если анимации сохранились.
     */
    public static boolean saveAnimations(File file,
                                         List<SkeletonAnimation> skeletonAnimations) {
        try {
            if (!file.exists()) {
                if (!file.createNewFile()) {
                    return false;
                }
            }
            FileWriter writer = new FileWriter(file);
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(SkeletonAnimation.class,
                            new SkeletonAnimationJsonSerializer())
                    .registerTypeAdapter(AnimationOptions.class,
                            new SkeletonAnimationOptionsJsonSerializer())
                    .registerTypeAdapter(SkeletonAnimationKeyFrame.class,
                            new SkeletonAnimationKeyFrameJsonSerializer())
                    .setPrettyPrinting().create();
            gson.toJson(skeletonAnimations, writer);

            writer.flush();
            writer.close();

            return true;
        } catch (IOException exception) {
            Animator.LOGGER.throwing(SkeletalUtils.class.getSimpleName(),
                    "saveAnimations", exception);
            return false;
        }
    }

    /**
     * Загружает скин скелета.
     *
     * @param path путь к файлу.
     * @return скин скелета.
     * @throws JsonIOException       при ошибках ввода/вывода в JSON.
     * @throws FileNotFoundException если нет файла.
     * @throws JsonSyntaxException   при ошибках в синтаксисе JSON.
     */
    public static Skin loadSkin(String path) throws JsonSyntaxException,
            JsonIOException, FileNotFoundException {
        return SkeletalUtils.loadSkin(new File(path));
    }

    /**
     * Загружает скин скелета.
     *
     * @param file файл.
     * @return скин скелета.
     * @throws JsonIOException       при ошибках ввода/вывода в JSON.
     * @throws FileNotFoundException если нет файла.
     * @throws JsonSyntaxException   при ошибках в синтаксисе JSON.
     */
    public static Skin loadSkin(File file) throws JsonSyntaxException,
            JsonIOException, FileNotFoundException {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Skin.class, new SkinJsonDeserializer())
                .registerTypeAdapter(SkinPatch.class,
                        new SkinPatchJsonDeserializer()).setPrettyPrinting()
                .create();
        return gson.<Skin>fromJson(new FileReader(file), Skin.class);
    }

    /**
     * Сохраняет скин в файл.
     *
     * @param path путь к файлу.
     * @param skin скин.
     * @return {@code true}, если скин сохранился.
     */
    public static boolean saveSkin(String path, Skin skin) {
        return SkeletalUtils.saveSkin(new File(path), skin);
    }

    /**
     * Сохраняет скин в файл.
     *
     * @param file файл.
     * @param skin скин.
     * @return {@code true}, если скин сохранился.
     */
    public static boolean saveSkin(File file, Skin skin) {
        try {
            if (!file.exists()) {
                if (!file.createNewFile()) {
                    return false;
                }
            }
            FileWriter writer = new FileWriter(file);
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(SkinPatch.class,
                            new SkinPatchJsonSerializer())
                    .registerTypeAdapter(Skin.class, new SkinJsonSerializer())
                    .setPrettyPrinting().create();
            gson.toJson(skin, writer);

            writer.flush();
            writer.close();

            return true;
        } catch (IOException exception) {
            Animator.LOGGER.throwing(SkeletalUtils.class.getSimpleName(),
                    "saveSkin", exception);
            return false;
        }
    }

    /**
     * Загружает параметры текстуры.
     *
     * @param path путь к файлу.
     * @return объект текстур.
     * @throws JsonIOException       при ошибках ввода/вывода в JSON.
     * @throws FileNotFoundException если нет файла.
     * @throws JsonSyntaxException   при ошибках в синтаксисе JSON.
     */
    public static Textures loadTextures(String path)
            throws JsonSyntaxException, JsonIOException, FileNotFoundException {
        return SkeletalUtils.loadTextures(new File(path));
    }

    /**
     * Загружает параметры текстуры.
     *
     * @param file файл.
     * @return объект текстур.
     * @throws JsonIOException       при ошибках ввода/вывода в JSON.
     * @throws FileNotFoundException если нет файла.
     * @throws JsonSyntaxException   при ошибках в синтаксисе JSON.
     */
    public static Textures loadTextures(File file)
            throws JsonSyntaxException, JsonIOException, FileNotFoundException {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Textures.class,
                        new TexturesJsonDeserializer(file.getParentFile())).setPrettyPrinting()
                .create();
        return gson.<Textures>fromJson(new FileReader(file), Textures.class);
    }

    /**
     * Сохраняет параметры текстур в файл.
     *
     * @param path     путь к файлу.
     * @param textures объект текстур.
     * @return {@code true}, если текстуры сохранились.
     */
    public static boolean saveTextures(String path, Textures textures) {
        return SkeletalUtils.saveTextures(new File(path), textures);
    }

    /**
     * Сохраняет параметры текстур в файл.
     *
     * @param file     файл.
     * @param textures объект текстур.
     * @return {@code true}, если текстуры сохранились.
     */
    public static boolean saveTextures(File file, Textures textures) {
        try {
            if (!file.exists()) {
                if (!file.createNewFile()) {
                    return false;
                }
            }
            FileWriter writer = new FileWriter(file);
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Textures.class,
                            new TexturesJsonSerializer()).setPrettyPrinting()
                    .create();
            gson.toJson(textures, writer);

            writer.flush();
            writer.close();

            return true;
        } catch (IOException exception) {
            Animator.LOGGER.throwing(SkeletalUtils.class.getSimpleName(),
                    "saveTextures", exception);
            return false;
        }
    }

    /**
     * Считывает все значения из скелета и записывает их в кадр анимации.
     *
     * @param name     имя кадра.
     * @param time     время кадра.
     * @param skeleton скелет.
     * @return кадр скелетной анимации.
     */
    public static SkeletonAnimationKeyFrame createKeyFrame(String name,
                                                           float time, Skeleton skeleton) {
        Map<String, float[]> boneKeyFrames = new HashMap<>();
        List<Bone> boneList = skeleton.getRoot().toList();

        for (Bone bone : boneList) {
            float[] values = new float[4];
            values[0] = bone.getPosition().x;
            values[1] = bone.getPosition().y;
            values[2] = bone.getAngle();
            values[3] = bone.getLength();
            boneKeyFrames.put(bone.getName(), values);
        }

        return new SkeletonAnimationKeyFrame(name, time, boneKeyFrames);
    }

    /**
     * Применяет все значения в кадре костям скелета.
     *
     * @param keyFrame кадр.
     * @param skeleton скелет.
     */
    public static void applyKeyFrame(SkeletonAnimationKeyFrame keyFrame,
                                     Skeleton skeleton) {
        Map<String, float[]> boneKeyFrames = keyFrame.getBoneKeyFrames();
        for (String boneName : boneKeyFrames.keySet()) {
            float[] boneKeyFrame = boneKeyFrames.get(boneName);
            Bone bone = skeleton.find(boneName);
            if (bone == null) continue;

            int size = boneKeyFrame.length;

            if (size >= 1) bone.setX(boneKeyFrame[0]);
            if (size >= 2) bone.setY(boneKeyFrame[1]);
            if (size >= 3) bone.setAngle(boneKeyFrame[2]);
            if (size >= 4) bone.setLength(boneKeyFrame[3]);
        }
    }

    /**
     * Обновляет значения в кадре анимации.
     *
     * @param skeleton скелет.
     * @param keyframe кадр.
     */
    public static void updateKeyFrame(SkeletonAnimationKeyFrame keyframe,
                                      Skeleton skeleton) {
        Map<String, float[]> boneKeyFrames = keyframe.getBoneKeyFrames();
        List<Bone> boneList = skeleton.getRoot().toList();

        for (Bone bone : boneList) {
            String name = bone.getName();
            float[] boneKeyFrame = boneKeyFrames.get(name);
            if (boneKeyFrame == null) {
                boneKeyFrame = new float[4];
                boneKeyFrames.put(name, boneKeyFrame);
            }

            int size = boneKeyFrame.length;

            if (size >= 1) boneKeyFrame[0] = bone.getX();
            if (size >= 2) boneKeyFrame[1] = bone.getY();
            if (size >= 3) boneKeyFrame[2] = bone.getAngle();
            if (size >= 4) boneKeyFrame[3] = bone.getLength();
        }
    }

}
