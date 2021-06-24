package com.example.models.requests;

import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class EmployeeRequest {
    @Min(0)
    private long id;

    @NotBlank
    @Size(max = 60, min = 5, message = "Name must be at least 2 characters and maximum 50 characters")
    private String name;

    @NotBlank(message = "Please enter the phone number")
    @Size(max = 11, min = 10, message = "Phone must be at least 10 characters and maximum 11 characters")
    @Pattern(regexp = "[0-9]")
    private String phone;

    @NotBlank(message = "Please enter email")
    @Size(max = 60, message = "Email maximum 60 characters")
    private String email;

    @NotBlank(message = "PLease enter password")
    private String password;

    @NotBlank
    private long departmentId;

    @URL
    @NotBlank(message = "PLease enter image")
    private String image;

    @Size
    @NotBlank(message = "PLease enter role")
    private String role;


    public String getEmail() {
        return email;
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

    public String getEmail(String email) {
        return this.email;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
