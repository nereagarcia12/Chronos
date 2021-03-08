package com.chronos.adservice.controller.interfaces;

import com.chronos.adservice.model.Category;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

public interface ICategoryController {

    public List<Category> getAllCategories();
}
