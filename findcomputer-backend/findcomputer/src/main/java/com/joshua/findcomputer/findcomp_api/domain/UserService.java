package com.joshua.findcomputer.findcomp_api.domain;

import com.joshua.findcomputer.findcomp_api.endpoint.user.payload.UserRegisterRequestPayload;
import com.joshua.findcomputer.findcomp_impl.helper.Pair;

import java.util.List;

public interface UserService {
	Pair<Boolean, List<String>> register(UserRegisterRequestPayload userRegisterRequestPayload);
}
