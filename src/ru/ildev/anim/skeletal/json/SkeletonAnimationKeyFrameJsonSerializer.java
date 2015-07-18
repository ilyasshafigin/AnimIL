package ru.ildev.anim.skeletal.json;

import com.google.gson.*;
import ru.ildev.anim.skeletal.SkeletonAnimationKeyFrame;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.Map.Entry;

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
        Map<String, float[]> boneKeyFrames = keyFrame.getBoneKeyFrames();

        JsonObject jsonBoneKeyFrames = new JsonObject();
        for (Entry<String, float[]> entry : boneKeyFrames.entrySet()) {
            float[] boneFrame = entry.getValue();

            JsonArray jsonBoneKeyFrame = new JsonArray();
            for (int i = 0; i < boneFrame.length; i++) {
                jsonBoneKeyFrame.add(new JsonPrimitive(boneFrame[i]));
            }
            jsonBoneKeyFrames.add(entry.getKey(), jsonBoneKeyFrame);
        }

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", name);
        jsonObject.addProperty("time", time);
        jsonObject.add("boneKeyFrames", jsonBoneKeyFrames);
        return jsonObject;
    }

}
