package com.boc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.boc.entities.Climate;
import com.boc.repositories.ClimateRepository;


@Service
public class ClimateServiceImpl implements ClimateService {

	@Autowired
	private ClimateRepository climateRepository;

	@Override
	public List<Climate> getAllClimateRecords() {
		return climateRepository.findAll();
	}

	/*@Override
	public void saveClimate(Climate climate) {
		this.climateRepository.save(climate);
	}*/

	@Override
	public Climate getClimateById(long id) {
		Optional<Climate> optional = climateRepository.findById(id);
		Climate Climate = null;
		if (optional.isPresent()) {
			Climate = optional.get();
		} else {
			throw new RuntimeException(" Climate recrod not found for id :: " + id);
		}
		return Climate;
	}

	public Page<Climate> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		System.out.println("No keyword found");
		
		return this.climateRepository.findAll(pageable);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Page<Climate> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection, String keyword) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		System.out.println("Keyword found");
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		
		Page<Climate> climateList= climateRepository.findAll(pageable);
		
		return (climateList);

	}

	@Override
	public void deleteClimateById(long id) {
		// TODO Auto-generated method stub
		
	}
}
