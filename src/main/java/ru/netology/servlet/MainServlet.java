package ru.netology.servlet;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.netology.controller.PostController;
//import ru.netology.repository.PostRepository;
//import ru.netology.service.PostService;
//
//import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainServlet extends HttpServlet {
  private PostController controller;
  final private static String API = "/api/posts";
  final private static String API_D = "/api/posts/\\d+";

  @Override
  public void init() {

    final var context = new AnnotationConfigApplicationContext("ru.netology");

    final var controller = context.getBean(PostController.class);
    final var repository = context.getBean("postRepository");
    final var service = context.getBean("postService");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
    try {
      final var path = req.getRequestURI();
      final var id = Long.parseLong(path.substring(path.lastIndexOf("/")));

      if (path.equals(API)) {
        controller.all(resp);
        return;
      }
      if (path.matches(API_D)) {
        controller.getById(id, resp);
        return;
      }
      resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
    } catch (Exception e) {
      e.printStackTrace();
      resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
    try {
      final var path = req.getRequestURI();

      if (path.equals(API)) {
        controller.save(req.getReader(), resp);
        return;
      }
      resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
    } catch (Exception e) {
      e.printStackTrace();
      resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
  }


  @Override
  public void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try {
      final var path = req.getRequestURI();
      final var id = Long.parseLong(path.substring(path.lastIndexOf("/")));

      if (path.matches(API_D)) {
        controller.removeById(id, resp);
        return;
      }
      resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
    } catch (Exception e) {
      e.printStackTrace();
      resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
  }
}