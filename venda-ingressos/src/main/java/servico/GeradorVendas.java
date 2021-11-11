package servico;

import model.Venda;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import serializer.VendasSerializer;

import java.math.BigDecimal;
import java.util.Properties;
import java.util.Random;

public class GeradorVendas {

    private static Random rand = new Random();
    private  static long operacao = 0;
    public static BigDecimal valorIngresso = BigDecimal.valueOf(500);

    public static void main(String[] args) {

        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, VendasSerializer.class.getName());

        try(KafkaProducer<String,Venda> producer = new KafkaProducer<String,Venda>(properties)) {


            while (true) {
                Venda venda = geraVenda();
                ProducerRecord<String, Venda> record = new ProducerRecord<String, Venda>("venda-ingressos", venda);
                producer.send(record);
                Thread.sleep(200);

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static Venda geraVenda() {
        long cliente = rand.nextLong();
        int qtdIngressos = rand.nextInt(10);

        return new Venda(operacao++,cliente,qtdIngressos,valorIngresso.multiply(BigDecimal.valueOf(qtdIngressos)));

    }
}
