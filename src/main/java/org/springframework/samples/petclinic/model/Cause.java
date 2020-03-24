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

package org.springframework.samples.petclinic.model;

import java.util.ArrayList;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;

@Entity
@Table(name = "causes")
public class Cause extends BaseEntity {

	// As a client, we can create a cause that contains a name and description (string),
	//a budget target (numeric), and an active non profit organization (string) that will use the budget for the cause.
	@Column(name = "name")
	@NotEmpty
	protected String	name;

	@Column(name = "description")
	@NotEmpty
	protected String	description;

	@Column(name = "budget")
	protected Integer	budget;
	
//	@Column(name = "total_donations")
//	protected Integer	totalDonations;

	@Column(name = "organization")
	@NotEmpty
	protected String	organization;
	
//	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cause")
//	private Set<Donation>	donations;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getBudget() {
		return budget;
	}

	public void setBudget(Integer budget) {
		this.budget = budget;
	}

//	public Integer getTotalDonations() {
//		return totalDonations;
//	}
//
//	public void setTotalDonations(Integer totalDonations) {
//		this.totalDonations = totalDonations;
//	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}
	
//	protected Set<Donation> getDonationsInternal() {
//		if (this.donations == null) {
//			this.donations = new HashSet<>();
//		}
//		return this.donations;
//	}
//
//	protected void setDonationsInternal(Set<Donation> donations) {
//		this.donations = donations;
//	}
//
//	public List<Donation> getDonations() {
//		List<Donation> sortedDonations = new ArrayList<>(getDonationsInternal());
//		PropertyComparator.sort(sortedDonations, new MutableSortDefinition("name", true, true));
//		return Collections.unmodifiableList(sortedDonations);
//	}
//
//	public void addDonation(Donation donation) {
//		getDonationsInternal().add(donation);
//		donation.setCause(this);
//	}
//	
//	@Override
//	public String toString() {
//		return "Cause [name=" + name + ", description=" + description + ", budget=" + budget + ", totalDonations="
//				+ totalDonations + ", organization=" + organization + ", donations=" + donations + "]";
//	}



}
