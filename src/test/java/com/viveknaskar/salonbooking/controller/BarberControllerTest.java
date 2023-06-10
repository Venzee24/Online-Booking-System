package com.viveknaskar.salonbooking.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.viveknaskar.salonbooking.domain.BarberDetails;
import com.viveknaskar.salonbooking.service.BarberService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(value = BarberController.class)
public class BarberControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    BarberService barberService;

    @Test
    public void getBarberAllRecordsTest() throws Exception {
        List<BarberDetails> barberDetailsList = new ArrayList<>();

        BarberDetails barberDetails = new BarberDetails();
        barberDetails.setId(123546);
        barberDetails.setName("test");
        barberDetails.setAddress("tester");
        barberDetails.setChargePerSession("12");
        barberDetails.setExperience("4");
        barberDetails.setPhoneNumber("1234654");
        barberDetails.setRating("5");
        barberDetailsList.add(barberDetails);

        Mockito.when(barberService.getBarberAllRecords()).thenReturn(barberDetailsList);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/barber/details").accept(
                MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Assert.assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }

    @Test
    public void getBarberAllRecordsTestInternalError() throws Exception {
        Mockito.when(barberService.getBarberAllRecords()).thenThrow(new RuntimeException());
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/barber/details").accept(
                MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), result.getResponse().getStatus());
    }

    @Test
    public void getBarberRecordSuccessTest() throws Exception {
        BarberDetails barberDetails = new BarberDetails();
        barberDetails.setId(123546);
        barberDetails.setName("test");
        barberDetails.setAddress("tester");
        barberDetails.setChargePerSession("12");
        barberDetails.setExperience("4");
        barberDetails.setPhoneNumber("1234654");
        barberDetails.setRating("5");
        Mockito.when(barberService.getBarberRecord(123546)).thenReturn(barberDetails);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/barber/details/123546").accept(
                MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Assert.assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }

    @Test
    public void getBarberRecordFailTest() throws Exception {
        Mockito.when(barberService.getBarberRecord(123)).thenThrow(new RuntimeException());
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/barber/details/123").accept(
                MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), result.getResponse().getStatus());
    }

    @Test
    public void createBarberRecordSuccessTest() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        BarberDetails barberDetails = new BarberDetails();
        barberDetails.setId(123546);
        barberDetails.setName("test");
        barberDetails.setAddress("tester");
        barberDetails.setChargePerSession("12");
        barberDetails.setExperience("4");
        barberDetails.setPhoneNumber("1234654");
        barberDetails.setRating("5");
        String json = mapper.writeValueAsString(barberDetails);
        Mockito.when(barberService.registerBarberDetails(barberDetails)).thenReturn(barberDetails);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/barber/register")
                .accept(MediaType.APPLICATION_JSON).content(json)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Assert.assertEquals(HttpStatus.CREATED.value(), result.getResponse().getStatus());
    }

    @Test
    public void createBarberRecordFailTest() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        BarberDetails barberDetails = new BarberDetails();
        barberDetails.setId(123546);
        barberDetails.setName("test");
        barberDetails.setAddress("tester");
        barberDetails.setChargePerSession("12");
        barberDetails.setExperience("4");
        barberDetails.setPhoneNumber("1234654");
        barberDetails.setRating("5");
        String json = mapper.writeValueAsString(barberDetails);
        Mockito.when(barberService.registerBarberDetails(barberDetails)).thenThrow(new RuntimeException());
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/barber/register")
                .accept(MediaType.APPLICATION_JSON).content(json)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), result.getResponse().getStatus());
    }
}