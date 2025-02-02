package com.example.demo.repository;

import com.example.demo.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface BoardRepository extends JpaRepository<Board, Integer> {

  @Query("select count(b) from Board b where b.title = ?1")
  long queryCount(String title);

  //부등호 등의 세부 조건인 경우 JPQL을 사용해주어야함.
  //JPQL을 사용해 insert, delete, update를 하는 경우 Transactional, Modifying넣어주어야함.
  @Transactional
  @Modifying
  @Query("delete from Board b where b.no > ?1")
  int deleteByIdAfter(Integer id);
}
