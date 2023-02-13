package com.ogulcan.repository.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "tblkiralama")
public class Kiralama {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private LocalDate kiralamaBaslangic;
    private LocalDate kiralamaBitis;

    @ManyToOne
    @JoinColumn(name = "arac_Id")
    Arac arac;
    @ManyToOne
    @JoinColumn(name = "kisi_Id")
    Kisi kisi;
}
