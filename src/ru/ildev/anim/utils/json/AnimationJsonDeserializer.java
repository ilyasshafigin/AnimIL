package ru.ildev.anim.utils.json;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import ru.ildev.anim.core.Animation;
import ru.ildev.anim.core.AnimationOptions;
import ru.ildev.anim.plugins.property.Property;

import java.lang.reflect.Type;
import java.util.List;

/**
 * @author Shafigin Ilyas <ilyas174@gmail.com>
 */
public class AnimationJsonDeserializer implements JsonDeserializer<Animation> {

    private Type propertiesType = new TypeToken<List<Property>>() {
    }.getType();
    private Type optionsType = new TypeToken<AnimationOptions>() {
    }.getType();

    @Override
    public Animation deserialize(JsonElement json, Type type,
                                 JsonDeserializationContext context) throws JsonParseException {
        /*JsonObject jsonObject = json.getAsJsonObject();
        JsonElement jsonType = jsonObject.get("type");
        JsonElement jsonOptions = jsonObject.get("options");
        JsonElement jsonProperties = jsonObject.get("properties");

        if (jsonType == null)
            throw new JsonSyntaxException("\"type\" of undefined");

        String animationType = jsonType.getAsString();
        AnimationOptions options = context.<AnimationOptions>deserialize(
                jsonOptions.getAsJsonObject(), this.optionsType);
        List<Property> properties = context
                .<List<Property>> deserialize(
                        jsonProperties.getAsJsonArray(), this.propertiesType);

        return new SkeletonAnimation(name, keyFrames, options);*/
        return null;
    }

}
