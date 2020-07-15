package com.joshua.findcomputer.findcomp_api.endpoint.item;

import com.joshua.findcomputer.findcomp_api.endpoint.ResponsePayload;
import com.joshua.findcomputer.findcomp_api.endpoint.item.payload.index.IndexItemRequestPayload;
import com.joshua.findcomputer.findcomp_api.endpoint.item.payload.index.IndexItemResponsePayload;
import com.joshua.findcomputer.findcomp_api.endpoint.item.payload.upsert.UpsertItemRequestPayload;
import com.joshua.findcomputer.findcomp_api.model.Item;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/v1/findcomp/item")
@RestController
@EnableAutoConfiguration
@Component("itemAPIV1")
public interface ItemAPIController {
	@PostMapping("/insert")
	public ResponsePayload insertItem(@NotNull @RequestBody UpsertItemRequestPayload upsertItemRequestPayload);

	@PostMapping(value = "/", produces = "application/json")
	public @ResponseBody
	IndexItemResponsePayload indexItem(@NotNull @RequestBody IndexItemRequestPayload indexItemRequestPayload);

	@GetMapping(value = "/show/{id}", produces = "application/json")
	public @ResponseBody
	Item showIndex(@NotNull @PathVariable("id") String id);

	@PutMapping("/update")
	public ResponsePayload updateItem(@NotNull @RequestBody UpsertItemRequestPayload upsertItemRequestPayload);

	@DeleteMapping("/delete/{id}")
	public ResponsePayload deleteItem(@NotNull @PathVariable("id") String id);
}
