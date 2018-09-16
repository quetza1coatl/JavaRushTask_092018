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
        calculateQuantityOfDevices (model);
        return "main";
    }

    @PostMapping
    public String find(@RequestParam String type, Map<String, Object> model){
        Iterable<Part> parts;
        if((type == null) || (type.isEmpty()))
            parts = repo.findAll();

        else
            parts = repo.findByTypeIgnoreCaseLike("%"+type+"%");
        model.put("parts", parts);
        calculateQuantityOfDevices (model);

        return "main";
    }
    @PostMapping("add")
    public String add(@RequestParam String type, @RequestParam(required = false, defaultValue = "false") String checkbox, @RequestParam int quantity, Map<String, Object> model){
        Part p = new Part(type, Boolean.valueOf(checkbox), quantity);
        repo.save(p);
        //перенаправляет на главную страницу, которая отображает весь список
        return "redirect:/";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String,Object> model){
        Iterable<Part> parts;
        switch (filter){
            case "necessary":
                parts = repo.findByIsNecessary(true);
                break;
            case "unnecessary":
                parts = repo.findByIsNecessary(false);
                break;
             default:
                 parts = repo.findAll();
        }
        model.put("parts", parts);
        calculateQuantityOfDevices (model);


        return "main";
    }

    public void calculateQuantityOfDevices(Map<String,Object> model){
        Integer quantity = repo.findMinQuantityInNecessaryDetails();

        model.put("total", quantity);

    }


}
