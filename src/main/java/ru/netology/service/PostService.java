package ru.netology.service;

import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;
import ru.netology.repository.PostRepository;

import java.util.List;
import java.util.Map;
//import java.util.Map;

public class PostService {
  private final PostRepository repository;

  public PostService(PostRepository repository) {
    this.repository = repository;
  }

  public Map<Integer, Post> all() {
    return repository.all();
  }

  public Post getById(int id) {
    return repository.getById(id).orElseThrow(NotFoundException::new);
  }

  public Post save(Post post) {
    return repository.save(post);
  }

  public void removeById(int id) {
    repository.removeById(id);
  }
}
