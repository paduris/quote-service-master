package com.paduris.git.create.quote.service;

import com.paduris.git.create.quote.exception.RiskLocationNotFoundException;
import com.paduris.git.create.quote.model.RiskLocation;
import com.paduris.git.create.quote.repository.RiskLocationRepository;
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
public class RiskLocationService {
    @NonNull
    private RiskLocationRepository riskLocationRepository;

    public RiskLocation save(RiskLocation riskLocation) {
        return riskLocationRepository.saveAndFlush(riskLocation);
    }
    public Optional<RiskLocation> findById(Long riskLocationId) {
        return riskLocationRepository.findById(riskLocationId);
    }

    public Page<RiskLocation> findAll(Pageable pageable) {
        return riskLocationRepository.findAll(pageable);
    }

    public ResponseEntity<?> deleteRiskLocation(@PathVariable Long riskLocationId) {
        return riskLocationRepository.findById(riskLocationId).map(riskLocation -> {
            riskLocationRepository.delete(riskLocation);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new RiskLocationNotFoundException("Risk Location Id" + riskLocationId));
    }
}
