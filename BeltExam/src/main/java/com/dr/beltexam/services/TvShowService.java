package com.dr.beltexam.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dr.beltexam.models.TvShow;
import com.dr.beltexam.repositories.TvShowRepo;

@Service
public class TvShowService {
	
	
	   @Autowired
	    private TvShowRepo tvRepo;
	    public TvShowService(TvShowRepo tvRepo) {
	    	this.tvRepo = tvRepo;
	    }
	    
	
		
		public List<TvShow> allTvShows(){
			return tvRepo.findAll();
		}
		
		
		public TvShow createTvShow(TvShow tv) {
			return tvRepo.save(tv);
		}
		
		public void deleteShow(long id) {
			tvRepo.deleteById(id);
		}
		
		
		
		public TvShow findShow(Long id) {
			Optional<TvShow> optionalShow = tvRepo.findById(id);
			if(optionalShow.isPresent()) {
				return optionalShow.get();
			}
			else {
				return null;
			}
		}
	
}
