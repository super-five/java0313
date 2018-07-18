package day004_Quartz_001;

import java.util.Date;

public class Item {
	private Integer id;
	private String name;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	private Double minPrice;
	private Double averagePrice;
	private Double maxPrice;
	private Date date;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(Double minPrice) {
		this.minPrice = minPrice;
	}
	public Double getAveragePrice() {
		return averagePrice;
	}
	public void setAveragePrice(Double averagePrice) {
		this.averagePrice = averagePrice;
	}
	public Double getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(Double maxPrice) {
		this.maxPrice = maxPrice;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "item [name=" + name + ", minPrice=" + minPrice + ", averagePrice=" + averagePrice + ", maxPrice="
				+ maxPrice + ", date=" + date + "]";
	}
	public Item() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Item(Integer id , String name, Double minPrice, Double averagePrice, Double maxPrice, Date date) {
		super();
		this.id = id;
		this.name = name;
		this.minPrice = minPrice;
		this.averagePrice = averagePrice;
		this.maxPrice = maxPrice;
		this.date = date;
	}
	
}
