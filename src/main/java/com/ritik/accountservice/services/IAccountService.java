package com.ritik.accountservice.services;

import com.ritik.accountservice.dto.CustomerDTO;

public interface IAccountService {
    /**
     *
     * @param customerDTO - CustomerDTO Object
     */
    void createAccount(CustomerDTO customerDTO);
}
