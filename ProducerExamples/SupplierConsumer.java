import java.util.*;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.ConsumerRecord;

public class SupplierConsumer{

        public static void main(String[] args) throws Exception{

                String topicName = "SupplierTopic";
                String groupName = "SupplierTopicGroup";

                Properties props = new Properties();
                props.put("bootstrap.servers", "localhost:9092,localhost:9093");
                props.put("group.id", groupName);// consumer group
                props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
                props.put("value.deserializer", "SupplierDeserializer");// custom deserializer


                KafkaConsumer<String, Supplier> consumer = new KafkaConsumer<>(props);
                consumer.subscribe(Arrays.asList(topicName)); // need to subscribe

                while (true){
                        ConsumerRecords<String, Supplier> records = consumer.poll(100);
                        for (ConsumerRecord<String, Supplier> record : records){
                                System.out.println("Supplier id= " + String.valueOf(record.value().getID()) + " Supplier  Name = " + record.value().getName() + " Supplier Start Date = " + record.value().getStartDate().toString());
                        }
                }

        }
}
