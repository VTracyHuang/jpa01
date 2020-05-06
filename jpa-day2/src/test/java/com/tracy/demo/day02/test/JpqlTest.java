package com.tracy.demo.day02.test;


import com.tracy.demo.day02.dao.CustomerDao;
import com.tracy.demo.day02.entity.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class JpqlTest {

    @Autowired
    private CustomerDao customerDao;

    @Test
    public void testFindJPQL(){
        Customer customer = customerDao.findJpql("黑马程序员");
        System.out.println(customer);
    }

    @Test
    public void findCustNameAndId(){
        Customer customer = customerDao.findCustNameAndId("黑马程序员", 5l);
        System.out.println(customer);
    }

    /**
     * 测试jpql的更新操作  //TransactionRequiredException: Executing an update/delete query
     *
     *  *springDataJpa中使用jpql完成 更新、删除操作
     *      *需要手动添加事务的支持
     *      *默认会执行结束之后，回滚事务
     *         @Rollback  (value = false)
     */
    @Test
    @Transactional
    @Rollback(value = false)
    public void testUpdateCustomer(){
        customerDao.updateCustomer(4l,"黑马程序员2");
    }

    /**
     * 测试sql查询
     */
    @Test
    public void testFindSql(){
        List<Object []> list = customerDao.findSql();
        for (Object [] obj: list
             ) {
            System.out.println(Arrays.toString(obj));
        }
    }
    @Test
    public void testConditionSql(){
        List<Object []> list = customerDao.findConditionSql("黑马程序员%");
        for (Object [] obj: list
        ) {
            System.out.println(Arrays.toString(obj));
        }
    }

    /**
     * 测试方法命名规则的sql
     */
    @Test
    public void testNamedSql(){
        Customer customer = customerDao.findByCustName("黑马程序员");
        System.out.println(customer);
    }

    /**
     * 测试方法命名规则的sql
     */
    @Test
    public void testNamedVagueSql(){
        List<Customer> list = customerDao.findByCustNameLike("黑马程序员%");
        list.forEach(System.out::println);

    }

    @Test
    public void testMultipleConditionSql() {
        List<Customer> list = customerDao.findByCustNameLikeAndCustIndustry("黑马程序员%", "Internet");
        list.forEach(System.out::println);
    }
}
