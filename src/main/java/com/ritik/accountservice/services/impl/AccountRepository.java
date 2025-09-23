package com.ritik.accountservice.services.impl;

import com.ritik.accountservice.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

interface AccountRepository extends JpaRepository<Account, Long> {
}
