package com.techli.googlepubsub.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techli.googlepubsub.model.QuatSenterez;
import com.techli.googlepubsub.repository.QuatRepository;

@RestController
@RequestMapping("/data")
public class DataManipulatorController {

	@Autowired
	private QuatRepository repository;
	
	@RequestMapping(value="/show", method=RequestMethod.GET)
	public Optional<QuatSenterez> saveData(int id) {
		return repository.findById(id);

	}
	
	@RequestMapping(value="/showAll", method=RequestMethod.GET)
	public Iterable<QuatSenterez> showAllData() {
		return repository.findAll();

	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public QuatSenterez saveData(@RequestBody QuatSenterez quatSenterez) {
		
		System.out.println("Received data: "+ quatSenterez.toString());
		return repository.save(quatSenterez);

	}

}
