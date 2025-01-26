package com.example.demo.service;

import com.example.demo.entity.UserAction;
import com.example.demo.repository.UserActionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserActionService {

    private final UserActionRepository userActionRepository;

    public List<UserAction> findAll() {
        return userActionRepository.findAll();
    }
}
