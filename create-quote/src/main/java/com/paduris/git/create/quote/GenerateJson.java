package com.paduris.git.create.quote;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.paduris.git.create.quote.model.Address;
import com.paduris.git.create.quote.model.Customer;
import com.paduris.git.create.quote.model.Quote;
import com.paduris.git.create.quote.model.RiskLocation;

import java.time.LocalDate;

public class GenerateJson {

    public static void main(String[] args) throws JsonProcessingException {
        Customer customer = Customer.builder()
                //.addressList(Stream.of(customerAddress).collect(Collectors.toSet()))
                //.riskLocations(Stream.of(riskLocation).collect(Collectors.toSet()))
                .name("A1 Plumbing Company")
                .roleType("Insured")
                .customerNumber("000233")
                .type("Business")
                .build();


        Address customerAddress = Address.builder()
                .type("MAIL")
                .state("ID")
                .zipCode("83716")
                .city("Boise")
                .addressLine2("Apt #123")
                .addressLine1("3432 S Apple st")
                .customer(customer)
                .build();

        customerAddress.setCreatedOn(LocalDate.now());



        Address riskLocationAddress = Address.builder()
                .type("MAIL")
                .state("ID")
                .zipCode("83702")
                .city("Boise")
                .addressLine2("2123")
                .addressLine1("410 Plaza street")
                .customer(customer)
                .build();

        riskLocationAddress.setCreatedOn(LocalDate.now());



        customer.setCreatedOn(LocalDate.now());


        RiskLocation riskLocation = RiskLocation.builder()
                .locationName("A1 Plaza")
                .address(riskLocationAddress)
                .contactName("Jimmy Nesham")
                .description("Risk Location for A1 Plaze")
                .numberOfEmployees(Integer.valueOf(10))
                .contactPhone("2089917800")
                .customer(customer)
                .build();


        riskLocation.setCreatedOn(LocalDate.now());


        Quote quote = Quote.builder()
                .customer(customer)
                .description("New Quote from A1 Plumbing Comp")
                .effectiveDate(LocalDate.now())
                //.riskLocationList(Stream.of(riskLocation).collect(Collectors.toSet()))
                .build();

       // customer.setAddressList(Stream.of(customerAddress).collect(Collectors.toSet()));
        //customer.setRiskLocations(Stream.of(riskLocation).collect(Collectors.toSet()));

        //customer.setQuotes(Stream.of(quote).collect(Collectors.toSet()));
        //quote.setCustomer(customer);

        quote.setCreatedOn(LocalDate.now());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(customerAddress);

        System.out.println(json);

    }
}
