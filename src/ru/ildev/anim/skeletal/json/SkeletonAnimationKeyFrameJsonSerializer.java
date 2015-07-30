package ru.ildev.anim.skeletal.json;

import com.google.gson.*;
import ru.ildev.anim.skeletal.SkeletonAnimationKeyFrame;

import java.lang.reflect.Type;

/**
 * @author Shafigin Ilyas (Шафигин Ильяс) <Ilyas74>
 */
public class SkeletonAnimationKeyFrameJsonSerializer implements
        JsonSerializer<SkeletonAnimationKeyFrame> {

    @Override
    public JsonElement serialize(SkeletonAnimationKeyFrame keyFrame, Type type,
                                 JsonSerializationContext context) {
        String name = keyFrame.getName();
        float time = keyFrame.getTime();

        JsonObject jsonBoneKeyFrames = new JsonObject();
        keyFrame.getBoneKeyFrames().forEach((boneName, boneFrame) -> {
            JsonArray jsonBoneKeyFrame = new JsonArray();
            for (float aBoneFrame : boneFrame) {
                jsonBoneKeyFrame.add(new JsonPrimitive(aBoneFrame));
            }
            jsonBoneKeyFrames.add(boneName, jsonBoneKeyFrame);
        });

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", name);
        jsonObject.addProperty("time", time);
        jsonObject.add("boneKeyFrames", jsonBoneKeyFrames);
        return jsonObject;
    }

}
