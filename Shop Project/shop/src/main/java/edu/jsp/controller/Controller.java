package edu.jsp.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;
import java.util.Properties;

import org.postgresql.Driver;

import edu.jsp.model.Product;
import edu.jsp.model.Shop;

public class Controller {
	static Connection cnt = null;
	static {
		
		try {
			Driver d = new Driver();
			DriverManager.registerDriver(d);
			FileInputStream fis = new FileInputStream("dbconfig.properties");
			Properties p = new Properties();
			p.load(fis);
			cnt = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Shop", p);
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//Shop
	public int addShop(Shop shop) {
		try {
			PreparedStatement pstmt = cnt.prepareStatement("INSERT INTO shop VALUES(?,?,?,?,?,?)");
			pstmt.setInt(1, shop.getId());
			pstmt.setString(2, shop.getShopName());
			pstmt.setString(3, shop.getGst());
			pstmt.setLong(4, shop.getContact());
			pstmt.setString(5, shop.getAddress());
			pstmt.setString(6, shop.getOwner());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	
	}
	
	public Shop isShopExist() {
		try {
			Statement stmt = cnt.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM shop");
			Shop isShopExist = new Shop();
			while(rs.next()) {
				isShopExist.setId(rs.getInt(1));
				isShopExist.setShopName(rs.getString(2));
				isShopExist.setGst(rs.getString(3));
				isShopExist.setContact(rs.getLong(4));
				isShopExist.setAddress(rs.getString(5));
				isShopExist.setOwner(rs.getString(6));
			}
			return isShopExist;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	//Products
	public void addProducts(Shop shop, List<Product> products) {

		for (Product product : products) {
			try {
				//Insert data to product table
				PreparedStatement pstmt = cnt.prepareStatement("INSERT INTO product VALUES(?,?,?,?,?)");
				pstmt.setInt(1, product.getId());
				pstmt.setString(2, product.getProductName());
				pstmt.setDouble(3, product.getPrice());
				pstmt.setInt(4, product.getQuantity());
				pstmt.setBoolean(5, product.isAvailabilty());
				pstmt.executeUpdate();
			}
			 catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			 }
			//Insert data to product table
			try {
				PreparedStatement pstmt2 = cnt.prepareStatement("INSERT INTO shop_product VALUES(?,?)");
				pstmt2.setInt(1, shop.getId());
				pstmt2.setInt(2, product.getId());
				pstmt2.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	public int removeProduct(int removepro) {
		try {
			CallableStatement precall = cnt.prepareCall("CALL remove_product(?,?,?)");
			precall.registerOutParameter(1, Types.INTEGER);
			precall.setInt(2, removepro);
			precall.registerOutParameter(3, Types.INTEGER);
			precall.execute();
			int previousrecord = precall.getInt(1);
			int currentrecord = precall.getInt(3);
			return previousrecord-currentrecord;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	public ResultSet fetchUpdateProduct(int updatepro) {
		try {
			PreparedStatement fetch_pstmt = cnt.prepareStatement("SELECT * FROM product WHERE id=?");
			fetch_pstmt.setInt(1, updatepro);
			ResultSet fetch_rs = fetch_pstmt.executeQuery();
			return fetch_rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public int updateProductprice(int pro_price_id, double price) {
		try {
			PreparedStatement price_pstmt = cnt.prepareStatement("UPDATE product SET product_price=? WHERE id=? ");
			price_pstmt.setDouble(1, price);
			price_pstmt.setInt(2, pro_price_id);
			int priceUpdatedCount = price_pstmt.executeUpdate();
			return priceUpdatedCount;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	public int updateProductName(int pro_name_id, String productName) {
		try {
			PreparedStatement name_pstmt = cnt.prepareStatement("UPDATE product SET product_name=? WHERE id=?");
			name_pstmt.setString(1, productName);
			name_pstmt.setInt(2, pro_name_id);
			int nameUpdateCount = name_pstmt.executeUpdate();
			return nameUpdateCount;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	public int updateProductQuantity(int pro_quantity_id, int quantity) {
		try {
			PreparedStatement quantity_pstmt = cnt.prepareStatement("UPDATE product SET product_quantity=? WHERE id=?");
			quantity_pstmt.setInt(1, quantity);
			quantity_pstmt.setInt(2, pro_quantity_id);
			int quantityUpdateCount = quantity_pstmt.executeUpdate();
			return quantityUpdateCount;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	public ResultSet fetchAllProducts() {
		try {
			Statement stmt = cnt.createStatement();
			ResultSet products = stmt.executeQuery("SELECT * FROM product");
			byte count=0;
			while(products.next()) {				//WHY
				if(++count>0) {
					break;
				}
			}
			if(count==1) {
				return stmt.executeQuery("SELECT * FROM product");
			}
			else {
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void closeconnection() {
		if (cnt!=null) {
			try {
				cnt.close();
				System.out.println("Connection Closed");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
