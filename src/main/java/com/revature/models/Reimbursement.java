package com.revature.models;

import java.sql.Blob;

import javax.persistence.*;

/**
 * REIM_ID              NUMBER
 * REIM_AMOUNT          NUMBER
 * REIM_SUBMITTED       TIMESTAMP
 * REIM_RESOLVED        TIMESTAMP
 * REIM_DESCRIPTION     VARCHAR(250)
 * REIM_RECIEPT         BLOB
 * REIM_AUTHOR          NUMBER
 * REIM_RESOLVER        NUMBER
 * REIM_STATUS_ID       NUMBER
 * REIM_TYPE_ID         NUMBER
 */


public class Reimbursement {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private int id;
    @Column(columnDefinition = "CHECK amount > 0") private float amount;
    private String submitted;
    private String resolved;
    private String desc;
    private Blob receipt;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name="userID") private User author;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name="userID") private User resolver;
    @OneToOne(fetch = FetchType.LAZY) @JoinColumn(name="statusID") private Status statusID;
    @OneToOne(fetch = FetchType.LAZY) @JoinColumn(name="typeID") private Type typeID;
}

    
