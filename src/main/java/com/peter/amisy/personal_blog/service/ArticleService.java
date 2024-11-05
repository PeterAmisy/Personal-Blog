package com.peter.amisy.personal_blog.service;

import com.peter.amisy.personal_blog.model.Article;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ArticleService {

     Article addArticle(Article article);

     void updateArticle(Article article);

     void deleteArticle(int id);

     Article getArticle(int id);

     List<Article> getArticles();
}
