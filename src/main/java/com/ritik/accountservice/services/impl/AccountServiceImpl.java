package com.ritik.accountservice.services.impl;

import com.ritik.accountservice.constants.AccountConstants;
import com.ritik.accountservice.dto.AccountDTO;
import com.ritik.accountservice.dto.CustomerDTO;
import com.ritik.accountservice.entity.Account;
import com.ritik.accountservice.entity.Customer;
import com.ritik.accountservice.exception.CustomerAlreadyExistsException;
import com.ritik.accountservice.exception.ResourceNotFoundException;
import com.ritik.accountservice.mapper.AccountMapper;
import com.ritik.accountservice.mapper.CustomerMapper;
import com.ritik.accountservice.repository.AccountRepository;
import com.ritik.accountservice.repository.CustomerRepository;
import com.ritik.accountservice.services.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDTO customerDTO) {
        Optional<Customer> existsCustomer = customerRepository.findByMobileNumber(customerDTO.getMobileNumber());

        if (existsCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer already exists with given mobile number " + existsCustomer.get().getMobileNumber());
        }

        Customer customer = CustomerMapper.mapToCustomer(customerDTO, new Customer());
        Customer savedCustomer = customerRepository.save(customer);
        accountRepository.save(createNewAccount(savedCustomer));
    }

    /**
     * @param customer - Customer Object
     * @return the new account details
     */
    private Account createNewAccount(Customer customer) {
        Account account = new Account();
        account.setCustomerId(customer.getCustomerId());
        long randomAccountNumber = 202500000000L + (long) (Math.random() * 1000000000L);
        account.setAccountNumber(randomAccountNumber);
        account.setAccountType(AccountConstants.SAVINGS);
        account.setBranchAddress(AccountConstants.ADDRESS);
        return account;
    }

    @Override
    public CustomerDTO fetchAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));

        Account account = accountRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Account", "mobileNumber", mobileNumber));
        CustomerDTO customerDTO = CustomerMapper.mapToCustomerDTO(customer, new CustomerDTO());
        customerDTO.setAccount(AccountMapper.toAccountDTO(account, new AccountDTO()));
        return customerDTO;
    }

    @Override
    public boolean updateAccount(CustomerDTO customerDTO) {
        boolean isUpdated = false;

        AccountDTO accountDTO = customerDTO.getAccount();
        if (accountDTO != null) {
            Account account = accountRepository.findById(accountDTO.getAccountNumber())
                    .orElseThrow(() -> new ResourceNotFoundException("Account", "accountNumber", accountDTO.getAccountNumber().toString()));

            AccountMapper.toAccount(accountDTO, account);
            account = accountRepository.save(account);

            long customerId = account.getCustomerId();
            Customer customer = customerRepository.findById(customerId)
                    .orElseThrow(() -> new ResourceNotFoundException("Customer", "customerId", String.valueOf(customerId)));
            CustomerMapper.mapToCustomer(customerDTO, customer);
            customerRepository.save(customer);
            isUpdated = true;
        }

        return isUpdated;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));

        accountRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());

        return true;
    }
}
