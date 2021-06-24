package com.example.models.entities;

import org.hibernate.validator.constraints.NotEmpty;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Date;

@Entity
@Table(name = "booking")
public class BookingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "check_in")
    private OffsetDateTime checkIn;

    @Column(name = "check_out")
    private OffsetDateTime checkOut;

    @Min(value = 1, message = "Số lượng người tối thiểu là 1")
    @Max(value = 50, message = "Sức lượng người tối đa là 50")
    @Column(name = "number_of_member")
    private Long numberOfMember;

    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "room_id")
    private Long roomId;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "title")
    private String title;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OffsetDateTime getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(OffsetDateTime checkIn) {
        this.checkIn = checkIn;
    }

    public OffsetDateTime getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(OffsetDateTime checkOut) {
        this.checkOut = checkOut;
    }

    public Long getNumberOfMember() {
        return numberOfMember;
    }

    public void setNumberOfMember(Long numberOfMember) {
        this.numberOfMember = numberOfMember;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
