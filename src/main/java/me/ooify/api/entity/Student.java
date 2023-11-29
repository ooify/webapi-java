package me.ooify.api.entity;

import java.math.BigDecimal;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

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
@ApiModel(value = "Student对象", description = "")
//@Component
//@Scope("prototype")
public class Student implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private Integer gender;

    private Integer age;

    private Double grade;

    private String major;


}
