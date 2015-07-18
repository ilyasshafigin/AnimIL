package ru.ildev.anim.skeletal.json;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import ru.ildev.anim.skeletal.Skeleton;

import java.lang.reflect.Type;

/**
 * @author Shafigin Ilyas (Шафигин Ильяс) <Ilyas74>
 */
public class SkeletonJsonSerializer implements JsonSerializer<Skeleton> {

    @Override
    public JsonElement serialize(Skeleton skeleton, Type type,
                                 JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("root", context.serialize(skeleton.getRoot()));
        return jsonObject;
    }

}
