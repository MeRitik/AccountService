package com.ritik.accountservice.services;

import com.ritik.accountservice.dto.CustomerDTO;

public interface IAccountService {
    /**
     *
     * Creates a new Customer Account
     *
     * @param customerDTO - CustomerDTO Object
     */
    void createAccount(CustomerDTO customerDTO);

    /**
     *
     * Fetch the customer details of an existing customer.
     *
     * @param mobileNumber - Input Mobile Number
     * @return CustomerDTO - Customer information including name, email and mobileNumber
     */
    CustomerDTO fetchAccount(String mobileNumber);

    /**
     * Updates the account details of an existing customer
     *
     * @param customerDTO - CustomerDTO Object containing updated customer information
     * @return boolean - Returns true if update is successful, false otherwise
     */
    boolean updateAccount(CustomerDTO customerDTO);

    /**
     * Deletes the account associated with the specified mobile number.
     *
     * @param mobileNumber the mobile number of the account to be deleted
     * @return boolean true if the account is successfully deleted, false otherwise
     */
    boolean deleteAccount(String mobileNumber);
}
