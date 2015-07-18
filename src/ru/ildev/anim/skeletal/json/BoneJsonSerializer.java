package ru.ildev.anim.skeletal.json;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import ru.ildev.anim.skeletal.Bone;
import ru.ildev.geom.Vector2;

import java.lang.reflect.Type;

/**
 * @author Shafigin Ilyas (Шафигин Ильяс) <Ilyas74>
 */
public class BoneJsonSerializer implements JsonSerializer<Bone> {

    @Override
    public JsonElement serialize(Bone bone, Type type,
                                 JsonSerializationContext context) {
        String name = bone.getName();
        Vector2 position = bone.getPosition();
        float angle = bone.getAngle();
        float length = bone.getLength();

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", name);
        jsonObject.addProperty("x", position.x);
        jsonObject.addProperty("y", position.y);
        jsonObject.addProperty("angle", angle);
        jsonObject.addProperty("length", length);
        jsonObject.add("children", context.serialize(bone.getChildren()));
        return jsonObject;
    }

}
