package com.lifepill.pharmacyservice.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.Assert;

import com.lifepill.pharmacyservice.controller.PharmacyController;
import com.lifepill.pharmacyservice.model.Pharmacy;
import com.lifepill.pharmacyservice.service.PharmacyService;

@WebMvcTest(value = PharmacyController.class)
public class TestPharmacyController {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PharmacyService pharmacyService;

    Pharmacy mockPharmacy = new Pharmacy(
            1L,
            "LifePill Pharmacy",
            "lifepill@gmail.com",
            "0771231234",
            "Hospital Road",
            "Maharagama",
            "Colombo",
            true,
            5,
            "password");

    ArrayList<Pharmacy> mockPharmacyList = new ArrayList<Pharmacy>() {
        {
            add(mockPharmacy);
        }
    };

    String examplePharmacyJson = "{\"pharmacyName\":\"LifePill Pharmacy\",\"pharmacyEmail\":\"lifepill@gmail.com\", \"pharmacyMobileNumber\":\"0771231234\",\"pharmacyAddressStreet\":\"Hospital Road\", \"pharmacyAddressCity\":\"Maharagama\", \"pharmacyAddressDistrict\":\"Colombo\", \"openStatus\": true , \"pharmacyRating\": 5, \"pharmacyPassword\":\"password\"}";

    // testing the get Pharmacy by id functionality
    @Test
    public void retrievePharmacy() throws Exception {

        Mockito.when(pharmacyService.getPharmacy(Mockito.anyLong())).thenReturn(mockPharmacy);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/pharmacies/1").accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse());
        String expected = "{\"pharmacyName\":\"LifePill Pharmacy\",\"pharmacyEmail\":\"lifepill@gmail.com\", \"pharmacyMobileNumber\":\"0771231234\",\"pharmacyAddressStreet\":\"Hospital Road\", \"pharmacyAddressCity\":\"Maharagama\", \"pharmacyAddressDistrict\":\"Colombo\", \"openStatus\": true , \"pharmacyRating\": 5}";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    // testing the add new pharmacy functionality
    @Test
    public void createNewPharmacy() throws Exception {
        Pharmacy mockPharmacy = new Pharmacy(
                1L,
                "LifePill Pharmacy",
                "lifepill@gmail.com",
                "0771231234",
                "Hospital Road",
                "Maharagama",
                "Colombo",
                true,
                5,
                "password");

        Mockito.when(pharmacyService.addPharmacy(Mockito.any(Pharmacy.class))).thenReturn(mockPharmacy);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/pharmacies")
                .accept(MediaType.APPLICATION_JSON).content(examplePharmacyJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());

        // assertEquals("http://localhost/pharmacies/1",
        // response.getHeader(HttpHeaders.LOCATION));
    }

    // testing the get all Pharmacies functionality
    @Test
    public void retrieveAllPharmacy() throws Exception {

        Mockito.when(pharmacyService.getAllPharmacies()).thenReturn(mockPharmacyList);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/pharmacies").accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse());
        String expected = "[{\"pharmacyName\":\"LifePill Pharmacy\",\"pharmacyEmail\":\"lifepill@gmail.com\", \"pharmacyMobileNumber\":\"0771231234\",\"pharmacyAddressStreet\":\"Hospital Road\", \"pharmacyAddressCity\":\"Maharagama\", \"pharmacyAddressDistrict\":\"Colombo\", \"openStatus\": true , \"pharmacyRating\": 5}]";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
        Assert.isTrue(result.getResponse().getContentAsString().contains("Colombo"), "Passed");
    }

    // testing the get Pharmacies by city functionality
    @Test
    public void retrievePharmaciesByCity() throws Exception {

        Mockito.when(pharmacyService.getPharmacyByCity(Mockito.anyString())).thenReturn(mockPharmacyList);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/pharmacies/city=Maharagama")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse());
        String expected = "[{\"pharmacyName\":\"LifePill Pharmacy\",\"pharmacyEmail\":\"lifepill@gmail.com\", \"pharmacyMobileNumber\":\"0771231234\",\"pharmacyAddressStreet\":\"Hospital Road\", \"pharmacyAddressCity\":\"Maharagama\", \"pharmacyAddressDistrict\":\"Colombo\", \"openStatus\": true , \"pharmacyRating\": 5}]";

        // JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(),
        // false);
        Assert.isTrue(result.getResponse().getContentAsString().contains("Maharagama"), expected);
    }

    // testing the get Pharmacies by district functionality
    @Test
    public void retrievePharmaciesByDistrict() throws Exception {

        Mockito.when(pharmacyService.getPharmacyByDistrict(Mockito.anyString())).thenReturn(mockPharmacyList);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/pharmacies/district=Colombo")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse());
        String expected = "[{\"pharmacyName\":\"LifePill Pharmacy\",\"pharmacyEmail\":\"lifepill@gmail.com\", \"pharmacyMobileNumber\":\"0771231234\",\"pharmacyAddressStreet\":\"Hospital Road\", \"pharmacyAddressCity\":\"Maharagama\", \"pharmacyAddressDistrict\":\"Colombo\", \"openStatus\": true , \"pharmacyRating\": 5}]";

        // JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(),
        // false);
        Assert.isTrue(result.getResponse().getContentAsString().contains("Colombo"), expected);
    }
}
