package net.ziemers.swxercise.lg.user.dto;

import javax.validation.constraints.NotNull;

/**
 * Das Data Transfer Object im Kontext der Rollen- und Rechteverwaltung. Es wird unter anderem auch aus einem
 * JSON-Objekt des {@link net.ziemers.swxercise.ui.RoleViewController}s gefüllt.
 */
public class FilmDto {	
	@NotNull
	private String name;
	private boolean verfuegbar;
	private int fsk;
	
    public FilmDto withFilmName(final String name) {
        this.name = name;
        return this;
    }
        
    public FilmDto withFilmVerfuegbar(final boolean verfuegbar) {
        this.verfuegbar = verfuegbar;
        return this;
    }
    
    public FilmDto withFilmFsk(final int fsk) {
        this.fsk = fsk;
        return this;
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
