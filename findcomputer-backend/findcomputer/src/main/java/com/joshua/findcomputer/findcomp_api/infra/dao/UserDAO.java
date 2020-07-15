package com.joshua.findcomputer.findcomp_api.infra.dao;

import com.joshua.findcomputer.findcomp_impl.infra.flushout.UserDataEntity;
import org.apache.catalina.User;

public interface UserDAO {
	Integer register(UserDataEntity userDataEntity);
}
