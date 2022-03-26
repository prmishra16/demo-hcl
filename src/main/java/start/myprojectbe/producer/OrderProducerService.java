package start.myprojectbe.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import start.myprojectbe.dto.OrderDto;

import java.util.Objects;
import java.util.concurrent.ExecutionException;

@Service
@Slf4j
public class OrderProducerService {
    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    private String topic;
    @Autowired
    private ObjectMapper mapper;

    public void publishOrder(OrderDto orderDto) throws JsonProcessingException, ExecutionException, InterruptedException {
        ListenableFuture<SendResult<String, String>> result =  kafkaTemplate.send(topic,orderDto.getOrderRefNo().toString(),
                mapper.writeValueAsString(orderDto));
        if(result.isDone() && !Objects.isNull(result.get().getRecordMetadata()))
        {
            log.info("published event {}",result.get().getProducerRecord().value());
        }

    }
}
