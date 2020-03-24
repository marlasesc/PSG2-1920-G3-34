/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.samples.petclinic.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Booking;
import org.springframework.samples.petclinic.model.Cause;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.model.Specialty;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.model.Visit;
import org.springframework.samples.petclinic.repository.BookingRepository;
import org.springframework.samples.petclinic.repository.CauseRepository;
import org.springframework.samples.petclinic.repository.OwnerRepository;
import org.springframework.samples.petclinic.repository.PetRepository;
import org.springframework.samples.petclinic.repository.VetRepository;
import org.springframework.samples.petclinic.repository.VisitRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Mostly used as a facade for all Petclinic controllers Also a placeholder
 * for @Transactional and @Cacheable annotations
 *
 * @author Michael Isvy
 */
@Service
public class ClinicService {

	private PetRepository		petRepository;

	private VetRepository		vetRepository;

	private OwnerRepository		ownerRepository;

	private VisitRepository		visitRepository;

	private BookingRepository	bookingRepository;

	private CauseRepository		causeRepository;


	@Autowired
	public ClinicService(final PetRepository petRepository, final VetRepository vetRepository, final OwnerRepository ownerRepository, final VisitRepository visitRepository, final BookingRepository bookingRepository, final CauseRepository causeRepository) {
		this.petRepository = petRepository;
		this.vetRepository = vetRepository;
		this.ownerRepository = ownerRepository;
		this.visitRepository = visitRepository;
		this.bookingRepository = bookingRepository;
		this.causeRepository = causeRepository;

	}

	@Transactional(readOnly = true)
	public Collection<PetType> findPetTypes() throws DataAccessException {
		return this.petRepository.findPetTypes();
	}

	@Transactional(readOnly = true)
	public Owner findOwnerById(final int id) throws DataAccessException {
		return this.ownerRepository.findById(id);
	}

	@Transactional(readOnly = true)
	public Collection<Owner> findOwnerByLastName(final String lastName) throws DataAccessException {
		return this.ownerRepository.findByLastName(lastName);
	}

	@Transactional
	public void saveOwner(final Owner owner) throws DataAccessException {
		this.ownerRepository.save(owner);
	}

	@Transactional
	public void saveVet(final Vet vet) throws DataAccessException {
		this.vetRepository.save(vet);
	}

	@Transactional
	public void saveVisit(final Visit visit) throws DataAccessException {
		this.visitRepository.save(visit);
	}

	@Transactional
	public void saveBooking(final Booking booking) throws DataAccessException {
		this.bookingRepository.save(booking);
	}

	@Transactional
	public void deleteBookingById(final int bookingId) throws DataAccessException {
		this.bookingRepository.deleteById(bookingId);
	}

	@Transactional(readOnly = true)
	public Pet findPetById(final int id) throws DataAccessException {
		return this.petRepository.findById(id);
	}

	@Transactional
	public void savePet(final Pet pet) throws DataAccessException {
		this.petRepository.save(pet);
	}

	@Transactional
	public void deletePetById(final int id) throws DataAccessException {
		this.petRepository.deleteById(id);
	}

	@Transactional(readOnly = true)
	@Cacheable(value = "vets")
	public Collection<Vet> findVets() throws DataAccessException {
		return this.vetRepository.findAll();
	}

	public Collection<Visit> findVisitsByPetId(final int petId) {
		return this.visitRepository.findByPetId(petId);
	}

	@Transactional
	public void deleteVisitsByPetId(final int petId) throws DataAccessException {
		this.visitRepository.deleteAllByPetId(petId);
	}

	public Vet findVetById(final int vetId) throws DataAccessException {
		return this.vetRepository.findById(vetId);
	}

	public Collection<Specialty> findSpecialties() throws DataAccessException {
		return this.vetRepository.findSpecialties();
	}

	public void saveSpecialty(final Specialty a) {
		this.vetRepository.save(a);
	}

	public Specialty findSpecialtyById(final int id) {
		return this.vetRepository.findSpecialtyById(id);
	}

	@Transactional
	public void deleteVistitById(final int id) throws DataAccessException {
		this.visitRepository.deleteById(id);
	}

	@Transactional
	public void deleteOwnerById(final int id) throws DataAccessException {
		this.ownerRepository.deleteById(id);
	}

	@Transactional
	public void deletePetsByOwberId(final int ownerId) throws DataAccessException {
		this.petRepository.deleteAllByOwnerId(ownerId);
	}

	@Transactional
	public void deleteVetById(final int vetId) throws DataAccessException {
		this.vetRepository.deleteById(vetId);
	}

	@Transactional
	public void saveCause(final Cause cause) throws DataAccessException {
		this.causeRepository.save(cause);
	}

	@Transactional
	public Collection<Cause> findAllCauses() throws DataAccessException {
		return this.causeRepository.findAll();
	}
	@Transactional
	public void deleteCauseById(final int id) throws DataAccessException {
		this.causeRepository.deleteById(id);
	}

	public Cause findCauseById(int causeId) {
		return this.causeRepository.findById(causeId);
	}

}
