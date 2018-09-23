package com.quetzalcoatl.JavaRushTask;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface PartRepository extends PagingAndSortingRepository<Part, Integer> {
    //возвращает список объектов при частичном совпадении при поиске по наименованию
    Page<Part> findByTypeIgnoreCaseLike(String type, Pageable pageable);

    //возвращает список объектов, фильтруя по обязательности/необязательности при сборке
    Page<Part> findByIsNecessary(boolean isNecessary, Pageable pageable);

    //находит минимальное количество компьютеров, которое можно собрать из обязательных деталей
    @Query(value="SELECT MIN (p.quantity) FROM Part p WHERE p.isNecessary=true")
    int findMinQuantityOfNecessaryDetails();

}
