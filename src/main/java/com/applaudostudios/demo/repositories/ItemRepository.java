package com.applaudostudios.demo.repositories;

import com.applaudostudios.demo.repositories.models.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    boolean existsByIdOrName(Long id, String name);
    boolean existsById(Long itemId);


    @Query(value = "SELECT i FROM Item i WHERE (:status is null or i.status = :status) " +
            " and (:enteredByUser is null or i.audit.enteredByUser = :enteredByUser) ")
    Page<Item> findItemsByRequest(Pageable page, @Param("status") String status, @Param("enteredByUser") String enteredByUser );
}
