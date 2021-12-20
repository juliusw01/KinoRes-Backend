package de.wi2020sebgruppe4.KinoTicketRes.model;

import java.util.UUID;

import javax.validation.constraints.NotNull;

public class TicketRequestObject {
	
	public UUID userID;
	public UUID seatID;
	public UUID showID;
	
	public TicketRequestObject(@NotNull UUID userID, @NotNull UUID seatID, @NotNull UUID priceID, @NotNull UUID showID) {
		this.userID = userID;
		this.seatID = seatID;
		this.showID = showID;
	}
	
}
