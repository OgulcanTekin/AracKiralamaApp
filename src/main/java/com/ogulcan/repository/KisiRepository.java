package com.ogulcan.repository;

import com.ogulcan.repository.entity.Kisi;
import com.ogulcan.utility.MyFactoryRepository;

public class KisiRepository extends MyFactoryRepository<Kisi,Long> {
    public KisiRepository() {
        super(new Kisi());
    }
}
