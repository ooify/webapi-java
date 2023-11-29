package me.ooify.api.controller;


import cn.dev33.satoken.util.SaResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import me.ooify.api.entity.Book;
import me.ooify.api.service.impl.BookServiceImpl;
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
@RequestMapping("/book")
public class BookController {
    @Resource
    private BookServiceImpl bookService;

    @GetMapping("/list")
    public SaResult getBooks(@RequestParam(value = "pageNum", required = false) Integer pageNum,
                             @RequestParam(value = "pageSize", required = false) Integer pageSize,
                             @RequestParam(value = "title", required = false) String title,
                             @RequestParam(value = "author", required = false) String author,
                             @RequestParam(value = "isbn", required = false) String isbn,
                             @RequestParam(value = "publishDate", required = false) Date publishDate) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setIsbn(isbn);
        book.setPublishDate(publishDate);

        if (pageNum == null || pageNum <= 0) {
            pageNum = 1; // 默认为第一页
        }
        if (pageSize == null || pageSize <= 0) {
            pageSize = 10; // 默认每页显示10条数据
        }
        Page<Book> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(book.getTitle())) {
            queryWrapper.like("title", book.getTitle());
        }
        if (StringUtils.isNotBlank(book.getAuthor())) {
            queryWrapper.like("author", book.getAuthor());
        }
        if (StringUtils.isNotBlank(book.getIsbn())) {
            queryWrapper.like("isbn", book.getIsbn());
        }
        if (book.getPublishDate() != null) {
            queryWrapper.eq("publish_date", book.getPublishDate());
        }

        return SaResult.ok("查询成功").setData(bookService.page(page, queryWrapper));
    }
    @GetMapping("/{id}")
    public SaResult getBookById(@RequestParam("id") Long id) {
        Book book = bookService.getById(id);
        return SaResult.ok("查询成功").setData(book);
    }
    @PostMapping
    public SaResult saveBook(@RequestBody Book book) {
        if (bookService.save(book)) {
            return SaResult.ok("新增成功");
        } else {
            return SaResult.error("新增失败");
        }
    }
    @PutMapping
    public SaResult updateBook(@RequestBody Book book) {
        if (bookService.updateById(book)) {
            return SaResult.ok("更新成功");
        } else {
            return SaResult.error("更新失败");
        }
    }
    @DeleteMapping("/{id}")
    public SaResult deleteBook(@PathVariable("id") Long id) {
        if (bookService.removeById(id)) {
            return SaResult.ok("删除成功");
        } else {
            return SaResult.error("删除失败");
        }
    }

}
