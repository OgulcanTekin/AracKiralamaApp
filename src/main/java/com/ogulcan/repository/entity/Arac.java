package com.ogulcan.repository.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "tblarac")
@NamedQueries({
        @NamedQuery(name = "arac.findKiralikArac",
                query = "select a from Arac a where a.state=false")
})
public class Arac {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String marka;
    String model;
    boolean state;// true kiralanmış demektir


}
