package net.ziemers.swxercise.lg.user.service;

import javax.inject.Inject;

import net.ziemers.swxercise.db.dao.user.FilmDao;
import net.ziemers.swxercise.lg.model.user.Film;
import net.ziemers.swxercise.lg.user.dto.FilmDto;

public class FilmService {
	@Inject
    private FilmDao dao;

    //film erstellen und suchen
    public Long createFilm(final FilmDto dto) {
        Film film = dao.findByName(dto.getName());
        if (film == null) {
            film = new Film(dto.getName());

            return dao.save(film);
        }
        return null;
    }
    
    public Film findFilm(String name) {
        return dao.findByName(name);
    }

    public Film findFilmById(long id) {
        return dao.findById(id);
    }

}

	
