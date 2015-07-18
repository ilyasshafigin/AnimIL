/*
 *
 */
package ru.ildev.anim.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import ru.ildev.anim.core.Animation;
import ru.ildev.anim.core.BasicAnimation.BasicAnimationBuilder;
import ru.ildev.anim.core.ParallelAnimation.ParallelAnimationBuilder;
import ru.ildev.anim.core.QueueAnimation.QueueAnimationBuilder;
import ru.ildev.anim.core.TimelineAnimation.TimelineAnimationBuilder;
import ru.ildev.utils.Builder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Shafigin Ilyas <ilyas174@gmail.com>
 */
public final class AnimationUtils {

    /**
     * @param path
     * @return
     */
    public static List<Animation> loadAnimations(Object target, String path)
            throws JsonIOException, JsonSyntaxException, FileNotFoundException {
        return AnimationUtils.loadAnimations(target, new File(path));
    }

    /**
     * @param file
     * @return
     * @throws JsonIOException
     * @throws JsonSyntaxException
     * @throws FileNotFoundException
     */
    public static List<Animation> loadAnimations(Object target, File file)
            throws JsonIOException, JsonSyntaxException, FileNotFoundException {
        Type builderListType = new TypeToken<List<Builder>>() {
        }.getType();
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Animation.class,
                        new ru.ildev.anim.utils.json.AnimationJsonDeserializer())
                        //.registerTypeAdapter(AnimationOptions.class, new AnimationOptionsJsonDeserializer())
                        //.registerTypeAdapter(Property.class, new PropertyJsonDeserializer())
                        //.registerTypeAdapter(KeyFrame.class, new KeyFrameJsonDeserializer())
                .setPrettyPrinting().create();
        List<Builder> builders = gson.<List<Builder>>fromJson(new FileReader(file), builderListType);
        List<Animation> animations = new ArrayList<>();
        for (Builder builder : builders) {
            if (builder instanceof BasicAnimationBuilder) {
                BasicAnimationBuilder basicBuilder = (BasicAnimationBuilder) builder;
                basicBuilder.target(target);
                animations.add(basicBuilder.build());
            } else if (builder instanceof QueueAnimationBuilder) {
                QueueAnimationBuilder queueBuilder = (QueueAnimationBuilder) builder;
                queueBuilder.target(target);
                animations.add(queueBuilder.build());
            } else if (builder instanceof TimelineAnimationBuilder) {
                TimelineAnimationBuilder timelineBuilder = (TimelineAnimationBuilder) builder;
                animations.add(timelineBuilder.build());
            } else if (builder instanceof ParallelAnimationBuilder) {
                ParallelAnimationBuilder parallelBuilder = (ParallelAnimationBuilder) builder;
                animations.add(parallelBuilder.build());
            } else {
                animations.add((Animation) builder.build());
            }
        }
        return animations;
    }
}
