package com.ritik.accountservice.services.impl;

import com.ritik.accountservice.dto.CustomerDTO;
import com.ritik.accountservice.repository.CustomerRepository;
import com.ritik.accountservice.services.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDTO customerDTO) {
    }
}
