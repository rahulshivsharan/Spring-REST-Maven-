package com.pk;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.pk.services.StudentService;
import com.pk.vo.StudentVO;

@RestController
@RequestMapping(value="/first")
public class MyFirst{
	
	public MyFirst() {
		System.out.println("Webservice 'MyFirst' initialised...");
	}

	
	@Autowired
	@Qualifier("studentService")
	private StudentService service;
	
	public StudentService getService() {
		return service;
	}

	public void setService(StudentService service) {
		this.service = service;
	}

	@RequestMapping(value="/hello",method=RequestMethod.GET,produces="application/json")	
	public ResponseEntity<String> sayHello() {		
		StringBuffer strb = new StringBuffer();
		strb.append("{")
			.append("\"message\"")
			.append(":")
			.append("\"Hello, this is my first message\"")
			.append("}");
		ResponseEntity<String> responseEntity = new ResponseEntity<String>(strb.toString(), HttpStatus.OK);
		return responseEntity;
	}
	
	
	@RequestMapping(value="/getStudents",method=RequestMethod.GET,produces="application/json")
	public List<StudentVO> getAllStudents(){		
		List<StudentVO> list = null;		
		try {			
			list = this.service.getAll();			
		}catch(Exception e) {
			e.printStackTrace();
		}	
		
		return list;
	}
	
	@RequestMapping(value="/fetchStudents",method=RequestMethod.GET,produces="application/json")
	public ResponseEntity<List<StudentVO>> fetchAllStudents(){		
		List<StudentVO> list = null;
		ResponseEntity<List<StudentVO>> responseEntity = null;		
		try {			
			list = this.service.getAll();
			responseEntity = new ResponseEntity<List<StudentVO>>(list,HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
		}	
		
		return responseEntity;
	}
	
	@RequestMapping(value="/student",method=RequestMethod.POST,produces="application/json",consumes="application/json")
	public ResponseEntity<List<StudentVO>> createStudent(@RequestBody StudentVO vo){		
		List<StudentVO> list = null;
		ResponseEntity<List<StudentVO>> responseEntity = null;		
		try {
			System.out.println(" POST Student "+vo);
			list = this.service.createStudent(vo);
			responseEntity = new ResponseEntity<List<StudentVO>>(list,HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
		}	
		
		return responseEntity;
	}
}
