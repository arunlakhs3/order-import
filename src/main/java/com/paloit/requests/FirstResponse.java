/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paloit.requests;

import com.paloit.application.attributes.Order;
import java.util.List;

/**
 *
 * @author alakshmanan
 */
public class FirstResponse
{
    long totalCount;

    List<Order> orders;

    public long getTotalCount()
    {
        return totalCount;
    }

    public void setTotalCount(long totalCount)
    {
        this.totalCount = totalCount;
    }

    public List<Order> getOrders()
    {
        return orders;
    }

    public void setOrders(List<Order> orders)
    {
        this.orders = orders;
    }

}
