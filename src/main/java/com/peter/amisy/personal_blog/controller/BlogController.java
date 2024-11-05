package com.peter.amisy.personal_blog.controller;

import com.peter.amisy.personal_blog.model.Article;
import com.peter.amisy.personal_blog.service.ArticleServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BlogController {

    private final ArticleServiceImpl articleService;

    public BlogController(ArticleServiceImpl articleService) {
        this.articleService = articleService;
    }

    // Display home page
    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("articles", articleService.getArticles());
        return "home";
    }

    // Display article
    @GetMapping("/article/{id}")
    public String article(Model model, @PathVariable int id) {
        model.addAttribute("article", articleService.getArticle(id));
        return "article";
    }

    // Display admin section
    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("articles", articleService.getArticles());
        return "admin";
    }

    // Add new article
    @PostMapping("/admin/new")
    public Article newArticle(Article article) {
        return articleService.addArticle(article);
    }

    // Edit article
    @PatchMapping("/admin/edit/{id}")
    public ResponseEntity<String> editArticle(@PathVariable int id, @RequestBody Article article) {
        Article existingArticle = articleService.getArticle(id);
        if (existingArticle == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Article not found");
        }
        existingArticle.setTitle(article.getTitle());
        existingArticle.setDate(article.getDate());
        existingArticle.setContent(article.getContent());
        articleService.updateArticle(existingArticle);
        return ResponseEntity.ok("Article updated successfully");
    }

    // Delete article
    @DeleteMapping("/admin/delete/{id}")
    public void deleteArticle(@PathVariable int id) {
        articleService.deleteArticle(id);
    }

    // Display edit article page
    @GetMapping("edit/{id}")
    public String edit(Model model, @PathVariable int id) {
        model.addAttribute("article", articleService.getArticle(id));
        return "edit";
    }

    // Display new article page
    @GetMapping("/new")
    public String newArticle(Model model) {
        model.addAttribute("article", articleService.addArticle(new Article()));
        return "new";
    }
}