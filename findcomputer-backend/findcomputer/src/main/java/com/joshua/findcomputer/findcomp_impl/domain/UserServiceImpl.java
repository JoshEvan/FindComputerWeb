package com.joshua.findcomputer.findcomp_impl.domain;

import com.joshua.findcomputer.findcomp_api.domain.UserService;
import com.joshua.findcomputer.findcomp_api.endpoint.user.payload.UserRegisterRequestPayload;
import com.joshua.findcomputer.findcomp_api.infra.dao.UserDAO;
import com.joshua.findcomputer.findcomp_impl.helper.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

import static com.joshua.findcomputer.findcomp_impl.helper.Helper.*;
import static com.joshua.findcomputer.findcomp_impl.infra.adapter.UserAdapter.convertRegisterPayloadToDataEntity;

@Component("userV1Service")
@Service
public class UserServiceImpl implements UserService, UserDetailsService {
	private final UserDAO userDAO;

	private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

	@Autowired
	public UserServiceImpl(@Qualifier("pgUser") UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	public Pair<Boolean, List<String>> register(UserRegisterRequestPayload userRegisterRequestPayload) {
		userRegisterRequestPayload.setPassword(
			passwordEncoder.encode(userRegisterRequestPayload.getPassword())
		);
		Integer stat = userDAO.register(convertRegisterPayloadToDataEntity(userRegisterRequestPayload));
		return new Pair<>(
			(stat == 1),
			Collections.singletonList(
				USER +(stat == 1 ?  SUCCESS:FAIL)+REGISTERED
			)
		);
	}

	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		return null;
	}
}
