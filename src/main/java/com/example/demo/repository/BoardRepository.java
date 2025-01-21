package com.example.demo.repository;

import com.example.demo.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface BoardRepository extends JpaRepository<Board, Integer> {

  @Query("select count(b) from Board b where b.title = ?1")
  long queryCount(String title);

  @Transactional
  @Modifying
  @Query("delete from Board b where b.id > ?1")
  int deleteByIdAfter(Integer id);
}
