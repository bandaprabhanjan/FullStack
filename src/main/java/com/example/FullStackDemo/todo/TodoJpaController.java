package com.example.FullStackDemo.todo;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class TodoJpaController {
	@Autowired
	private TodoHardcodedService todoService;
	@Autowired
	private TodoJpaRepository todoJpaRepository;
	
	@GetMapping("/jpa/users/{username}/todos")
	public List<TodoBean> getAllTodos(@PathVariable String username)
	{
		return todoJpaRepository.findByUsername(username);
	}
	
	@GetMapping("/jpa/users/{username}/todos/{id}")
	public TodoBean getTodo(@PathVariable String username,@PathVariable long id)
	{
		return todoJpaRepository.findById(id).get();
	}

	@DeleteMapping("/jpa/users/{username}/todos/{id}")
	public ResponseEntity<Void> deleteTodo(
			@PathVariable String username, @PathVariable long id){
		TodoBean todo = todoService.deleteById(id);
		if(todo!=null)	{
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/jpa/users/{username}/todos/{id}")
	public ResponseEntity<TodoBean> updateTodo(
			@PathVariable String username, @PathVariable long id,@RequestBody TodoBean todo){
		TodoBean todoUpdated = todoService.save(todo);
		return new ResponseEntity<TodoBean>(todo,HttpStatus.OK);
	}
	
	@PostMapping("/jpa/users/{username}/todos")
	public ResponseEntity<Void> insertTodo(
			@PathVariable String username,@RequestBody TodoBean todo){
		TodoBean createdTodo=todoService.save(todo);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("{id}").buildAndExpand(createdTodo.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}