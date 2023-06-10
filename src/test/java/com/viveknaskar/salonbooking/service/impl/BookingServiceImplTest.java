package com.viveknaskar.salonbooking.service.impl;

import com.viveknaskar.salonbooking.data.BookingRepository;
import com.viveknaskar.salonbooking.domain.Booking;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class BookingServiceImplTest {

    @Mock
    BookingRepository bookingRepository;

    @Mock
    MongoTemplate mongoTemplate;

    @InjectMocks
    BookingServiceImpl bookingService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createBookingServiceSuccessTest(){
        Booking booking = new Booking();
        booking.setId(123546);
        booking.setBarberId(12);
        booking.setBarberName("tester");
        booking.setName("test");
        booking.setBookingDate("4");
        booking.setPhone("1234654");
        booking.setStartTime("5");
        booking.setEndTime("6");
        booking.setSubject("testing");
        booking.setLocation("Melbourne");
        booking.setComments("Good");
        booking.setBlock(true);
        Mockito.when(bookingRepository.save(booking)).thenReturn(booking);
        Assert.assertEquals(booking,bookingService.createBookingService(booking));
    }

    @Test
    public void getAllBookingServicesSuccessTest(){
        List<Booking> bookingList = new ArrayList<>();
        Booking booking = new Booking();
        booking.setId(123546);
        booking.setBarberId(12);
        booking.setBarberName("tester");
        booking.setName("test");
        booking.setBookingDate("4");
        booking.setPhone("1234654");
        booking.setStartTime("5");
        booking.setEndTime("6");
        booking.setSubject("testing");
        booking.setLocation("Melbourne");
        booking.setComments("Good");
        booking.setBlock(true);
        bookingList.add(booking);
        Mockito.when(bookingRepository.findAll()).thenReturn(bookingList);
        Assert.assertEquals(bookingList,bookingService.getAllBookingServices());
    }

    @Test
    public void getBarberAvailabilitySuccessTest(){
        List<Booking> bookingList = new ArrayList<>();
        Booking booking = new Booking();
        booking.setId(123546);
        booking.setBarberId(12);
        booking.setBarberName("tester");
        booking.setName("test");
        booking.setBookingDate("4");
        booking.setPhone("1234654");
        booking.setStartTime("5");
        booking.setEndTime("6");
        booking.setSubject("testing");
        booking.setLocation("Melbourne");
        booking.setComments("Good");
        booking.setBlock(true);
        bookingList.add(booking);
        Mockito.when(mongoTemplate.find(Mockito.any(),Mockito.any())).thenReturn(Collections.singletonList(bookingList));
        Assert.assertEquals(false,bookingService.getBarberAvailability(booking));
    }

    @Test
    public void getBarberAvailabilityListEmptyTest(){
        Booking booking = new Booking();
        booking.setId(123546);
        booking.setBarberId(12);
        booking.setBarberName("tester");
        booking.setName("test");
        booking.setBookingDate("4");
        booking.setPhone("1234654");
        booking.setStartTime("5");
        booking.setEndTime("6");
        booking.setSubject("testing");
        booking.setLocation("Melbourne");
        booking.setComments("Good");
        booking.setBlock(true);
        Mockito.when(mongoTemplate.find(Mockito.any(),Mockito.any())).thenReturn(Collections.emptyList());
        Assert.assertEquals(true,bookingService.getBarberAvailability(booking));
    }

    @Test
    public void getAllBookingServicesAdminSuccessTest(){
        List<Booking> bookingList = new ArrayList<>();
        Booking booking = new Booking();
        booking.setId(123546);
        booking.setBarberId(12);
        booking.setBarberName("tester");
        booking.setName("test");
        booking.setBookingDate("4");
        booking.setPhone("1234654");
        booking.setStartTime("5");
        booking.setEndTime("6");
        booking.setSubject("testing");
        booking.setLocation("Melbourne");
        booking.setComments("Good");
        booking.setBlock(true);
        bookingList.add(booking);

        List<Booking> bookingList1 = new ArrayList<>();
        Booking booking1 = new Booking();
        booking1.setId(123546);
        booking1.setBarberId(12);
        booking1.setBarberName("tester");
        booking1.setName("test");
        booking1.setBookingDate("4");
        booking1.setPhone("1234654");
        booking1.setStartTime("5");
        booking1.setEndTime("6");
        booking1.setSubject("testing");
        booking1.setLocation("Melbourne");
        booking1.setComments("Good");
        booking1.setBlock(true);
        bookingList1.add(booking1);
        Mockito.when(bookingRepository.findAll()).thenReturn(bookingList);
        Assert.assertEquals(bookingList1,bookingService.getAllBookingServicesForAdmin());
    }

    @Test
    public void getBookingOfBarberPass(){
        List<Booking> bookingList = new ArrayList<>();
        Booking booking = new Booking();
        booking.setId(123546);
        booking.setBarberId(12);
        booking.setBarberName("tester");
        booking.setName("test");
        booking.setBookingDate("4");
        booking.setPhone("1234654");
        booking.setStartTime("5");
        booking.setEndTime("6");
        booking.setSubject("testing");
        booking.setLocation("Melbourne");
        booking.setComments("Good");
        booking.setBlock(true);
        bookingList.add(booking);
        Mockito.when(bookingRepository.findByBarberId(12)).thenReturn(bookingList);
        Assert.assertEquals(bookingList,bookingService.getBookingOfBarber(12));
    }
}