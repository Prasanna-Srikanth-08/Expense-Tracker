package com.expensetracker.Api;

import com.expensetracker.DTO.CategoryDTO;
import com.expensetracker.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class CategoryApi {

    @Autowired
    private CategoryService categoryService;

    @SchemaMapping(typeName = "Mutation", field = "addCategory")
    public CategoryDTO addCategory(@Argument("categoryDto") CategoryDTO categoryDTO){
        return categoryService.addCategory(categoryDTO);
    }

    @SchemaMapping(typeName = "Query", field = "getAllCategories")
    public List<CategoryDTO> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @SchemaMapping(typeName = "Mutation", field = "updateCategory")
    public CategoryDTO updateCategory(@Argument("updateCategoryDto") CategoryDTO categoryDTO){
        return categoryService.updateCategory(categoryDTO);
    }

    @SchemaMapping(typeName = "Mutation", field = "DeleteCategory")
    public String deleteCategory(@Argument("categoryId") int categoryId){
        return categoryService.deleteCategory(categoryId);
    }
}
