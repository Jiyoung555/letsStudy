package com.example.letsStudy.service;
import com.example.letsStudy.dto.ArticleForm;
import com.example.letsStudy.entity.Article;
import com.example.letsStudy.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@RequiredArgsConstructor
@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    public Article update(Long id, ArticleForm form){
        log.info("form: " + form.toString());

        Article target = articleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 Article이 없습니다.")
        );

        log.info("target: " + target.toString());

        target.rewrite(form.getTitle(), form.getContent());
        Article saved = articleRepository.save(target);

        log.info("saved: " + saved.toString());

        return saved;
    }

}
