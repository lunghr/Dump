package main.Models.Json;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.ZonedDateTime;

public class ZoneDateTimeAdapter implements JsonSerializer<ZonedDateTime>, JsonDeserializer<ZonedDateTime> {

    @Override
    public ZonedDateTime deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return ZonedDateTime.parse(jsonElement.getAsString());
    }

    @Override
    public JsonElement serialize(ZonedDateTime zonedDateTime, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(zonedDateTime.toString());
    }
}
