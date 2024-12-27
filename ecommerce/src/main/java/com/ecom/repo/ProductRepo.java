package com.ecom.repo;




import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.ecom.entity.Product;
import com.ecom.entity.ProductCategory;
@RepositoryRestResource(collectionResourceRel="Product" ,path="product")
@CrossOrigin(origins="http://localhost:4200")
public interface ProductRepo  extends JpaRepository<Product,Long>{
	
	Page<Product> findByCategoryId(@Param("id")Long id,Pageable pageable);
	Page<Product> findByNameContaining(@Param("name")String name,Pageable pageable);
}
