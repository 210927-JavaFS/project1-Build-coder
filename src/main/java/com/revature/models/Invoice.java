package com.revature.models;

import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

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

@Entity
public class Invoice {

    public enum Status {PENDING, APPROVED, DENIED};
    public enum Type {LODGING, TRAVEL, FOOD, OTHER};

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) @Column(name="invoice_id") private int id;
    @Column(name="invoice_amt", columnDefinition = "CHECK amount > 0") private double amount;
    @Column(name="invoice_submit") @CreationTimestamp @Temporal(TemporalType.TIMESTAMP) private Date submitted;
    @Column(name="invoice_resolved") @CreationTimestamp @Temporal(TemporalType.TIMESTAMP) private Date resolved;
    @Column(name="invoice_desc") private String desc;
    @Column(name="invoice_receipt") private String receipt;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name="author_id") private User author;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name="resolver_id") private User resolver;
    @Enumerated(EnumType.STRING) @Column(name="invoice_stat") private Status status;
    @Enumerated(EnumType.STRING) @Column(name="invoice_type") private Type type;


    // /**
    //  * method below is for testing. del after test pass
    //  * @param status
    //  * @param type
    //  */
    // public Invoice(Status status, Type type) {
    //     this.status = status;
    //     this.type = type;
    // }

    // /**
    //  * method below is for testing. del after test pass
    //  * @param submitted
    //  * @param resolved
    //  */
    // public Invoice(Date submitted, Date resolved){
    //     this.submitted = submitted;
    //     this.resolved = resolved;
    // }



    public Invoice(int id, double amount, Date submitted, Date resolved, String desc, String receipt, User author,
            User resolver, Status status, Type type) {
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

    public Invoice(double amount, Date submitted, Date resolved, String desc, String receipt, User author,
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getSubmitted() {
        return submitted;
    }

    public void setSubmitted(Date submitted) {
        this.submitted = submitted;
    }

    public Date getResolved() {
        return resolved;
    }

    public void setResolved(Date resolved) {
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
        long temp;
        temp = Double.doubleToLongBits(amount);
        result = prime * result + (int) (temp ^ (temp >>> 32));
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
        if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
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
        return "Invoice [amount=" + amount + ", author=" + author + ", desc=" + desc + ", id=" + id + ", receipt="
                + receipt + ", resolved=" + resolved + ", resolver=" + resolver + ", status=" + status + ", submitted="
                + submitted + ", type=" + type + "]";
    }
}

    
