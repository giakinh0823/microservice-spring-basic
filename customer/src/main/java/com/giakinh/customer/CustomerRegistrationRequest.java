package com.giakinh.customer;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CustomerRegistrationRequest {
    private String firstName;
    private String lastName;
    private String email;
}
