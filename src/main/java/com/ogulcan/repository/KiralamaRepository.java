package com.ogulcan.repository;

import com.ogulcan.repository.entity.Kiralama;
import com.ogulcan.utility.MyFactoryRepository;

import javax.persistence.TypedQuery;
import java.util.List;

public class KiralamaRepository extends MyFactoryRepository<Kiralama,Long> {

    public KiralamaRepository() {
        super(new Kiralama());
    }

}
