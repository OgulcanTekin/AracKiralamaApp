package com.ogulcan.service;

import com.ogulcan.repository.KiralamaRepository;
import com.ogulcan.repository.entity.Arac;
import com.ogulcan.repository.entity.Kiralama;
import com.ogulcan.repository.entity.Kisi;
import com.ogulcan.utility.MyFactoryService;
import lombok.Getter;

import java.util.List;
import java.util.Scanner;

@Getter
public class KiralamaService extends MyFactoryService<KiralamaRepository, Kiralama,Long> {
    private Scanner scanner;
    private AracService aracService;
    private KisiService kisiService;
    public KiralamaService(){
        super(new KiralamaRepository());
    }
    private  int secim(){
        this.scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    private String ifade(){
        this.scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

}
