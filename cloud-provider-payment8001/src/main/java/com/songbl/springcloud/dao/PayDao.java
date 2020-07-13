package com.songbl.springcloud.dao;

import com.songbl.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PayDao {

    public int create(Payment payment);

    public Payment getPaymentById( Long id);
}
