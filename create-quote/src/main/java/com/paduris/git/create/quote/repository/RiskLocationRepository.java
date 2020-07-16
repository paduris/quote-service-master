package com.paduris.git.create.quote.repository;


import com.paduris.git.create.quote.model.RiskLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author paduris
 */

@Repository
public interface RiskLocationRepository extends JpaRepository<RiskLocation, Long> {
}
