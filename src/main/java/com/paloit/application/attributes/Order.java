/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paloit.application.attributes;

import com.opencsv.bean.CsvBindByName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author alakshmanan
 */
@Component
@Scope(value = "prototype")
public class Order extends CsvBean
{
    @NotNull
    @NotEmpty
    @NotBlank
    @CsvBindByName(column = "Region")
    String region;

    @NotEmpty
    @NotNull
    @NotBlank
    @CsvBindByName(column = "Country")
    String country;

    @NotEmpty
    @NotNull
    @NotBlank
    @CsvBindByName(column = "Item Type")
    String itemType;

    @NotEmpty
    @NotNull
    @NotBlank
    @CsvBindByName(column = "Sales Channel")
    String salesChannel;

    @NotNull
    @NotBlank
    @CsvBindByName(column = "Order Priority")
    String orderPriority;

    @NotNull
    @NotBlank
    @CsvBindByName(column = "Order Date")
    String orderDate;

    @NotNull
    @CsvBindByName(column = "Order ID")
    String orderId;

    @NotEmpty
    @NotNull
    @NotBlank
    @CsvBindByName(column = "Ship Date")
    String shipDate;

    @NotEmpty
    @NotNull
    @NotBlank
    @CsvBindByName(column = "Units Sold")
    String unitsSold;

    @NotEmpty
    @NotNull
    @NotBlank
    @CsvBindByName(column = "Unit Price")
    String unitPrice;

    @NotEmpty
    @NotNull
    @NotBlank
    @CsvBindByName(column = "Unit Cost")
    String unitCost;

    @NotNull
    @NotEmpty
    @NotBlank
    @CsvBindByName(column = "Total Revenue")
    String totalRevenue;

    @NotNull
    @NotEmpty
    @NotBlank
    @CsvBindByName(column = "Total Cost")
    String totalCost;

    @NotNull
    @NotEmpty
    @NotBlank
    @CsvBindByName(column = "Total Profit")
    String totalProfit;

    String nric;

    public String getRegion()
    {
        return region;
    }

    public void setRegion(String region)
    {
        this.region = region;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public String getItemType()
    {
        return itemType;
    }

    public void setItemType(String itemType)
    {
        this.itemType = itemType;
    }

    public String getSalesChannel()
    {
        return salesChannel;
    }

    public void setSalesChannel(String salesChannel)
    {
        this.salesChannel = salesChannel;
    }

    public String getOrderPriority()
    {
        return orderPriority;
    }

    public void setOrderPriority(String orderPriority)
    {
        this.orderPriority = orderPriority;
    }

    public String getOrderDate()
    {
        return orderDate;
    }

    public void setOrderDate(String orderDate)
    {
        this.orderDate = orderDate;
    }

    public String getOrderId()
    {
        return orderId;
    }

    public void setOrderId(String orderId)
    {
        this.orderId = orderId;
    }

    public String getShipDate()
    {
        return shipDate;
    }

    public void setShipDate(String shipDate)
    {
        this.shipDate = shipDate;
    }

    public String getUnitsSold()
    {
        return unitsSold;
    }

    public void setUnitsSold(String unitsSold)
    {
        this.unitsSold = unitsSold;
    }

    public String getUnitPrice()
    {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice)
    {
        this.unitPrice = unitPrice;
    }

    public String getUnitCost()
    {
        return unitCost;
    }

    public void setUnitCost(String unitCost)
    {
        this.unitCost = unitCost;
    }

    public String getTotalRevenue()
    {
        return totalRevenue;
    }

    public void setTotalRevenue(String totalRevenue)
    {
        this.totalRevenue = totalRevenue;
    }

    public String getTotalCost()
    {
        return totalCost;
    }

    public void setTotalCost(String totalCost)
    {
        this.totalCost = totalCost;
    }

    public String getTotalProfit()
    {
        return totalProfit;
    }

    public void setTotalProfit(String totalProfit)
    {
        this.totalProfit = totalProfit;
    }

    public String getNric()
    {
        return nric;
    }

    public void setNric(String nric)
    {
        this.nric = nric;
    }
}
