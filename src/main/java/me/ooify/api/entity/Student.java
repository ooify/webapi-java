package me.ooify.api.entity;

import java.io.Serializable;

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

public class Student implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private Integer gender;

    private Integer age;

    private Double grade;

    private String major;


}
