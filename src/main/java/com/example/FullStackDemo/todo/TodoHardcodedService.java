package com.example.FullStackDemo.todo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TodoHardcodedService {
	private static List<TodoBean> todos = new ArrayList<TodoBean>();
	private static long idCounter = 0;

	static {
		todos.add(new TodoBean(++idCounter, "Prabhanjan", "Learn about Angular", new Date(), false));
		todos.add(new TodoBean(++idCounter, "Prabhanjan", "Learn about FullStack Course", new Date(), false));
		todos.add(new TodoBean(++idCounter, "Prabhanjan", "Play Games", new Date(), false));
	}

	public List<TodoBean> findAll() {
		return todos;
	}

	public TodoBean save(TodoBean todo) {
		if (todo.getId() == -1 || todo.getId() == 0) {
			todo.setId(++idCounter);
			todos.add(todo);
		} else {
			deleteById(todo.getId());
			todos.add(todo);
		}
		return todo;
	}

	public TodoBean deleteById(long id) {
		TodoBean todo = findById(id);
		if (todo == null)
			return null;
		if (todos.remove(todo)) {
			return todo;
		}
		return null;
	}

	public TodoBean findById(long id) {
		for (TodoBean todo : todos) {
			if (todo.getId() == id) {
				return todo;
			}
		}
		return null;
	}
}
