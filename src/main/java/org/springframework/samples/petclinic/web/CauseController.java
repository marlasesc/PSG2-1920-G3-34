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

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cause;
import org.springframework.samples.petclinic.model.Donation;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CauseController {

	private final ClinicService clinicService;


	@ModelAttribute("cause")
	public Cause loadCause() {
		Cause cause = new Cause();
		return cause;
	}

	@Autowired
	public CauseController(final ClinicService clinicService) {
		this.clinicService = clinicService;
	}

	@GetMapping(value = "/causes/new")
	public String initNewCauseForm(final Map<String, Object> model) {
		return "causes/createOrUpdateCauseForm";
	}

	@PostMapping(value = "/causes/new")
	public String processNewCauseForm(@Valid final Cause cause, final BindingResult result) {
		if (result.hasErrors()) {
			return "causes/createOrUpdateCauseForm";
		} else {
			this.clinicService.saveCause(cause);
			return "redirect:/causes";
		}
	}

	@GetMapping(value = "/causes/{causeId}/edit")
	public String initUpdateOwnerForm(@PathVariable("causeId") final int causeId, final Model model) {
		Cause cause = this.clinicService.findCauseById(causeId);
		model.addAttribute(cause);
		return "causes/createOrUpdateCauseForm";
	}

	@PostMapping(value = "/causes/{causeId}/edit")
	public String processUpdateOwnerForm(@Valid final Cause cause, final BindingResult result, @PathVariable("causeId") final int causeId) {
		if (result.hasErrors()) {
			return "causes/createOrUpdateCauseForm";
		} else {
			cause.setId(causeId);
			this.clinicService.saveCause(cause);
			return "redirect:/causes/{causeId}";
		}
	}

	@GetMapping("/causes/{causeId}")
	public ModelAndView showCause(@PathVariable("causeId") final int causeId) {
		ModelAndView mav = new ModelAndView("causes/causeDetails");
		Integer sumDonations = this.clinicService.findDonationsByCauseId(causeId);
		mav.addObject("sumDonations", sumDonations);
		mav.addObject(this.clinicService.findCauseById(causeId));
		return mav;
	}

	@GetMapping(value = "/causes")
	public String showCauses(final Map<String, Object> model) {
		model.put("causes", this.clinicService.findAllCauses().stream().filter(x -> x.getBudget() > x.getSumDonations()).collect(Collectors.toList()));
		model.put("closed_causes", this.clinicService.findAllCauses().stream().filter(x -> x.getBudget() == x.getDonations().stream().mapToInt(y -> y.getMoneyAmount()).sum()).collect(Collectors.toList()));
		return "causes/causeList";
	}

	@GetMapping(value = "/causes/{causeId}/delete")
	public String processDeletion(@PathVariable("causeId") final int causeId, final ModelMap model) {
		this.clinicService.deleteCauseById(causeId);

		return "redirect:/causes";

	}

	@ModelAttribute("donations")
	public Collection<Donation> findAllDonations() {
		return this.clinicService.findAllDonations();
	}
}
