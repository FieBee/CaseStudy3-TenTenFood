package com.example.tentententen.service.bill_detail;

import com.example.tentententen.model.Bill_detail;
import com.example.tentententen.service.IService;

public interface IBill_DetailService extends IService<Bill_detail> {
    void insert(Bill_detail bill_detail);
}
