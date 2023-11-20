package com.example.todobackend.service;

import com.example.todobackend.entity.Stat;
import com.example.todobackend.repo.StatRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StatService {

    // сервис имеет право обращаться к репозиторию (БД)
    private final StatRepository repository;

    public StatService(StatRepository repository) {
        this.repository = repository;
    }

    public Stat findStat(String email) {
        return repository.findByUserEmail(email);
    }

}
