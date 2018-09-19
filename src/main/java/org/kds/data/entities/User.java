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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private long userId;

    /**
     * mappedBy, attributed only used by the non-owning side of the relationship.
     * it basically says this credential reference is mapped by the user filed in the User object.
     *
     * Note that we have not used the cascade attribute on the OneToOne mapping or @JoinColumn here.
     * It is because User is not the owning party of this relationship.
     *
     * If we use cascade and @JoinColumn in both sides, these two entities will go to never ending loop of
     * persisting each other.
     */
    @OneToOne(mappedBy = "user")
    private Credential credential;

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

    /**
     *  Demo how to inform hibernate to ignore a filed in a entity class
     *  by adding @Transient
     *
     */
    @Transient
    private boolean valid;

}
