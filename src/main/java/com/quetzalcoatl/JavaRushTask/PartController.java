package com.quetzalcoatl.JavaRushTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Map;

@Controller
public class PartController {
    @Autowired
    private PartRepository repo;

    @GetMapping
    public String main(Map<String, Object> model) {
        Iterable<Part> parts = repo.findAll();
        model.put("parts", parts);

        //возвращает название html-шаблона
        return "main";
    }


}
