package com.example.demo.repository;

import com.example.demo.entity.Blog;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository
        extends JpaRepository<Blog, Long> {


    @Query("select count(b) from Blog b")
    long totalCount();

    @Query("select count(b) from Blog b where b.blogId >= 3")
    long up3();

    @Query("select b from Blog b where b.content2 like '%행복%' order by b.blogId DESC")
    List<Blog> findHappy();


    @Query("select b.content from Blog b where b.blogId = 1")
    String findByBlogId1();

    @Query("select b.content from Blog b where b.blogId = 2")
    String findByBlogId2();

    @Query("select b from Blog b where b.blogId = 3")
    Blog findByBlogId3();
}

