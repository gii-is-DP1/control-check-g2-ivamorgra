package org.springframework.samples.petclinic.feeding;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.pet.Pet;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Feeding {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
	
	@Column(name = "start_date")        
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@NotNull
    LocalDate startDate;
	
	@Column(name = "weeks_duration") 
	@Min(value = 1)
    double weeksDuration;
	
	@ManyToOne
	@NotNull
    Pet pet;   
	
	@ManyToOne
	FeedingType feedingType;
}
