package com.example.models.entities;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "employee")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    @NotEmpty(message = "{employee.name}")
    @Size(max = 50,min = 5,message = "{employee.name.length}")
    private String name;

    @NotEmpty(message = "Please enter the phone number !")
    @Pattern(regexp = "@[a-z0-9_]")
    @Column(name = "phone")
    private String phone;

    @NotEmpty(message = "Please enter email")
    @Pattern(regexp = "[a-z0-9_]+@[a-z0-9_]+",message = "Wrong format xxx@ttc-solution.com.vn")
    @Column(name = "email")
    private String email;


    @Column(name = "password")
    @NotEmpty(message = "PLease enter password")
    @Pattern(regexp = "@[a-z0-9_]")
    private String password;

    @Column(name = "department_id")
    private long departmentId;

    @Column(name = "image")
    private String image;

    public EmployeeEntity(long id, String name, String phone, String email, String password, long departmentId, String image) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.departmentId = departmentId;
        this.image = image;
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

