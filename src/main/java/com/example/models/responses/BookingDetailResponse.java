package com.example.models.responses;

public class BookingDetailResponse {
    private Long id;
    private Long bookingId;
    private Long employeeId;
    private String employeeName;
    private String employeeImage;

    public BookingDetailResponse(Long id, Long bookingId, Long employeeId, String employeeName, String employeeImage) {
        this.id = id;
        this.bookingId = bookingId;
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeeImage = employeeImage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeImage() {
        return employeeImage;
    }

    public void setEmployeeImage(String employeeImage) {
        this.employeeImage = employeeImage;
    }
}
