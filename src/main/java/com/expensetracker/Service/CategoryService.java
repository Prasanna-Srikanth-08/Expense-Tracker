package com.expensetracker.Service;

import com.expensetracker.DTO.CategoryDTO;
import com.expensetracker.Entity.Category;
import com.expensetracker.Repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@PreAuthorize("isAuthenticated()")
public class CategoryService {

     private static final Logger logger = LoggerFactory.getLogger(CategoryService.class);

        @Autowired
        private CategoryRepository categoryRepository;

        public CategoryDTO addCategory(CategoryDTO categoryDTO) {
            Category persistedCategory = null;
            if (categoryDTO != null) {
                Category category = CategoryDTO.prepareCategory(categoryDTO);
                persistedCategory = new Category();
                try {
                    persistedCategory = categoryRepository.save(category);
                } catch (Exception e) {
                    logger.error("Error occurred while adding category ", e);
                }
            }
            CategoryDTO result = Category.prepareCategoryDto(persistedCategory);
            return result;
        }

        public List<CategoryDTO> getAllCategories(){
            List<Category> categoryList = categoryRepository.findAll(Sort.by(Sort.Direction.ASC,"name"));
            List<CategoryDTO> categoryDTOList = new ArrayList<>();
            categoryList.stream().forEach(category -> {
                CategoryDTO categoryDTO = Category.prepareCategoryDto(category);
                categoryDTOList.add(categoryDTO);
            });
            return categoryDTOList;
        }

        public CategoryDTO updateCategory(CategoryDTO categoryDTO){
            Optional<Category> category = categoryRepository.findById(categoryDTO.getCategoryId());
            if(category.isPresent()){
                category.get().setName(categoryDTO.getName());
                try{
                   categoryRepository.save(category.get());
                }
                catch (Exception e){
                    logger.error("Error occurred while updating category ",e);
                }
            }
            else{
                logger.error("No record found for given category id");
            }
        return categoryDTO;
        }

        public String deleteCategory(int categoryId){
            Optional<Category> category = categoryRepository.findById(categoryId);
            if(category.isEmpty()){
                logger.info("No record found for given category id");
            }
            try {
                categoryRepository.deleteById(categoryId);
            }
            catch (Exception e){
                logger.info("Error while deleting category {}",category.get().getName());
            }
            return "Category deleted successfully";
        }
}
