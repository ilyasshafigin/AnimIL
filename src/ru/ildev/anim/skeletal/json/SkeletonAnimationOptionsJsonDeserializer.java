package ru.ildev.anim.skeletal.json;

import com.google.gson.*;
import ru.ildev.anim.core.AnimationConstants;
import ru.ildev.anim.core.AnimationOptions;
import ru.ildev.anim.easings.Easing;

import java.lang.reflect.Type;

/**
 * @author Shafigin Ilyas <ilyas174@gmail.com>
 */
public class SkeletonAnimationOptionsJsonDeserializer implements
        JsonDeserializer<AnimationOptions> {

    @Override
    public AnimationOptions deserialize(JsonElement json, Type type,
                                        JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        JsonElement jsonDuration = jsonObject.get("duration");
        JsonElement jsonDelay = jsonObject.get("delay");
        JsonElement jsonFps = jsonObject.get("fps");
        JsonElement jsonTimeScale = jsonObject.get("timeScale");
        JsonElement jsonEasing = jsonObject.get("easing");
        JsonElement jsonRepeat = jsonObject.get("repeat");
        JsonElement jsonRepeatDelay = jsonObject.get("repeatDelay");
        JsonElement jsonTimeMode = jsonObject.get("timeMode");
        JsonElement jsonPlayMode = jsonObject.get("playMode");

        AnimationOptions options = new AnimationOptions();

        if (jsonDuration != null) {
            if (this.isString(jsonDuration) && jsonDuration.getAsString().equals("infinity")) {
                options.setDuration(AnimationConstants.DURATION_INFINITE);
            } else {
                options.setDuration(jsonDuration.getAsFloat());
            }
        }
        if (jsonDelay != null && this.isNumber(jsonDelay))
            options.setDelay(jsonDelay.getAsFloat());
        if (jsonFps != null && this.isNumber(jsonFps))
            options.setFps(jsonFps.getAsInt());
        if (jsonTimeScale != null && this.isNumber(jsonTimeScale))
            options.setTimeScale(jsonTimeScale.getAsFloat());
        if (jsonEasing != null && this.isString(jsonEasing))
            options.setEasing(Easing.getEasing(jsonEasing.getAsString()));
        if (jsonRepeat != null) {
            if (this.isString(jsonRepeat) && jsonRepeat.getAsString().equals("infinity")) {
                options.setRepeat(AnimationConstants.REPEAT_INFINITE);
            } else {
                options.setRepeat(jsonRepeat.getAsInt());
            }
        }
        if (jsonRepeatDelay != null && this.isNumber(jsonRepeatDelay))
            options.setRepeatDelay(jsonRepeatDelay.getAsFloat());
        if (jsonTimeMode != null && this.isString(jsonTimeMode)) {
            String timeMode = jsonTimeMode.getAsString();
            if (timeMode.equals("milliseconds")) {
                options.setTimeMode(AnimationConstants.MILLISECONDS);
            } else if (timeMode.equals("seconds")) {
                options.setTimeMode(AnimationConstants.SECONDS);
            } else if (timeMode.equals("frames")) {
                options.setTimeMode(AnimationConstants.FRAMES);
            }
        }
        if (jsonPlayMode != null && this.isString(jsonPlayMode)) {
            String playMode = jsonPlayMode.getAsString();
            if (playMode.equals("forward")) {
                options.setPlayMode(AnimationConstants.FORWARD);
            } else if (playMode.equals("backward")) {
                options.setPlayMode(AnimationConstants.BACKWARD);
            } else if (playMode.equals("yoyo")) {
                options.setPlayMode(AnimationConstants.YOYO);
            }
        }
        return options;
    }

    private boolean isNumber(JsonElement element) {
        if (!element.isJsonPrimitive()) return false;
        JsonPrimitive primitive = (JsonPrimitive) element;
        return primitive.isNumber();
    }

    private boolean isString(JsonElement element) {
        if (!element.isJsonPrimitive()) return false;
        JsonPrimitive primitive = (JsonPrimitive) element;
        return primitive.isString();
    }

}
