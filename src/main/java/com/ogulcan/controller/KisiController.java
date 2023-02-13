package com.ogulcan.controller;

import com.ogulcan.repository.entity.Arac;
import com.ogulcan.repository.entity.Kisi;
import com.ogulcan.service.AracService;
import com.ogulcan.service.KiralamaService;
import com.ogulcan.service.KisiService;

import java.util.Scanner;

public class KisiController {
    private Scanner scanner;
    private AracService aracService;
    private KiralamaService kiralamaService;
    private KisiService kisiService;

    public KisiController(){
        aracService=new AracService();
        kiralamaService=new KiralamaService();
        kisiService= new KisiService();
    }
    private  int secim(){
        this.scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
    private String ifade(){
        this.scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
    public void kisiEkleme(){
        System.out.println("Eklenecek kisinin adı...: ");
        String ad= ifade();
        System.out.println("Eklenecek kisinin soyadı...: ");
        String model= ifade();
        kisiService.save(Kisi.builder()
                .isim("Ogulcan")
                .soyisim("Tekin")
                .build()
        );
    }
}
