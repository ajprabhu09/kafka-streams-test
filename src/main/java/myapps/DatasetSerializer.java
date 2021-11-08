package myapps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class DatasetSerializer implements Serializer<Map<String, Object>> {
    @Override
    public byte[] serialize(String s, Map<String, Object> stringObjectMap) {
        try {
            return new ObjectMapper().writeValueAsBytes(stringObjectMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
