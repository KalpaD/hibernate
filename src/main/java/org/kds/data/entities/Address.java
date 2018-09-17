package org.kds.data.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * This is an example of Composite Value in Hibernate eco system
 *
 */
@Getter
@Setter
/**
 * @Embeddable indicate hibernate that this class can be embedded in to other
 * entities.
 */
@Embeddable
public class Address {

    @Column(name = "ADDRESS_LINE_1")
    private String addressLineOne;

    @Column(name = "ADDRESS_LINE_2")
    private String addressLineTwo;

    @Column(name = "CITY")
    private String city;

    @Column(name = "STATE")
    private String state;

    @Column(name = "ZIP_CODE")
    private String zipCode;

    public Address() {
    }
}