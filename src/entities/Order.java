package entities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entities.enums.OrderStatus;

public class Order {

	private static SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	private static SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
	
	private Date moment;
	private OrderStatus status;
	
	private Client client;
	private List<OrderItem> items = new ArrayList<OrderItem>();
	
	public Order() {
	}
	
	public Order(Date moment, OrderStatus status, Client client) {
		this.moment = moment;
		this.status = status;
		this.client = client;
	}
	
	public Date getMoment() {
		return moment;
	}
	
	public void setMoment(Date moment) {
		this.moment = moment;
	}
	
	public OrderStatus getStatus() {
		return status;
	}
	
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	
	public Client getClient() {
		return client;
	}
	
	public void setClient(Client client) {
		this.client = client;
	}
	
	public void addItem(OrderItem item) {
		items.add(item);
	}
	
	public void removeItem(OrderItem item) {
		items.remove(item);
	}
	
	public Double total() {
		double sum = 0.0;
		for (OrderItem i : items) {
			sum += i.subTotal();
		}
		return sum;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Order moment: ")
		.append(sdf1.format(moment))
		.append("\n")
		.append("Order status: ")
		.append(status).append("\n")		
		.append("Client: ")
		.append(client.getName())
		.append(" (")
		.append(sdf2.format(client.getBirthDate()))
		.append(") - ")
		.append(client.getEmail())
		.append("\n")
		.append("Order items:")
		.append("\n");
		double sum = 0.0;
		for (OrderItem i : items) {
			sum += i.subTotal();
			sb.append(i.getProduct().getName())
			.append(", $")
			.append(String.format("%.2f", i.getPrice()))
			.append(", Quantity: ")
			.append(i.getQuantity())
			.append(", Subtotal: $")
			.append(String.format("%.2f%n", i.subTotal()));
		}
		sb.append("Total price: $")
		.append(String.format("%.2f", sum));
		return sb.toString();
	}
}
