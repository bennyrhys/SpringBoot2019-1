package com.bennyrhys.cache;

import com.bennyrhys.cache.bean.Employee;
import com.bennyrhys.cache.mapper.EmployeeMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import javax.annotation.Resource;

@SpringBootTest
class SpringBoot01CacheApplicationTests {
    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    RedisTemplate redisTemplate;//k-v都是对象的
    @Autowired
    StringRedisTemplate stringRedisTemplate;//操作k-v都是字符串的


//    @Autowired //最好下面的名字匹配
    @Resource
    RedisTemplate<Object, Employee> empRedisTemplate;
    /**
     * Redis常见的五大数据类型
     *  String（字符串）、List（列表）、Set（集合）、Hash（散列）、ZSet（有序集合）
     *  stringRedisTemplate.opsForValue()[String（字符串）]
     *  stringRedisTemplate.opsForList()[List（列表）]
     *  stringRedisTemplate.opsForSet()[Set（集合）]
     *  stringRedisTemplate.opsForHash()[Hash（散列）]
     *  stringRedisTemplate.opsForZSet()[ZSet（有序集合）]
     */

    //测试redis命令行对应的功能
    @Test
    public void test01(){
        //存入append s1
//        stringRedisTemplate.opsForValue().append("s1","hello");

        //读取s1
        String s1 = stringRedisTemplate.opsForValue().get("s1");
        System.out.println(s1);

        //列表插入
//        stringRedisTemplate.opsForList().leftPush("l1","1");
//        stringRedisTemplate.opsForList().leftPush("l1","2");
//        stringRedisTemplate.opsForList().leftPush("l1","3");

    }

    //
    @Test
    public void test02(){
        Employee employee = employeeMapper.selectEmployee(1);
        //默认如果保存对象，使用jdk序列化机制，序列化后的数据保存到redis中
        //redisTemplate.opsForValue().set("emp-01",empById);
        //1、将数据以json的方式保存
        //(1)自己将对象转为json
        //(2)redisTemplate默认的序列化规则；改变默认的序列化规则；


        //1、此处报错 要可序列化的employee类public class Employee implements Serializable {


        //2、导入要转化的serializer json
        //Jackson2JsonRedisSerializer<Employee> serializer = new Jackson2JsonRedisSerializer<Employee>(Employee.class);
        //自己放一个序列化器
//        redisTemplate.setDefaultSerializer(serializer);
//        redisTemplate.opsForValue().set("save1",employee);


        //3、手写更改默认配置
        empRedisTemplate.opsForValue().set("save1",employee);




    }

    @Test
    void contextLoads() {
        Employee employee = employeeMapper.selectEmployee(1);
        System.out.printf("printout-data："+employee);
    }

}
