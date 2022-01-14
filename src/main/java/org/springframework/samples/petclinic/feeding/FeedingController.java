package org.springframework.samples.petclinic.feeding;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.owner.Owner;
import org.springframework.samples.petclinic.owner.OwnerService;
import org.springframework.samples.petclinic.pet.Pet;
import org.springframework.samples.petclinic.pet.PetService;
import org.springframework.samples.petclinic.pet.exceptions.DuplicatedPetNameException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FeedingController {
    
	private static final String VIEWS_FEEDING_CREATE_OR_UPDATE_FORM = "feedings/createOrUpdateFeedingForm";
	private final FeedingService feedingService;
	private final PetService petService;
	
	@Autowired
	public FeedingController( FeedingService feedingService,PetService petService) {
		this.feedingService = feedingService;
		this.petService =  petService;
	}
	
	@GetMapping(value = "/feeding/create")
	public String initCreationForm(ModelMap model) {
		Feeding feeding = new Feeding();
		List<Pet> pets = this.petService.getAllPets();
		List<FeedingType> feedingtypes = feedingService.getAllFeedingTypes();
		model.put("feeding", feeding);
		model.put("pets", pets);
		model.put("feedingTypes", feedingtypes);
		return VIEWS_FEEDING_CREATE_OR_UPDATE_FORM;
	}
	
	
	@PostMapping(value = "/feeding/create")
	public String processCreationForm(Pet pet, FeedingType feedingType, @Valid Feeding feeding, BindingResult result, ModelMap model) {		
		if (result.hasErrors()) {
			model.put("feeding", feeding);
			return VIEWS_FEEDING_CREATE_OR_UPDATE_FORM;
		}
		else {
                try {
					this.feedingService.save(feeding);
				} catch (UnfeasibleFeedingException e) {
					e.printStackTrace();
				}  
                return VIEWS_FEEDING_CREATE_OR_UPDATE_FORM;
		}
	}
}
