package com.quetzalcoatl.JavaRushTask;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PartRepository extends CrudRepository<Part, Integer> {
    //возвращает список объектов при частичном совпадении при поиске по наименованию
    List<Part> findByTypeIgnoreCaseLike(String type);

    //возвращает список объектов, фильтруя по значению поля isNecessary
    List<Part> findByIsNecessary(boolean isNecessary);
}
