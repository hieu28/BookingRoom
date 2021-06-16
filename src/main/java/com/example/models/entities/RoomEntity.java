package com.example.models.entities;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;


@Entity
@Table(name = "room")
public class RoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotEmpty(message = "Tên không được để trống")
    private String name;

    @Column(name = "location_id")
    private int locationId;

    @Column(name = "capacity")
    @Min(value = 1, message = "Sức chứa tối thiểu là 1")
    @Max(value = 50, message = "Sức chứa tối đa là 50")
    private int capacity;

    @Column(name = "description", columnDefinition = "TEXT")
    @NotEmpty(message = "Mô tả không được để trống")
    private String description;

    @Column(name = "image", columnDefinition = "TEXT")
    @NotEmpty(message = "Ảnh không được để trống")
    private String image;

    @Column(name = "status", columnDefinition = "BIT")
    private boolean status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
