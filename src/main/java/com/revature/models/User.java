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

@Entity
@Table(name = "users")
public class User {

    public enum Role{EMPLOYEE, MANAGER};

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) @Column(name="user_id") private int id;
    @Column(name="user_name", nullable = false, unique = true) private String userName;
    @Column(name="user_password", nullable = false) private int password;
    @Column(name="user_firstName") private String firstName;
    @Column(name="user_lastName") private String lastName;
    @Column(name="user_email", nullable = false) private String email;
    @Enumerated(EnumType.STRING) @Column(name="user_role") private Role role;

    public User(int id, String userName, int password, String firstName, String lastName, String email, Role role) {
        super();
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
    }

    public User(String userName, int password, String firstName, String lastName, String email, Role role) {
        super();
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
    }

    public User() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + id;
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((role == null) ? 0 : role.hashCode());
        result = prime * result + ((userName == null) ? 0 : userName.hashCode());
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
        User other = (User) obj;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (firstName == null) {
            if (other.firstName != null)
                return false;
        } else if (!firstName.equals(other.firstName))
            return false;
        if (id != other.id)
            return false;
        if (lastName == null) {
            if (other.lastName != null)
                return false;
        } else if (!lastName.equals(other.lastName))
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        if (role != other.role)
            return false;
        if (userName == null) {
            if (other.userName != null)
                return false;
        } else if (!userName.equals(other.userName))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "User [email=" + email + ", firstName=" + firstName + ", id=" + id + ", lastName=" + lastName
                + ", password=" + password + ", role=" + role + ", userName=" + userName + "]";
    }    
}

    