package com.softwear.webapp5.repository;

import com.softwear.webapp5.model.Statics;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaticsRepository extends JpaRepository<Statics, Long> {
    public Page<Statics> findByEarns(double earns, Pageable page);    
}
