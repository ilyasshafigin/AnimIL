package ru.ildev.anim.skeletal.json;

import com.google.gson.*;
import ru.ildev.anim.core.Animator;
import ru.ildev.anim.skeletal.ImageTexture;
import ru.ildev.anim.skeletal.Texture;
import ru.ildev.anim.skeletal.Textures;
import ru.ildev.geom.Transform2;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Shafigin Ilyas (Шафигин Ильяс) <Ilyas74>
 */
public class TexturesJsonDeserializer implements JsonDeserializer<Textures> {

    private File root = null;

    /**
     * @param root
     */
    public TexturesJsonDeserializer(File root) {
        if (root == null) throw new NullPointerException("root == null");
        this.root = root;
    }

    @Override
    public Textures deserialize(JsonElement json, Type type,
                                JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        JsonElement jsonFolder = jsonObject.get("folder");
        JsonElement jsonTextures = jsonObject.get("textures");

        if (jsonTextures == null) {
            throw new JsonSyntaxException("\"textures\" of undefined");
        }

        JsonArray jsonTexturesArray = jsonTextures.getAsJsonArray();

        String folder = jsonFolder != null ? jsonFolder.getAsString() : "";
        List<Texture> list = new ArrayList<>();

        for (JsonElement jsonElement : jsonTexturesArray) {
            JsonObject jsonTexture = jsonElement.getAsJsonObject();

            JsonElement jsonName = jsonTexture.get("name");
            JsonElement jsonPath = jsonTexture.get("path");
            JsonElement jsonWidth = jsonTexture.get("width");
            JsonElement jsonHeight = jsonTexture.get("height");
            JsonElement jsonTransform = jsonTexture.get("transform");

            if (jsonName == null) {
                throw new JsonSyntaxException("\"name\" of undefined");
            }

            String name = jsonName.getAsString();
            String path = jsonPath != null ? jsonPath.getAsString() : "";
            BufferedImage image;
            try {
                image = ImageIO.read(new File(this.root, folder + path));
            } catch (IOException exception) {
                Animator.LOGGER.throwing(this.getClass().getSimpleName(),
                        "deserialize", exception);
                return null;
            }

            float width = jsonWidth != null ? jsonWidth.getAsFloat()
                    : image.getWidth();
            float height = jsonHeight != null ? jsonHeight
                    .getAsFloat() : image.getHeight();
            Transform2 transform = null;
            if (jsonTransform != null) {
                transform = new Transform2();
                JsonArray jsonTransformArray = jsonElement.getAsJsonArray();
                transform.m11 = jsonTransformArray.get(0).getAsFloat();
                transform.m12 = jsonTransformArray.get(1).getAsFloat();
                transform.m13 = jsonTransformArray.get(2).getAsFloat();
                transform.m21 = jsonTransformArray.get(3).getAsFloat();
                transform.m22 = jsonTransformArray.get(4).getAsFloat();
                transform.m23 = jsonTransformArray.get(5).getAsFloat();
            }

            Texture texture = new ImageTexture(name, image);
            texture.setWidth(width);
            texture.setHeight(height);
            texture.setPath(path);
            texture.setTransform(transform);

            list.add(texture);
        }

        Textures textures = new Textures(list);
        textures.setFolder(folder);
        return textures;
    }

}
