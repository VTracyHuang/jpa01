package com.tracy.test;


import com.tracy.demo.utils.JpaUtils;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class JpqlTest {
    /**
     * 查询全部
     *      jpql: from com.tracy.demo.entity.Customer
     *      sql: SELECT * FROM cst_customer
     */
    @Test
    public void testFindAll(){
        //1.获取entityManager对象
        EntityManager em = JpaUtils.getEntityManager();
        //2.开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //3.查询全部
        //String jpql = "from com.tracy.demo.entity.Customer";
        String jpql = "from Customer";
        Query query = em.createQuery(jpql); //创建Query查询对象，query对象才是执行jqpl的对象

        //发送查询，并封装结果集
        List list = query.getResultList();
        for (Object  obj:list) {
            System.out.println(obj);
        }
        //4.提交事务
        tx.commit();
        //5.释放资源
        em.close();
    }

    /**
     * 排序查询：倒序查询全部客户（根据id排序）
     *      sql:  SELECT * FROM cst_customer ORDER BY cust_id DESC
     *      jpql: from Cuatomer order by custId desc
     *
     * 进行jpql查询
     *      1.创建query查询对象
     *      2.对参数赋值
     *      3.查询，并得到返回结果
     */
    @Test
    public void testOrders(){
        //1.获取entityManager对象
        EntityManager em = JpaUtils.getEntityManager();
        //2.开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //3.查询全部
        //String jpql = "from com.tracy.demo.entity.Customer";
        String jpql = "from Customer order by custId desc";
        Query query = em.createQuery(jpql); //创建Query查询对象，query对象才是执行jqpl的对象

        //发送查询，并封装结果集
        List list = query.getResultList();
        for (Object  obj:list) {
            System.out.println(obj);
        }
        //4.提交事务
        tx.commit();
        //5.释放资源
        em.close();
    }

    /**
     * 使用jpql查询，统计客户的总数
     *      sql: SELECT COUNT(cust_id) FROM cst_customer
     *      jpql: select count(custId) from Customer
      */
    @Test
    public void testCount(){
        //1.获取entityManager对象
        EntityManager em = JpaUtils.getEntityManager();
        //2.开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //3.查询全部
        //i.根据Jpql语句创建Query查询对象

        //String jpql = "from com.tracy.demo.entity.Customer";
        String jpql = "SELECT count(custId) from Customer";
        Query query = em.createQuery(jpql); //创建Query查询对象，query对象才是执行jqpl的对象

        Object result = query.getSingleResult();
        System.out.println(result);
        /**
         * getResultList : 直接将查询结果封装为list集合
         * getSingleResult : 得到唯一的结果集
         */

        //4.提交事务
        tx.commit();
        //5.释放资源
        em.close();
    }

    /**
     *  分页查询：
     *      sql: select * from cst_customer limit ?,?
     *      jpql: from Customer
     */
    @Test
    public void testPaged(){
        //1.获取entityManager对象
        EntityManager em = JpaUtils.getEntityManager();
        //2.开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //3.查询全部
        //i.根据Jpql语句创建Query查询对象

        //String jpql = "from com.tracy.demo.entity.Customer";
        String jpql = "from Customer";
        Query query = em.createQuery(jpql); //创建Query查询对象，query对象才是执行jqpl的对象
        //ii.对参数赋值      ————分页参数
        //起始索引
        query.setFirstResult(0);
        query.setMaxResults(2);
        //iii.发送查询，并封装结果

        /**
         * getResultList : 直接将查询结果封装为list集合
         * getSingleResult : 得到唯一的结果集
         */
        List list = query.getResultList();
        for (Object  obj:list) {
            System.out.println(obj);
        }

        //4.提交事务
        tx.commit();
        //5.释放资源
        em.close();
    }

    /**
     *  条件查询：
     *      案例：查询客户名称以“传智播客”开头的客户
     *      sql:SELECT * FROM cst_customer WHERE cust_name LIKE ?
     *      jpql: from Customer where custName like ?
     */
    @Test
    public void testCondition(){
        //1.获取entityManager对象
        EntityManager em = JpaUtils.getEntityManager();
        //2.开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //3.查询全部
        //i.根据Jpql语句创建Query查询对象
        String jpql = "from Customer where custName like ?";
        Query query = em.createQuery(jpql); //创建Query查询对象，query对象才是执行jqpl的对象
        //ii.对参数赋值      ————占位符参数
        query.setParameter(1,"传智播客%");
        //iii.发送查询，并封装结果

        /**
         * getResultList : 直接将查询结果封装为list集合
         * getSingleResult : 得到唯一的结果集
         */
        List list = query.getResultList();
        for (Object  obj:list) {
            System.out.println(obj);
        }

        //4.提交事务
        tx.commit();
        //5.释放资源
        em.close();
    }
}
