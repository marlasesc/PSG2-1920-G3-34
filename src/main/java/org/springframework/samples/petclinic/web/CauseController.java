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

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cause;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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

	@GetMapping(value = "/causes")
	public String showVisits(final Map<String, Object> model) {
		model.put("causes", this.clinicService.findAllCauses());
		return "causes/causeList";
	}

}
