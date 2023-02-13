package com.ogulcan;

import com.ogulcan.controller.AracController;
import com.ogulcan.controller.KiralamaController;
import com.ogulcan.controller.KisiController;
import com.ogulcan.repository.entity.Arac;

import java.util.Scanner;

public class AracKiralamaApp {

//    static AracController aracController= new AracController();
//    static KisiController kisiController=new KisiController();
//    static KiralamaController kiralamaController=new KiralamaController();

    /**
     * Twitter Menu'de gerekli değisiklikleri yap
     */
    public static void main(String[] args) {
        int secim=0;
        Scanner scanner=new Scanner(System.in);
        do {
            System.out.println("*********************************************");
            System.out.println("*******   ARAC KIRALAMA UYGULAMASI   ********");
            System.out.println("*********************************************");
            System.out.println();
            System.out.println("1- Arac Ekleme");
            System.out.println("2- Arac Arama");
            System.out.println("3- Kisi Ekleme");
            System.out.println("4- Kiralama");
            System.out.println("5- Rapor");
            secim= scanner.nextInt();
            switch (secim){
                case 1: new AracController().arabaEkle();
                    break;
                case 2:
                    System.out.println(new AracController().findById());
                    break;
                case 3: new KisiController().kisiEkleme();
                    break;
                case 4: new KiralamaController().kirala();
                    break;
                case 5: rapor();
                    break;

            }
        }while(secim!=0);
        System.out.println("YINE BEKLERIZ....");
    }

    private static void rapor(){
        Scanner scanner=new Scanner(System.in);
        int secim=0;
        do {
            System.out.println("Lütfen secim yapın ---->");
            System.out.println("1- Bosta Olan Araclar");
            System.out.println("2- Kirada Olan Araclar");
            System.out.println("3- Herhangi bir musterinin kiraladigi araclar");
            System.out.println("0- Ana Menuye don");
            secim= scanner.nextInt();
            switch (secim) {
                case 1:
                    new AracController().bostaOlanAraclar();
                    break;
                case 2:
                    new AracController().kiradaOlanAraclar();
                    break;
                case 3: new KiralamaController().herhangiMusterininKiraladigiAraclar(); break;
            }
        }while (secim!=0);
    }
}
