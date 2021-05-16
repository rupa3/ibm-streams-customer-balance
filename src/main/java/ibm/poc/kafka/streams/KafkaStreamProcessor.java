package ibm.poc.kafka.streams;

import ibm.poc.kafka.streams.models.BalanceInfo;
import ibm.poc.kafka.streams.models.CustomerInfo;
import ibm.poc.kafka.streams.models.CustomerBalanceInfo;
import org.apache.kafka.streams.kstream.GlobalKTable;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class KafkaStreamProcessor {

		@Bean
		public Function<KStream<String, CustomerInfo>,
				Function<GlobalKTable<String, BalanceInfo>,
										KStream<String, CustomerBalanceInfo>>> process() {

			return customerInfoStream -> (
					balanceInfoTable -> (
							customerInfoStream.join(balanceInfoTable,
									(key, customerInfo) -> customerInfo.getAccountId(),
									(customerInfo, balanceInfo) ->  {
										CustomerBalanceInfo customerBalanceInfo = new CustomerBalanceInfo();
										customerBalanceInfo.setCustomerId(customerInfo.getCustomerId());
										customerBalanceInfo.setBalance(balanceInfo.getBalance());
										customerBalanceInfo.setAccountId(customerInfo.getAccountId());
										customerBalanceInfo.setPhoneNumber(customerInfo.getPhoneNumber());
										return customerBalanceInfo;
									})
					)

			);
		}
	}