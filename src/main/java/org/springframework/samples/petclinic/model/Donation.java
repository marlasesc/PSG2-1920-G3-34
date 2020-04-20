
package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "donations")
public class Donation extends BaseEntity {

	@Column(name = "name")
	@NotEmpty
	private String		name;

	@Column(name = "description")
	private String		description;

	@Column(name = "money_amount")
	@NotNull
	private Integer		moneyAmount;

	@Column(name = "date_donation")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate	dateDonation;

	@ManyToOne
	@JoinColumn(name = "cause_id")
	private Cause		cause;


	public Cause getCause() {
		return this.cause;
	}

	public void setCause(final Cause cause) {
		this.cause = cause;
	}

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

	public Integer getMoneyAmount() {
		return this.moneyAmount;
	}

	public void setMoneyAmount(final Integer moneyAmount) {
		this.moneyAmount = moneyAmount;
	}

	public LocalDate getDateDonation() {
		return this.dateDonation;
	}

	public void setDateDonation(final LocalDate dateDonation) {
		this.dateDonation = dateDonation;
	}

}
