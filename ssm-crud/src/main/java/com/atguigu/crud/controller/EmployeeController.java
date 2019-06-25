package com.atguigu.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.crud.bean.Employee;
import com.atguigu.crud.bean.Msg;
import com.atguigu.crud.service.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 处理员工CRUD 请求
 * 
 * @author Danicoz
 *
 *         1、控制类， jsp 转过来这里处理
 */
@Controller
public class EmployeeController {
	// 交给Service 处理业务逻辑
	@Autowired
	EmployeeService employeeService;
	
	/*
	 * 导入jackson 包， 把返回的对象生成json 对象
	 */
	@RequestMapping("/emps")
	@ResponseBody
	public Msg getEmpsWithJson(@RequestParam(value = "pn", defaultValue = "1") Integer pn){
		PageHelper.startPage(pn, 5);// 紧跟查询数据方法就是分页查询
		List<Employee> emps = employeeService.getAll();

		// 封装了详细的分页信息，包括员工信息， 5 表示连续显示的页数
		PageInfo page = new PageInfo(emps, 5);
		return Msg.sucess().add("pageInfo", page);
	}
	
	/**
	 * @RequestMapping("/emps")
	 * jsp 实现的界面显示
	 */
	// 跳转过来的URI
	public String getEmps(
			@RequestParam(value = "pn", defaultValue = "1") Integer pn,
			Model model) {
		System.out.println("跳转成功！");

		// 利用pageHelper 进行分页查询
		// 传入页码和每页的行数
		PageHelper.startPage(pn, 5);// 紧跟查询数据方法就是分页查询
		List<Employee> emps = employeeService.getAll();

		// 封装了详细的分页信息，包括员工信息， 5 表示连续显示的页数
		PageInfo page = new PageInfo(emps, 5);
		
		//把信息放入Model中，后续可在jsp 取数
		model.addAttribute("pageInfo", page);
		return "list";// 配置了页面拦截器，会跳转到对应的jsp上
	}

}
