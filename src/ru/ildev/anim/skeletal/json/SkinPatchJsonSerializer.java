package ru.ildev.anim.skeletal.json;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import ru.ildev.anim.skeletal.SkinPatch;

import java.lang.reflect.Type;

/**
 * @author Shafigin Ilyas (Шафигин Ильяс) <Ilyas74>
 */
public class SkinPatchJsonSerializer implements JsonSerializer<SkinPatch> {

    @Override
    public JsonElement serialize(SkinPatch skinPatch, Type type,
                                 JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("bone", skinPatch.getBoneName());
        jsonObject.addProperty("texture", skinPatch.getTextureName());
        jsonObject.addProperty("angle", skinPatch.getAngle());
        jsonObject.addProperty("centerX", skinPatch.getCenterX());
        jsonObject.addProperty("centerY", skinPatch.getCenterY());
        return jsonObject;
    }

}
