package com.example.FullStackDemo.helloworld;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class HelloWorldController {
@GetMapping("/Helloworld")
public HelloWorldBean hello()
{
	//throw new RuntimeException("Error has happened");
	return new HelloWorldBean("Hello World Prabhanjan");
}
@GetMapping("/Helloworld/path-variable/{name}")
public HelloWorldBean helloPathvariable(@PathVariable String name) 
{
	return new HelloWorldBean(String.format("Helloworld, %s", name));
}
}
