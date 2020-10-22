package com.applaudostudios.demo.repositories;

import com.applaudostudios.demo.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>, PagingAndSortingRepository<Item, Long> {
    boolean existsById(Long id);
}
