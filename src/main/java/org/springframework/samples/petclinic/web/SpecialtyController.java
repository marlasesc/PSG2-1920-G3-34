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


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Specialty;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Juergen Hoeller
 * @author Ken Krebs
 * @author Arjen Poutsma
 */
@Controller
@RequestMapping("/vets/{vetId}")
public class SpecialtyController {

	private static final String	VIEWS_SPECIALTIES_CREATE_OR_UPDATE_FORM	= "specialties/createOrUpdateSpecialtyForm";

	private final ClinicService	clinicService;


	@Autowired
	public SpecialtyController(final ClinicService clinicService) {
		this.clinicService = clinicService;
	}

	@ModelAttribute("specialties")
	public Collection<Specialty> populateSpecialties() {
		return this.clinicService.findSpecialties();
	}

	@ModelAttribute("vet")
	public Vet findVet(@PathVariable("vetId") final int vetId) {
		return this.clinicService.findVetById(vetId);
	}
	
	@GetMapping(value = "/specialties/new")
	public String initCreationForm(final Vet vet, final ModelMap model) {
		model.put("specialty", new Specialty());
		return SpecialtyController.VIEWS_SPECIALTIES_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/specialties/new")
	public String processCreationForm(final Vet vet, @Valid Specialty specialtyName, final BindingResult result, final ModelMap model) {
		if (result.hasErrors()) {
			model.put("specialty", specialtyName);
			return SpecialtyController.VIEWS_SPECIALTIES_CREATE_OR_UPDATE_FORM;
		} else {
			Specialty specialty = clinicService.findSpecialties().stream().filter(s -> s.getName().equals(specialtyName.getName())).findFirst().orElse(null);
			if(specialty!=null) {
				vet.addSpecialty(specialty);
				clinicService.saveVet(vet);
			}
			return "redirect:/vets/{vetId}";
		}
	}

	@GetMapping(value = "/specialties/{specialtyId}/edit")
	public String initUpdateForm(@PathVariable("specialtyId") int id, final int specialtyId, final ModelMap model) {
		Specialty specialty = this.clinicService.findSpecialtyById(specialtyId);
		model.put("specialty", specialty);
		return SpecialtyController.VIEWS_SPECIALTIES_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/specialties/{specialtyId}/edit")
	public String processUpdateForm(@PathVariable("specialtyId")int specialtyId, @Valid final Specialty specialty, final BindingResult result, final Vet vet, final ModelMap model) {
		if (result.hasErrors()) {
			model.put("specialty", specialty);
			return SpecialtyController.VIEWS_SPECIALTIES_CREATE_OR_UPDATE_FORM;
		} else {
			Specialty s = this.clinicService.findSpecialtyById(specialtyId);
			vet.addSpecialty(s); 
			clinicService.saveVet(vet);
			return "redirect:/vets/{vetId}";
		}
	}

}
