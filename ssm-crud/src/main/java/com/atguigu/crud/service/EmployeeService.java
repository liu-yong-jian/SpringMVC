package com.atguigu.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.crud.bean.Employee;
import com.atguigu.crud.dao.EmployeeMapper;

/**
 * 
 * @author Danicoz
 *
 *1、员工业务处理，与Dao 打交道
 */
@Service
public class EmployeeService {

	@Autowired
	EmployeeMapper employeeMapper;//引入Dao
	
	/**
	 * 查询所有员工
	 * @return
	 */
	public List<Employee> getAll() {
		return employeeMapper.selectByExampleWithDept(null);
	}

}
