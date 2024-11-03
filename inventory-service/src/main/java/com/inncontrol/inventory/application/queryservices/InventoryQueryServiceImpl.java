package com.inncontrol.inventory.application.queryservices;

import com.inncontrol.inventory.domain.model.aggregates.Inventory;
import com.inncontrol.inventory.domain.model.queries.GetAllItemsQuery;
import com.inncontrol.inventory.domain.model.queries.GetItemByBrandQuery;
import com.inncontrol.inventory.domain.model.queries.GetItemByIdQuery;
import com.inncontrol.inventory.domain.services.InventoryQueryService;
import com.inncontrol.inventory.infrastructure.persistence.jpa.repositories.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryQueryServiceImpl implements InventoryQueryService {
    private final ItemRepository itemRepository;

    public InventoryQueryServiceImpl(ItemRepository itemRepository) {

        this.itemRepository = itemRepository;
    }

    @Override
    public List<Inventory> handle(GetAllItemsQuery query){
        return itemRepository.findAll();
    }

    @Override
    public Optional<Inventory> handle(GetItemByIdQuery query){
        return itemRepository.findById(query.itemId());
    }

    @Override
    public List<Inventory> handle(GetItemByBrandQuery query)
    {
        return itemRepository.findAllByBrand(query.brandName());
    }



}
