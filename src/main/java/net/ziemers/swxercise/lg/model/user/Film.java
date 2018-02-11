package net.ziemers.swxercise.lg.model.user;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;

import net.ziemers.swxercise.db.BaseEntity;

@Entity 
@NamedQueries({
    @NamedQuery(name = "Film.findById", query = "SELECT f FROM Film f WHERE f.id = :id"),
    @NamedQuery(name = "Film.findByName", query = "SELECT f FROM Film f WHERE lower(f.name) = lower(:name)")})

public class Film extends BaseEntity {
	@NotNull
	private String name;
	private boolean verfuegbar;
	private int fsk;
	
	public Film() {}
	
	public Film(String name){
		super();
		this.name=name;	
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isVerfuegbar() {
		return verfuegbar;
	}

	public void setVerfuegbar(boolean verfuegbar) {
		this.verfuegbar = verfuegbar;
	}

	public int getFsk() {
		return fsk;
	}

	public void setFsk(int fsk) {
		this.fsk = fsk;
	}
}