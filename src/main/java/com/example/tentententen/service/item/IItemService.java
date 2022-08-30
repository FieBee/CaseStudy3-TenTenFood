package com.example.tentententen.service.item;

import com.example.tentententen.model.Item;
import com.example.tentententen.service.IService;

public interface IItemService<Item> extends IService {
    public void save(Item p, int[] categories);
}
