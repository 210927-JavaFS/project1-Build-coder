package com.revature.models;

import javax.annotation.processing.Generated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;

/**
 * ERS_USER_ROLE_ID         NUMBER
 * USER_ROLE                VARCHAR2(10)
 */

public class Role {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private int roleID;
    private String role;
}
