package com.anle.employeeservice.query.controller;

import java.util.List;

import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.TypeCollector.ReachableTypes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anle.employeeservice.query.model.EmployeeReponseModel;
import com.anle.employeeservice.query.queries.GetAllEmployeeQuery;
import com.anle.employeeservice.query.queries.GetEmployeesQuery;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeQueryController {
	@Autowired
	private QueryGateway queryGateway;

	@GetMapping("/{employeeId}")
	public EmployeeReponseModel getEmployeeDetail(@PathVariable String employeeId) {
		GetEmployeesQuery employeesQuery = new GetEmployeesQuery();
		employeesQuery.setEmployeeId(employeeId);
		EmployeeReponseModel employeeReponseModel = queryGateway
				.query(employeesQuery, ResponseTypes.instanceOf(EmployeeReponseModel.class)).join();
		return employeeReponseModel;
	}

	@GetMapping
	public List<EmployeeReponseModel> getAllEmployee() {
		List<EmployeeReponseModel> list = queryGateway
				.query(new GetAllEmployeeQuery(), ResponseTypes.multipleInstancesOf(EmployeeReponseModel.class)).join();
		return list;
	}
}
