package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Comment;
import com.example.payloads.CommentDTO;
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
