package com.revature.models;

/**
 * REIM_TYPE_ID         NUMBER
 * REIM_TYPE            VARCHAR2(10)
 */

public class Type {
    
    private int typeID;
    private String type;

    public Type(int typeID, String type) {
        super();
        this.typeID = typeID;
        this.type = type;
    }

    public Type(){
        super();
    }

    public int getTypeID() {
        return typeID;
    }

    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((type == null) ? 0 : type.hashCode());
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
        Type other = (Type) obj;
        if (type == null) {
            if (other.type != null)
                return false;
        } else if (!type.equals(other.type))
            return false;
        if (typeID != other.typeID)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Type [type=" + type + ", typeID=" + typeID + "]";
    }
}
