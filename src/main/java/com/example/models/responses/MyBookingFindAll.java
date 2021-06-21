package com.example.models.responses;

import java.util.Date;

public class MyBookingFindAll {
    private Long Id;
    private Date checkIn;
    private Date chechOut;
    private Long numberOfMember;
    private String title;
    private String nameAdBooking;
    private String nameRoom;
    private Long roomId;
    private Long bookingId;
    private Long employeeId;
    private String description;
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

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Long getId() {
        return Id;
    }
    public void setId(Long id) {
        Id = id;
    }

    public Date getChechOut() {
        return chechOut;
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

    public void setChechOut(Date chechOut) {
        this.chechOut = chechOut;
    }

    public Long getNumberOfMember() {
        return numberOfMember;
    }

    public void setNumberOfMember(Long numberOfMember) {
        this.numberOfMember = numberOfMember;
    }

    public String getNameAdBooking() {
        return nameAdBooking;
    }

    public void setNameAdBooking(String nameAdBooking) {
        this.nameAdBooking = nameAdBooking;
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
