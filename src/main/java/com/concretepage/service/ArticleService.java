package com.concretepage.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.concretepage.entity.Article;
import com.concretepage.repository.ArticleRepository;

@Service
public class ArticleService implements IArticleService {
	@Autowired
	private ArticleRepository articleRepository;

	
	public Article getArticleById(long articleId) {
		Article obj = articleRepository.findByArticleId(articleId);
		return obj;
	}	
	public List<Article> getAllArticles(){
		List<Article> list = new ArrayList<>();
		articleRepository.findAll().forEach(e -> list.add(e));
		return list;
	}
	public synchronized boolean addArticle(Article article){
	        List<Article> list = articleRepository.findByTitleAndCategory(article.getTitle(), article.getCategory()); 	
                if (list.size() > 0) {
    	           return false;
                } else {
    	           article = articleRepository.save(article);
    	           return true;
                }
	}
	public void updateArticle(Article article) {
		articleRepository.save(article);
	}
	public void deleteArticle(Article article) {
		articleRepository.delete(article);
	}
} 