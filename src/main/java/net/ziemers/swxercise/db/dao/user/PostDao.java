package net.ziemers.swxercise.db.dao.user;

import javax.ejb.Stateless;

import net.ziemers.swxercise.db.dao.GenericDao;
import net.ziemers.swxercise.lg.model.user.Post;

@Stateless
public class PostDao extends GenericDao {
    public Post findById(final Long id) {
        Post post = null;

        try {
            // ermittelt den ersten Datensatz mit der gesuchten Id, auch wenn
            // er sich nicht im Persistence Context befindet
            post = (Post) entityManager.createNamedQuery("Post.findById")
                    .setParameter("id", id).getSingleResult();
        } catch (Exception e) {
            /* nix */
        }
        return post;
    }
}
