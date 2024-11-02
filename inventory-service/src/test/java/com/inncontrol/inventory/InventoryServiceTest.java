package com.inncontrol.inventory;

import com.inncontrol.inventory.application.commandservices.InventoryCommandServiceImpl;
import com.inncontrol.inventory.domain.model.aggregates.Inventory;
import com.inncontrol.inventory.domain.model.commands.CreateItemsCommand;
import com.inncontrol.inventory.domain.model.commands.DeleteItemsCommand;
import com.inncontrol.inventory.domain.model.commands.UpdateInventoryCommand;
import com.inncontrol.inventory.infrastructure.persistence.jpa.repositories.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class InventoryCommandServiceImplTest {

    @Mock
    private ItemRepository inventoryRepository;

    @InjectMocks
    private InventoryCommandServiceImpl inventoryCommandService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void handleCreateItemsCommand_WithValidData() {
        CreateItemsCommand createItemsCommand = new CreateItemsCommand("itemName", "description", 10, "brand");
        Inventory inventory = new Inventory(createItemsCommand);

        when(inventoryRepository.save(any(Inventory.class))).thenReturn(inventory);

        Optional<Inventory> result = inventoryCommandService.handle(createItemsCommand);

        assertTrue(result.isPresent());
        assertEquals("itemName", result.get().getProductTitle());
        assertEquals("brand", result.get().getBrand());
        assertEquals(10, result.get().getProductQuantity());
        assertEquals("description", result.get().getProductDescription());
    }
}