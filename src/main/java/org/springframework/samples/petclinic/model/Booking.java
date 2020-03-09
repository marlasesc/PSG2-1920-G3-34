package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "books")
public class Booking extends BaseEntity {

	@Column(name = "start_date")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate startDate;

	@Column(name = "finish_date")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate finishDate;

	@ManyToOne
	@JoinColumn(name = "pet_id")
	private Pet pet;


	public LocalDate getStartDate() {
		return this.startDate;
	}


	public void setStartDate(final LocalDate startDate) {
		this.startDate = startDate;
	}


	public LocalDate getFinishDate() {
		return this.finishDate;
	}


	public void setFinishDate(final LocalDate finishDate) {
		this.finishDate = finishDate;
	}


	public Pet getPet() {
		return this.pet;
	}


	public void setPet(final Pet pet) {
		this.pet = pet;
	}



}
