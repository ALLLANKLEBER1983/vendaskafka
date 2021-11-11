package serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import model.Venda;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class VendasSerializer implements Serializer<Venda> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Serializer.super.configure(configs, isKey);
    }

    @SneakyThrows
    @Override
    public byte[] serialize(String topic, Venda venda) {
        return new ObjectMapper().writeValueAsBytes(venda);
    }

    @Override
    public byte[] serialize(String topic, Headers headers, Venda data) {
        return Serializer.super.serialize(topic, headers, data);
    }
}
