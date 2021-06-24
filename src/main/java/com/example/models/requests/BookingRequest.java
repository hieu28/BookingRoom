package com.example.models.requests;

import java.util.Date;

public class BookingRequest {
    private Date checkIn;
    private Date checkOut;
    private Long numberOfMember;
    private Long employeeId;
    private Long roomId;
    private String title;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
