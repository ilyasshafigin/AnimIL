package ru.ildev.anim.skeletal.json;

import com.google.gson.*;
import ru.ildev.anim.skeletal.Bone;
import ru.ildev.anim.skeletal.Skeleton;

import java.lang.reflect.Type;

/**
 * @author Shafigin Ilyas (Шафигин Ильяс) <Ilyas74>
 */
public class SkeletonJsonDeserializer implements JsonDeserializer<Skeleton> {

    @Override
    public Skeleton deserialize(JsonElement json, Type type,
                                JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        JsonElement jsonRoot = jsonObject.get("root");

        if (jsonRoot == null) {
            throw new JsonSyntaxException("\"root\" of undefined");
        }

        return new Skeleton(context.<Bone>deserialize(jsonRoot, Bone.class));
    }

}
