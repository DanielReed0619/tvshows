package com.dr.beltexam.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dr.beltexam.models.TvShow;



@Repository
public interface TvShowRepo extends CrudRepository<TvShow, Long>{
	
	 List<TvShow> findAll();
	 
	 Optional<TvShow> findById(Long id);
	 
	 
	 void deleteById(Long id);
	
	 
}