package com.joshua.findcomputer.findcomp_api.endpoint.user;

import com.joshua.findcomputer.findcomp_api.endpoint.ResponsePayload;
import com.joshua.findcomputer.findcomp_api.endpoint.user.payload.UserAuthRegRequestPayload;
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
	public ResponsePayload insertItem(@NotNull @RequestBody UserAuthRegRequestPayload userRegistPayload);
}
