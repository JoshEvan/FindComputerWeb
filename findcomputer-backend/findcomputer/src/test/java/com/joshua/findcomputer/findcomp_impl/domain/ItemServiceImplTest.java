package com.joshua.findcomputer.findcomp_impl.domain;

import com.joshua.findcomputer.findcomp_api.domain.ItemService;
import com.joshua.findcomputer.findcomp_api.endpoint.item.payload.upsert.UpdateItemRequestPayload;
import com.joshua.findcomputer.findcomp_api.infra.dao.ItemDAO;
import com.joshua.findcomputer.findcomp_api.model.Item;
import com.joshua.findcomputer.findcomp_impl.helper.Pair;
import com.joshua.findcomputer.findcomp_impl.infra.flushout.ItemDataEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) // kalo ga pake ini, harus uncomment yang autoCloseable
class ItemServiceImplTest {
    private ItemService testedItemService;
//    private AutoCloseable autoCloseable;
    @Mock private ItemDAO itemDAO;

    @BeforeEach
    void setUp() {
//        autoCloseable = MockitoAnnotations.openMocks(this);
        testedItemService = new ItemServiceImpl(itemDAO);
    }

//    @AfterEach
//    void tearDown() throws Exception {
//        // destroy resources after each test
//        autoCloseable.close();
//    }

    @Test
    void show() {
        UUID uuid = UUID.randomUUID();
        Item item = new Item()
                .setId(uuid)
                .setName("1")
                .setCategory("cat")
                .setPriceAmount(BigDecimal.valueOf(100))
                .setDescription("haha")
                .setOwner("me");
        ItemDataEntity itemDataEntity = new ItemDataEntity()
                .setId(uuid)
                .setName("1")
                .setCategory("cat")
                .setPrice(BigDecimal.valueOf(100))
                .setDescription("haha")
                .setOwner("me");
        when(itemDAO.show(uuid)).thenReturn(java.util.Optional.of(itemDataEntity));
        Item returned = testedItemService.show(uuid.toString());
        assertThat(item).isEqualToComparingFieldByField(returned);
    }
}