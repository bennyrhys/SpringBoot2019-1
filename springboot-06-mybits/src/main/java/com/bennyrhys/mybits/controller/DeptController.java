package com.bennyrhys.mybits.controller;

import com.bennyrhys.mybits.bean.Department;
import com.bennyrhys.mybits.bean.Employee;
import com.bennyrhys.mybits.mapper.DepartmentMapper;
import com.bennyrhys.mybits.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeptController {

    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    EmployeeMapper employeeMapper;

    //通过id查询
    @GetMapping("/dept/{id}")
    public Department getDepartment(@PathVariable("id") Integer id){
        return departmentMapper.getDeptById(id);
    }
    //插入
    @GetMapping("/dept")
    public Department insertdept(Department department){
        departmentMapper.insertDept(department);
        return department;
    }
    //查询员工
    @GetMapping("/emp/{id}")
    public Employee getEmp(@PathVariable("id") Integer id){
        return employeeMapper.getEmp(id);
    }
}
