package br.com.dopad.domain.entities.util;

import br.com.dopad.domain.entities.line.Line;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JsonUtil {
    private final ObjectMapper objectMapper;

    public JsonUtil(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public <T> List<T> readListFromJsonArray(String jsonArray, Class<T> type) {
        try {
            return objectMapper.readValue(jsonArray, objectMapper.getTypeFactory().constructCollectionType(List.class, type));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error converting JSON to list", e);
        }
    }

    public String writeListAsJsonArray(List<?> list) {
        try {
            return objectMapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error converting list to JSON", e);
        }
    }
}
