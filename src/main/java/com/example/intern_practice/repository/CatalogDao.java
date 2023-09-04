package com.example.intern_practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.intern_practice.entity.Catalog;

@Repository
public interface CatalogDao extends JpaRepository<Catalog, Integer>{

}
