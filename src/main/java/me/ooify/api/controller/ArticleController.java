package me.ooify.api.controller;


import cn.dev33.satoken.util.SaResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import me.ooify.api.entity.Article;
import me.ooify.api.service.impl.ArticleServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author jonsan
 * @since 2023-11-27
 */
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Resource
    private ArticleServiceImpl articleService;

    @GetMapping("/list")
    public SaResult getArticles(@RequestParam(value = "pageNum", required = false) Integer pageNum,
                                @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                @RequestParam(value = "title", required = false) String title,
                                @RequestParam(value = "author", required = false) String author,
                                @RequestParam(value = "content", required = false) String content,
                                @RequestParam(value = "cover", required = false) String cover,
                                @RequestParam(value = "likes", required = false) Integer likes,
                                @RequestParam(value = "publishDate", required = false) Date publishDate) {
        Article article = new Article();
        article.setTitle(title);
        article.setAuthor(author);
        article.setContent(content);
        article.setCover(cover);
        article.setLikes(likes);
        article.setPublishDate(publishDate);

        if (pageNum == null || pageNum <= 0) {
            pageNum = 1; // 默认为第一页
        }
        if (pageSize == null || pageSize <= 0) {
            pageSize = 10; // 默认每页显示10条数据
        }
        Page<Article> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(article.getTitle())) {
            queryWrapper.like("title", article.getTitle());
        }
        if (StringUtils.isNotBlank(article.getAuthor())) {
            queryWrapper.like("author", article.getAuthor());
        }
        if (StringUtils.isNotBlank(article.getContent())) {
            queryWrapper.like("content", article.getContent());
        }
        if (StringUtils.isNotBlank(article.getCover())) {
            queryWrapper.like("cover", article.getCover());
        }
        if (article.getLikes() != null) {
            queryWrapper.eq("likes", article.getLikes());
        }
        if (article.getPublishDate() != null) {
            queryWrapper.eq("publish_date", article.getPublishDate());
        }


        return SaResult.ok("查询成功").setData(articleService.page(page, queryWrapper));
    }

    @GetMapping("/{id}")
    public SaResult getArticleById(@RequestParam("id") Long id) {
        Article article = articleService.getById(id);
        return SaResult.ok("查询成功").setData(article);
    }

    @PostMapping
    public SaResult saveArticle(@RequestBody Article article) {
        if (articleService.save(article)) {
            return SaResult.ok("新增成功");
        } else {
            return SaResult.error("新增失败");
        }
    }

    @PutMapping
    public SaResult updateArticle(@RequestBody Article article) {
        if (articleService.updateById(article)) {
            return SaResult.ok("更新成功");
        } else {
            return SaResult.error("更新失败");
        }
    }

    @DeleteMapping("/{id}")
    public SaResult deleteArticle(@PathVariable("id") Long id) {
        if (articleService.removeById(id)) {
            return SaResult.ok("删除成功");
        } else {
            return SaResult.error("删除失败");
        }
    }


}
