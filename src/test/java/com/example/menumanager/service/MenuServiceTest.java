package com.example.menumanager.service;

import com.example.menumanager.dto.MenuDto;
import com.example.menumanager.entity.Menu;
import com.example.menumanager.exception.MenuNotFoundException;
import com.example.menumanager.repository.MenuRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MenuServiceTest {

    @Mock
    private MenuRepo menuRepo;

    @InjectMocks
    private MenuService menuService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void findAllMenus_returnsListOfMenus() {

        MenuRepo mockRepo = mock(MenuRepo.class);
        IdService idService = mock(IdService.class);
        MenuService service = new MenuService(idService ,mockRepo);

        Menu menu1 = new Menu("1", "Menu1", 10.0, "Chicken", "Fries", "Water");
        Menu menu2 = new Menu("2", "Menu2", 12.0, "Chicken2", "Fries", "Water");

        List<Menu> expectedMenus = List.of(menu1, menu2);

        when(mockRepo.findAll()).thenReturn(expectedMenus);

        List<Menu> actualMenus = service.findAllMenu();

        assertEquals(expectedMenus, actualMenus);
        verify(mockRepo).findAll();
    }


    @Test
    void findById() {
        Menu menu1 = new Menu("1", "Menu1", 10.0,
                "Chicken", "Fries", "Water");
        MenuRepo mockRepo = mock(MenuRepo.class);
        IdService mockIdService = mock(IdService.class);

        MenuService menuService = new MenuService(mockIdService, mockRepo);

        when(mockRepo.findById(menu1.id())).thenReturn(Optional.of(menu1));

        Menu actual = menuService.findById(menu1.id());

        assertEquals(menu1, actual);
        verify(mockRepo).findById(menu1.id());
    }

    @Test
    void createMenu() {
        MenuDto menuDto = new MenuDto("Menu1", 15.99, "Steak", "Fries", "Wine");
        MenuRepo menuRepo = mock(MenuRepo.class);
        IdService idService = mock(IdService.class);

        MenuService menuService = new MenuService(idService, menuRepo);

        when(idService.createId()).thenReturn("22");
        Menu menu = new Menu("22", menuDto.name(), menuDto.price(),
                menuDto.mainDish(), menuDto.sideDish(), menuDto.beverage());

        when(menuRepo.save(menu)).thenReturn(menu);

        Menu createMenu = menuService.createMenu(menuDto);

        verify(menuRepo).save(menu);
        assertEquals(menu, createMenu);
    }


    @Test
    void deleteMenuNotFound() {
        Menu menu1 = new Menu("1", "Menu1", 10.0, "Chicken", "Fries", "Water");

        when(menuRepo.findById("1")).thenReturn(Optional.empty());

        assertThrows(MenuNotFoundException.class, () -> {
            menuService.deleteMenuById(menu1.id());
        });

        verify(menuRepo, times(0)).delete(menu1);
    }

    @Test
    void deleteMenuFound() {
        Menu menu = new Menu("1", "Menu1", 15.99, "Steak", "Fries", "Wine");

        when(menuRepo.findById("1")).thenReturn(Optional.of(menu));

        Menu result = menuService.deleteMenuById("1");

        assertNotNull(result);
        assertEquals("1", result.id());

        verify(menuRepo).delete(menu);
    }
}