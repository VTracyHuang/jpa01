package com.tracy.demo.day02.test;


import com.tracy.demo.day02.dao.CustomerDao;
import com.tracy.demo.day02.entity.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class CustomerDaoTest {

    @Autowired
    private CustomerDao customerDao;

    /**
     * 根据id查询
     */
    @Test
    public void testFindOne(){
        Customer customer = customerDao.findOne(3l);
        System.out.println(customer);
    }

    /**
     * save: 保存或者更新
     *          根据传递的对象是否存在主键id,如果没有id主键属性，保存
     *          如果没有id主键属性，保存
     *          存在id主键属性，根据Id查询数据，更新数据
     */
    @Test
    public void save(){
        Customer customer = new Customer();
        customer.setCustName("黑马程序员2");
        customer.setCustLevel("vip");
        customer.setCustIndustry("it教育");
        customerDao.save(customer);
    }
    @Test
    public void update(){
        Customer customer = new Customer();
        customer.setCustId(5l);
        customer.setCustName("黑马程序员");
        customer.setCustLevel("vip");
        customer.setCustIndustry("it教育");
        customerDao.save(customer);
    }

    @Test
    public void testDelete(){
        customerDao.delete(3l);
    }
    /**
     * 查询所有
     */
    @Test
    public void testFindAll(){
        List<Customer> list = customerDao.findAll();
        list.forEach(System.out::println);
    }

    /**
     * 测试统计查询：查询客户的总数量
     */
    @Test
    public void testCount(){
        long count = customerDao.count();
        System.out.println(count);
    }
    /**
     * 测试判断id为4的客户是否存在
     *      1.可以查询一下id为4的客户
     *          如果值为空，代表不存在，如果不为空，代表存在
     *      2.判断数据库中id为4的客户的数量
     *          如果数量为0，代表不存在，如果大于9，代表存在
     */
    @Test
    public void testExists(){
        boolean exists = customerDao.exists(4l);
        System.out.println("id为4的客户是否存在："+exists);
    }

    /**
     * 根据id从数据库查询
     * @Tramsaction 保证getOne正常运行  //报错org.hibernate.LazyInitializationException: could not initialize proxy - no Session
     *
     * findOne:
     *         em.find();             立即加载
     * getOne:
     *         em.getReference();    延迟加载
     *         * 返回的是一个客户的动态代理对象
     *         *什么时候用，什么时候查询
     */
    @Test
    @Transactional
    public void testGetOne(){
        Customer customer = customerDao.getOne(4L);
        System.out.println(customer);
    }
}
