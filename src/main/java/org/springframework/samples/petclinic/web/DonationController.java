
package org.springframework.samples.petclinic.web;

import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cause;
import org.springframework.samples.petclinic.model.Donation;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DonationController {

	private ClinicService clinicService;


	@Autowired
	public DonationController(final ClinicService clinicService) {
		this.clinicService = clinicService;
	}

	@InitBinder("donation")
	public void initPetBinder(final WebDataBinder dataBinder) {
		dataBinder.setValidator(new DonationValidator());
	}

	@ModelAttribute("donation")
	public Donation loadDonation(@PathVariable("causeId") final int causeId) {
		Donation donation = new Donation();
		donation.setCause(this.clinicService.findCauseById(causeId));
		return donation;
	}

	@GetMapping(value = "/causes/{causeId}/donations/new")
	public String initCreationForm(@PathVariable("causeId") final int causeId, final ModelMap model) {
		Cause cause = this.clinicService.findCauseById(causeId);
		cause.setId(causeId);
		model.put("cause", cause);
		return "causes/createDonationForm";
	}

	@PostMapping(value = "/causes/{causeId}/donations/new")
	public String processCreationForm(@Valid final Donation donation, final BindingResult result, @PathVariable("causeId") final int causeId, final Cause cause, final ModelMap model) {
		cause.setId(causeId);
		if (result.hasErrors()) {
			model.put("donation", donation);
			return "causes/createDonationForm";
		} else {
			donation.setDateDonation(LocalDate.now());
			donation.setCause(cause);
			cause.addDonation(donation);
			this.clinicService.saveDonation(donation);
			return "redirect:/causes/{causeId}";
		}
	}

}
