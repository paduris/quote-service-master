package com.paduris.git.create.quote.service;

import com.paduris.git.create.quote.exception.AddressNotFoundException;
import com.paduris.git.create.quote.model.Address;
import com.paduris.git.create.quote.repository.AddressRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class AddressService {
    @NonNull
    private AddressRepository addressRepository;

    public Address createAddress(Address address) {
        return addressRepository.saveAndFlush(address);
    }

    public Page<Address> findAll(Pageable pageable) {
        return addressRepository.findAll(pageable);
    }

    public ResponseEntity<?> deleteAddress(@PathVariable Long addressId) {
        return addressRepository.findById(addressId).map(address -> {
            addressRepository.delete(address);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new AddressNotFoundException("Address Id" + addressId));
    }

    public Optional<Address> findById(Long addressId) {
        return addressRepository.findById(addressId);
    }

}
