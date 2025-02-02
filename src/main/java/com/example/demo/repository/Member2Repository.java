package com.example.demo.repository;

import com.example.demo.entity.Member;
import com.example.demo.entity.Member2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Member2Repository extends JpaRepository<Member2, String> {
}
