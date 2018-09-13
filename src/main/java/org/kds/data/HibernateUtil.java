package org.kds.data;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.kds.data.entities.User;

/**
 *  This class holds the logic to create the Hibernate Session,
 *  It is done by suing the SessionFactory and it provides a single instance of the SessionFactory for the entire application.
 *
 */
@Slf4j
public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // create a hibernate configuration
            Configuration configuration = new Configuration();
            // register the annotated class with the configuration
            configuration.addAnnotatedClass(User.class);

            // create a Service registry
            StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder().build();
            // build a session factory by providing the service registry to it.
            return configuration.buildSessionFactory(standardServiceRegistry);
        } catch (HibernateException he) {
            log.error("Error while creating the session factory", he);
            throw new RuntimeException(he);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
