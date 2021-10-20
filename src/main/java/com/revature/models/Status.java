package com.revature.models;

import javax.annotation.processing.Generated;
import javax.persistence.GenerationType;
import javax.persistence.*;

/**
 * REIM_STATUS_ID           NUMBER
 * REIM_STATUS              VARCHAR2(10)
 */

public class Status {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private int statusID;
    private String status;
}
