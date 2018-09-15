package com.quetzalcoatl.JavaRushTask;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PartRepository extends CrudRepository<Part, Integer> {
    //возвращает список объектов при частичном совпадении
    List<Part> findByTypeIgnoreCaseLike(String type);
}
