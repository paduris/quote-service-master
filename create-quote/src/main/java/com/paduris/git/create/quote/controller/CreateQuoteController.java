package com.paduris.git.create.quote.controller;


import com.paduris.git.create.quote.exception.AddressNotFoundException;
import com.paduris.git.create.quote.exception.QuoteNotFoundException;
import com.paduris.git.create.quote.exception.RiskLocationNotFoundException;
import com.paduris.git.create.quote.model.Address;
import com.paduris.git.create.quote.model.Customer;
import com.paduris.git.create.quote.model.Quote;
import com.paduris.git.create.quote.model.RiskLocation;
import com.paduris.git.create.quote.service.AddressService;
import com.paduris.git.create.quote.service.CustomerService;
import com.paduris.git.create.quote.service.QuoteService;
import com.paduris.git.create.quote.service.RiskLocationService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author paduris
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CreateQuoteController {

    @NonNull
    private QuoteService quoteService;

    @NonNull
    private CustomerService customerService;

    @NonNull
    private RiskLocationService riskLocationService;

    @NonNull
    private AddressService addressService;


    @PostMapping("/create")
    public Quote createQuote(@Valid @RequestBody Quote quote) {
        return quoteService.save(quote);
    }

    @GetMapping("/list")
    public Page<Quote> getAllQuotes(Pageable pageable) {
        return quoteService.findAll(pageable);
    }


    @GetMapping("/riskLocations/list")
    public Page<RiskLocation> getAllRiskLocations(Pageable pageable) {
        return riskLocationService.findAll(pageable);
    }

    @GetMapping("/adresses/list")
    public Page<Address> getAllAddresses(Pageable pageable) {
        return addressService.findAll(pageable);
    }


    @GetMapping("/riskLocations/{riskLocationId}")
    public RiskLocation findRiskLocation(@PathVariable Long riskLocationId) {
        return riskLocationService.findById(riskLocationId)
                .orElseThrow(() -> new RiskLocationNotFoundException("RiskLocationId " + riskLocationId + " not found"));
    }


    @GetMapping("/addresses/{addressId}")
    public Address findAddress(@PathVariable Long addressId) {
        return addressService.findById(addressId)
                .orElseThrow(() -> new AddressNotFoundException("AddressId " + addressId + " not found"));
    }

    @GetMapping("/quotes/{quoteId}")
    public Quote findQuote(@PathVariable Long quoteId) {
        return quoteService.findById(quoteId)
                .orElseThrow(() -> new QuoteNotFoundException("QuoteId " + quoteId + " not found"));
    }


    @PutMapping("/quotes/{quoteId}")
    public Quote updateQuote(@PathVariable Long quoteId, @Valid @RequestBody Quote quoteRequest) {
        return quoteService.findById(quoteId)
                .map(quote -> {
                    quote.setDescription(quoteRequest.getDescription());
                    quote.setEffectiveDate(quoteRequest.getEffectiveDate());
                    quote.setQuoteNumber(quoteRequest.getQuoteNumber());
                    quote.setUpdatedOn(quoteRequest.getUpdatedOn());
                    return quoteService.save(quote);
                })
                .orElseThrow(() -> new QuoteNotFoundException("QuoteId " + quoteId + " not found"));
    }

    @DeleteMapping("/quotes/{quoteId}")
    public ResponseEntity<?> deleteQuote(@PathVariable Long quoteId) {
        return quoteService.findById(quoteId).map(quote -> {
            quoteService.delete(quote);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new QuoteNotFoundException("QuoteId " + quoteId + " not found"));
    }


    // Here we need to call Customer Service ( Create Customer if not available )

    @PostMapping("/quotes/{quoteId}/customer")
    public Customer createCustomer(@PathVariable(value = "quoteId") Long quoteId,
                                   @Valid @RequestBody Customer customer) {
        return quoteService.findById(quoteId).map(quote -> {
            quote.setCustomer(customer);
            quoteService.save(quote);
            return customerService.save(customer);
        }).orElseThrow(() -> new QuoteNotFoundException("QuoteId " + quoteId + " not found"));
    }


    @PostMapping("/quote/{quoteId}/riskLocation")
    public RiskLocation createRiskLocation(@PathVariable(value = "quoteId") Long quoteId,
                                           @Valid @RequestBody RiskLocation riskLocation) {
        return quoteService.findById(quoteId).map(quote -> {
            riskLocation.setCustomer(quote.getCustomer());
            return riskLocationService.save(riskLocation);
        }).orElseThrow(() -> new QuoteNotFoundException("QuoteId " + quoteId + " not found"));
    }


    // Call address service to create a new address
    @PostMapping("/address")
    public Address createAddressForRiskLocation(
            @Valid @RequestBody Address address) {
        return addressService.createAddress(address);
    }

    @PostMapping("/riskLocation/{riskLocationId}/address")
    public RiskLocation addAddressToRiskLocation(@PathVariable(value = "riskLocationId") Long riskLocationId,
                                                 @Valid @RequestBody Address address) {
        return riskLocationService.findById(riskLocationId).map(riskLocation -> {
            riskLocation.setAddress(address);
            return riskLocationService.save(riskLocation);
        }).orElseThrow(() -> new RiskLocationNotFoundException("RiskLocation Id " + riskLocationId + " not found"));
    }


}
