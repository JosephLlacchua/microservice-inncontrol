package com.inncontrol.inventory.application.commandservices;

import com.inncontrol.inventory.domain.model.aggregates.Inventory;
import com.inncontrol.inventory.domain.model.commands.CreateItemsCommand;
import com.inncontrol.inventory.domain.model.commands.DeleteItemsCommand;
import com.inncontrol.inventory.domain.model.commands.UpdateInventoryCommand;
import com.inncontrol.inventory.domain.services.InventoryCommandService;
import com.inncontrol.inventory.infrastructure.persistence.jpa.repositories.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InventoryCommandServiceImpl implements InventoryCommandService {

    private final ItemRepository itemRepository;

    public InventoryCommandServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Optional<Inventory> handle(CreateItemsCommand command) {
        if(itemRepository.existsByProductTitle(command.itemTitle())){
            throw new IllegalArgumentException("This item title already exists");
        }

        var item = new Inventory(command);
        var createdItems = itemRepository.save(item);
        return Optional.of(createdItems);
    }

    @Override
    public Optional<Inventory> handle(UpdateInventoryCommand command)
    {
        var result = itemRepository.findById(command.id());
        if (result.isEmpty()) throw new IllegalArgumentException("Item does not exist");
        var inventoryToUpdate = result.get();
        try {
            var updatedInventory = itemRepository.save(inventoryToUpdate.updateInformation(command.itemTitle(), command.itemDescription(),command.itemQuantity(),command.brand()));
            return Optional.of(updatedInventory);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating Inventory: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeleteItemsCommand command) {
        if (!itemRepository.existsById(command.id())) {
            throw new IllegalArgumentException("Course does not exist");
        }
        try {
            itemRepository.deleteById(command.id());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting course: " + e.getMessage());
        }

    }


}


