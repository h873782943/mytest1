package com.itheima.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @program: MyJpa
 * @description:
 * @author: Mr.Hou
 * @create: 2020-11-04 10:37
 **/
public class JpaUtils {

    private static EntityManagerFactory entityManagerFactory=null;

    static {
        entityManagerFactory = Persistence.createEntityManagerFactory("myjpa");
    }

    public static EntityManager getEntityManager(){
        EntityManager manager = entityManagerFactory.createEntityManager();
        return manager;
    }
}

