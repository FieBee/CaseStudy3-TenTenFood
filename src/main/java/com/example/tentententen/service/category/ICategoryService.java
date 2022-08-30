package com.example.tentententen.service.category;

import com.example.tentententen.model.Category;
import com.example.tentententen.model.Item;
import com.example.tentententen.service.IService;

import java.util.List;

public interface ICategoryService extends IService {
    List<Category> findAllByItemId(int item_id);


}
