package com.quetzalcoatl.JavaRushTask;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PartRepository extends PagingAndSortingRepository<Part, Integer> {
    //возвращает список объектов при частичном совпадении при поиске по наименованию
    Page<Part> findByTypeIgnoreCaseLike(String type, Pageable pageable);

    //возвращает список объектов, фильтруя по значению поля isNecessary
    Page<Part> findByIsNecessary(boolean isNecessary, Pageable pageable);

    @Query(value = "SELECT min(quantity) FROM part WHERE is_necessary = true", nativeQuery = true)
    Integer findMinQuantityOfNecessaryDetails();
}
