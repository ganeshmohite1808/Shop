package edu.jsp.view;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import edu.jsp.controller.Controller;
import edu.jsp.model.Product;
import edu.jsp.model.Shop;

public class View {
	static Scanner myInput = new Scanner(System.in);
	static Controller control = new Controller();//connect view and controller
	static Shop shop = new Shop();
	static {
		Shop shopExist = control.isShopExist();
		if (shopExist.getId()!=0) {
			shop = shopExist;
			System.out.println("--------WELCOME BACK TO SHOP-------");
			System.out.println("Shop details : ");
			System.out.println("Id       :  "+ shop.getId());
			System.out.println("Name     :  "+ shop.getShopName());
			System.out.println("Address  :  "+ shop.getAddress());
			System.out.println("GST      :  "+ shop.getGst());
			System.out.println("Contact  :  "+ shop.getContact());
			System.out.println("Owner    :  "+ shop.getOwner());
			
		} else {
			System.out.println("-----Welcome To Shop-----");
			System.out.print("Enter id          : ");
			shop.setId(myInput.nextInt());
			myInput.nextLine();
			System.out.print("Enter ShopName    : ");
			String shopName = myInput.nextLine();
			shop.setShopName(shopName);
			System.out.print("Enter address     : ");
			String address = myInput.nextLine();
			shop.setAddress(address);
			System.out.print("Enter GST number  : ");
			String gst = myInput.nextLine();
			shop.setGst(gst);
			System.out.print("Enter contact     : ");
			long contact = myInput.nextLong();
			myInput.nextLine();
			shop.setContact(contact);
			System.out.print("Enter owner name  : ");
			String owner = myInput.nextLine();
			shop.setOwner(owner);
			
			if (control.addShop(shop)!= 0) {
				System.out.println("Shop has been added");
			}
		}
		
	}
	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		do {
			System.out.println("------------Select which option you want to perform---------------");
			System.out.println("0. Exit");
			System.out.println("1. Add Product");
			System.out.println("2. Fetch Product");
			System.out.println("3. Update Product");
			System.out.println("4. Delete Product");
			System.out.print("Enter your desired choice : ");
			byte userChoice = myInput.nextByte();
			switch (userChoice) {
			case 0://Exit
				myInput.close();
				control.closeconnection();
				System.exit(0);
				break;
			case 1:// ADD PRODUCT
				List<Product> products = new ArrayList<Product>();
				boolean continueToAdd = true;
				
				do {
					Product pro = new Product();
					System.out.print("Enter Product id : ");
					pro.setId(myInput.nextInt());
					myInput.nextLine();
					System.out.print("Enter Product name : ");
					pro.setProductName(myInput.nextLine());
					System.out.print("Enter Product price : ");
					pro.setPrice(myInput.nextDouble());
					System.out.print("Enter Product quantity : ");
					int quantity = myInput.nextInt();
					myInput.nextLine();
					pro.setQuantity(quantity);
					if (quantity>0) {
						//set availability true
						pro.setAvailabilty(true);
					} else {
						//set availability false
						pro.setAvailabilty(false);
					}
					products.add(pro);
					System.out.print("Continue to add products ? Y/N : ");
					String toContinue = myInput.nextLine();
					if (toContinue.equalsIgnoreCase("n")) {
						continueToAdd = false;
					}
					
				} while (continueToAdd);
				control.addProducts(shop, products);
				break;
			case 2://fetch product
				ResultSet productResultSet1 = control.fetchAllProducts();
			if (productResultSet1 == null) {
				System.out.println("------No product exist-------");
			} else {
				System.out.println("Available product in shop : ");
				System.out.printf("--------------------------------------------------------------------------------%n");
				System.out.printf("| %-3s | %-27s | %-13s | %-8s | %-6s |%n", "Id", "Product Name", "Price", "Quantity", "Availability");
				try {
					while(productResultSet1.next()) {
						System.out.printf("| %-3d |",productResultSet1.getInt(1) );
						System.out.printf(" %-27s |",productResultSet1.getString(2));
						System.out.printf(" %-13f |",productResultSet1.getDouble(3));
						System.out.printf(" %-8d |",productResultSet1.getInt(4));
						System.out.printf(" %-12b |%n",productResultSet1.getBoolean(5));
					}
					System.out.printf("-------------------------------------------------------------------------------%n");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				break;
			case 3://Update Product
				ResultSet productResultSet11 = control.fetchAllProducts();
				if (productResultSet11 == null) {
					System.out.println("No product exist");
				} else {
					System.out.println("Available product in shop : ");
					System.out.printf("-------------------------------------------------------------------------------%n");
					System.out.printf("| %-3s | %-27s | %-13s | %-8s | %-6s |%n", "Id", "Product Name", "Price", "Quantity", "Availability");
					try {
						while(productResultSet11.next()) {
							System.out.printf("| %-3d |",productResultSet11.getInt(1) );
							System.out.printf(" %-27s |",productResultSet11.getString(2));
							System.out.printf(" %-13f |",productResultSet11.getDouble(3));
							System.out.printf(" %-8d |",productResultSet11.getInt(4));
							System.out.printf(" %-12b |%n",productResultSet11.getBoolean(5));
						}
						System.out.printf("----------------------------------------------------------------------------%n");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					myInput.nextLine();
					System.out.println("Enter product id to update : ");
					int updatepro = myInput.nextInt();
					myInput.nextLine();
					ResultSet fetched_result = control.fetchUpdateProduct(updatepro);
					boolean continuetoAdd = true;
					do {
						System.out.printf("---------------------------------------------------------------------------------%n");
						System.out.printf("| %-3s | %-27s | %-13s | %-8s | %-6s |%n", "Id", "Product Name", "Price", "Quantity", "Availability");
						try {
							while(fetched_result.next()) {
								System.out.printf("| %-3d |",fetched_result.getInt(1) );
								System.out.printf(" %-27s |",fetched_result.getString(2));
								System.out.printf(" %-13f |",fetched_result.getDouble(3));
								System.out.printf(" %-8d |",fetched_result.getInt(4));
								System.out.printf(" %-12b |%n",fetched_result.getBoolean(5));
							}
							System.out.printf("------------------------------------------------------------------------------%n");
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println("PLEASE REMEMBER THE ID, What you want to update ?\n 1. Product Name\n 2. Price\n 3. Quantity\n");
						System.out.print("Select your Desire option : ");
						Product productUpdate = new Product();
						byte userInput = myInput.nextByte();
						myInput.nextLine();
						switch (userInput) {
						case 1://Product Name
							System.out.print("Enter the Id : ");
							int pro_name_id = myInput.nextInt();
							myInput.nextLine();
							System.out.print("Enter new Product Name : ");
							productUpdate.setProductName(myInput.nextLine());
							String productName = productUpdate.getProductName();
							int nameUpdateCount = control.updateProductName(pro_name_id, productName);
							if(nameUpdateCount != 0) {
								System.out.println("------Product Name has been Updated-------");
							}
							else {
								System.out.println("-------Unable to update the name-------");
							}
							break;
						case 2://Price
							System.out.print("Enter the Id : ");
							int pro_price_id = myInput.nextInt();
							myInput.nextLine();
							System.out.print("Enter new Price : ");
							productUpdate.setPrice(myInput.nextDouble());
							myInput.nextLine();
							double price = productUpdate.getPrice();
							int priceUpdatedcount = control.updateProductprice(pro_price_id, price);
							if(priceUpdatedcount != 0) {
								System.out.println("-------Price has been Updated-------");
							}
							else {
								System.out.println("-------Unable to Update The Price-------");
							}
							break;
						case 3://Quantity
							System.out.print("Enter the Id : ");
							int pro_quantity_id = myInput.nextInt();
							System.out.print("Enter new Quantity : ");
							int quantity1 = myInput.nextInt();
							productUpdate.setQuantity(quantity1);
							myInput.nextLine();
							double quantity = productUpdate.getPrice();
							if (quantity1>0) {
								//set availability true
								productUpdate.setAvailabilty(true);
							} else {
								//set availability false
								productUpdate.setAvailabilty(false);
							}
							int quantityUpdateCount = control.updateProductQuantity(pro_quantity_id, quantity1);
							if(quantityUpdateCount != 0) {
								System.out.println("------Quantity has been Updated------");
							}
							else {
								System.out.println("--------Unable to update the Quantity-------");
							}
							
							break;

						default:
							System.out.println("--------Invalid Selection------");
							break;
						}
						System.out.println("Continue To Update Product Y/N ?");
						String continueAdd = myInput.nextLine();
						if(continueAdd.equalsIgnoreCase("n")) {
								continuetoAdd = false;
							}
					} while (continuetoAdd);
				}
				break;
			case 4:
				ResultSet productResultSet = control.fetchAllProducts();
				if (productResultSet == null) {
					System.out.println("No product exist");
				} else {
					System.out.println("Available product in shop : ");
					System.out.printf("-------------------------------------------------------------------------------------------------------%n");
					System.out.printf("| %-3s | %-27s | %-13s | %-8s | %-6s |%n", "Id", "Product Name", "Price", "Quantity", "Availability");
					try {
						while(productResultSet.next()) {
							System.out.printf("| %-3d |",productResultSet.getInt(1) );
							System.out.printf(" %-27s |",productResultSet.getString(2));
							System.out.printf(" %-13f |",productResultSet.getDouble(3));
							System.out.printf(" %-8d |",productResultSet.getInt(4));
							System.out.printf(" %-12b |%n",productResultSet.getBoolean(5));
						}
						System.out.printf("---------------------------------------------------------------------------------------------------%n");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					myInput.nextLine();
					System.out.println("Enter product id to remove : ");
					int removepro = myInput.nextInt();
					myInput.nextLine();
					if(control.removeProduct(removepro)!=0) {
						System.out.println("------Product has been Removed--------");
					}
					else {
						System.out.println("Unable to remove product");
					}
				}
				break;
			
			default:
				System.out.println("-----Invalid Selection------");
				break;
			}
		} while (true);
//		
	

}
}

