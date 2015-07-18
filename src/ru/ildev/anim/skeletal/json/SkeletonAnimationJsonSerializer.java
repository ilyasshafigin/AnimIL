package ru.ildev.anim.skeletal.json;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import ru.ildev.anim.core.AnimationConstants;
import ru.ildev.anim.core.AnimationOptions;
import ru.ildev.anim.skeletal.SkeletonAnimation;
import ru.ildev.anim.skeletal.SkeletonAnimationKeyFrame;

import java.lang.reflect.Type;
import java.util.List;

/**
 * @author Shafigin Ilyas <ilyas174@gmail.com>
 */
public class SkeletonAnimationJsonSerializer implements
        JsonSerializer<SkeletonAnimation> {

    @Override
    public JsonElement serialize(SkeletonAnimation skeletonAnimation, Type type,
                                 JsonSerializationContext context) {
        String name = skeletonAnimation.getName();
        AnimationOptions options = skeletonAnimation.getOptions();
        List<SkeletonAnimationKeyFrame> keyFrames = skeletonAnimation.getKeyFrames();

        float duration = options.getDuration();
        float delay = options.getDelay();
        int fps = options.getFps();
        float timeScale = options.getTimeScale();
        String easing = options.getEasing().getCompoundName();
        int repeat = options.getRepeat();
        float repeatDelay = options.getRepeatDelay();
        String timeMode = "";
        {
            if (options.getTimeMode() == AnimationConstants.MILLISECONDS) {
                timeMode = "milliseconds";
            } else if (options.getTimeMode() == AnimationConstants.SECONDS) {
                timeMode = "seconds";
            } else if (options.getTimeMode() == AnimationConstants.FRAMES) {
                timeMode = "frames";
            }
        }
        String playMode = "";
        {
            if (options.getPlayMode() == AnimationConstants.FORWARD) {
                playMode = "forward";
            } else if (options.getPlayMode() == AnimationConstants.BACKWARD) {
                playMode = "backward";
            } else if (options.getPlayMode() == AnimationConstants.YOYO) {
                playMode = "yoyo";
            }
        }

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", name);
        if (duration == AnimationConstants.DURATION_INFINITE) {
            jsonObject.addProperty("duration", "infinity");
        } else {
            jsonObject.addProperty("duration", duration);
        }
        jsonObject.addProperty("delay", delay);
        jsonObject.addProperty("fps", fps);
        jsonObject.addProperty("timeScale", timeScale);
        jsonObject.addProperty("easing", easing);
        if (repeat == AnimationConstants.REPEAT_INFINITE) {
            jsonObject.addProperty("repeat", "infinity");
        } else {
            jsonObject.addProperty("repeat", repeat);
        }
        jsonObject.addProperty("repeatDelay", repeatDelay);
        jsonObject.addProperty("timeMode", timeMode);
        jsonObject.addProperty("playMode", playMode);
        jsonObject.add("keyFrames", context.serialize(keyFrames));
        return jsonObject;
    }

}
