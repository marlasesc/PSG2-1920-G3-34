package org.springframework.samples.petclinic.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Booking;

public interface BookingRepository {

	void save(Booking booking) throws DataAccessException;

	void delete(Booking booking) throws DataAccessException;

	void deleteById(Integer id) throws DataAccessException;

}
