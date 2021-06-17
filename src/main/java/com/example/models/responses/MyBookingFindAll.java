package com.example.models.responses;

import java.util.Date;

public class MyBookingFindAll {
    private Date checkIn;
    private Date chechOut;
    private Long numberOfMember;
    private String nameAdBooking;
    private String nameRoom;
    private String title;
    private Long roomId;
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

    public Date getChechOut() {
        return chechOut;
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
