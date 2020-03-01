package com.bennyrhys.cache.service;

import com.bennyrhys.cache.bean.Department;
import com.bennyrhys.cache.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {
    @Autowired
    DepartmentMapper departmentMapper;
    @Autowired
    CacheManager cacheManager;

    //id查找部门
//    @Cacheable(cacheNames = "dept")
//    public Department selectDepartmentByid(Integer id){
//        Department department = departmentMapper.selectDepartmentByid(id);
//        return department;
//    }


    //截取缓存进程操作
    @Cacheable(cacheNames = "dept")
    public Department selectDepartmentByid(Integer id){
        Department department = departmentMapper.selectDepartmentByid(id);
        Cache dept = cacheManager.getCache("dept");
        //获取某个缓存
        dept.put("key-dept1",department);
        return department;
    }
}
