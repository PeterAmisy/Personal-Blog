package com.peter.amisy.personal_blog.service;

import com.peter.amisy.personal_blog.model.Article;
import com.peter.amisy.personal_blog.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }



    @Override
    public Article addArticle(Article article) {
        return articleRepository.save(article);
    }

    @Override
    public void updateArticle(Article article) {
        articleRepository.save(article);
    }

    @Override
    public void deleteArticle(int id) {
        articleRepository.delete(getArticle(id));
    }

    @Override
    public Article getArticle(int id) {
        return articleRepository.getReferenceById(id);
    }

    @Override
    public List<Article> getArticles() {
        return articleRepository.findAll();
    }
}
