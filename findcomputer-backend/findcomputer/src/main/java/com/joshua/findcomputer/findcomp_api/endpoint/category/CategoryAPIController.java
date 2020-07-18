package com.joshua.findcomputer.findcomp_api.endpoint.category;

import com.joshua.findcomputer.findcomp_api.endpoint.category.payload.IndexCategoryResponsePayload;
import com.joshua.findcomputer.findcomp_api.model.Item;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/v1/findcomp/category")
@RestController
@EnableAutoConfiguration
@Component("catAPIV1")
public interface CategoryAPIController {
	@GetMapping(value = "/index", produces = "application/json")
	public @ResponseBody IndexCategoryResponsePayload index();
}
