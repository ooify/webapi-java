package me.ooify.api.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@ApiModel(value = "Article对象", description = "")
public class Article implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String title;

    private String author;

    private String content;

    private String cover;

    private Integer likes;

    private LocalDateTime publishDate;


}
