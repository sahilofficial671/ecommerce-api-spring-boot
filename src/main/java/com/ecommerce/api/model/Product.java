package com.ecommerce.api.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Component
@Table(name = "products")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@NotNull(message = "Please add valid product name.")
	@Size(max = 150)
	private String name;
	
	@Size(max = 255)
	private String description;
	
	@NotNull(message = "Please add valid product quantity.")
	@Column(name = "quantity")
	private Integer quantity;
	
	@NotNull(message = "Please add valid product price.")
	@Column(name = "price")
	private Double price;
	
	@NotNull(message = "Please add valid product special price.")
	@Column(name = "special_price")
	private Double specialPrice;
	
    @NotNull(message = "Please add valid product categories.")
    @ManyToMany(targetEntity = Category.class, 
			cascade = {CascadeType.DETACH, CascadeType.MERGE}, 
			fetch = FetchType.LAZY)
    @JoinTable(
			  name = "product_categories", 
			  joinColumns = @JoinColumn(name = "product_id", referencedColumnName="id"), 
			  inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName="id"))
	private List<Category> categories;
    
	@Size(max = 500)
	private String mainImagePath;
	
    @OneToMany(targetEntity = Item.class, 
			cascade = {CascadeType.DETACH, CascadeType.MERGE}, 
			fetch = FetchType.LAZY,
			mappedBy="product")
    @JsonIgnore
    private List<Item> items;
	
    @JsonManagedReference
	@OneToMany(targetEntity = ProductImage.class, 
			cascade = {CascadeType.ALL}, 
			fetch = FetchType.LAZY,
			mappedBy = "product")
	@Valid
	private List<ProductImage> images;

    @NotNull(message = "Please add select valid product slug.")
    @Column(name = "slug")
	private String slug;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Date createdAt;
    
	@Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    
    private Date updatedAt;
	
	public Product() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getSpecialPrice() {
		return specialPrice;
	}

	public void setSpecialPrice(Double specialPrice) {
		this.specialPrice = specialPrice;
	}

	public String getMainImagePath() {
		return mainImagePath;
	}

	public void setMainImagePath(String mainImagePath) {
		this.mainImagePath = mainImagePath;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
	
	public List<ProductImage> getImages() {
		return images;
	}

	public void setImages(List<ProductImage> images) {
		this.images = images;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
}