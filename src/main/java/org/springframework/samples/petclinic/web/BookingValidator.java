package org.springframework.samples.petclinic.web;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
		if (booking.getStartDate() != null && !this.isDateNotOverlaped(booking, booking.getPet().getBookings())) {
			errors.rejectValue("startDate", "El intervalo de la nueva reserva no deber solapar el intervalo de ninguna otra reserva", "El intervalo de la nueva reserva no deber solapar el intervalo de ninguna otra reserva");
			errors.rejectValue("finishDate", "El intervalo de la nueva reserva no deber solapar el intervalo de ninguna otra reserva", "El intervalo de la nueva reserva no deber solapar el intervalo de ninguna otra reserva");
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

	private boolean isDateNotOverlaped(final Booking booking, final List<Booking> bookings) {
		List<Booking> oldBookings = bookings.stream()
			.filter(b -> b.getFinishDate() != null && b.getStartDate() != null)
			.collect(Collectors.toList());

		return IntStream.range(0, oldBookings.size() - 1)
			.boxed()
			.allMatch(i -> !oldBookings.get(i).getStartDate().isBefore(oldBookings.get(i+1).getFinishDate()));
	}

}
