package org.kds.data;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.kds.data.entities.Bank;
import org.kds.data.entities.TimeTest;
import org.kds.data.entities.User;

import java.util.Date;

@Slf4j
public class App {

    private Session session;
    private SessionFactory sessionFactory;

    App () {
        // open a session
        sessionFactory = HibernateUtil.getSessionFactory();
        session = sessionFactory.openSession();
    }

    /**
     * Create a user via the hibernate session.
     * @param session The hibernate session.
     * @return Id of the newly created user.
     */
    private long createUser(Session session) {

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
        long id  = (long) session.save(user);
        // commit the unit of work
        transaction.commit();

        return id;
    }

    /**
     *  Update the given user with single property.
     */
    private void updateUser(Session session, long userId, Object property) {

        // start a new transaction
        Transaction updateTx = session.beginTransaction();
        // get the newly create user from the db
        User newUser = session.get(User.class, userId);
        // set property to ne value
        newUser.setFirstName("Jane");
        // use the same session to update the user
        session.update(newUser);
        // commit the update
        updateTx.commit();
    }


    private void createTimeRecord(Session session) {

        // create a new transaction
        Transaction createTx = session.beginTransaction();
        // create a new time record
        TimeTest timeTest = new TimeTest(new Date());
        // save the time record
        session.save(timeTest);
        // commit the record creation
        createTx.commit();

        session.refresh(timeTest);

        log.info("Time Record : ", timeTest.toString());
    }


    private void createBankRecord(Session session) {

        Transaction bankTx = session.beginTransaction();

        Bank bank = new Bank();
        bank.setName("Federal Trust");
        bank.setAddressLine1("33 Wall Street");
        bank.setAddressLine2("Suite 233");
        bank.setCity("New York");
        bank.setState("NY");
        bank.setZipCode("12345");
        // bank.setInternational(false);
        bank.setCreatedBy("Kevin");
        bank.setCreatedDate(new Date());
        bank.setLastUpdatedBy("Kevin");
        bank.setLastUpdatedDate(new Date());
      // bank.getContacts().add("Joe");
      // bank.getContacts().add("Mary");

        session.save(bank);

        bankTx.commit();
    }


    /**
     * Main entry point of the hibernate demo program.
     *
     * @param args
     */
    public static void main(String[] args) {

        log.info("Starting up Hibernate application..");

        App app = new App();
        try {
            Session session = app.getSession();
            // create a user
            //long userId = app.createUser(session);

            // update the user
            //app.updateUser(session, userId, "Jane");

            // create and print time record.
            //app.createTimeRecord(session);

            app.createBankRecord(session);

        } catch (Exception ex) {
            log.error("Error while executing the hibernate operations", ex);
        } finally {
            app.closeSession();
            app.closeSessionFactory();
            log.info("Session and SessionFactory closed successfully.");
        }

        log.info("Shutting down Hibernate application..");
    }

    /**
     * Return the hibernate session.
     *
     * @return The hibernate session.
     */
    private Session getSession() {
        return this.session;
    }

    /**
     * Close the hibernate session.
     *
     */
    private void closeSession() {
        // close the session
        session.close();
    }

    /**
     * Close the session factory.
     */
    private void closeSessionFactory() {
        sessionFactory.close();
    }
}
