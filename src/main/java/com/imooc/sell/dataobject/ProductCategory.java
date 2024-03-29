package com.imooc.sell.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * 产品类目类
 */
@Data
@Entity
@DynamicUpdate  //时间戳更新
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)   //自增Id
    /** 自增产品类目ID*/
    private Integer categoryId;

    /** 自增产品类目名称*/
    private String categoryName;

    /** 自增产品类目类型*/
    private Integer categoryType;


    private Date createTime;


    private Date updateTime;

    public ProductCategory() {
    }

    public ProductCategory(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }
}
