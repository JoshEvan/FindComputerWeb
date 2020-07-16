package com.joshua.findcomputer.findcomp_impl.domain;

import com.joshua.findcomputer.findcomp_api.domain.UserService;
import com.joshua.findcomputer.findcomp_api.endpoint.user.payload.UserAuthRequestPayload;
import com.joshua.findcomputer.findcomp_api.endpoint.user.payload.UserProfileRequestPayload;
import com.joshua.findcomputer.findcomp_api.infra.dao.UserDAO;
import com.joshua.findcomputer.findcomp_api.model.User;
import com.joshua.findcomputer.findcomp_impl.helper.Pair;
import com.joshua.findcomputer.findcomp_impl.infra.flushout.UserDataEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.joshua.findcomputer.findcomp_impl.helper.Helper.*;
import static com.joshua.findcomputer.findcomp_impl.infra.adapter.UserAdapter.convertDataEntitiesToModels;
import static com.joshua.findcomputer.findcomp_impl.infra.adapter.UserAdapter.convertRegisterPayloadToDataEntity;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Component("userV1Service")
@Service
public class UserServiceImpl implements UserDetailsService, UserService{
	private final UserDAO userDAO;

	private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

	@Autowired
	public UserServiceImpl(@Qualifier("pgUser") UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	public Pair<Boolean, List<String>> register(UserProfileRequestPayload profileRequestPayload) {
		profileRequestPayload.setPassword(
			passwordEncoder.encode(profileRequestPayload.getPassword())
		);
		Integer stat = userDAO.register(convertRegisterPayloadToDataEntity(profileRequestPayload));
		return new Pair<>(
			(stat == 1),
			Collections.singletonList(
				USER +(stat == 1 ?  SUCCESS:FAIL)+REGISTERED
			)
		);
	}

	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		List<UserDataEntity> userDataEntities = userDAO.index();
		List<User> users = convertDataEntitiesToModels(userDataEntities);

		Optional<User> found = users
				.stream()
				.filter(
					appUser -> s.equals(appUser.getUsername())
				).findFirst();

		return found.orElse(null);
	}
}
