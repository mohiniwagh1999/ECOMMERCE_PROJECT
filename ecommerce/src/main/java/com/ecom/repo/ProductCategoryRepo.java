package com.ecom.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.ecom.entity.ProductCategory;
@RepositoryRestResource(collectionResourceRel="ProductCategory" ,path="productCategory")
@CrossOrigin(origins="http://localhost:4200")
public interface ProductCategoryRepo extends JpaRepository<ProductCategory,Long> {

}
