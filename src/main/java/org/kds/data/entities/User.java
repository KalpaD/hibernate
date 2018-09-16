package org.kds.data.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "finances_user")
public class User {

    /**
     * Here we demo how to use a table for id generation and how to plug that to the
     * id value of the entity using hibernate.
     *
     * If we leave the @GeneratedValue without any configuration, then the generation strategy will be
     * defaulted to AUTO mode, AUTO allow hibernate to select a id generation strategy for us based on the DB
     * vendor.
     *
     * Keep in mind that sequence generation is faster than id generation strategy.
     * But may not be available in all vendors.
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "user_table_generator")
    @TableGenerator(name = "user_table_generator",
            table = "ifinances_keys", pkColumnName = "PK_NAME", valueColumnName = "PK_VALUE")
    @Column(name = "USER_ID")
    private long userId;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    /**
     * Here we demonstrates another Column attribute
     * nullable , this can prevent attributes getting set to
     * null values. The importance here is hibernate will complain about it
     * before it reach the db level at all.
     */
    @Column(name = "BIRTH_DATE", nullable = false)
    private Date birthDate;

    @Column(name = "EMAIL_ADDRESS")
    private String emailAddress;

    @Column(name = "LAST_UPDATED_DATE")
    private Date lastUpdatedDate;

    @Column(name = "LAST_UPDATED_BY")
    private String lastUpdatedBy;

    /**
     *  This demonstrates the use of updatable property in @Column
     *  to control the updatability of certain columns.
     *
     *  Here the CREATED_DATE should not be updated once it created.
     */
    @Column(name = "CREATED_DATE", updatable = false)
    private Date createdDate;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "USER_ADDRESS_LINE_1")
    private String addressLineOne;

    @Column(name = "USER_ADDRESS_LINE_2")
    private String addressLineTwo;

    @Column(name = "CITY")
    private String city;

    @Column(name = "STATE")
    private String state;

    @Column(name = "ZIP_CODE")
    private String zipCode;

}
