package ru.ildev.anim.skeletal.json;

import com.google.gson.*;
import ru.ildev.anim.skeletal.SkinPatch;

import java.lang.reflect.Type;

/**
 * @author Shafigin Ilyas (Шафигин Ильяс) <Ilyas74>
 */
public class SkinPatchJsonDeserializer implements JsonDeserializer<SkinPatch> {

    @Override
    public SkinPatch deserialize(JsonElement json, Type type,
                                 JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        JsonElement jsonBone = jsonObject.get("bone");
        JsonElement jsonTexture = jsonObject.get("texture");
        JsonElement jsonAngle = jsonObject.get("angle");
        JsonElement jsonCenterX = jsonObject.get("centerX");
        JsonElement jsonCenterY = jsonObject.get("centerY");

        if (jsonBone == null) {
            throw new JsonSyntaxException("\"bone\"");
        }

        if (jsonTexture == null) {
            throw new JsonSyntaxException("\"texture\"");
        }

        String bone = jsonBone.getAsString();
        String texture = jsonTexture.getAsString();

        float angle = jsonAngle != null ? jsonAngle.getAsFloat() : 0.0f;
        float centerX = jsonCenterX != null ? jsonCenterX.getAsFloat() : 0.0f;
        float centerY = jsonCenterY != null ? jsonCenterY.getAsFloat() : 0.0f;

        SkinPatch skinPatch = new SkinPatch(bone, texture);
        skinPatch.setAngle(angle);
        skinPatch.setCenter(centerX, centerY);
        return skinPatch;
    }

}
