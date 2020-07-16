package com.joshua.findcomputer.findcomp_api.endpoint.user;

import com.joshua.findcomputer.findcomp_api.endpoint.ResponsePayload;
import com.joshua.findcomputer.findcomp_api.endpoint.user.payload.UserAuthRequestPayload;
import com.joshua.findcomputer.findcomp_api.endpoint.user.payload.UserProfileRequestPayload;
import com.joshua.findcomputer.findcomp_api.endpoint.user.payload.UserUpdateProfileRequestPayload;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/v1/findcomp/user")
@RestController
@EnableAutoConfiguration
@Component("userPIV1")
public interface UserAPIController {
	@PostMapping("/register")
	public ResponsePayload register(@NotNull @RequestBody UserProfileRequestPayload profileRequestPayload);

	@PutMapping("/update")
	public ResponsePayload update(@NotNull @RequestBody UserUpdateProfileRequestPayload profileRequestPayload);
}
