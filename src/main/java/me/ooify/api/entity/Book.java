package me.ooify.api.entity;


import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * <p>
 *
 * </p>
 *
 * @author jonsan
 * @since 2023-11-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Book implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String title;

    private String author;

    private String bookcover;

    private String isbn;

    private Date publishDate;

    private Date createAt;


}
