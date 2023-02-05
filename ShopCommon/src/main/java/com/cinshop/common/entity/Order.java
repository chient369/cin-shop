package com.cinshop.common.entity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.cinshop.common.OrderStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;

@Entity
@Table(name = "orders")
public class Order {

	@Id
	@Column(name = "order_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "order_num", length = 16, unique = true, nullable = false)
	private String orderNum;

	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
	@JoinColumn(name = "customer_id")
	private Customer customer;

	private Integer discountPercent;
	
	private Integer shippingCost;

	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "tax_id")
	private Tax tax;

	@Enumerated(EnumType.STRING)
	private OrderStatus status;

	@OneToOne
	@JoinColumn(name = "payment_method")
	private PaymentMethod paymentMethod;

	@OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
	private List<OrderDetail> orderDetails = new ArrayList<>();

	@Temporal(TemporalType.DATE)
	private Date orderTime;

	private Integer total;

	public Order() {
		String randomNum = "OD" +Calendar.getInstance().getTimeInMillis() / 60;
		this.orderNum = randomNum;
	}

	public Order(Integer id) {
		super();
		this.id = id;
	}

	public Order(Customer customer, Integer discountPercent, Tax tax, PaymentMethod paymentMethod, Integer total) {
		super();
		this.customer = customer;
		this.discountPercent = discountPercent;
		this.tax = tax;
		this.paymentMethod = paymentMethod;
		this.total = total;
	}

	// 注文詳細を追加
	public void addOrderDetail(OrderDetail detail) {
		this.orderDetails.add(detail);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Integer getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(Integer discountPercent) {
		this.discountPercent = discountPercent;
	}

	public Tax getTax() {
		return tax;
	}

	public void setTax(Tax tax) {
		this.tax = tax;
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}


	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	
	
	
	public Integer getShippingCost() {
		return shippingCost;
	}

	public void setShippingCost(Integer shippingCost) {
		this.shippingCost = shippingCost;
	}

	@Transient
	public String getOrderDateString() {
		SimpleDateFormat formatter = new SimpleDateFormat("YYYY年MM月dd日");  
	   return formatter.format(this.orderTime);
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", orderNum=" + orderNum + ", customer=" + customer + ", discountPercent="
				+ discountPercent + ", tax=" + tax + ", status=" + status + ", paymentMethod=" + paymentMethod
				+ ", orderDetails=" + orderDetails + ", orderTime=" + orderTime + ", total=" + total + "]";
	}
	
	

	

}
