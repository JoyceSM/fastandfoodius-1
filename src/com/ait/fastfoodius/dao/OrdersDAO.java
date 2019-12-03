package com.ait.fastfoodius.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ait.fastfoodius.bean.OrderBean;
import com.ait.fastfoodius.resource.DatabaseConnection;
import com.ait.fastfoodius.resource.deliveryStatus;
import com.ait.fastfoodius.resource.paymentStatus;

public class OrdersDAO {
	// Connection DB
	private Connection con = null;
	OrderBean order = new OrderBean();

	public void insertOrder(OrderBean order) {
		PreparedStatement stmtp = null;
		String cmd = "insert into orders (customer_ID,orderAddress,orderCity,orderPostalCode,orderEmailAddress,"
				+ " orderPhoneNumber,orderDate,paymentStatus,deliveryStatus,requiredDeliveryDate,orderchannel)"
				+ "values (?,?,?,?,?,?,?,?,?,?,?);";
		try {
			con = new DatabaseConnection().connect();
			stmtp = con.prepareStatement(cmd);
			stmtp.setInt(1, order.getCustomer_ID());
			stmtp.setString(2, order.getOrderAddress());
			stmtp.setString(3, order.getOrderCity());
			stmtp.setString(4, order.getOrderPostalCode());
			stmtp.setString(5, order.getOrderPostalCode());
			stmtp.setString(6, order.getOrderPhoneNumber());
			stmtp.setDate(7, new java.sql.Date(order.getOrderDate().getTime()));
			stmtp.setString(8, order.getPaymentStatus());
			stmtp.setString(9, order.getDeliveryStatus());
			stmtp.setDate(10, new java.sql.Date(order.getOrderDate().getTime()));
			stmtp.setString(11, order.getOrderChannel());
			System.out.println(stmtp.toString());
			stmtp.executeUpdate();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public List<OrderBean> retrieveOrder(int customerID) {
		PreparedStatement stmtp = null;
		ResultSet rs = null;
		List<OrderBean> orderlist = new ArrayList<OrderBean>();
		String cmd = "select * from orders where customer_ID = ? ;";
		try {
			con = new DatabaseConnection().connect();
			stmtp = con.prepareStatement(cmd);
			stmtp.setInt(1, customerID);
			rs = stmtp.executeQuery();
			rs = stmtp.executeQuery();
			while (rs.next()) {
				OrderBean order = new OrderBean();
				order.setOrder_ID(rs.getInt("order_ID"));
				order.setCustomer_ID(rs.getInt("customer_ID"));
				order.setOrderAddress(rs.getString("orderAddress"));
				order.setOrderCity(rs.getString("orderCity"));
				order.setOrderPostalCode(rs.getString("orderPostalCode"));
				order.setOrderEmailAddress(rs.getString("orderEmailAddress"));
				order.setOrderPhoneNumber(rs.getString("orderPhoneNumber"));
				order.setOrderDate(rs.getDate("orderDate"));
				order.setRequiredDeliveryDate(rs.getDate("requiredDeliveryDate"));
				order.setPaymentStatus(rs.getString("paymentStatus"));
				order.setDeliveryStatus(rs.getString("deliveryStatus"));
				order.setDeliveredby(rs.getString("deliveredBy"));
				order.setWhenDelivered(rs.getDate("whenDelivered"));
				orderlist.add(order);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		return orderlist;

	}

	public int retrieveLastOrder(Integer customerID) {
		PreparedStatement stmtp = null;
		ResultSet rs = null;
		int maxOrder = 0;
		String cmd = "select max(order_id) from orders where customer_ID = ? ;";
		try {
			con = new DatabaseConnection().connect();
			stmtp = con.prepareStatement(cmd);
			stmtp.setInt(1, customerID);
			rs = stmtp.executeQuery();
			while (rs.next()) {
				// fullfill order
				order.setOrder_ID(rs.getInt(1));
			}
			maxOrder = order.getOrder_ID();
			return maxOrder;
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return maxOrder;
	}

	public List<OrderBean> retrieveAllOrders() {
		PreparedStatement stmtp = null;
		List<OrderBean> orderlist = new ArrayList<OrderBean>();
		ResultSet rs = null;
		String cmd = "select order_ID, customer_ID,  orderAddress, orderCity, orderPostalCode, orderEmailAddress, "
				+ "orderPhoneNumber,orderDate, paymentStatus, "
				+ "requiredDeliveryDate,  deliveredBy, deliveryStatus, whenDelivered from orders order by deliveryStatus, orderDate ;";
		try {
			con = new DatabaseConnection().connect();
			stmtp = con.prepareStatement(cmd);
			System.out.println(cmd.toString());
			rs = stmtp.executeQuery();
			while (rs.next()) {
				OrderBean order = new OrderBean();
				order.setOrder_ID(rs.getInt("order_ID"));
				order.setCustomer_ID(rs.getInt("customer_ID"));
				order.setOrderAddress(rs.getString("orderAddress"));
				order.setOrderCity(rs.getString("orderCity"));
				order.setOrderPostalCode(rs.getString("orderPostalCode"));
				order.setOrderEmailAddress(rs.getString("orderEmailAddress"));
				order.setOrderPhoneNumber(rs.getString("orderPhoneNumber"));
				order.setOrderDate(rs.getDate("orderDate"));
				order.setRequiredDeliveryDate(rs.getDate("requiredDeliveryDate"));
				order.setPaymentStatus(rs.getString("paymentStatus"));
				order.setDeliveryStatus(rs.getString("deliveryStatus"));
				order.setDeliveredby(rs.getString("deliveredBy"));
				order.setWhenDelivered(rs.getDate("whenDelivered"));
				orderlist.add(order);
			}

			System.out.println(stmtp.toString());
			System.out.println(order.toString());

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return orderlist;
	}

	public void retrieveOrderByOrderChannel(String orderChannel) {
		PreparedStatement stmtp = null;
		ResultSet rs = null;
		String cmd = "select * from orders where orderChannel = ? ;";
		try {
			con = new DatabaseConnection().connect();
			stmtp = con.prepareStatement(cmd);
			stmtp.setString(1, orderChannel);
			rs = stmtp.executeQuery();
			while (rs.next()) {
				// LoadItems
				// orderItem.setOrderChannel(rs.getString(1));
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public void retrieveOrder(Date requiredDeliveryDate) {
		PreparedStatement stmtp = null;
		ResultSet rs = null;
		String cmd = "select * from orders where requiredDeliveryDate = ? ;";
		try {
			con = new DatabaseConnection().connect();
			stmtp = con.prepareStatement(cmd);
			stmtp.setDate(1, new java.sql.Date(requiredDeliveryDate.getTime()));
			rs = stmtp.executeQuery();
			while (rs.next()) {
				// fullfill order
				order.setOrder_ID(rs.getInt(1));
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public void retrieveOrderbyID(int orderId) {
		PreparedStatement stmtp = null;
		ResultSet rs = null;
		String cmd = "select * from orders where orderId = ? ;";
		try {
			con = new DatabaseConnection().connect();
			stmtp = con.prepareStatement(cmd);
			stmtp.setInt(1, orderId);
			rs = stmtp.executeQuery();
			while (rs.next()) {
				// fullfill order
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public List<OrderBean> retrieveOrderAssigned(String deliveredBy) {
		PreparedStatement stmtp = null;
		List<OrderBean> orderlist = new ArrayList<OrderBean>();
		ResultSet rs = null;
		String cmd = "select order_ID, orderAddress, orderCity, requiredDeliveryDate, deliveryStatus, paymentStatus, orderPhoneNumber "
				+ "from orders where deliveryStatus in ( ? , ? ) and deliveredBy = ? ;";

		try {
			con = new DatabaseConnection().connect();
			stmtp = con.prepareStatement(cmd);
			stmtp.setString(1, deliveryStatus.ASSIGNED.getStatus().toString());
			stmtp.setString(2, deliveryStatus.ONTHEWAY.getStatus().toString());
			stmtp.setString(3, deliveredBy);
			rs = stmtp.executeQuery();

			while (rs.next()) {
				OrderBean order = new OrderBean();
				order.setOrder_ID(rs.getInt("order_ID"));
				order.setOrderAddress(rs.getString("orderAddress"));
				order.setOrderCity(rs.getString("orderCity"));
				order.setRequiredDeliveryDate(rs.getDate("requiredDeliveryDate"));
				order.setPaymentStatus(rs.getString("paymentStatus"));
				order.setDeliveryStatus(rs.getString("deliveryStatus"));
				order.setOrderPhoneNumber(rs.getString("orderPhoneNumber"));
				orderlist.add(order);

			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return orderlist;
	}


	public void updateOrderDelivered(String loginDeliverer, int orderId) {
		PreparedStatement stmtp = null;
		String cmd = "update orders set deliveryStatus = ?, paymentStatus = ? , deliveredBy = ?, whenDelivered = ? where order_id = ? ;";
		try {
			con = new DatabaseConnection().connect();
			stmtp = con.prepareStatement(cmd);
			stmtp.setString(1, deliveryStatus.DELIVERED.getStatus().toString());
			stmtp.setString(2, paymentStatus.PAID_ON_DELIVERY.toString());
			stmtp.setString(3, loginDeliverer);
			//stmtp.setDate(4, new java.sql.Date());
			stmtp.setInt(5, orderId);
			stmtp.executeUpdate();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}
	

	public List<OrderBean> retrieveOrderforDelivery() {
		PreparedStatement stmtp = null;
		List<OrderBean> orderlist = new ArrayList<OrderBean>();
		ResultSet rs = null;
		String cmd = "select order_ID, orderAddress, orderCity, requiredDeliveryDate, paymentStatus, orderPhoneNumber, deliveryStatus "
				+ "from orders where deliveryStatus = ? ;";

		try {
			con = new DatabaseConnection().connect();
			stmtp = con.prepareStatement(cmd);
			stmtp.setString(1, deliveryStatus.PENDING.getStatus().toString());
			rs = stmtp.executeQuery();

			while (rs.next()) {
				OrderBean order = new OrderBean();
				order.setOrder_ID(rs.getInt("order_ID"));
				order.setOrderAddress(rs.getString("orderAddress"));
				order.setOrderCity(rs.getString("orderCity"));
				order.setRequiredDeliveryDate(rs.getDate("requiredDeliveryDate"));
				order.setPaymentStatus(rs.getString("paymentStatus"));
				order.setOrderPhoneNumber(rs.getString("orderPhoneNumber"));
				order.setDeliveryStatus(rs.getString("deliveryStatus"));
				orderlist.add(order);
			}

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return orderlist;
	}

	public List<OrderBean> retrieveOrderDelivered() {
		PreparedStatement stmtp = null;
		List<OrderBean> orderlist = new ArrayList<OrderBean>();
		ResultSet rs = null;
		String cmd = "select order_ID, deliveryStatus, deliveredBy, whenDelivered "
				+ "from orders where deliveryStatus = ? ;";

		try {
			con = new DatabaseConnection().connect();
			stmtp = con.prepareStatement(cmd);
			stmtp.setString(1, deliveryStatus.DELIVERED.toString());
			rs = stmtp.executeQuery();

			while (rs.next()) {
				OrderBean order = new OrderBean();
				order.setOrder_ID(rs.getInt("order_ID"));
				order.setDeliveryStatus(rs.getString("deliveryStatus"));
				order.setDeliveredby(rs.getString("deliveredby"));
				order.setWhenDelivered(rs.getDate("whenDelivered"));
				orderlist.add(order);
			}

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return orderlist;
	}



	public void updateAssignedDriver(int orderId, String driverEmail) {
		PreparedStatement stmtp = null;
		String cmd = "update orders set deliveredby = ?, deliveryStatus = ? where order_id = ?;";

		try {
			con = new DatabaseConnection().connect();
			stmtp = con.prepareStatement(cmd);
			stmtp.setString(1, driverEmail);
			stmtp.setString(2, deliveryStatus.ASSIGNED.getStatus().toString());
			stmtp.setInt(3, orderId);
			stmtp.executeUpdate();
			System.out.println(stmtp.toString());
		} catch (Exception e1) {
			e1.printStackTrace();
			System.out.println(stmtp.toString());
		}
	}
	
	
	public void updateOrderOnTheWay(int orderId) {
		PreparedStatement stmtp = null;
		String cmd = "update orders set deliveryStatus = ? where order_id = ?;";

		try {
			con = new DatabaseConnection().connect();
			stmtp = con.prepareStatement(cmd);
			stmtp.setString(1, deliveryStatus.ONTHEWAY.getStatus().toString());
			stmtp.setInt(2, orderId);
			stmtp.executeUpdate();
			System.out.println(stmtp.toString());
		} catch (Exception e1) {
			e1.printStackTrace();
			System.out.println(stmtp.toString());
		}

	}
	
	
	public void updateOrderDelivered(int orderId,java.util.Date date) {
		PreparedStatement stmtp = null;
		String cmd = "update orders set deliveryStatus = ?, whenDelivered = ?, paymentStatus = ?  where order_id = ?;";

		try {
			con = new DatabaseConnection().connect();
			stmtp = con.prepareStatement(cmd);
			stmtp.setString(1, deliveryStatus.DELIVERED.getStatus().toString());
			stmtp.setDate(2, new java.sql.Date(date.getTime()));
			stmtp.setString(3, paymentStatus.PAID_ON_DELIVERY.toString());
			stmtp.setInt(4, orderId);
			stmtp.executeUpdate();
			System.out.println(stmtp.toString());
		} catch (Exception e1) {
			e1.printStackTrace();
			System.out.println(stmtp.toString());
		}

	}


}
