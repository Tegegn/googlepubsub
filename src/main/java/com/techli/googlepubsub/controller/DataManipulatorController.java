package com.techli.googlepubsub.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techli.googlepubsub.dto.DBResponse;
import com.techli.googlepubsub.model.QuatSenterez;
import com.techli.googlepubsub.repository.QuatRepository;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;

@Api(tags = "This Api performs basic crud operation on cloud mysql database", description = "CRUD on DB")
@RestController
@RequestMapping("/data")
public class DataManipulatorController {

	@Autowired
	private QuatRepository repository;
	
	@ApiOperation(notes = "Given an id of a record this method fetches the entity from DB", value = "Fetch Data")
	@ApiResponse(response = QuatSenterez.class, code = 0, message = "Returns the entity saved")
	@RequestMapping(value="/show", method=RequestMethod.GET)
	public Optional<QuatSenterez> saveData(@RequestParam int id) {
		return repository.findById(id);

	}
	
	@RequestMapping(value="/showAll", method=RequestMethod.GET)
	public Iterable<QuatSenterez> showAllData() {
		return repository.findAll();

	}
	
	@RequestMapping(value="/createUpdate", method=RequestMethod.PUT)
	public QuatSenterez saveOrModifyData(@RequestBody QuatSenterez quatSenterez) {
		
		System.out.println("Received data: "+ quatSenterez.toString());
		return repository.save(quatSenterez);

	}
	
	@RequestMapping(value="/delete", method=RequestMethod.DELETE)
	public DBResponse deleteData(@RequestParam int id) {
				repository.deleteById(id);
				DBResponse dbResponse = new DBResponse();
				dbResponse.setId(id);
				dbResponse.setNotify("Record with id = "+ id+ " has been deleted!");
				dbResponse.setStatus(HttpStatus.OK);
				System.out.println("Modification request for record with id = "+ id + " received");
				return dbResponse;
	}


}
