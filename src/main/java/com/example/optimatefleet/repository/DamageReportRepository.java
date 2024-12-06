package com.example.optimatefleet.repository;

import com.example.optimatefleet.model.DamageReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DamageReportRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void createDamageReport (DamageReport damageReport) {
        //Gemmer damage_desciption i databasen
        String damage_description_sql = "INSERT INTO damage_description (description) VALUES (?)";

        jdbcTemplate.update(damage_description_sql,
                damageReport.getDamage_desciption()
                );

        //Hiver det nye damage_id der er blevet auto_incrementet ud
        String getDamageDescriptionID_sql = "SELECT MAX(damage_id) as damage_id FROM damage_description";

        Integer damage_id = jdbcTemplate.queryForObject(getDamageDescriptionID_sql, Integer.class);

        //Gemmer damage_price i databasen
        String damage_price_sql = "INSERT INTO damage_price (damage_id, damage_price) VALUES (?, ?)";

        jdbcTemplate.update(damage_price_sql,
                damage_id,
                damageReport.getDamage_price()
                );

        //Gemmer damage_report i databasen
        String damage_report_sql = "INSERT INTO damage_report (license_plate, damage_id, mileage_over_limit) VALUES (?, ?, ?)";

        jdbcTemplate.update(damage_report_sql,
                damageReport.getLicense_plate(),
                damage_id,
                damageReport.getMileage_over_limit()
            );
    }
}
