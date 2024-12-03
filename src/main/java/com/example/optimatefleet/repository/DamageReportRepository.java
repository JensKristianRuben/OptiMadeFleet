package com.example.optimatefleet.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DamageReportRepository {

    @Autowired
    JdbcTemplate damageReportTemplate;
}
