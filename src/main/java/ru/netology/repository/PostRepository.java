package ru.netology.repository;;

import ru.netology.model.Post;


import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

// Stub
public class PostRepository {

  final private List<Post> listPost = new CopyOnWriteArrayList<>();
  private long ID = 0;

  public List<Post> all() {
    for (Post post : listPost) {
      System.out.println("ID: " + post.getId() + ", CurrentContent:" + post.getContent());
    }
    return listPost;
  }


  public Optional<Post> getById(long id) {
    Post postCurrent = null;
    for (Post post : listPost) {
      if (post.getId() == id) {
        postCurrent = post;
      } else {
        System.out.println("Нет такого ID!");
      }
    }
    return Optional.ofNullable(postCurrent);
  }

  public Post save(Post post) {
    if (post.getId() == 0) {
      ID++;
      post.setId(ID);
    } else {
      for (Post currentPost : listPost) {
        if (currentPost.getId() == post.getId()) {
          currentPost.setContent(post.getContent());
        } else {
          ID++;
          System.out.println("Постов с таким ID не существует, новый ID: " + ID);
          post.setId(ID);
        }
      }
    }
    listPost.add(post);
    return post;
  }

  public void removeById ( long id){
    for (Post post : listPost) {
      if (post.getId() == id) {
        listPost.remove(post);
      } else {
        System.out.println("Нет такого ID!");
      }
    }
  }
}