package com.inncontrol.inventory.infrastructure.persistence.jpa.repositories;

import com.inncontrol.inventory.domain.model.aggregates.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Inventory, Long>{
    List<Inventory> findAllByBrand(String brand);
    boolean existsByProductTitle(String itemName);
}
