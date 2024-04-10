package com.expensetracker.DTO;

import com.expensetracker.Entity.Category;
import lombok.Data;

@Data
public class CategoryDTO {
    private Integer categoryId;
    private String name;

    public static Category prepareCategory(CategoryDTO categoryDTO){
        Category category = new Category();
        category.setCategoryId(categoryDTO.getCategoryId());
        category.setName(categoryDTO.getName());
        return category;
    }
}
