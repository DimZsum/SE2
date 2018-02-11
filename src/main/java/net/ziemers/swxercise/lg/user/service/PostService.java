package net.ziemers.swxercise.lg.user.service;

import javax.inject.Inject;

import net.ziemers.swxercise.db.dao.user.PostDao;
import net.ziemers.swxercise.lg.model.user.Post;
import net.ziemers.swxercise.lg.user.dto.PostDto;

public class PostService {
	@Inject
    private PostDao dao;

    //film erstellen und suchen
    public Long createPost(final PostDto dto) {
        Post post = new Post(dto.getNachricht(),dto.getUser());
        return dao.save(post);
    }

    public Post findPostById(long id) {
        return dao.findById(id);
    }

}