package com.joshua.findcomputer.findcomp_impl.endpoint.category;

import com.joshua.findcomputer.findcomp_api.domain.CategoryService;
import com.joshua.findcomputer.findcomp_api.endpoint.category.CategoryAPIController;
import com.joshua.findcomputer.findcomp_api.endpoint.category.payload.IndexCategoryResponsePayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component("catAPIV1")
public class CategoryAPIControllerImpl implements CategoryAPIController {
	private final CategoryService categoryService;

	@Autowired
	public CategoryAPIControllerImpl(@Qualifier("serviceV1Cat") CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@Override
	public IndexCategoryResponsePayload index() {
		return new IndexCategoryResponsePayload().setCategories(categoryService.index());
	}
}
