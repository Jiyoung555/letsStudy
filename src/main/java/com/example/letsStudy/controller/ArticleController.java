package com.example.letsStudy.controller;
import com.example.letsStudy.dto.ArticleForm;
import com.example.letsStudy.entity.Article;
import com.example.letsStudy.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Controller
public class ArticleController {
    private final ArticleRepository articleRepository;

    @GetMapping("/articles")
    public String index(Model model){
        Iterable<Article> articles = articleRepository.findAll();
        model.addAttribute("articles", articles);

        model.addAttribute("msg", "안녕하세요");
        return "articles/index";
    }

    @GetMapping("/articles/new")
    public String newArticle(){
        return "articles/new";
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model){
        Article article = articleRepository.findById(id).orElse(null);
        model.addAttribute("article", article);

        return "articles/show";
    }

    @GetMapping("/articles/init")
    public String init(){
        List<Article> articleList = new ArrayList();

        for(int i = 0; i < 3; i++){
            Article article = new Article(null, "title" + i, "content" + i);
            articleList.add(article);
        }

        articleRepository.saveAll(articleList);

        return "redirect:/articles";
    }

    @GetMapping("/articles/edit/{id}")
    public String edit(@PathVariable Long id, Model model){
        Article target = articleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 Article이 없습니다.")
        );

        model.addAttribute("article", target);
        return "articles/edit";
    }

    //@PostMapping("/articles")
    //public String create(ArticleForm form){
    //    log.info(form.toString());
    //    return "redirect:/articles";
    //}

}
