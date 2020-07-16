package com.paduris.git.create.quote.model;

import lombok.*;


import javax.persistence.*;

@Data
@Builder
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Customer")
@Entity(name = "Customer")
public class Customer extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "type", nullable = false, length = 80)
    private String type;

    @Column(name = "name", nullable = false, length = 80)
    private String name;

    @Column(name = "role_type", nullable = false, length = 80)
    private String roleType;

    @Column(name = "customer_number", nullable = false, length = 10)
    private String customerNumber;

}
