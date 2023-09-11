package com.jsp.airLineProject.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Entity
public class Fare {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
private int fareId;
private String currency;
private double amount;
@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "fare")
private Flight flight;
}
