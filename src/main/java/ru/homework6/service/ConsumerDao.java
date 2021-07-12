package ru.homework6.service;

import org.hibernate.Session;
import org.springframework.stereotype.Component;
import ru.homework6.model.Consumer;

@Component
public class ConsumerDao {
//    @Autowired
    public Service service;

    public void printBasket(Long id){
        try(Session session = service.getFactory().getCurrentSession()){
            session.beginTransaction();
            Consumer consumer = session.get(Consumer.class,id);
            System.out.println(consumer);
            session.getTransaction().commit();
        }
    }
}
