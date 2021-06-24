package com.example.models.requests;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class RoomCreatedRequest {

    @NotNull(message = "Room name is required")
    @NotEmpty(message = "Room name is required")
    @ApiModelProperty(
            example="Phòng tầng 8",
            notes="Full name cannot be empty",
            required=true
    )
    private String name;

    @NotNull(message = "LocationId is required")
    @NotEmpty(message = "LocationId is required")
    private Long locationId;

    @NotNull(message = "Capacity is required")
    @NotEmpty(message = "Capacity is required")
    private int capacity;

    @NotNull(message = "LocationId is required")
    @NotEmpty(message = "LocationId is required")
    private String description;

    private String image;

    private boolean status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
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
