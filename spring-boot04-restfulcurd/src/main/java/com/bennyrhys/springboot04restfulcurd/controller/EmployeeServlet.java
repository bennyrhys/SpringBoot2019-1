package com.bennyrhys.springboot04restfulcurd.controller;

import com.bennyrhys.springboot04restfulcurd.dao.DepartmentDao;
import com.bennyrhys.springboot04restfulcurd.dao.EmployeeDao;
import com.bennyrhys.springboot04restfulcurd.entities.Department;
import com.bennyrhys.springboot04restfulcurd.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class EmployeeServlet {
    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    DepartmentDao departmentDao;

    //查询所有员工返回列表
    @GetMapping("/emps")
    public String list(Model model){
        //放在请求域中共享，页面方便获取数据
        Collection<Employee> employees = employeeDao.getAll();
        model.addAttribute("emps",employees);
        return "emp/list";
    }

    //查询出添加员工界面
    @GetMapping("/emp")
    public String toaddEmp(Model model){
        //查出用户部门，返回页面
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        return "emp/add";
    }

    //添加员工
    //SpringMVC要求表单的name和bean的name一样
    @PostMapping("/emp")
    public String addEmp(Employee employee){
        //添加员工信息
//        System.out.println(employee);
        employeeDao.save(employee);
        //返回列表界面
        //redirect重定向 forword转发 /为当前项目路径
        return "redirect:/emps";
    }

    //来到当前修改页面，查出当前员工回显
    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable("id") Integer id, Model model){
        Employee employee = employeeDao.get(id);
        model.addAttribute("emp",employee);

        //查出用户部门，返回页面
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);

        //回到修改页面，emp/add是一个添加、修改二合一的页面
        return "emp/add";
    }

    //修改员工信息
    @PutMapping("/emp")
    public String updateEmployee(Employee employee){
        //查看传值数据
//        System.out.println(employee);
//        存储数据
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    //员工删除
    @DeleteMapping("/emp/{id}")
    public String deleteEmp(@PathVariable("id") Integer id){
        employeeDao.delete(id);
        return "redirect:/emps";
    }
}
