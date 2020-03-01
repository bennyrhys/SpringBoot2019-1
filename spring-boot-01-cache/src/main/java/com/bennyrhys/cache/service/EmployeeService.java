package com.bennyrhys.cache.service;

import com.bennyrhys.cache.bean.Employee;
import com.bennyrhys.cache.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@CacheConfig(cacheNames = "emp")//配置缓存公共属性
@Service
public class EmployeeService {
    /**
     * @Autowired默认按类型装配（这个注解是属于spring的）
     *  依赖对象必须存在，如果要允许null值，可以设置它的required属性为false @Autowired(required=false)。
     *  也可以使用名称装配，配合@Qualifier注解。
     *  @Autowired
     *  @Qualifier("userDao")
     *
     * @Resource(name="userDao") 是JDK1.6支持的注解，默认按照名称进行装配
     */
    @Resource//有红线警示mapper，此注解去除
    @Autowired
    EmployeeMapper employeeMapper;


    //查找

    /**
     *将方法的结果进行缓存，以后再要相同的数据直接从缓存中获取就不用再调用方法了
     *
     *      *
     *      * 原理：
     *      *   1、自动配置类；CacheAutoConfiguration
     *      *   2、缓存的配置类
     *      *   org.springframework.boot.autoconfigure.cache.GenericCacheConfiguration
     *      *   org.springframework.boot.autoconfigure.cache.JCacheCacheConfiguration
     *      *   org.springframework.boot.autoconfigure.cache.EhCacheCacheConfiguration
     *      *   org.springframework.boot.autoconfigure.cache.HazelcastCacheConfiguration
     *      *   org.springframework.boot.autoconfigure.cache.InfinispanCacheConfiguration
     *      *   org.springframework.boot.autoconfigure.cache.CouchbaseCacheConfiguration
     *      *   org.springframework.boot.autoconfigure.cache.RedisCacheConfiguration
     *      *   org.springframework.boot.autoconfigure.cache.CaffeineCacheConfiguration
     *      *   org.springframework.boot.autoconfigure.cache.GuavaCacheConfiguration
     *      *   org.springframework.boot.autoconfigure.cache.SimpleCacheConfiguration【默认】
     *      *   org.springframework.boot.autoconfigure.cache.NoOpCacheConfiguration
     *      *   3、哪个配置类默认生效：SimpleCacheConfiguration；
     *      *
     *      *   4、给容器中注册了一个CacheManager：ConcurrentMapCacheManager
     *      *   5、可以获取和创建ConcurrentMapCache类型的缓存组件；他的作用将数据保存在ConcurrentMap中；
     *      *
     *      *   运行流程：
     *      *   @Cacheable：
     *      *   1、方法运行之前，先去查询Cache（缓存组件），按照cacheNames指定的名字获取；
     *      *      （CacheManager先获取相应的缓存），第一次获取缓存如果没有Cache组件会自动创建。
     *      *   2、去Cache中查找缓存的内容，使用一个key，默认就是方法的参数；
     *      *      key是按照某种策略生成的；默认是使用keyGenerator生成的，默认使用SimpleKeyGenerator生成key；
     *      *          SimpleKeyGenerator生成key的默认策略；
     *      *                  如果没有参数；key=new SimpleKey()；
     *      *                  如果有一个参数：key=参数的值
     *      *                  如果有多个参数：key=new SimpleKey(params)；
     *      *   3、没有查到缓存就调用目标方法；
     *      *   4、将目标方法返回的结果，放进缓存中
     *      *
     *      *   @Cacheable标注的方法执行之前先来检查缓存中有没有这个数据，默认按照参数的值作为key去查询缓存，
     *      *   如果没有就运行方法并将结果放入缓存；以后再来调用就可以直接使用缓存中的数据；
     *      *
     *      *   核心：
     *      *      1）、使用CacheManager【ConcurrentMapCacheManager】按照名字得到Cache【ConcurrentMapCache】组件
     *      *      2）、key使用keyGenerator生成的，默认是SimpleKeyGenerator
     *      *
     *
     *
     *几个属性：
     *  cacheManager缓存管理：对缓存真正对crud操作在cache组件中，每个缓存组件有自己唯一的名字
     *
     *  cacheName/value：指定缓存组件的名字;将方法的返回结果放在哪个缓存中，是数组的方式，可以指定多个缓存；
     *      key：缓存数据使用的key，可以用它来指定。默认是使用方法参数的值 1-方法返回值
     *          编写spel表达式 #id #a0 #p0 #root.args[0]
     *          key = "#root.methodName+'['+#id+']'"
     *      keyGenerator:key生成器；也可以自己指定key的生成器组件id
     *      key/keyGenerator而选一
     *
     *      cacheManager：指定缓存管理器
     *      cacheResolver：指定获取解析器
     *      cacheManager/cacheResolver二选一
     *
     *      condition：指定符合条件下才缓存
     *          , condition = "#id > 0"
     *      unless否定缓存；为true时不缓存，可以获取到结果进行判断。
     *          , unless = "#result == null "
     *
     *      sync:是否使用异步模式
     *          如果异步unless就不支持了
     *
     */
    @Cacheable(/*cacheNames = {"emp"}*//*,keyGenerator = "myKeyGenerator",condition = "#a0>1",unless = "#a0==2"*/)
    public Employee getEmploee(Integer id){
        System.out.printf("查找："+id+"号员工\n");
        Employee employee = employeeMapper.selectEmployee(id);
        return employee;
    }
    /**
     * @CachePut：既调用方法，又更新缓存数据；同步更新缓存
     * 修改了数据库的某个数据，同时更新缓存；
     * 运行时机：
     *  1、先调用目标方法
     *  2、将目标方法的结果缓存起来
     *
     * 测试步骤：
     *  1、查询1号员工；查到的结果会放在缓存中；
     *          key：1  value：lastName：张三
     *  2、以后查询还是之前的结果
     *  3、更新1号员工；【lastName:zhangsan；gender:0】
     *          将方法的返回值也放进缓存了；
     *          key：传入的employee对象  值：返回的employee对象；
     *  4、查询1号员工？
     *      应该是更新后的员工；
     *          key = "#employee.id":使用传入的参数的员工id；
     *          key = "#result.id"：使用返回后的id
     *             @Cacheable的key是不能用#result
     *      为什么是没更新前的？【1号员工没有在缓存中更新】
     *
     */
    @CachePut(/*value = "emp",*/ key = "#result.id")
    public Employee updateEmp(Employee employee){
        System.out.printf("员工更新："+employee);
        employeeMapper.updateEmployee(employee);
        return employee;
    }

    /**
     * 删除数据 清除缓存
     @CacheEvict：缓存清除
      *  key：指定要清除的数据
      *  allEntries = true：指定清除这个缓存中所有的数据
      *  beforeInvocation = false：缓存的清除是否在方法之前执行
      *      默认代表缓存清除操作是在方法执行之后执行;如果出现异常缓存就不会清除
      *
      *  beforeInvocation = true：
      *      代表清除缓存操作是在方法运行之前执行，无论方法是否出现异常，缓存都清除
     *
     */

    @CacheEvict(/*value = "emp"*/ /*key = "id"*/ /*allEntries = true*/)
    public void deleteEmp(Integer id){
        System.out.printf("删除缓存及数据"+id);
//        employeeMapper.deleteEmployee(id);
    }
    // @Caching 定义复杂的缓存规则
    @Caching(
            cacheable = {
                    @Cacheable(value = "emp", key = "#lastName")
            },
            put = {
                    @CachePut(value = "emp", key = "#result.id"),
                    @CachePut(value = "emp", key = "#result.email")
            }
    )
    public Employee selectEmpBylastName(String lastName){
        return employeeMapper.selectEmpBylastName(lastName);
    }

}
