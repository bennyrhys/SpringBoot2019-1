package com.bennyrhys.cache.controller;

import com.bennyrhys.cache.bean.Employee;
import com.bennyrhys.cache.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;
    //根据id查找
    @GetMapping("/emp/{id}")
    public Employee getEmployee(@PathVariable("id") Integer id){
        Employee emploee = employeeService.getEmploee(id);
        return emploee;
    }
    //更新
    @GetMapping("/emp")
    public Employee updateEmp(Employee employee){
        Employee emp = employeeService.updateEmp(employee);
        return emp;
    }

    //删除
    @GetMapping("/delemp")
    public String deleteEmp(Integer id){
        employeeService.deleteEmp(id);
        return "success";
    }
    //查找-按名字
    @GetMapping("/emp/lastname/{lastName}")
    public Employee getEmpBylastName(@PathVariable("lastName") String lastName){
        return employeeService.selectEmpBylastName(lastName);
    }
}
