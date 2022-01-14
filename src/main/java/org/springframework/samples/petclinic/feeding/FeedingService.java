package org.springframework.samples.petclinic.feeding;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.pet.PetRepository;
import org.springframework.samples.petclinic.pet.VisitRepository;
import org.springframework.stereotype.Service;

@Service
public class FeedingService {
	
	private FeedingRepository feedingRepository;
	
	@Autowired
	public FeedingService(FeedingRepository feedingRepository) {
		this.feedingRepository = feedingRepository;
	}
	
	@Transactional(readOnly = true)
    public List<Feeding> getAll(){
        return this.feedingRepository.findAll();
    }
	
	
    public List<FeedingType> getAllFeedingTypes(){
        return null;
    }
    
    @Transactional(readOnly = true)
    public FeedingType getFeedingType(String typeName) {
        return this.feedingRepository.getFeedingType(typeName);
    }

    public Feeding save(Feeding p) throws UnfeasibleFeedingException {
        return null;       
    }

    
}
