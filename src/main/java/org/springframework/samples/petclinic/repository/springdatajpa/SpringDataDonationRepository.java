
package org.springframework.samples.petclinic.repository.springdatajpa;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.samples.petclinic.model.Donation;
import org.springframework.samples.petclinic.repository.DonationRepository;

public interface SpringDataDonationRepository extends DonationRepository, Repository<Donation, Integer> {

	@Override
	@Query("SELECT SUM(d.moneyAmount) FROM Donation d WHERE d.cause.id = ?1")
	Integer findSumDonationsByCauseId(int causeId) throws DataAccessException;

}
