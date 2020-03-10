package org.springframework.samples.petclinic.web;

import java.util.List;

import org.springframework.samples.petclinic.model.Booking;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class BookingValidator implements Validator {

	private static final String REQUIRED = "required";

	@Override
	public boolean supports(final Class<?> clazz) {
		return Booking.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(final Object target, final Errors errors) {
		Booking booking = (Booking) target;

		// startDate validation
		if (booking.getStartDate() == null) {
			errors.rejectValue("startDate", BookingValidator.REQUIRED, BookingValidator.REQUIRED);
		}

		// startDate must be after every other finishDate
		if (booking.getStartDate() != null && this.isDateOverlaped(booking, booking.getPet().getBookings())) {
			errors.rejectValue("startDate", "La fecha de inicio debe ser posterior o igual a la fecha de fin de la última reserva", "La fecha de inicio debe ser posterior o igual a la fecha de fin de la última reserva");
		}

		// finishDate validation
		if (booking.getFinishDate() == null) {
			errors.rejectValue("finishDate", BookingValidator.REQUIRED, BookingValidator.REQUIRED);
		}

		// finishDate must be after startDate
		if (booking.getStartDate() != null && booking.getFinishDate() != null && booking.getStartDate().isAfter(booking.getFinishDate())) {
			errors.rejectValue("finishDate", "La fecha de fin debe ser posterior o igual a la fecha de inicio", "La fecha de fin debe ser posterior o igual a la fecha de inicio");
		}
	}

	private boolean isDateOverlaped(final Booking booking, final List<Booking> bookings) {
		return bookings.stream().map(b -> b.getFinishDate()).filter(f -> f != null && !f.equals(booking.getFinishDate())).anyMatch(f -> booking.getStartDate().isBefore(f));
	}

}
