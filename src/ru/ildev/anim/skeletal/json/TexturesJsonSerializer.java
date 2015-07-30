package ru.ildev.anim.skeletal.json;

import com.google.gson.*;
import ru.ildev.anim.skeletal.Textures;
import ru.ildev.geom.Transform2;

import java.lang.reflect.Type;

/**
 * @author Shafigin Ilyas (Шафигин Ильяс) <Ilyas74>
 */
public class TexturesJsonSerializer implements JsonSerializer<Textures> {

    @Override
    public JsonElement serialize(Textures textures, Type type,
                                 JsonSerializationContext context) {
        String folder = textures.getFolder();

        JsonArray jsonTexturesArray = new JsonArray();
        textures.getMap().forEach((name, texture) -> {
            JsonObject jsonTexture = new JsonObject();
            jsonTexture.addProperty("name", texture.getName());
            jsonTexture.addProperty("path", texture.getPath());
            jsonTexture.addProperty("width", texture.getWidth());
            jsonTexture.addProperty("height", texture.getHeight());

            Transform2 transform = texture.getTransform();
            if (transform != null) {
                JsonArray jsonTransform = new JsonArray();
                jsonTransform.add(new JsonPrimitive(transform.m11));
                jsonTransform.add(new JsonPrimitive(transform.m12));
                jsonTransform.add(new JsonPrimitive(transform.m13));
                jsonTransform.add(new JsonPrimitive(transform.m21));
                jsonTransform.add(new JsonPrimitive(transform.m22));
                jsonTransform.add(new JsonPrimitive(transform.m23));
                jsonTexture.add("transform", jsonTransform);
            }
            jsonTexturesArray.add(jsonTexture);
        });

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("folder", folder);
        jsonObject.add("textures", jsonTexturesArray);
        return jsonObject;
    }

}
