package com.revature.models;

import javax.persistence.*;

/**
 * ERS_USERS_ID             NUMBER
 * ERS_USERNAME             VARCHAR2(50)
 * ERS_PASSWORD             VARCHAR2(50)
 * USER_FIRST_NAME          VARCHAR2(100)
 * USER_LAST_NAME           VARCHAR2(100)
 * USER_EMAIL               VARCHAR2(150)
 * USER_ROLE_ID             NUMBER
 */

public class User {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private int userID;
    @Column(nullable = false, unique = true) private String userName;
    @Column(nullable = false) private String password;
    private String firstName;
    private String lastName;
    @Column(nullable = false, unique = true) private String email;
    @OneToOne(fetch = FetchType.LAZY) @JoinColumn(name="roleID") private Role role;    
}

    