package ru.homework5;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ProductDao {
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
            System.out.println(findById(1l));
            deleteById(1l);
            System.out.println(findAll());
            Product product = new Product("bread",35);
            saveOrUpdate(product);
            System.out.println(findAll());
            product = new Product("maul",105);
            saveOrUpdate(product);
            System.out.println(findAll());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            shutdown();
        }
    }

    public static Product findById(Long id){
        Product product;
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            product = session.get(Product.class, id);
            session.getTransaction().commit();
        }
        return product;
    }

    public static List<Product> findAll(){
        List<Product> listProducts;
        try(Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            listProducts =session.createQuery("from Product").getResultList();
            session.getTransaction().commit();
        }
        return listProducts;
    }
    public static void deleteById(Long id){
        try(Session session = factory.getCurrentSession()){
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            session.delete(product);
            session.getTransaction().commit();
            System.out.println("Delete id = " + id);
        }
    }
    public static void saveOrUpdate(Product product){
        try(Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            session.saveOrUpdate(product);
            session.getTransaction().commit();
        }
    }

    public static void shutdown() {
        factory.close();
    }
}
