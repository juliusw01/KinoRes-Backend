package de.wi2020sebgruppe4.KinoTicketRes.model;

import java.util.UUID;

import javax.validation.constraints.NotNull;

public class TicketRequestObject {
	
	public UUID userID;
	public UUID seatID;
	public UUID showID;
	public int paymentMethod;
	
	public TicketRequestObject(@NotNull UUID userID, @NotNull UUID seatID, @NotNull UUID priceID, @NotNull UUID showID, @NotNull int paymentMethod) {
		this.userID = userID;
		this.seatID = seatID;
		this.showID = showID;
		this.paymentMethod = paymentMethod;
	}
	
}
