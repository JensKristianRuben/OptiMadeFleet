package com.example.optimatefleet.service;

import com.example.optimatefleet.repository.DamageReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DamageReportService {

    @Autowired
    private DamageReportRepository damageReportRepository;
}
