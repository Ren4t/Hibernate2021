package ru.homework6.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.homework5.Product;

import java.util.List;
// черновик
public class ProductDao {
    private static SessionFactory factory;

    public ProductDao(SessionFactory factory) {
        this.factory = factory;
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

}
