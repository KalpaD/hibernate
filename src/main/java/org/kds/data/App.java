package org.kds.data;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.kds.data.entities.User;

import java.util.Date;

@Slf4j
public class App {

    private Session session;

    App () {
        // open a session
        session = HibernateUtil.getSessionFactory().openSession();
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

    /**
     * Return the hibernate session.
     *
     * @return The hibernate session.
     */
    private Session getSession() {
        return session;
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
     * Main entry point of the hibernate demo program.
     *
     * @param args
     */
    public static void main(String[] args) {

        log.info("Starting up Hibernate application..");

        App app = new App();

        Session session = app.getSession();
        // create a user
        long userId = app.createUser(session);

        app.updateUser(session, userId, "Jane");

        app.closeSession();
        log.info("Shutting down Hibernate application..");
    }
}
