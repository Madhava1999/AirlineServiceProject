package com.jsp.airLineProject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jsp.airLineProject.entity.Inventory;
@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer>{

}
