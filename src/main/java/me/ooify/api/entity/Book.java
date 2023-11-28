package me.ooify.api.entity;

import java.time.LocalDate;
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
@ApiModel(value = "Book对象", description = "")
public class Book implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String title;

    private String author;

    private String bookcover;

    private String isbn;

    private LocalDate publishDate;

    private LocalDateTime createAt;


}
