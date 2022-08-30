package com.example.tentententen.service.item;

import com.example.tentententen.model.Item;
import com.example.tentententen.service.IService;

public interface IItemService extends IService<Item> {
    void insert(com.example.tentententen.model.Item item);

    public void save(Item p, int[] categories);
}
