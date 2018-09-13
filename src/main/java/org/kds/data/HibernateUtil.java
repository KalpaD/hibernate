package org.kds.data;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

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
            // create a Service registry
            /**
             *  Service : provides various types of functionality, ina pluggable manner.
             *
             *  For example , to access JDBC connection and manage it, (opening, closing) provided by
             *  ConnectionProvider service.
             *
             *  ServiceRegistry : Hosts and manages services, service have lifecycle and have scope
             *
             */
            StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder()
                    .configure() // configures settings from hibernate.cfg.xml
                    .build();
            // build a session factory by providing the service registry to it.
            return new MetadataSources(standardServiceRegistry).buildMetadata().buildSessionFactory();
        } catch (HibernateException he) {
            log.error("Error while creating the session factory", he);
            throw new RuntimeException(he);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
