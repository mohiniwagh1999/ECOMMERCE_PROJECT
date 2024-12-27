package com.ecom.entity;

import java.math.BigDecimal;
import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String description;
	private String title;
	private BigDecimal unitPrice;
	private String imageUrl;
	private boolean active;
	private Integer unitStock;
	private Date dateCreated;
	private Date LastUpdate;
	
	
	@ManyToOne
	@JoinColumn(name="categoryId" ,nullable=false)
	private ProductCategory category;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
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


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public BigDecimal getUnitPrice() {
		return unitPrice;
	}


	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}


	public String getImageUrl() {
		return imageUrl;
	}


	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}


	public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}


	public Integer getUnitStock() {
		return unitStock;
	}


	public void setUnitStock(Integer unitStock) {
		this.unitStock = unitStock;
	}


	public Date getDateCreated() {
		return dateCreated;
	}


	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}


	public Date getLastUpdate() {
		return LastUpdate;
	}


	public void setLastUpdate(Date lastUpdate) {
		LastUpdate = lastUpdate;
	}


	public ProductCategory getCategory() {
		return category;
	}


	public void setCategory(ProductCategory category) {
		this.category = category;
	}
	
	
	
	
	

}
