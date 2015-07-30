package ru.ildev.anim.skeletal.json;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import ru.ildev.anim.skeletal.Skin;
import ru.ildev.anim.skeletal.SkinPatch;

import java.lang.reflect.Type;
import java.util.List;

/**
 * @author Shafigin Ilyas <ilyas174@gmail.com>
 */
public class SkinJsonDeserializer implements JsonDeserializer<Skin> {

    private Type patchesType = new TypeToken<List<SkinPatch>>() {
    }.getType();

    @Override
    public Skin deserialize(JsonElement json, Type type,
                            JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        JsonElement jsonPatches = jsonObject.get("patches");

        if (jsonPatches == null) {
            throw new JsonSyntaxException("\"patches\" of unefined");
        }

        List<SkinPatch> patches = context.deserialize(jsonPatches.getAsJsonArray(), this.patchesType);
        Skin skin = new Skin();
        patches.forEach(skin::addPatch);
        return skin;
    }
}
