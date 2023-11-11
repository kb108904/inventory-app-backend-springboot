package com.inventoryAppDBService.InventroyService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.inventoryAppDBService.InventroyService.model.UserItem;
import com.inventoryAppDBService.InventroyService.repository.UserItemRepository;

@SpringBootTest
@AutoConfigureMockMvc
@SpringJUnitConfig
class InventroyServiceApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserItemRepository userItemsRepo;

	@Test
	void contextLoads() {
	}

	@Test
	public void testHealthCheck() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get("/"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content()
						.string(Matchers.matchesPattern("ok @ \\d{2}:\\d{2}:\\d{2} \\d{4}-\\d{2}-\\d{2}")));
	}

	@Test
	public void testGetUsersInventoryWithOwnerId() throws Exception {
		String ownerId = "123";

		List<UserItem> items = new ArrayList<>();

		items.add(new UserItem());
		items.add(new UserItem());

		when(userItemsRepo.findAllByOwnerId(ownerId)).thenReturn(items);

		mockMvc.perform(MockMvcRequestBuilders.get("/inventory/{ownerId}", ownerId))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(TestConst.TWO_USERITEM_STRING));
	}

	@Test
	public void testGetUsersInventoryWithoutOwnerId() throws Exception {
		List<UserItem> items = new ArrayList<>();
		// Add some UserItems to the list as if they were fetched from the repository.
		items.add(new UserItem());
		items.add(new UserItem());

		when(userItemsRepo.findAll()).thenReturn(items);

		mockMvc.perform(MockMvcRequestBuilders.get("/inventory/"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(TestConst.TWO_USERITEM_STRING));
	}

	@Test
	void testAddItemToOwner() throws Exception {
		String ownerId = "123";
		String itemId = "456";

		UserItem userItem = new UserItem();

		mockMvc.perform(MockMvcRequestBuilders.post("/inventory/{ownerId}/item/{itemId}", ownerId, itemId)
				.contentType(MediaType.APPLICATION_JSON)
				.content(Utilities.asJsonString(userItem)))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(TestConst.DATA_ADDED));

		userItem.setOwnerId(ownerId);
		verify(userItemsRepo, times(1)).save(any(UserItem.class));
	}

	@Test
	void testUpdateItem() throws Exception {
		String ownerId = "123";
		String itemId = "456";

		UserItem userItem = new UserItem();
		userItem.setOwnerId(ownerId);
		userItem.setItemId(itemId);

		// Mocking userItemsRepo
		when(userItemsRepo.findById(itemId)).thenReturn(Optional.empty());

		// Test when the item doesn't exist
		mockMvc.perform(MockMvcRequestBuilders.put("/inventory/{ownerId}/item/{itemId}", ownerId, itemId)
				.contentType(MediaType.APPLICATION_JSON)
				.content(Utilities.asJsonString(userItem)))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("Item Created"));

		// Verify that save is called
		verify(userItemsRepo, times(1)).save(any(UserItem.class));

		// Reset the mock
		reset(userItemsRepo);

		// Mocking userItemsRepo again, this time the item exists
		when(userItemsRepo.findById(itemId)).thenReturn(Optional.of(userItem));

		// Test when the item exists
		mockMvc.perform(MockMvcRequestBuilders.put("/inventory/{ownerId}/item/{itemId}", ownerId, itemId)
				.contentType(MediaType.APPLICATION_JSON)
				.content(Utilities.asJsonString(userItem)))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("Item already exists"));

		// Verify that save is not called
		verify(userItemsRepo, never()).save(any(UserItem.class));
	}

}
