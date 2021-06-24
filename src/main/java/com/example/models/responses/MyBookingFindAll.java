package com.example.models.responses;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Date;

public class MyBookingFindAll {

    private Long Id;

    private OffsetDateTime checkIn;

    private OffsetDateTime checkOut;

    private Long numberOfMember;

    private String title;

    private String employeeName;

    private String nameRoom;

    private Long roomId;

    private Long bookingId;

    private Long employeeId;

    private String description;

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
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

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getNumberOfMember() {
        return numberOfMember;
    }

    public void setNumberOfMember(Long numberOfMember) {
        this.numberOfMember = numberOfMember;
    }

    public String getNameRoom() {
        return nameRoom;
    }

    public void setNameRoom(String nameRoom) {
        this.nameRoom = nameRoom;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
