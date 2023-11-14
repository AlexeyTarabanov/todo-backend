package com.example.todobackend.controllers;

import com.example.todobackend.entity.Category;
import com.example.todobackend.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Используем @RestController вместо обычного @Controller, чтобы все ответы сразу оборачивались в JSON,
 * иначе пришлось бы добавлять лишние объекты в код, использовать @ResponseBody для ответа,
 * указывать тип отправки JSON
 *
 * Названия методов могут быть любыми, главное не дублировать их имена внутри класса и URL mapping
 * */

@RestController
@RequestMapping("/category")
public class CategoryController {

    // доступ к данным из БД
    private CategoryService categoryService;

    // используем автоматическое внедрение экземпляра класса через конструктор
    // не используем @Autowired ля переменной класса, т.к. "Field injection is not recommended "
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/id")
    public Category findById() {
        return categoryService.findById(60130L);
    }
}
