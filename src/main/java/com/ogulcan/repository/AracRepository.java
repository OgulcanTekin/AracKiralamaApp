package com.ogulcan.repository;

import com.ogulcan.repository.entity.Arac;
import com.ogulcan.utility.MyFactoryRepository;

public class AracRepository extends MyFactoryRepository<Arac,Long> {
    public AracRepository() {
        super(new Arac());
    }
}
