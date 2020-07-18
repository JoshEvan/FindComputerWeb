package com.joshua.findcomputer.findcomp_api.endpoint.category.payload;

import com.joshua.findcomputer.findcomp_api.endpoint.ResponsePayload;
import com.joshua.findcomputer.findcomp_api.model.Category;
import lombok.Getter;

import java.util.List;

public class IndexCategoryResponsePayload extends ResponsePayload {
	@Getter
	List<Category> categories;

	public IndexCategoryResponsePayload setCategories(List<Category> categories) {
		this.categories = categories;return this;
	}
}
