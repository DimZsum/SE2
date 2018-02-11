package net.ziemers.swxercise.lg.user.service;

import java.util.Collection;

import javax.inject.Inject;

import net.ziemers.swxercise.db.dao.user.PostDao;
import net.ziemers.swxercise.lg.model.user.Post;
import net.ziemers.swxercise.lg.user.dto.PostDto;

public class PostService {
	@Inject
	PostDao dao;
    
	public Collection<Post> findAllPosts() {
        return dao.findAll(Post.class);
    }
	
    public Long createPost(final PostDto dto) {
        Post post = new Post(dto.getUser(), dto.getMessage());
        return dao.save(post);
    }
    
    public Post findPost(long id) {
    	return dao.findById(id);
    }

}
