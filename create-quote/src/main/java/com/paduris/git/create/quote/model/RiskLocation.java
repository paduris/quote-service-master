package com.paduris.git.create.quote.model;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

/**
 * @author paduris
 * <p>
 * Risk location entity class
 */


@Data
@Builder
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Risk_Location")
@Table(name = "Risk_Location")
public class RiskLocation extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Address address;

    @Column(name = "location_name", nullable = false, length = 80)
    private String locationName;

    @Column(name = "description", nullable = false, length = 80)
    private String description;

    @Column(name = "contact_name", nullable = false, length = 80)
    private String contactName;

    @Column(name = "contact_phone", nullable = false, length = 10)
    private String contactPhone;

    @Column(name = "no_of_employees", nullable = false, length = 5)
    private Integer numberOfEmployees;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "cust_id", foreignKey = @ForeignKey(name = "CUST_ID_RISK_LOC_FK"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Customer customer;

}
