package com.songbl.springcloud.service.impl;

import com.songbl.springcloud.entities.Payment;
import com.songbl.springcloud.dao.PayDao;
import com.songbl.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PayDao payDao ;


    @Override
    public int create(Payment payment) {
        return payDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return payDao.getPaymentById(id);
    }
}
