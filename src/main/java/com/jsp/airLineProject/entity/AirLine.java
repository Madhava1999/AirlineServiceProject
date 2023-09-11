package com.jsp.airLineProject.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
@Entity
@ToString
@Builder

public class AirLine {
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
	private int airLineId;
private String airName;
@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "airLine")
private List<FlightInfo> flightInfo;
}
