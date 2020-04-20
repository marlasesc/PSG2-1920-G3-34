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

package org.springframework.samples.petclinic.web;

import org.springframework.samples.petclinic.model.Cause;
import org.springframework.samples.petclinic.model.Donation;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * <code>Validator</code> for <code>Pet</code> forms.
 * <p>
 * We're not using Bean Validation annotations here because it is easier to define such
 * validation rule in Java.
 * </p>
 *
 * @author Ken Krebs
 * @author Juergen Hoeller
 */
public class DonationValidator implements Validator {

	@Override
	public void validate(final Object obj, final Errors errors) {
		Donation donation = (Donation) obj;
		Cause cause = donation.getCause();
		Integer budget = cause.getBudget();
		Integer sumDonations = cause.getSumDonations();
		Integer cantidadRestante = budget - sumDonations;
		// name validation

		if (sumDonations + donation.getMoneyAmount() > budget) {
			errors.rejectValue("moneyAmount", "invalid", "No se puede donar si supera el objetivo (falta " + cantidadRestante.toString() + " para alcanzar el objetivo)");
		}

		if (donation.getMoneyAmount() == 0) {
			errors.rejectValue("moneyAmount", "zero", "Se debe realizar una donación superior a 0 euros");
		}
	}

	@Override
	public boolean supports(final Class<?> clazz) {
		return Donation.class.isAssignableFrom(clazz);
	}

}
