package com.ritik.accountservice.services;

import com.ritik.accountservice.dto.CustomerDTO;

public interface IAccountService {
    /**
     * @param customerDTO - CustomerDTO Object
     */
    void createAccount(CustomerDTO customerDTO);

    /**
     * @param mobileNumber - Input Mobile Number
     * @return CustomerDTO - Customer information including name, email and mobileNumber
     */
    CustomerDTO fetchAccount(String mobileNumber);
}
