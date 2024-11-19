package com.inncontrol.inventory.domain.services;


import com.inncontrol.inventory.domain.model.aggregates.Inventory;
import com.inncontrol.inventory.domain.model.commands.CreateItemsCommand;
import com.inncontrol.inventory.domain.model.commands.DeleteItemsCommand;
import com.inncontrol.inventory.domain.model.commands.UpdateInventoryCommand;

import java.util.Optional;

public interface InventoryCommandService {
     Optional<Inventory> handle(CreateItemsCommand command);
    Optional<Inventory> handle(UpdateInventoryCommand command);
    void handle(DeleteItemsCommand command);
}
