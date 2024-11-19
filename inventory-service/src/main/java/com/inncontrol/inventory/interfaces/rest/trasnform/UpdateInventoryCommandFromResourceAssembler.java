package com.inncontrol.inventory.interfaces.rest.trasnform;


import com.inncontrol.inventory.domain.model.commands.UpdateInventoryCommand;
import com.inncontrol.inventory.interfaces.rest.resources.UpdateInventoryResource;

public class UpdateInventoryCommandFromResourceAssembler {
    public static UpdateInventoryCommand toCommandFromResource(Long inventoryId, UpdateInventoryResource resource){
        return new UpdateInventoryCommand(inventoryId, resource.productTitle(), resource.productDescription(), resource.Quantity(), resource.Brand());
    }
}
