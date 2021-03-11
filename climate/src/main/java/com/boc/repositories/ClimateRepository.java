package com.boc.repositories;

import com.boc.entities.Climate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClimateRepository extends JpaRepository<Climate, Long> {
	/*@Query(value="select * from Climate c where c.stationname like %:keyword%", nativeQuery=true)
	List<Climate> findByKeyword(@Param("keyword") String keyword);*/
}
