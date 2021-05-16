

package ibm.poc.kafka.streams;

import com.fasterxml.jackson.databind.ObjectMapper;
import ibm.poc.kafka.streams.models.BalanceInfo;
import ibm.poc.kafka.streams.models.CustomerInfo;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.streams.KeyValue;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.JsonSerde;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KafkaProducer {

	public static void main(String... args) {

		ObjectMapper mapper = new ObjectMapper();
		Serde<CustomerInfo> customerSerde = new JsonSerde<>(CustomerInfo.class, mapper);
		Serde<BalanceInfo> balanceSerde = new JsonSerde<>(BalanceInfo.class, mapper);

		Map<String, Object> props = new HashMap<>();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(ProducerConfig.RETRIES_CONFIG, 0);
		props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
		props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
		props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, customerSerde.serializer().getClass());

		List<KeyValue<String, CustomerInfo>> customerDetailsSetOne = Arrays.asList(
				new KeyValue<>("111", new CustomerInfo("5678","tom","5453455555","111")),
				new KeyValue<>("222", new CustomerInfo("4321","sony","4353453455","222")),
				new KeyValue<>("333", new CustomerInfo("2435","peter","4534342342","333")),
				new KeyValue<>("444", new CustomerInfo("8756","john","6967532432","444")),
				new KeyValue<>("555", new CustomerInfo("3246","dave","6067576575","555")),
				new KeyValue<>("666", new CustomerInfo("6789","sara","8788977879","666"))
		);

		List<KeyValue<String, CustomerInfo>> customerDetailsSetTwo = Arrays.asList(
				new KeyValue<>("1111", new CustomerInfo("56788","tom2","5453455555","1111")),
				new KeyValue<>("2222", new CustomerInfo("43218","sony2","4353453455","2222")),
				new KeyValue<>("3333", new CustomerInfo("24358","peter2","4534342342","3333")),
				new KeyValue<>("4444", new CustomerInfo("87568","john2","6967532432","4444")),
				new KeyValue<>("5555", new CustomerInfo("32468","dave2","6067576575","5555")),
				new KeyValue<>("6666", new CustomerInfo("67898","sara2","8788977879","6666"))
		);

		DefaultKafkaProducerFactory<String, CustomerInfo> pf = new DefaultKafkaProducerFactory<>(props);
		KafkaTemplate<String, CustomerInfo> template = new KafkaTemplate<>(pf, true);
		template.setDefaultTopic("customer-info");

		for (KeyValue<String,CustomerInfo> keyValue : customerDetailsSetOne) {
			template.sendDefault(keyValue.key, keyValue.value);
		}

		List<KeyValue<String, BalanceInfo>> balanceInfoSetOne = Arrays.asList(
				new KeyValue<>("111", new BalanceInfo("34323","111",10)),
				new KeyValue<>("222", new BalanceInfo("46545","222",20)),
				new KeyValue<>("333", new BalanceInfo("67598","333",30)),
				new KeyValue<>("444", new BalanceInfo("63346","444",40)),
				new KeyValue<>("555", new BalanceInfo("76545","555",50)),
				new KeyValue<>("666", new BalanceInfo("89759","666",60))
		);

		List<KeyValue<String, BalanceInfo>> balanceInfoSetTwo = Arrays.asList(
				new KeyValue<>("1111", new BalanceInfo("34323","1111",100)),
				new KeyValue<>("2222", new BalanceInfo("46545","2222",200)),
				new KeyValue<>("3333", new BalanceInfo("67598","3333",300)),
				new KeyValue<>("4444", new BalanceInfo("63346","4444",400)),
				new KeyValue<>("5555", new BalanceInfo("76545","5555",500)),
				new KeyValue<>("6666", new BalanceInfo("89759","6666",600))
		);

		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, balanceSerde.serializer().getClass());

		DefaultKafkaProducerFactory<String, BalanceInfo> pf1 = new DefaultKafkaProducerFactory<>(props);
		KafkaTemplate<String, BalanceInfo> template1 = new KafkaTemplate<>(pf1, true);
		template1.setDefaultTopic("balance-info");

		for (KeyValue<String,BalanceInfo> keyValue : balanceInfoSetOne) {
			template1.sendDefault(keyValue.key, keyValue.value);
		}

	}

}


