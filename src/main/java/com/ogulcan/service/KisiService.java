package com.ogulcan.service;

import com.ogulcan.repository.KisiRepository;
import com.ogulcan.repository.entity.Kisi;
import com.ogulcan.utility.MyFactoryRepository;
import com.ogulcan.utility.MyFactoryService;
import lombok.Getter;

@Getter
public class KisiService extends MyFactoryService<KisiRepository, Kisi,Long> {

    public KisiService(){
        super(new KisiRepository());
    }
}
