package com.example.menumanager.controller;

import com.example.menumanager.dto.MenuDto;
import com.example.menumanager.entity.Menu;
import com.example.menumanager.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/menu")
public class MenuController {

    private final MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping
    public List<Menu> getAll() {
        return menuService.findAllMenu();
    }

    @PostMapping
    public Menu createMenu(@RequestBody MenuDto menuDto) {
        return menuService.createMenu(menuDto);
    }

    @PutMapping("/{id}")
    public Menu updateMenu(@PathVariable String id, @RequestBody MenuDto menuDto) {
        return menuService.updateMenu(id, menuDto);
    }

    @DeleteMapping("/{id}")
    public Menu deleteCharacter(@PathVariable String id) {
        return menuService.deleteMenuById(id);
    }

    @GetMapping("/{id}")
    public Menu findById(@PathVariable String id) {
        return menuService.findById(id);
    }
}
