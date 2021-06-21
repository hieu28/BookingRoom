package com.example.services;

import com.example.models.requests.BookingDetailRequest;
import com.example.models.responses.BookingReponse;

public interface IBookingDetailService {
     BookingReponse save(BookingDetailRequest bookingDetail);

}
