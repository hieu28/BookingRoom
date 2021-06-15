package com.example.room.services;

import com.example.room.models.entities.Employee;
import com.example.room.repositories.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServices {
    private final AccountRepository repository;
    private Optional<Employee> account;

    public AccountServices(AccountRepository repository) {
        this.repository = repository;
    }

    public Employee findAccount(String email){
        account = repository.findByEmail(email);
        if(!account.isPresent()){
            return null;
        }
        return account.get();

    }


}
