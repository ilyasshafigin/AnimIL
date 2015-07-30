package ru.ildev.anim.skeletal.json;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import ru.ildev.anim.skeletal.Bone;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * @author Shafigin Ilyas (Шафигин Ильяс) <Ilyas74>
 */
public class BoneJsonDeserializer implements JsonDeserializer<Bone> {

    private Type childrenType = new TypeToken<ArrayList<Bone>>() {
    }.getType();

    @Override
    public Bone deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        JsonElement jsonName = jsonObject.get("name");
        JsonElement jsonX = jsonObject.get("x");
        JsonElement jsonY = jsonObject.get("y");
        JsonElement jsonAngle = jsonObject.get("angle");
        JsonElement jsonLength = jsonObject.get("length");
        JsonElement jsonChildren = jsonObject.get("children");

        if (jsonName == null) {
            throw new JsonSyntaxException("\"name\" of undefined");
        }

        String name = jsonName.getAsString();
        float x = jsonX != null ? jsonX.getAsFloat() : 0.0f;
        float y = jsonY != null ? jsonY.getAsFloat() : 0.0f;
        float angle = jsonAngle != null ? jsonAngle.getAsFloat() : 0.0f;
        float length = jsonLength != null ? jsonLength.getAsFloat() : 0.0f;

        Bone bone = new Bone(name);
        bone.setPosition(x, y);
        bone.setAngle(angle);
        bone.setLength(length);

        if (jsonChildren != null) {
            ArrayList<Bone> children = context.deserialize(jsonChildren.getAsJsonArray(), this.childrenType);
            for (Bone child : children) {
                bone.add(child);
            }
        }

        return bone;
    }

}
