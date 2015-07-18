package ru.ildev.anim.skeletal.json;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import ru.ildev.anim.core.AnimationOptions;
import ru.ildev.anim.skeletal.SkeletonAnimation;
import ru.ildev.anim.skeletal.SkeletonAnimationKeyFrame;

import java.lang.reflect.Type;
import java.util.List;

/**
 * @author Shafigin Ilyas <ilyas174@gmail.com>
 */
public class SkeletonAnimationJsonDeserializer implements
        JsonDeserializer<SkeletonAnimation> {

    private Type keyFramesType = new TypeToken<List<SkeletonAnimationKeyFrame>>() {
    }.getType();
    private Type optionsType = new TypeToken<AnimationOptions>() {
    }.getType();

    @Override
    public SkeletonAnimation deserialize(JsonElement json, Type type,
                                         JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        JsonElement jsonName = jsonObject.get("name");
        JsonElement jsonOptions = jsonObject.get("options");
        JsonElement jsonKeyFrames = jsonObject.get("keyFrames");

        if (jsonName == null) {
            throw new JsonSyntaxException(
                    "\"keyFrames\" of undefined");
        }
        if (jsonOptions == null) {
            throw new JsonSyntaxException(
                    "\"options\" of undefined");
        }
        if (jsonKeyFrames == null) {
            throw new JsonSyntaxException(
                    "\"keyFrames\" of undefined");
        }

        String name = jsonName.getAsString();
        AnimationOptions options = context.<AnimationOptions>deserialize(
                jsonOptions.getAsJsonObject(), this.optionsType);
        List<SkeletonAnimationKeyFrame> keyFrames = context
                .<List<SkeletonAnimationKeyFrame>>deserialize(
                        jsonKeyFrames.getAsJsonArray(), this.keyFramesType);

        return new SkeletonAnimation(name, keyFrames, options);
    }

}
