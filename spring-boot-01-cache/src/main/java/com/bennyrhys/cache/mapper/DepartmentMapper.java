package com.bennyrhys.cache.mapper;

import com.bennyrhys.cache.bean.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper//可以不写
public interface DepartmentMapper {
    //id查找
    @Select("select * from department where id=#{id}")
    Department selectDepartmentByid(Integer id);

    //增加

}
