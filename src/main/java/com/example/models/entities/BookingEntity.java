package com.example.models.entities;

import org.hibernate.validator.constraints.NotEmpty;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Date;

@Entity
@Table(name = "booking")
public class BookingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "check_in")
    private Date checkIn;

    @Column(name = "check_out")
    private Date checkOut;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "title")
    private String title;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
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
}
