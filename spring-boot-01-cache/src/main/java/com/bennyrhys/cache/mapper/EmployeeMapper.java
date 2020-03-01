package com.bennyrhys.cache.mapper;

import com.bennyrhys.cache.bean.Employee;
import org.apache.ibatis.annotations.*;

@Mapper//可以不写
public interface EmployeeMapper {

    //增
    @Insert("insert into employee (lastName,email,gender,d_id) values (#{lastName},#{email},#{gender},#{dId})")
    public void insertEmployee(Employee employee);

    //删
    @Delete("delete from employee where id=#{id}")
    public void deleteEmployee(Integer id);

    //改
    @Update("update employee set lastName=#{lastName},email=#{email},gender=#{gender},d_id=#{dId}")
    public void updateEmployee(Employee employee);

    //查-按id
    @Select("select * from employee where id=#{id}")
    public Employee selectEmployee(Integer id);
    //查-按名字
    @Select("select * from employee where lastName=#{lastName}")
    public Employee selectEmpBylastName(String lastName);
}
