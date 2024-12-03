package com.example.optimatefleet.repository;

import com.example.optimatefleet.model.PreSaleContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PreSaleContractRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void createPreSaleContract(PreSaleContract preSaleContract){
        String sqlCheckCity = "SELECT COUNT(*) FROM city WHERE zip_code = ?";
        int cityCount = jdbcTemplate.queryForObject(sqlCheckCity, Integer.class, preSaleContract.getZip_code());

        if (cityCount == 0) {
            String sqlCity = "INSERT INTO city(zip_code, city_name) VALUES (?, ?)";
            jdbcTemplate.update(sqlCity, preSaleContract.getZip_code(), preSaleContract.getCity_name());
        }

        String sqlAddress = "INSERT INTO address(zip_code, street_name, street_number) VALUES (?, ?, ?)";

        jdbcTemplate.update(sqlAddress, preSaleContract.getZip_code(), preSaleContract.getStreet_name(), preSaleContract.getStreet_number());

        String getAddressId = "SELECT address_id FROM address WHERE zip_code = ? AND street_name = ? AND street_number = ?";

        int addressID = jdbcTemplate.queryForObject(getAddressId, new Object[]{preSaleContract.getZip_code(),
                preSaleContract.getStreet_name(), preSaleContract.getStreet_number()}, Integer.class
        );

        String sqlBuyer = "INSERT INTO buyer(cvr, company_name, company_phonenumber, email, address_id) VALUES (?, ?, ?, ?, ?)";

        jdbcTemplate.update(sqlBuyer, preSaleContract.getCvr(), preSaleContract.getCompany_name(), preSaleContract.getCompany_phonenumber(), preSaleContract.getEmail(), addressID);

        String sqlPreSaleContract = "INSERT INTO pre_sale_contract(cvr, license_plate, delivery_location, price, max_km) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sqlPreSaleContract, preSaleContract.getCvr(), preSaleContract.getLicense_plate(), preSaleContract.getDelivery_location(), preSaleContract.getPrice(), preSaleContract.getMax_km());

    }
    public List<PreSaleContract> fetchAllPreSaleContracts(){
        String fetchSql = "SELECT \n" +
                "    pre_sale_contract.license_plate, \n" +
                "    pre_sale_contract.delivery_location, \n" +
                "    pre_sale_contract.price, \n" +
                "    pre_sale_contract.max_km,\n" +
                "    buyer.company_name,\n" +
                "    buyer.company_phonenumber,\n" +
                "    buyer.email,\n" +
                "    buyer.cvr,\n" +
                "    address.street_name,\n" +
                "    address.street_number,\n" +
                "    city.city_name,\n" +
                "    address.zip_code\n" +
                "FROM pre_sale_contract\n" +
                "INNER JOIN buyer ON pre_sale_contract.cvr = buyer.cvr\n" +
                "INNER JOIN address ON buyer.address_id = address.address_id\n" +
                "INNER JOIN city ON address.zip_code = city.zip_code;";

        return jdbcTemplate.query(fetchSql, new BeanPropertyRowMapper<>(PreSaleContract.class));
    }
    public void deletePreSaleContract(String licensePlate){
        String getCvrSql = "SELECT cvr FROM pre_sale_contract WHERE license_plate = ?";

        Integer cvr = jdbcTemplate.queryForObject(getCvrSql, Integer.class, licensePlate);

        String getAddressIdSql = "SELECT address_id FROM buyer WHERE cvr = ?";

        Integer addressId = jdbcTemplate.queryForObject(getAddressIdSql, Integer.class, cvr);

        String deletePreSaleContractSql = "DELETE FROM pre_sale_contract WHERE license_plate = ?";
        jdbcTemplate.update(deletePreSaleContractSql, licensePlate);

        String deleteBuyerSql = "DELETE FROM buyer WHERE cvr = ?";
        jdbcTemplate.update(deleteBuyerSql, cvr);

        String deleteAddressSql = "DELETE FROM address WHERE address_id = ?";
        jdbcTemplate.update(deleteAddressSql, addressId);
    }
}
