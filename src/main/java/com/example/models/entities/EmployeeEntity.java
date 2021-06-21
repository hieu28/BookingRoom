package com.example.models.entities;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "employee")
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    @NotNull
    @Size(max = 50, min = 2, message = "Name must be at least 2 characters and maximum 50 characterss")

    private String name;

    @NotNull(message = "Please enter the phone number")
    @Column(name = "phone")
    private String phone;

    @Email
    @Column(name = "email")
    @NotNull(message = "Please enter email")
    private String email;

    @Column(name = "password")
    @NotNull(message = "PLease enter password")
    private String password;

    @Column(name = "department_id")
    private long departmentId;

    @Column(name = "image")
    @NotNull(message = "PLease enter image")
    private String image;


    public EmployeeEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(long departmentId) {
        this.departmentId = departmentId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

