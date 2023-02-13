package com.ogulcan.service;

import com.ogulcan.repository.AracRepository;
import com.ogulcan.repository.entity.Arac;
import com.ogulcan.utility.MyFactoryService;

public class AracService extends MyFactoryService<AracRepository, Arac,Long> {

    public AracService(){
        super(new AracRepository());
    }
}
