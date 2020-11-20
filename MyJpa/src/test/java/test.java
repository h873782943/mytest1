import com.itheima.bean.Customer;
import com.itheima.util.JpaUtils;
import org.junit.Test;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

/**
 * @program: MyJpa
 * @description:
 * @author: Mr.Hou
 * @create: 2020-11-04 09:24
 **/
public class test {

    @Test
    public void test() {
/**
 * 创建实体管理类工厂，借助Persistence的静态方法获取
 * 其中传递的参数为持久化单元名称，需要jpa配置文件中指定
 */
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("myjpa");
////创建实体管理类
//        EntityManager em = factory.createEntityManager();
////获取事务对象
//        EntityTransaction tx = em.getTransaction();
////开启事务
//        tx.begin();
//        Customer c = new Customer();
//        c.setCustName("传智播客5");
////保存操作
//        em.persist(c);
////提交事务
//        tx.commit();
////释放资源
//        em.close();
        factory.close();
    }
    
    @Test
    public void fun(){
        EntityManager entityManager = JpaUtils.getEntityManager();
        Customer c = new Customer();
        c.setCustName("传智学院1");
        c.setCustLevel("VIP客户2");
        c.setCustSource("网络2");
        c.setCustIndustry("IT教育1");
        c.setCustAddress("昌平区北七家镇2");
        c.setCustPhone("010‐843893402");
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(c);
        transaction.commit();
        entityManager.close();


    }
    @Test
    public void fun01(){
        // 定义对象
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
// 获取实体管理对象
            em = JpaUtils.getEntityManager();
// 获取事务对象
            Customer c1 = em.find(Customer.class, 1L);
// 提交事务
            System.out.println(c1); // 输出查询对象
        } catch (Exception e) {
// 回滚事务
            tx.rollback();
            e.printStackTrace();
        } finally {
// 释放资源
            em.close();
        }

    }

    @Test
    public void fun02(){
        EntityManager manager = JpaUtils.getEntityManager();
        Customer customer = manager.find(Customer.class, 1L);
        Customer customer1 = manager.find(Customer.class, 1L);
        System.out.println(customer==customer1);
        manager.close();

        Customer customer2 = JpaUtils.getEntityManager().find(Customer.class, 1L);
        System.out.println(customer==customer2);
    }

    @Test
    public void fun03(){
        EntityManager entityManager = JpaUtils.getEntityManager();
        Customer reference = entityManager.getReference(Customer.class, 2L);
        System.out.println(reference);
        entityManager.close();
    }

    @Test
    public void fun04(){
        EntityManager entityManager = JpaUtils.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Customer customer = entityManager.find(Customer.class, 2L);
        customer.setCustName("aaaaa");
        entityManager.merge(customer);
        transaction.commit();
        entityManager.close();
    }
    @Test
    public void fun05(){
        EntityManager entityManager = JpaUtils.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Customer customer = entityManager.find(Customer.class, 2L);
        entityManager.remove(customer);
        transaction.commit();
        entityManager.close();
    }

    @Test
    public void fun06(){
        EntityManager r = JpaUtils.getEntityManager();
        EntityTransaction rTransaction = r.getTransaction();
        rTransaction.begin();

        String jsql="from Customer";
        Query query = r.createQuery(jsql);

        List resultList = query.getResultList();
        for (Object o : resultList) {
            System.out.println(o);
        }
        rTransaction.commit();
        r.close();

    }

    @Test
    public void fun07(){
        EntityManager manager = JpaUtils.getEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        String jsql="from Customer";
        Query query = manager.createQuery(jsql);
        query.setFirstResult(0);
        query.setMaxResults(2);
        List resultList = query.getResultList();
        System.out.println(resultList);
        transaction.commit();
        manager.close();
    }

}

