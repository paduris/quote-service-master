package com.paduris.git.create.quote.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;


/**
 * @author paduris
 * Quote Entity class
 */


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity(name = "Quote")
@Table(name = "Quote")
public class Quote extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "quote_number", nullable = false)
    private String quoteNumber;

    @Column(name = "descripton", nullable = true)
    private String description;

    @Column(name = "effective_date", nullable = false)
    private LocalDate effectiveDate;

//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
//    @JsonIgnoreProperties("quote")
//    private Set<RiskLocation> riskLocationList;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "cust_id", foreignKey = @ForeignKey(name = "CUST_QUOTE_ID_FK"))
    @JsonIgnoreProperties("quote")
    private Customer customer;
}
