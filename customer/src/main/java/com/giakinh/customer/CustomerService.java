package com.giakinh.customer;

import com.giakinh.clients.fraud.FraudCheckResponse;
import com.giakinh.clients.fraud.FraudClient;
import com.giakinh.clients.notification.NotificationClient;
import com.giakinh.clients.notification.NotificationRequest;
import com.giakinh.rabbitmq.RabbitMQProducer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;
    private final RabbitMQProducer rabbitMQProducer;
    private final CustomerConfig customerConfig;

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .build();
        customerRepository.saveAndFlush(customer);
        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());
        if(fraudCheckResponse!=null && fraudCheckResponse.isFraudster()){
            throw new IllegalStateException("fraudster");
        }

        NotificationRequest notificationRequest = new NotificationRequest(customer.getId(), customer.getEmail(),
                String.format("Hi %s, Welcome to GiaKinh", customer.getFirstName()), "giakinh0823");


        rabbitMQProducer.publish(notificationRequest, customerConfig.getInternalExchange(), customerConfig.getInternalNotificationRoutingQueue());
    }
}
