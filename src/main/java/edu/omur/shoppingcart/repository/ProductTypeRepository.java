package edu.omur.shoppingcart.repository;

import edu.omur.shoppingcart.entity.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductTypeRepository extends JpaRepository<ProductType, String> {
}