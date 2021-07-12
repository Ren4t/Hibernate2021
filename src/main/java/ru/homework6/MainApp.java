package ru.homework6;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.homework6.service.AppConfig;
import ru.homework6.service.ConsumerDao;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        ConsumerDao consumerDao = applicationContext.getBean(ConsumerDao.class);
        consumerDao.printBasket(3L);

        applicationContext.close();
    }
}
