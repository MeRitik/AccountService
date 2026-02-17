package com.ritik.accountservice.services;

import com.ritik.accountservice.dto.CustomerDetailsDto;

public interface ICustomersService {
    /**
     * Fetches the customer details including accounts, loans, and card information
     * based on the provided mobile number.
     *
     * @param mobileNumber  the mobile number of the customer whose details need to be fetched
     * @param correlationId the unique id of the request
     * @return a CustomerDetailsDto object containing the customer's details including
     * name, email, account information, loan details, and card information
     */
    CustomerDetailsDto fetchCustomerDetails(String mobileNumber, String correlationId);
}
