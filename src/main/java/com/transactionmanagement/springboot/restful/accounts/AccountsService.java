package com.transactionmanagement.springboot.restful.accounts;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Service

@RequiredArgsConstructor
public class AccountsService {
    private final AccountsRepository accountsRepository;


    public List<Accounts> findAll() {
        return accountsRepository.findAll();
    }

    public Optional<Accounts> findById(Long id) {
        return accountsRepository.findById(id);
    }

    public Accounts save(Accounts details) {
        return accountsRepository.save(details);
    }

    public void deleteById(Long id) {
        accountsRepository.deleteById(id);
    }
}