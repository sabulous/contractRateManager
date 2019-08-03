package com.sabulous.contractRateManager.services;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.sabulous.contractRateManager.model.Contract;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class ContractServiceImpl implements ContractService {

    private final String CONTRACTS_TABLE = "CONTRACT";
    private final String TABLE_FIELDS = "(ORIGIN, DESTINATION, " + "AGENT_NAME, COMMODITY, "
            + "WEIGHT_BREAK, CURRENCY, " + "VALUE, VALID_FROM, VALID_TO)";

    @Autowired
    JdbcTemplate jdbcTemplate;

    // OK
    @Override
    public List<Contract> getAllContracts() {
        return (ArrayList<Contract>) jdbcTemplate.query("select * from CONTRACT",
                new BeanPropertyRowMapper(Contract.class));
    }

    // OK
    @Override
    public Contract getContractById(int id) {
        return jdbcTemplate.queryForObject("select * from CONTRACT where id=?", new Object[] { id },
                new BeanPropertyRowMapper<Contract>(Contract.class));
    }

    @Override
    public Contract addOrEditContract(Contract contract) {
        System.out.println("Tried to INSERT the following :");
        contract.print();

        
        // generate sql Date objects from util Date objects for enabling DB INSTERT operation
        DateFormat formatter = new SimpleDateFormat("YYYY-MM-DD");
        System.out.println("DATE FROM IS ==== " + contract.getValidFrom().toString());
        System.out.println("DATE TO IS ==== " + contract.getValidTo().toString());
        Date fromDate = new java.sql.Date(contract.getValidFrom().getTime());
        Date toDate = new java.sql.Date(contract.getValidTo().getTime());
        
        int rowsAffected = -1;
        
        if(allFieldsAreProvided(contract)) {
            String insertSql = "INSERT INTO " + CONTRACTS_TABLE + " " + TABLE_FIELDS + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            rowsAffected = jdbcTemplate.update(insertSql,
                                contract.getOrigin(),
                                contract.getDestination(),
                                contract.getAgentName(),
                                contract.getCommodity(),
                                contract.getWeightBreak(),
                                contract.getCurrency(),
                                contract.getValue(),
                                fromDate,
                                toDate);
        } else {
            throw new NullPointerException("Missing field! Please provide all the parameters!");
        }

        if (rowsAffected == 0 || rowsAffected == -1) { 
            try {
                throw new Exception(rowsAffected + " rows have been updated.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("* INFORMATION: " + rowsAffected + " rows has been successfully changed.");
        }

        return contract;
	}

    private boolean allFieldsAreProvided(Contract c) {
        return (
            c.getOrigin() != null
            && c.getDestination() != null
            && c.getAgentName() != null
            && c.getCommodity() != null
            && c.getAgentName() != null
            && c.getCommodity() != null
            && c.getWeightBreak() != null
            && c.getCurrency() != null
            && c.getValue() != null
            && c.getValidFrom() != null
            && c.getValidTo() != null
            );
    }

    // OK
	@Override
	public void deleteContract(int id) {
        String deleteSql = "DELETE FROM " + CONTRACTS_TABLE + " WHERE ID = " + Integer.toString(id);
        Contract contract = getContractById(id);
		if(contract != null) {
            jdbcTemplate.execute(deleteSql);
        } else {
            throw new NullPointerException("Null Pointer Exception : No such id to delete.");
        }
	}

    // OK
	@Override
	public void printContracts() {
        ArrayList<Contract> contractz = (ArrayList<Contract>) getAllContracts();
        for(Contract c : contractz) {
            c.print();
        }        
    }
    
    // OK
    @Override
    public void printContract(int id) {
        Contract c = getContractById(id);
        c.print();
    }

	@Override
	public int findNextId() {

		return 0;
	}

}