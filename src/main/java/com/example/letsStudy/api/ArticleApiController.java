package com.example.letsStudy.api;
import com.example.letsStudy.dto.ArticleForm;
import com.example.letsStudy.entity.Article;
import com.example.letsStudy.repository.ArticleRepository;
import com.example.letsStudy.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.net.www.MimeTable;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@RestController
public class ArticleApiController {

    private final ArticleService articleService;

    @Autowired
    private ArticleRepository articleRepository;

    @PostMapping("/api/articles")
    public Long create(@RequestBody ArticleForm form){
        log.info(form.toString());

        Article article = form.toEntity();

        Article saved = articleRepository.save(article);
        log.info(saved.toString());

        return saved.getId();
    }

    @GetMapping("/api/article/{id}")
    public ArticleForm getArticle(@PathVariable Long id){
        Article entity = articleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 Article이 없습니다.")
        );

        ArticleForm dto = new ArticleForm(entity);
        return dto;
    }

    @GetMapping("/api/articles")
    public List<ArticleForm> getArticles(){
        Iterable<Article> entities = articleRepository.findAll();

        List<ArticleForm> articleFormList = new ArrayList();

        for(Article article : entities){
            ArticleForm dto = new ArticleForm(article);
            articleFormList.add(dto);
        }

        return articleFormList;
    }

    @PutMapping("api/articles/{id}")
    public Long update(@PathVariable Long id, @RequestBody ArticleForm form){
        log.info("form: " + form.toString());

        Article saved = articleService.update(id, form);

        return saved.getId();
    }



}
