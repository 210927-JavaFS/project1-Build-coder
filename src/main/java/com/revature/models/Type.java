package com.revature.models;

import javax.annotation.processing.Generated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;

/**
 * REIM_TYPE_ID         NUMBER
 * REIM_TYPE            VARCHAR2(10)
 */

public class Type {
    
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private int typeID;
    private String type;
}
