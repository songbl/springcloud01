package com.songbl.springcloud.service;

import com.songbl.springcloud.entities.Payment;


public interface PaymentService {

     int create(Payment payment);

     Payment getPaymentById( Long id);
}
