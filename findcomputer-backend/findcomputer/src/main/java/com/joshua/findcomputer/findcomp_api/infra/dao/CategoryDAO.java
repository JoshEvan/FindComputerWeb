package com.joshua.findcomputer.findcomp_api.infra.dao;

import com.joshua.findcomputer.findcomp_impl.infra.flushout.CategoryDataEntity;

import java.util.List;

public interface CategoryDAO {
	List<CategoryDataEntity> index();
}
