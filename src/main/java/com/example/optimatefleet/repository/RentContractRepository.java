package com.example.optimatefleet.repository;

import com.example.optimatefleet.model.RentContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class RentContractRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void createRentContract(RentContract rentContract) {
        String sqlCheckCity = "SELECT COUNT(*) FROM city WHERE zip_code = ?";
        int cityCount = jdbcTemplate.queryForObject(sqlCheckCity, Integer.class, rentContract.getZip_code());

        if (cityCount == 0) {
            String sqlCity = "INSERT INTO city(zip_code, city_name) VALUES (?, ?)";
            jdbcTemplate.update(sqlCity, rentContract.getZip_code(), rentContract.getCity_name());
        }

        String sqlAddressCheck = "SELECT address_id FROM address WHERE zip_code = ? AND street_name = ? AND street_number = ?";
        Integer addressID;

        try {
            addressID = jdbcTemplate.queryForObject(
                    sqlAddressCheck,
                    new Object[]{rentContract.getZip_code(), rentContract.getStreet_name(), rentContract.getStreet_number()},
                    Integer.class
            );
        } catch (EmptyResultDataAccessException e) {

            String sqlAddress = "INSERT INTO address(zip_code, street_name, street_number) VALUES (?, ?, ?)";
            jdbcTemplate.update(sqlAddress, rentContract.getZip_code(), rentContract.getStreet_name(), rentContract.getStreet_number());

            addressID = jdbcTemplate.queryForObject(
                    sqlAddressCheck,
                    new Object[]{rentContract.getZip_code(), rentContract.getStreet_name(), rentContract.getStreet_number()},
                    Integer.class
            );
        }
        String sqlRenterCheck = "SELECT drivers_license_number FROM renter WHERE drivers_license_number = ?";
        String driversLicenseNumber = null;
        try {
            driversLicenseNumber = jdbcTemplate.queryForObject(sqlRenterCheck,
                    new Object[]{rentContract.getDrivers_license_number()},
                    String.class);

        }catch (EmptyResultDataAccessException e){
            String sqlRenter = "INSERT INTO renter (drivers_license_number, renter_first_name, renter_last_name," +
                    " address_id, renter_phonenumber, email, date_of_birth) VALUES (?, ?, ?, ?, ?, ?, ?)";
            jdbcTemplate.update(sqlRenter, rentContract.getDrivers_license_number(), rentContract.getRenter_first_name(),
                    rentContract.getRenter_last_name(), addressID, rentContract.getRenter_phonenumber(),
                    rentContract.getEmail(), rentContract.getDate_of_birth());
        }

        String sqlRentContract = "INSERT INTO rent_contract (drivers_license_number, license_plate, rental_start_date, " +
                "rental_end_date, pickup_location, return_location, max_km, rent_contract_type, contract_terminated) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sqlRentContract, rentContract.getDrivers_license_number(), rentContract.getLicense_plate(), rentContract.getRental_start_date(), rentContract.getRental_end_date(),
                rentContract.getPickup_location(), rentContract.getReturn_location(), rentContract.getMax_km(), rentContract.getRent_contract_type(), rentContract.isContract_terminated());
    }

    public List<RentContract> fetchAllRentContracts() {
        String getRentContractsSql = "SELECT \n" +
                "    rent_contract.license_plate, \n" +
                "    rent_contract.rental_start_date, \n" +
                "    rent_contract.rental_end_date, \n" +
                "    rent_contract.pickup_location, \n" +
                "    rent_contract.return_location, \n" +
                "    rent_contract.max_km, " +
                "    rent_contract.rent_contract_type," +
                "    rent_contract.contract_terminated, " +
                "    renter.drivers_license_number,\n" +
                "    renter.renter_first_name,\n" +
                "    renter.renter_last_name,\n" +
                "    renter.renter_phonenumber,\n" +
                "    renter.email,\n" +
                "    renter.date_of_birth,\n" +
                "    address.street_name,\n" +
                "    address.street_number,\n" +
                "    city.city_name,\n" +
                "    address.zip_code\n" +
                "FROM rent_contract\n" +
                "INNER JOIN renter ON rent_contract.drivers_license_number = renter.drivers_license_number\n" +
                "INNER JOIN address ON renter.address_id = address.address_id\n" +
                "INNER JOIN city ON address.zip_code = city.zip_code;\n";

        RowMapper rowMapper = new BeanPropertyRowMapper(RentContract.class);

        return jdbcTemplate.query(getRentContractsSql, rowMapper);

    }

    public void deleteByLicensePlate(String licensePlate) {

        String deleteRentContractSql = "DELETE FROM rent_contract WHERE license_plate = ?";
        jdbcTemplate.update(deleteRentContractSql, licensePlate);
    }

    public void updateRentContract(RentContract rentContract){
        String updateCity = "UPDATE city SET city_name = ? WHERE zip_code = ?";

        jdbcTemplate.update(updateCity,
                rentContract.getCity_name(),
                rentContract.getZip_code());

        String updateAddress = "UPDATE address SET street_name = ?, street_number = ? WHERE zip_code = ?";

        jdbcTemplate.update(updateAddress,
                rentContract.getStreet_name(),
                rentContract.getStreet_number(),
                rentContract.getZip_code());

        String updateRenter = "UPDATE renter SET renter_first_name = ?, renter_last_name = ?, renter_phonenumber = ?, " +
                "    email = ?, " +
                "    date_of_birth = ? " +
                "WHERE drivers_license_number = ?";

        jdbcTemplate.update(updateRenter,
                rentContract.getRenter_first_name(),
                rentContract.getRenter_last_name(),
                rentContract.getRenter_phonenumber(),
                rentContract.getEmail(),
                rentContract.getDate_of_birth(),
                rentContract.getDrivers_license_number());

        String updateRentContract = "UPDATE rent_contract SET license_plate = ?, max_km = ?, rental_start_date = ?, " +
                "    rental_end_date = ?, " +
                "    pickup_location = ?, " +
                "    return_location = ?, " +
                "    rent_contract_type = ?," +
                "    contract_terminated = ? " +
                "WHERE license_plate = ?";

        jdbcTemplate.update(updateRentContract,
                rentContract.getLicense_plate(),
                rentContract.getMax_km(),
                rentContract.getRental_start_date(),
                rentContract.getRental_end_date(),
                rentContract.getPickup_location(),
                rentContract.getReturn_location(),
                rentContract.getRent_contract_type(),
                rentContract.isContract_terminated(),
                rentContract.getLicense_plate());
    }

}
