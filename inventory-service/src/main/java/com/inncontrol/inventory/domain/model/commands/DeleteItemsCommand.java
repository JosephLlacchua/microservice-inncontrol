package com.inncontrol.inventory.domain.model.commands;

public record DeleteItemsCommand(Long id) {
    public DeleteItemsCommand{
        if (id == null || id < 0) {
            throw new IllegalArgumentException("Id cannot be null");
        }
    }
}
