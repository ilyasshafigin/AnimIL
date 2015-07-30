package ru.ildev.anim.skeletal.json;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import ru.ildev.anim.skeletal.Skin;

import java.lang.reflect.Type;

/**
 * @author Shafigin Ilyas <ilyas174@gmail.com>
 */
public class SkinJsonSerializer implements JsonSerializer<Skin> {

    @Override
    public JsonElement serialize(Skin skin, Type type, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("patches", context.serialize(skin.getMap().values()));
        return jsonObject;
    }

}
