package com.ecommerce.api.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "items")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@NotNull(message = "Please add valid order product quantity.")
	private Integer quantity;
	
	@JsonBackReference
    @ManyToOne(targetEntity = Order.class,
    		optional = false, fetch = FetchType.LAZY,
    		cascade = {CascadeType.DETACH, CascadeType.MERGE})
    @JoinColumn(name = "order_id", referencedColumnName="id")
    private Order order;

	@NotNull(message = "No product found in order.")
    @ManyToOne(targetEntity = Product.class,
    		optional = false, fetch = FetchType.LAZY,
    		cascade = {CascadeType.DETACH, CascadeType.MERGE})
	@JoinColumn(name = "product_id", referencedColumnName="id")
    private Product product;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", quantity=" + quantity + ", orderId=" + order.getId() + ", product=" + product + "]";
	}
	
	
}
