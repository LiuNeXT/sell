package com.imooc.sell.repository;

import com.imooc.sell.dataobject.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductInfoRepository  extends JpaRepository<ProductInfo,String> {

    List<ProductInfo> findByProductStatus(Integer ProductStatusId);

    @Override
    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo findByProductId(String productId);
}
