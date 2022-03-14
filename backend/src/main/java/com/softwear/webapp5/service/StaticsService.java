package com.softwear.webapp5.service;

import com.softwear.webapp5.model.Statics;
import com.softwear.webapp5.repository.StaticsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class StaticsService {

    @Autowired
    private StaticsRepository staticsRepository;

    public Page<Statics> findAll(Pageable pageable){
        return staticsRepository.findAll(pageable);
	}
}