package ru.homework5;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DaoApp {
    private static SessionFactory factory;

    public static void init() {
        PrepareData.forcePrepareData();
        factory = new Configuration()
                .configure("configs/homework5/hibernate.cfg.xml")
                .buildSessionFactory();
    }

    public static void main(String[] args) {
        try {
            init();
            ProductDao productDao = new ProductDao(factory);
            System.out.println(productDao.findById(1l));
            productDao.deleteById(1l);
            System.out.println(productDao.findAll());
            Product product = new Product("bread",35);
            productDao.saveOrUpdate(product);
            System.out.println(productDao.findAll());
            product = new Product("maul",105);
            productDao.saveOrUpdate(product);
            System.out.println(productDao.findAll());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            shutdown();
        }
    }
    public static void shutdown() {
        factory.close();
    }
}
