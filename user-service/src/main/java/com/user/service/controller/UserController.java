package com.user.service.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.user.service.entity.Employee;
import com.user.service.repository.UserRepository;
import com.user.service.utility.Department;
import com.user.service.utility.EmployeeDto;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/employee")
@Slf4j
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private RestTemplate restTemplate;

	@PostMapping("/")
	public Employee createUser(@RequestBody Employee employee) {
		log.info("conroller method of user service");
		return userRepository.save(employee);
	}

	@GetMapping("/{id}")
	public EmployeeDto getEmployee(@PathVariable Long id) {
		log.info("conroller method of user service");
		Employee employee = userRepository.findByEmployeeId(id);
		Department department = restTemplate
				.getForObject("http://DEPARTMENT-SERVICE/departments/" + employee.getDepartmentId(), Department.class);

		EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);

		employeeDto.setDepartment(department);
		return employeeDto;

	}

	@GetMapping("/")
	public List<Employee> getAll() {
		return userRepository.findAll();
	}
}
