package com.joshua.findcomputer.findcomp_impl.domain;

import com.joshua.findcomputer.findcomp_api.domain.CategoryService;
import com.joshua.findcomputer.findcomp_api.infra.dao.CategoryDAO;
import com.joshua.findcomputer.findcomp_api.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.joshua.findcomputer.findcomp_impl.infra.adapter.CategoryAdapter.convertDataEntitiesToModels;

@Component("serviceV1Cat")
@Service
public class CategoryServiceImpl implements CategoryService {
	private final CategoryDAO categoryDAO;

	@Autowired
	public CategoryServiceImpl(@Qualifier("pgCat") CategoryDAO categoryDAO) {
		this.categoryDAO = categoryDAO;
	}

	@Override
	public List<Category> index() {
		return convertDataEntitiesToModels(categoryDAO.index());
	}
}
