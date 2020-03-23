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

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;

@MappedSuperclass
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
	@NotEmpty
	protected Integer	budget;

	@Column(name = "organization")
	@NotEmpty
	protected String	organization;


	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public Integer getBudget() {
		return this.budget;
	}

	public void setBudget(final Integer budget) {
		this.budget = budget;
	}

	public String getOrganization() {
		return this.organization;
	}

	public void setOrganization(final String organization) {
		this.organization = organization;
	}

}
