package com.ritik.accountservice.services.impl;

import com.ritik.accountservice.dto.AccountDTO;
import com.ritik.accountservice.dto.CardDTO;
import com.ritik.accountservice.dto.CustomerDetailsDto;
import com.ritik.accountservice.dto.LoanDTO;
import com.ritik.accountservice.entity.Account;
import com.ritik.accountservice.entity.Customer;
import com.ritik.accountservice.exception.ResourceNotFoundException;
import com.ritik.accountservice.mapper.AccountMapper;
import com.ritik.accountservice.mapper.CustomerMapper;
import com.ritik.accountservice.repository.AccountRepository;
import com.ritik.accountservice.repository.CustomerRepository;
import com.ritik.accountservice.services.ICustomersService;
import com.ritik.accountservice.services.client.CardsFeignClient;
import com.ritik.accountservice.services.client.LoansFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomersServiceImpl implements ICustomersService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final CardsFeignClient cardsFeignClient;
    private final LoansFeignClient loansFeignClient;

    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));

        Account account = accountRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Account", "mobileNumber", mobileNumber));

        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
        customerDetailsDto.setAccount(AccountMapper.toAccountDTO(account, new AccountDTO()));

        ResponseEntity<LoanDTO> loanDto = loansFeignClient.fetchLoanDetails(mobileNumber);
        customerDetailsDto.setLoan(loanDto.getBody());

        ResponseEntity<CardDTO> cardDto = cardsFeignClient.fetchCardDetails(mobileNumber);
        customerDetailsDto.setCard(cardDto.getBody());

        return customerDetailsDto;
    }
}
