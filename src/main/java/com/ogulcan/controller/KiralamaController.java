package com.ogulcan.controller;

import com.ogulcan.repository.entity.Arac;
import com.ogulcan.repository.entity.Kiralama;
import com.ogulcan.repository.entity.Kisi;
import com.ogulcan.service.AracService;
import com.ogulcan.service.KiralamaService;
import com.ogulcan.service.KisiService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class KiralamaController {
    private Scanner scanner;
    private AracService aracService;
    private KiralamaService kiralamaService;
    private KisiService kisiService;

    private AracController aracController;
    private KiralamaController kiralamaController;
    private KisiController kisiController;

    public KiralamaController(){
        aracService=new AracService();
        kiralamaService=new KiralamaService();
        kisiService= new KisiService();
        aracController=new AracController();
        kisiController=new KisiController();
    }
    private  int secim(){
        this.scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    private String ifade(){
        this.scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void kirala(){
        try {
            System.out.println("Kiralama yapmak istediginiz başlangic tarihi...: ");
            LocalDate baslangicTarihi = LocalDate.parse(ifade());
            System.out.println("Kiralamanın bitis tarihi...: ");
            LocalDate bitisTarihi = LocalDate.parse(ifade());
            // ARAC LISTESINI GORDUKTEN SONRA SECIM YAPSIN
            System.out.println("Tüm Aracların Listesi");
            System.out.println(aracController.araclariGoster());

            System.out.println("Kiralayacaginiz arac id'si...: ");
            Long aracId = Long.parseLong(ifade());
            System.out.println("Kiralama yapacak kisi id'si...: ");
            Long kisiId = Long.parseLong(ifade());
            kiralamaService.save(Kiralama.builder().arac(Arac.builder().id(aracId).build())
                    .kisi(Kisi.builder().id(kisiId).build())
                    .kiralamaBaslangic(baslangicTarihi).kiralamaBitis(bitisTarihi).build());
            Arac arac=aracService.findById(aracId).get();
            arac.setState(true);
            aracService.save(arac);
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Hatali bir giris yaptiniz... " +
                    " Lutfen Tarih girerken 2222-12-31 seklinde giris yapiniz ");
        }


    }

    public void herhangiMusterininKiraladigiAraclar(){
        System.out.println("Detaylarini gormek istediginiz kiralama id'si...:");
        Long kiraId=Long.parseLong(ifade());
        System.out.println("Kisi ve arac bilgileri su sekildedir =====> ");


        Long kisiId=kiralamaService.findById(kiraId).get().getId();
        Long aracId=kiralamaService.findById(kiraId).get().getArac().getId();
        System.out.println(kiralamaService.findById(kiraId));
        System.out.println(kisiService.findById(kiraId));
        System.out.println(aracService.findById(aracId));
    }

}
