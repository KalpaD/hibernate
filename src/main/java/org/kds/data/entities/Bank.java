package org.kds.data.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "bank")
public class Bank {

    @Id
    @Column(name = "BANK_ID")
    private long id;

    @Column(name = "NAME")
    private String name;

    /**
     * @Embedded Indicate this type is embedded using a Embeddable type.
     */
    @Embedded
    private Address address = new Address();

    @Column(name = "LAST_UPDATED_DATE")
    private Date lastUpdatedDate;

    @Column(name = "IS_INTERNATIONAL")
    private int isInternational;

    @Column(name = "LAST_UPDATED_BY")
    private String lastUpdatedBy;

    @Column(name = "CREATED_DATE")
    private Date createdDate;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "ADDRESS_TYPE")
    private String addressType;

    /**
     * @ElementCollection says that we are trying to map a collection of instance
     * that are basic or composite type.(Embeddable)
     */
    @ElementCollection
    /**
     * @CollectionTable says that for the collection mapping hibernate should look at the
     * table = bank_contact and the joinColumns = BANK_ID indicates hibernate the way to find the mapping using a
     * common column.
     */
    @CollectionTable(name = "bank_contact", joinColumns = @JoinColumn(name = "BANK_ID"))
    @Column(name = "NAME") // @Column indicates these strings should be stored in "NAME" column.
    private Collection<String> contacts = new ArrayList<>();

    public String getAddressLine1() {
        return address.getAddressLineOne();
    }

    public void setAddressLine1(String addressLine1) {
        this.address.setAddressLineOne(addressLine1);
    }

    public String getAddressLine2() {
        return address.getAddressLineTwo();
    }

    public void setAddressLine2(String addressLine2) {
        this.address.setAddressLineTwo(addressLine2);
    }

    public String getCity() {
        return address.getCity();
    }

    public void setCity(String city) {
        this.address.setCity(city);
    }

    public String getState() {
        return address.getState();
    }

    public void setState(String state) {
        this.address.setState(state);
    }

    public String getZipCode() {
        return address.getZipCode();
    }

    public void setZipCode(String zipCode) {
        this.address.setZipCode(zipCode);
    }
}
