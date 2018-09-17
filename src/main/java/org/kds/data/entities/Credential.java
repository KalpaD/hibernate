package org.kds.data.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name="credential")
public class Credential {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="CREDENTIAL_ID")
    public Long credentialId;

    /**
     *  Source entity is Credential , is holds the foreign key USER_ID
     *  Target entity is User
     *
     *  This relationship is One to One
     *
     *  The CascadeType.ALL : mean both entities to be persisted at the same time.
     *
     *  @JoinColumn defines the column which should be used to join the tables.
     *
     *  And this is a unidirectional relationship, we will be able to access User from credential
     *  but can not access credential from User. User does not have reference to credential.
     *
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID")
    public User user;

    @Column(name="USERNAME")
    private String username;

    @Column(name="PASSWORD")
    private String password;
}
