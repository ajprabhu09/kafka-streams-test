package myapps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DatasetDeSerializer implements Deserializer<Map<String, Object>> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Deserializer.super.configure(configs, isKey);
    }

    @Override
    public Map<String, Object> deserialize(String s, byte[] bytes) {
        TypeReference<HashMap<String,Object>> typeRef
                = new TypeReference<HashMap<String,Object>>() {};
        try {
            return new ObjectMapper().readValue(bytes, typeRef);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public void close() {
        Deserializer.super.close();
    }
}
