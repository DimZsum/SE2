package net.ziemers.swxercise.db.dao.user;

import javax.ejb.Stateless;

import net.ziemers.swxercise.db.dao.GenericDao;
import net.ziemers.swxercise.lg.model.user.Film;

@Stateless
public class FilmDao extends GenericDao{

   
    public Film findById(final Long id) {
        Film film = null;

        try {
            // ermittelt den ersten Datensatz mit der gesuchten Id, auch wenn
            // er sich nicht im Persistence Context befindet
            film = (Film) entityManager.createNamedQuery("Film.findById")
                    .setParameter("id", id).getSingleResult();
        } catch (Exception e) {
            /* nix */
        }
        return film;
    }
    
    public Film findByName(final String name) {
        Film film = null;

        try {
            
            film = (Film) entityManager.createNamedQuery("Film.findByName")
                    .setParameter("name", name).getSingleResult();
        } catch (Exception e) {
            /* nix */
        }
        return film;
    }
}
