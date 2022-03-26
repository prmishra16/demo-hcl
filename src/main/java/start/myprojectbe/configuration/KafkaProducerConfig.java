package start.myprojectbe.configuration;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.Map;

@Configuration
public class KafkaProducerConfig {
//    kafka:
//    bootstrap-server: localhost:9092
//    client-id: portfoliosvc
//    acks: all
//    retries: 3
//    key-serializer: org.apache.kafka.common.serialization.StringSerializer
//    value-serializer: org.apache.kafka.common.serialization.StringSerializer
//    outbound-topic: portfolio-outbound
    @Value("${kafka.bootstrap-server}")
    private String bootStrapServer;
    @Value("${kafka.client-id}")
    private String clientId;
    @Value("${kafka.acks}")
    private String acks;
    @Value("${kafka.retries}")
    private String retries;
    @Autowired
    private KafkaProperties kafkaProperties;

    @Bean
    public ProducerFactory<String,String> producerFactory(){
        Map<String,Object> configProps = kafkaProperties.buildProducerProperties();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,bootStrapServer);
        configProps.put(ProducerConfig.CLIENT_ID_CONFIG,clientId);
        configProps.put(ProducerConfig.ACKS_CONFIG,acks);
        configProps.put(ProducerConfig.RETRIES_CONFIG,retries);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String,String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }


}
