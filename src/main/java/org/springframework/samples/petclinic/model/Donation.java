
package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "donations")
public class Donation extends NamedEntity {

	@Column(name = "description")
	private String	description;

	@Column(name = "money_amount")
	@NotNull
	private Double	moneyAmount;


	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public Double getMoneyAmount() {
		return this.moneyAmount;
	}

	public void setMoneyAmount(final Double moneyAmount) {
		this.moneyAmount = moneyAmount;
	}

}
