package org.kds.data.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACCOUNT_ID")
    private long id;

    /**
     *  Theoretically, the @JoinColumn should be placed on the entity which has the foreign key constrains
     *  but this time it is a OneToMany unit directional relationship, hence the Transaction entity will not have a reference to
     *  Account entity.
     *
     *  Hence , we put the @JoinColumn on the Account entity.
     *
     *  Furthermore, note that the Transaction entity doe not have ACCOUNT_ID filed instead we configure
     *  @JoinColumn( ,  nullable = false)
     *  so hibernate automatically populate the accountId filed in the Transaction.
     *
     */
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ACCOUNT_ID", nullable = false)
    private List<Transaction> transactions = new ArrayList<>();

    @Column(name = "BANK_ID")
    private long bankId;

    @Column(name = "ACCOUNT_TYPE")
    private String accountType;

    @Column(name = "NAME")
    private String name;

    @Column(name = "INITIAL_BALANCE")
    private BigDecimal initBalance;

    @Column(name = "CURRENT_BALANCE")
    private BigDecimal currentBalance;

    @Column(name = "OPEN_DATE")
    private Date openDate;

    @Column(name = "CLOSE_DATE")
    private Date closeDate;

    @Column(name = "LAST_UPDATED_DATE")
    private Date lastUpdatedDate;

    @Column(name = "LAST_UPDATED_BY")
    private String lastUpdatedBy;

    @Column(name = "CREATED_DATE")
    private Date createdDate;

    @Column(name = "CREATED_BY")
    private String createdBy;
}
