package ru.ildev.anim.skeletal.json;

import com.google.gson.*;
import ru.ildev.anim.skeletal.SkeletonAnimationKeyFrame;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author Shafigin Ilyas (Шафигин Ильяс)
 */
public class SkeletonAnimationKeyFrameJsonDeserializer implements
        JsonDeserializer<SkeletonAnimationKeyFrame> {

    @Override
    public SkeletonAnimationKeyFrame deserialize(JsonElement json, Type type,
                                                 JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        JsonElement jsonName = jsonObject.get("name");
        JsonElement jsonTime = jsonObject.get("time");
        JsonElement jsonBoneKeyFrames = jsonObject.get("boneKeyFrames");

        if (jsonName == null)
            throw new JsonSyntaxException("\"name\" of undefined");
        if (jsonTime == null)
            throw new JsonSyntaxException("\"time\" of undefined");
        if (jsonBoneKeyFrames == null)
            throw new JsonSyntaxException("\"boneKeyFrames\" of undefined");

        String name = jsonName.getAsString();
        float time = jsonTime.getAsFloat();
        Map<String, float[]> boneKeyFrames = new HashMap<>();

        for (Entry<String, JsonElement> entry : jsonBoneKeyFrames
                .getAsJsonObject().entrySet()) {
            JsonArray jsonBoneFrames = entry.getValue().getAsJsonArray();

            int size = jsonBoneFrames.size();

            float[] boneFrames = new float[size];
            if (size >= 1) boneFrames[0] = jsonBoneFrames.get(0).getAsFloat();
            if (size >= 2) boneFrames[1] = jsonBoneFrames.get(1).getAsFloat();
            if (size >= 3) boneFrames[2] = jsonBoneFrames.get(2).getAsFloat();
            if (size >= 4) boneFrames[3] = jsonBoneFrames.get(3).getAsFloat();
            boneKeyFrames.put(entry.getKey(), boneFrames);
        }

        return new SkeletonAnimationKeyFrame(name, time, boneKeyFrames);
    }

}
