package com.revature.models;

import javax.persistence.*;

// for time stamping
import java.text.SimpleDateFormat;
import java.util.Date;


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

public class Invoice {

    public enum Status {PENDING, APPROVED, DENIED};
    public enum Type {LODGING, TRAVEL, FOOD, OTHER};

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) @Column(name="invoice_id") private int id;
    @Column(name="invoice_amt", columnDefinition = "CHECK amount > 0") private float amount;
    @Column(name="invoice_submit") private String submitted = new SimpleDateFormat().format(new Date());
    @Column(name="invoice_resolved") private String resolved = new SimpleDateFormat().format(new Date());
    @Column(name="invoice_desc") private String desc;
    @Column(name="invoice_receipt") private String receipt;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name="author_id") private User author;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name="resolver_id") private User resolver;
    @Enumerated(EnumType.STRING) @Column(name="invoice_stat") private Status status;
    @Enumerated(EnumType.STRING) @Column(name="invoice_type") private Type type;

    public Invoice(int id, float amount, String submitted, String resolved, String desc, String receipt,
            User author, User resolver, Status status, Type type) {
        super();
        this.id = id;
        this.amount = amount;
        this.submitted = submitted;
        this.resolved = resolved;
        this.desc = desc;
        this.receipt = receipt;
        this.author = author;
        this.resolver = resolver;
        this.status = status;
        this.type = type;
    }

    public Invoice(float amount, String submitted, String resolved, String desc, String receipt, User author,
            User resolver, Status status, Type type) {
        super();
        this.amount = amount;
        this.submitted = submitted;
        this.resolved = resolved;
        this.desc = desc;
        this.receipt = receipt;
        this.author = author;
        this.resolver = resolver;
        this.status = status;
        this.type = type;
    }

    public Invoice() {
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
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
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result + ((submitted == null) ? 0 : submitted.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
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
        Invoice other = (Invoice) obj;
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
        if (status != other.status)
            return false;
        if (submitted == null) {
            if (other.submitted != null)
                return false;
        } else if (!submitted.equals(other.submitted))
            return false;
        if (type != other.type)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Reimbursement [amount=" + amount + ", author=" + author + ", desc=" + desc + ", id=" + id + ", receipt="
                + receipt + ", resolved=" + resolved + ", resolver=" + resolver + ", status=" + status + ", submitted="
                + submitted + ", type=" + type + "]";
    }
}

    
