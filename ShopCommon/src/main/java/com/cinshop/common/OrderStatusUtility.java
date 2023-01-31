package com.cinshop.common;

public class OrderStatusUtility {
	public static OrderStatus getOrderStatus(Integer id) {
		OrderStatus sts = null;
		switch (id) {
		case 1:
			sts = OrderStatus.PAID;
			break;

		case 2:
			sts =  OrderStatus.PLACED;
			break;

		case 3:
			sts =  OrderStatus.PROCESSING;
			break;

		case 4:
			sts =  OrderStatus.DELIVERING;
			break;

		case 5:
			sts =  OrderStatus.COMPLETED;
			break;
		}
		return sts;
	}
}
