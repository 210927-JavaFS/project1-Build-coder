package com.revature.models;

import java.sql.Blob;

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

    private int id;
    private float amount;
    private String submitted;
    private String resolved;
    private String desc;
    private Blob receipt;
    private User author;
    private User resolver;
    private Status statusID;
    private Type typeID;

    public Reimbursement(int id, float amount, String submitted, String resolved, String desc, Blob receipt,
            User author, User resolver, Status statusID, Type typeID) {
        super();
        this.id = id;
        this.amount = amount;
        this.submitted = submitted;
        this.resolved = resolved;
        this.desc = desc;
        this.receipt = receipt;
        this.author = author;
        this.resolver = resolver;
        this.statusID = statusID;
        this.typeID = typeID;
    }

    public Reimbursement(){
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getSubmitted() {
        return submitted;
    }

    public void setSubmitted(String submitted) {
        this.submitted = submitted;
    }

    public String getResolved() {
        return resolved;
    }

    public void setResolved(String resolved) {
        this.resolved = resolved;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Blob getReceipt() {
        return receipt;
    }

    public void setReceipt(Blob receipt) {
        this.receipt = receipt;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public User getResolver() {
        return resolver;
    }

    public void setResolver(User resolver) {
        this.resolver = resolver;
    }

    public Status getStatusID() {
        return statusID;
    }

    public void setStatusID(Status statusID) {
        this.statusID = statusID;
    }

    public Type getTypeID() {
        return typeID;
    }

    public void setTypeID(Type typeID) {
        this.typeID = typeID;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Float.floatToIntBits(amount);
        result = prime * result + ((author == null) ? 0 : author.hashCode());
        result = prime * result + ((desc == null) ? 0 : desc.hashCode());
        result = prime * result + id;
        result = prime * result + ((receipt == null) ? 0 : receipt.hashCode());
        result = prime * result + ((resolved == null) ? 0 : resolved.hashCode());
        result = prime * result + ((resolver == null) ? 0 : resolver.hashCode());
        result = prime * result + ((statusID == null) ? 0 : statusID.hashCode());
        result = prime * result + ((submitted == null) ? 0 : submitted.hashCode());
        result = prime * result + ((typeID == null) ? 0 : typeID.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Reimbursement other = (Reimbursement) obj;
        if (Float.floatToIntBits(amount) != Float.floatToIntBits(other.amount))
            return false;
        if (author == null) {
            if (other.author != null)
                return false;
        } else if (!author.equals(other.author))
            return false;
        if (desc == null) {
            if (other.desc != null)
                return false;
        } else if (!desc.equals(other.desc))
            return false;
        if (id != other.id)
            return false;
        if (receipt == null) {
            if (other.receipt != null)
                return false;
        } else if (!receipt.equals(other.receipt))
            return false;
        if (resolved == null) {
            if (other.resolved != null)
                return false;
        } else if (!resolved.equals(other.resolved))
            return false;
        if (resolver == null) {
            if (other.resolver != null)
                return false;
        } else if (!resolver.equals(other.resolver))
            return false;
        if (statusID == null) {
            if (other.statusID != null)
                return false;
        } else if (!statusID.equals(other.statusID))
            return false;
        if (submitted == null) {
            if (other.submitted != null)
                return false;
        } else if (!submitted.equals(other.submitted))
            return false;
        if (typeID == null) {
            if (other.typeID != null)
                return false;
        } else if (!typeID.equals(other.typeID))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Reimbursement [amount=" + amount + ", author=" + author + ", desc=" + desc + ", id=" + id + ", receipt="
                + receipt + ", resolved=" + resolved + ", resolver=" + resolver + ", statusID=" + statusID
                + ", submitted=" + submitted + ", typeID=" + typeID + "]";
    }
}

    
