package com.bennyrhys.mybits.mapper;

import com.bennyrhys.mybits.bean.Employee;

//@mapper maperscan
public interface EmployeeMapper {


    public Employee getEmp(Integer id);

    public void insertEmp(Employee employee);
}
