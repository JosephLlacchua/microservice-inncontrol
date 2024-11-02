package com.inncontrol.inventory.interfaces.rest.trasnform;

import com.inncontrol.inventory.domain.model.commands.CreateItemsCommand;
import com.inncontrol.inventory.interfaces.rest.resources.CreateInventoryResource;

public class CreateInventoryCommandFromResourceAssembler {
    public static CreateItemsCommand toCommandFromResource(CreateInventoryResource resource){
        return new CreateItemsCommand(resource.productTitle(), resource.productDescription(), resource.Quantity(), resource.Brand());
    }
}
