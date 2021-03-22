/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paloit.application.mongodb;

import com.paloit.application.attributes.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author alakshmanan
 */
public interface OrderRepository extends MongoRepository<Order, String>
{
    @Override
    public Page<Order> findAll(Pageable pageable);

}
