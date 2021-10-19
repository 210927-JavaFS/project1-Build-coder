package com.revature.models;

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
    private String receipt;
    private int authorID;
    private int resolver;
    private int typeID;

    public Reimbursement(int id, float amount, String submitted, String resolved, String desc, String receipt,
            int authorID, int resolver, int typeID) {
        super();
        this.id = id;
        this.amount = amount;
        this.submitted = submitted;
        this.resolved = resolved;
        this.desc = desc;
        this.receipt = receipt;
        this.authorID = authorID;
        this.resolver = resolver;
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

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public int getAuthorID() {
        return authorID;
    }

    public void setAuthorID(int authorID) {
        this.authorID = authorID;
    }

    public int getResolver() {
        return resolver;
    }

    public void setResolver(int resolver) {
        this.resolver = resolver;
    }

    public int getTypeID() {
        return typeID;
    }

    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Float.floatToIntBits(amount);
        result = prime * result + authorID;
        result = prime * result + ((desc == null) ? 0 : desc.hashCode());
        result = prime * result + id;
        result = prime * result + ((receipt == null) ? 0 : receipt.hashCode());
        result = prime * result + ((resolved == null) ? 0 : resolved.hashCode());
        result = prime * result + resolver;
        result = prime * result + ((submitted == null) ? 0 : submitted.hashCode());
        result = prime * result + typeID;
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
        if (authorID != other.authorID)
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
        if (resolver != other.resolver)
            return false;
        if (submitted == null) {
            if (other.submitted != null)
                return false;
        } else if (!submitted.equals(other.submitted))
            return false;
        if (typeID != other.typeID)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Reimbursement [amount=" + amount + ", authorID=" + authorID + ", desc=" + desc + ", id=" + id
                + ", receipt=" + receipt + ", resolved=" + resolved + ", resolver=" + resolver + ", submitted="
                + submitted + ", typeID=" + typeID + "]";
    }
}

    
