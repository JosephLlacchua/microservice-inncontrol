package com.inncontrol.inventory.interfaces.rest.trasnform;


import com.inncontrol.inventory.domain.model.aggregates.Inventory;
import com.inncontrol.inventory.interfaces.rest.resources.InventoryResource;

public class InventoryResourceFromEntityAssembler {
    public  static InventoryResource toResourceFromEntity(Inventory entity){
        return new InventoryResource(entity.getId(),entity.getProductTitle(), entity.getProductDescription(), entity.getBrand(), entity.getProductQuantity());
    }
}
