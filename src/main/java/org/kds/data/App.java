package org.kds.data;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.kds.data.entities.User;

import java.util.Date;

@Slf4j
public class App {
    public static void main(String[] args) {

        log.info("Starting up Hibernate application..");
        // open a session
        Session session = HibernateUtil.getSessionFactory().openSession();
        // Begin a unit of work
        Transaction transaction = session.beginTransaction();

        // create a new User
        User user = new User();
        user.setBirthDate(new Date());
        user.setCreatedBy("Admin");
        user.setCreatedDate(new Date());
        user.setEmailAddress("newuser@newcomp.com.au");
        user.setFirstName("John");
        user.setLastName("Deo");
        user.setLastUpdatedBy("Admin");
        user.setLastUpdatedDate(new Date());
        user.setAddressLineOne("Unit 5, 190 High Street");
        user.setAddressLineTwo("Randwick");
        user.setCity("New York");
        user.setState("NY");

        // insert the user
        session.save(user);

        // commit the unit of work
        transaction.commit();

        // close the session
        session.close();
        log.info("Shutting down Hibernate application..");
    }
}
