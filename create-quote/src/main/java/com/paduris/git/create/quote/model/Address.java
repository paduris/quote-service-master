package com.paduris.git.create.quote.model;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

/**
 * @author paduris
 * <p>
 * Address Entity class
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode
@Entity(name = "Address")
@Table(name = "Address")
public class Address extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "addr_line_1", nullable = false, length = 80)
    private String addressLine1;

    @Column(name = "addr_line_2", nullable = true, length = 80)
    private String addressLine2;

    @Column(name = "city", nullable = false, length = 80)
    private String city;

    @Column(name = "state", nullable = false, length = 80)
    private String state;

    @Column(name = "zip_code", nullable = false, length = 80)
    private String zipCode;

    @Column(name = "type", nullable = false, length = 80)
    private String type;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "cust_id", foreignKey = @ForeignKey(name = "CUST_ID_ADDR_FK"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Customer customer;
}
