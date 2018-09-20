package com.quetzalcoatl.JavaRushTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.*;


@Controller
public class PartController {
    @Autowired
    private PartRepository repo;
    @GetMapping("/refresh")
    public String refresh(){

        return "redirect:/";
    }

    @GetMapping("/")
    public String main(@RequestParam(required = false, defaultValue = "") String type, Model model,
                       @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Part> pages;
        if((type == null) || (type.isEmpty()))
            pages = repo.findAll(pageable);

        else
            pages = repo.findByTypeIgnoreCaseLike("%"+type+"%", pageable);

        model.addAttribute("parts", pages.getContent());
        Map<Integer,Integer> map = new HashMap<>();
        for(int i=0; i<pages.getTotalPages();i++)
            map.put(i, i+1);
        Set<Map.Entry<Integer, Integer>> entrySet = map.entrySet();
        if((type == null) || (type.isEmpty()))
            model.addAttribute("url", "/?");
        else
            model.addAttribute("url", "/?type=" + type + "&");
        model.addAttribute("array", entrySet);
        calculateQuantityOfDevices (model);
        return "main";
    }


    @PostMapping("/")
    public String add(@RequestParam String type,
                      @RequestParam(required = false, defaultValue = "false") String checkbox,
                      @RequestParam int quantity){
        Part p = new Part(type, Boolean.valueOf(checkbox), quantity);
        repo.save(p);
        return "redirect:/";
    }

    @GetMapping("/filter")
    public String filter(@RequestParam String filter, Model model,
                         @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable){
        Page<Part> pages;
        switch (filter){
            case "necessary":
                pages = repo.findByIsNecessary(true, pageable);
                break;
            case "unnecessary":
                pages = repo.findByIsNecessary(false, pageable);
                break;
             default:
                 pages = repo.findAll(pageable);
        }
         model.addAttribute("parts", pages.getContent());

        Map<Integer,Integer> map = new HashMap<>();
        for(int i=0; i<pages.getTotalPages();i++)
            map.put(i, i+1);
        Set<Map.Entry<Integer, Integer>> entrySet = map.entrySet();
        model.addAttribute("url", "/filter?filter=" + filter + "&");
        model.addAttribute("array", entrySet);
        calculateQuantityOfDevices (model);


        return "main";
    }

    @PostMapping("delete")
    public String delete(@RequestParam String name){
        repo.deleteById(Integer.valueOf(name));
        return "redirect:/";
    }

    @PostMapping("edit")
    public String edit(@RequestParam String name, Map<String,Object> model){
        Part p = repo.findById(Integer.valueOf(name)).orElseThrow(() -> new RuntimeException("Can't find part with id = " + name));
        model.put("part", p);
        return "edit";
    }

    @PostMapping("update")
    public String update(@RequestParam Integer id,
                         @RequestParam(required = false) String type,
                         @RequestParam(required = false, defaultValue = "false") String checkbox,
                         @RequestParam (required = false) int quantity){
        Part p = new Part(id, type, Boolean.valueOf(checkbox), quantity);
        repo.save(p);
        return "redirect:/";

    }


    public void calculateQuantityOfDevices(Model model){
        Integer quantity = repo.findMinQuantityOfNecessaryDetails();

        model.addAttribute("total", quantity);

    }


}
