package com.ogulcan.controller;

import com.ogulcan.repository.entity.Arac;
import com.ogulcan.service.AracService;
import com.ogulcan.service.KiralamaService;
import com.ogulcan.service.KisiService;
import lombok.Getter;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Getter
public class AracController {
    private Scanner scanner = new Scanner(System.in);
    private AracService aracService;
    private KiralamaService kiralamaService;
    private KisiService kisiService;

    public AracController() {
        aracService = new AracService();
        kiralamaService = new KiralamaService();
        kisiService = new KisiService();
    }

    private int secim() {
        this.scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    private String ifade() {
        this.scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void arabaEkle() {
        System.out.println("Eklenecek araba markası...: ");
        String marka = ifade();
        System.out.println("Eklenecek araba modeli...: ");
        String model = ifade();
        aracService.save(Arac.builder()
                .marka(marka)
                .model(model)
                .build()
        );
    }

    public Optional<Arac> findById() {
        System.out.println("Arac id'si giriniz....: ");
        Long id = Long.parseLong(scanner.nextLine());

        return aracService.findById(id);

    }

    public List<Arac> araclariGoster() {
        return aracService.findAll();
    }
    public void kiradaOlanAraclar(){
        System.out.println("Bosta olan araclar...:");
        /**
         * default durumda arac kiralanmıs gozukuyor !!!!!
         */
        aracService.findAll().forEach(x->{
            if (x.isState()){
                System.out.println(x.getMarka()+" "+x.getModel());
            }
        });
    }
    public void bostaOlanAraclar() {
        System.out.println("Kirada olan araclar...: ");

        // Aracın state durumu default true
        /**
         * KiralamaControllerda eger kiralama yap methodunun sonuna false eklersem cozulur.
         */

        aracService.findAll().forEach(x -> {
            if (!x.isState())
                System.out.println(x.getMarka() + " " + x.getModel());
        });
    }


}
