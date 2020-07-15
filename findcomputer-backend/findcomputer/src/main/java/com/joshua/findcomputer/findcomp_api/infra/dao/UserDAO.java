package com.joshua.findcomputer.findcomp_api.infra.dao;

import com.joshua.findcomputer.findcomp_impl.infra.flushout.UserDataEntity;
import org.apache.catalina.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO {
	Integer register(UserDataEntity userDataEntity);
	public List<UserDataEntity> index();
}
