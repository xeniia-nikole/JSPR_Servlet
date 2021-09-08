package ru.netology.model;

public class Post {
  private int id;
  private String content;

  public Post() {
  }

  public Post(int id, String content) {
    this.id = id;
    this.content = content;
  }

  public int getID() {
    return id;
  }

  public void setID(int id) {
    this.id = id;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }
}
