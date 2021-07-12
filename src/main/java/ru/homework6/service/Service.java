package ru.homework6.service;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;
import ru.homework5.PrepareData;
import javax.annotation.PostConstruct;

@Component
public class Service {
    private static SessionFactory factory;

    @PostConstruct
    public static void init() {
        PrepareData.forcePrepareData();
        factory = new Configuration()
                .configure("configs/homework6/hibernate.cfg.xml")
                .buildSessionFactory();
    }

    public static SessionFactory getFactory() {
        return factory;
    }
}
