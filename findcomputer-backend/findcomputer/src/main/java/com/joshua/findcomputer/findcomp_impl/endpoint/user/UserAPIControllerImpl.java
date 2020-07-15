package com.joshua.findcomputer.findcomp_impl.endpoint.user;

import com.joshua.findcomputer.findcomp_api.domain.UserService;
import com.joshua.findcomputer.findcomp_api.endpoint.ResponsePayload;
import com.joshua.findcomputer.findcomp_api.endpoint.user.UserAPIController;
import com.joshua.findcomputer.findcomp_api.endpoint.user.payload.UserRegisterRequestPayload;
import com.joshua.findcomputer.findcomp_impl.helper.HttpStatus;
import com.joshua.findcomputer.findcomp_impl.helper.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.List;

import static com.joshua.findcomputer.findcomp_impl.helper.HttpStatus.*;

@Component("userPIV1")
public class UserAPIControllerImpl implements UserAPIController {
	private final UserService userService;

	@Autowired
	public UserAPIControllerImpl(@Qualifier("userV1Service") UserService userService) {
		this.userService = userService;
	}

	@Override
	public ResponsePayload insertItem(@NotNull UserRegisterRequestPayload userRegistPayload) {
		Pair<Boolean, List<String >> res = userService.register(userRegistPayload);
		return new ResponsePayload()
			.setStatus((res.getKey() ? SUCCESS : FAIL).toString())
			.setMessage(res.getVal());
	}
}
