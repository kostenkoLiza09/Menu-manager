package com.example.menumanager.service;

import com.example.menumanager.dto.MenuDto;
import com.example.menumanager.entity.Menu;
import com.example.menumanager.exception.MenuNotFoundException;
import com.example.menumanager.repository.MenuRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    private final IdService idService;

    private final MenuRepo menuRepo;



    public MenuService(IdService idService, MenuRepo menuRepo) {
        this.idService = idService;
        this.menuRepo = menuRepo;
    }

    public Menu createMenu(MenuDto menuDto) {
        String id = idService.createId();

        Menu menu = new Menu(
                id,
                menuDto.name(),
                menuDto.price(),
                menuDto.mainDish(),
                menuDto.sideDish(),
                menuDto.beverage()
        );

        return menuRepo.save(menu);
    }

    public Menu updateMenu (String id, MenuDto menuDto){

        Menu oldData = menuRepo.findById(id)
                .orElseThrow(() -> new MenuNotFoundException("Menu is not found"));

        Menu menu = new Menu(
                oldData.id(),
                menuDto.name(),
                menuDto.price(),
                menuDto.mainDish(),
                menuDto.sideDish(),
                oldData.beverage()
        );
        return menuRepo.save(menu);
    }

    public Menu deleteMenuById (String id){
        Menu menu = menuRepo.findById(id)
                .orElseThrow(() -> new MenuNotFoundException("Id is not found"));
        menuRepo.delete(menu);
        return menu;
    }

    public Menu findById (String id){
        return menuRepo.findById(id)
                .orElseThrow(() -> new MenuNotFoundException("Id is not found"));
    }

    public List<Menu> findAllMenu (){
        return menuRepo.findAll();
    }
}