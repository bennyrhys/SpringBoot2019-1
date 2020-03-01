package com.bennyrhys.cache.controller;

import com.bennyrhys.cache.bean.Department;
import com.bennyrhys.cache.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @GetMapping("/dept/{id}")
    public Department getDepartmentByid(@PathVariable("id") Integer id){
        Department department = departmentService.selectDepartmentByid(id);
        return department;
    }

}
