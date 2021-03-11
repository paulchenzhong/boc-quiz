package com.boc.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import com.boc.entities.Climate;


public interface ClimateService {
	List<Climate> getAllClimateRecords();
	//void saveClimate(Climate climate);
	Climate getClimateById(long id);
	void deleteClimateById(long id);
	Page<Climate> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection, String keyword);
	Page<Climate> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
