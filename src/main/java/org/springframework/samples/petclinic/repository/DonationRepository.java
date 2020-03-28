
package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Donation;

public interface DonationRepository {

	Collection<Donation> findAll() throws DataAccessException;

	void save(Donation donation) throws DataAccessException;

	Donation findById(int donationId) throws DataAccessException;

	Integer findSumDonationsByCauseId(int causeId) throws DataAccessException;

}
