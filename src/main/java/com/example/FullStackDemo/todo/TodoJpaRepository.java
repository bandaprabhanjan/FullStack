package com.example.FullStackDemo.todo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.lang.String;
import com.example.FullStackDemo.todo.TodoBean;
import java.util.List;

@Repository
public interface TodoJpaRepository extends JpaRepository<TodoBean, Long> {
	List<TodoBean> findByUsername(String username);	

}
