package de.wi2020sebgruppe4.KinoTicketRes.model;

import java.sql.Date;

public class ReviewRequestObject {
	
	public String titel;
	public String content;
	public User user;
	public Movie movie;
	public int stars;
	public java.sql.Date date;
	
	public ReviewRequestObject(String titel, String content, User user, Movie movie, int stars, Date date) {
		super();
		this.titel = titel;
		this.content = content;
		this.user = user;
		this.movie = movie;
		this.stars = stars;
		this.date = date;
	}
}
