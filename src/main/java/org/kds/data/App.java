package org.kds.data;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;

@Slf4j
public class App {
    public static void main(String[] args) {

        log.info("Starting up Hibernate application..");
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.close();
        log.info("Shutting down Hibernate application..");
    }
}
