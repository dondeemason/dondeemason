package com.oms.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.oms.dto.IsUnique;
import com.oms.model.ExpectedOrders;
import com.oms.model.Users;
import com.oms.model.showProductForProduction;



public class DBQueries {
	private int numberOfRow;
	final DBConnect DB = new DBConnect("training-db.cosujmachgm3.ap-southeast-1.rds.amazonaws.com","ORCL","TRNG","cpi12345");
	
	Connection conn = null;
	Statement stmt = null; 
	ResultSet rs = null;
	
	public boolean isUserAuthenticated(String username, String password) {
		String usernameDB = "";
        String passwordDB = "";
        
        boolean isExist = false;
		try {
			conn = DB.getConnection();
			stmt = conn.createStatement();
			String query = "SELECT username, password, role_id FROM users_1";
			
			
			rs = stmt.executeQuery(query);

			while(rs.next()) {
				usernameDB = rs.getString("username");
				passwordDB = rs.getString("password");
				
				if(username.equals(usernameDB) && password.equals(passwordDB)) {
					isExist = true;
				}
			}
			
		}catch(Exception e) {
			System.out.println(e);
		}
		
		return isExist;
	}
	
	public void addNewUser(String username, String email, String password, int role) {
		try {
			conn = DB.getConnection();
			conn.setAutoCommit(false);
			
			String query = "INSERT INTO users_1 (user_id, role_id, username, password, email, user_status) "
					+ "VALUES (user_id_1.nextval, ?, ?, ?, ?, 0)";
			
			PreparedStatement pstmt = conn.prepareStatement(query);	

			pstmt.setInt(1, role);
			pstmt.setString(2, username);
			pstmt.setString(3, password);
			pstmt.setString(4, email);
			
			Integer res = pstmt.executeUpdate();
			
			System.out.println("Add New User Success!");

			conn.commit();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Failed to add new user");
		}
	}
	
	public IsUnique isUsernameUnique(String username, String email) {
		conn = DB.getConnection();
		
		IsUnique isUnique = new IsUnique(true, "unique");
		
		try {
			conn = DB.getConnection();
			stmt = conn.createStatement();
			String query = "SELECT username, email FROM users_1";
			System.out.println("username is unique query");
			rs = stmt.executeQuery(query);

			while(rs.next()) {
				String usernameDB = rs.getString("username");
				String emailDB = rs.getString("email");
				
				if(username.equals(usernameDB)) {
					isUnique.setUnique(false);
					isUnique.setMessage("The username you entered is already being used. Please try again");
				}
				else if(email.equals(emailDB)) {
					isUnique.setUnique(false);
					isUnique.setMessage("The email you entered is already being used. Please try again");
				}
			}

		}catch(Exception e) {
			System.out.println(e);
		}
		
		return isUnique;
		
	}
	
	public IsUnique isEmailUnique(String email) {
		conn = DB.getConnection();
		
		IsUnique isUnique = new IsUnique(true, "unique");
		
		try {
			conn = DB.getConnection();
			stmt = conn.createStatement();
			String query = "SELECT email FROM users_1";
			rs = stmt.executeQuery(query);

			while(rs.next()) {
				String emailDB = rs.getString("email");
				
				if(email.equals(emailDB)) {
					isUnique.setUnique(false);
					isUnique.setMessage("The email you entered is already being used. Please try again");
				}
			}

		}catch(Exception e) {
			System.out.println(e);
		}
		
		return isUnique;
		
	}
	
	public void forgotPassword(String username, String email, String newPassword) {
		try {
			conn = DB.getConnection();
			conn.setAutoCommit(false);
			
			String query = "UPDATE users_1 SET password = ? WHERE username = ? AND email = ?";
	
			PreparedStatement pstmt = conn.prepareStatement(query);	

			pstmt.setString(1, newPassword);
			pstmt.setString(2, username);
			pstmt.setString(3, email);

			Integer res = pstmt.executeUpdate();
			
			System.out.println("Change Password Success!");

			conn.commit();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Failed to change password");
		}
	}
	
	public boolean isCombinationCorrect(String username, String email) {
		String usernameDB = "";
        String emailDB = "";
        
        boolean isCorrect = false;
        
		try {
			conn = DB.getConnection();
			stmt = conn.createStatement();
			
			String query = "SELECT username, email FROM users_1";
			
			rs = stmt.executeQuery(query);

			while(rs.next()) {
				usernameDB = rs.getString("username");
				emailDB = rs.getString("email");
				
				if(username.equals(usernameDB) && email.equals(emailDB)) {
					isCorrect = true;
				}
			}
			
		}catch(Exception e) {
			System.out.println("DBQueries.isCombinationCorrect() " + e);
		}
		
		return isCorrect;
	}
	
	public boolean isCredentialAuthenticated(String username, String email, int role) {
		
		String usernameDB = "";
        String emailDB = "";
		int roleDB;
		
		boolean isCorrect = false;
		
		try {
			
			conn = DB.getConnection();
			stmt = conn.createStatement();
			String query = "SELECT username, email, role_id FROM users_1";
			rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				usernameDB = rs.getString("username");
				emailDB = rs.getString("email");
				roleDB = rs.getInt("role_id");
				
				if(username.equals(usernameDB) && email.equals(emailDB) && role == roleDB) {
					isCorrect = true;
				}
			}
		} catch (Exception e) {
			System.out.println("DBQueries.isCredentialAuthenticated() " + e);
		}
		return isCorrect;
	}
	
	public void disableUser(int id) {
		try {
			conn = DB.getConnection();
			conn.setAutoCommit(false);
			
			String query = "UPDATE users_1 SET user_status = 1 WHERE user_id = ?";
	
			PreparedStatement pstmt = conn.prepareStatement(query);	

			pstmt.setInt(1, id);

			Integer res = pstmt.executeUpdate();
			
			System.out.println("Disable User Success!");

			conn.commit();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Failed to Disable User");
		}
	}
	
	public void enableUser(int id) {
		try {
			conn = DB.getConnection();
			conn.setAutoCommit(false);
			
			String query = "UPDATE users_1 SET user_status = 0 WHERE user_id = ?";
	
			PreparedStatement pstmt = conn.prepareStatement(query);	

			pstmt.setInt(1, id);

			Integer res = pstmt.executeUpdate();
			
			System.out.println("Enable User Success!");

			conn.commit();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Failed to enable User");
		}
	}
	
	// Save for future usage
	public Users getUserById(int id) {
		Users users = new Users();
		
		try {
			conn = DB.getConnection();
			stmt = conn.createStatement();
			
			String query = "SELECT * FROM users_1 WHERE user_id = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(query);	
			pstmt.setInt(1, id);
			
			ResultSet rs = pstmt.executeQuery();

			if(rs.next()) {
				users.setUserId(rs.getInt("user_id"));
				users.setRoleId(rs.getInt("role_id"));
				users.setPassword(rs.getString("password"));
				users.setUsername(rs.getString("username"));
				users.setEmail(rs.getString("email"));
				users.setUserStatus(rs.getString("user_status"));
			}
			
			rs.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("Failed to get User Id : "+ e);
		}
		
		return users;
	}
	
	public void editUser(int id, String username, String email, int roleId) {
		
		try {
			conn = DB.getConnection();
			conn.setAutoCommit(false);
			
			String query = "UPDATE users_1 SET username = ?, email = ?, role_id = ? WHERE user_id = ?";
	
			PreparedStatement pstmt = conn.prepareStatement(query);	

			pstmt.setString(1, username);
			pstmt.setString(2, email);
			pstmt.setInt(3, roleId);
			pstmt.setInt(4, id);

			Integer res = pstmt.executeUpdate();
			
			System.out.println("Edit User Success!");

			conn.commit();
			pstmt.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Failed to edit user");
		}
		
	}
	
	public List<Users> getAllUsers(int pageNum, int pageSize, String orderby, String sortby){
		List<Users> allUserList = new ArrayList<>();
		
		try {
			conn = DB.getConnection();
			
			String query = "select * from users_1 order by "+orderby+" "+sortby+" offset "+pageNum+" rows fetch next "+pageSize+" rows only"; // pagination

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
		
			
			while(rs.next()) {
				Users users = new Users();
				users.setUserId(rs.getInt("user_id"));
				users.setRoleId(rs.getInt("role_id"));
				users.setUsername(rs.getString("username"));
				users.setPassword(rs.getString("password"));
				users.setEmail(rs.getString("email"));
				users.setUserStatus(rs.getString("user_status"));
				
				int roleId = rs.getInt("user_id");
				
//				if(roleId == 1) {
//					users.setRoleDescription("Administrator");
//				} 
//				if (roleId == 2) {
//					users.setRoleDescription("Producer");
//				}
//				if (roleId == 3) {
//					users.setRoleDescription("Order Taker");
//				}
//				if (roleId == 4) {
//					users.setRoleDescription("Auditor");
//				}
				
				allUserList.add(users);
			}
			
			rs.close();
			rs = stmt.executeQuery("select count(*) totalpages from users_1");
			
			if(rs.next()) {
				this.numberOfRow = rs.getInt("totalpages");
			}
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Failed to list all users");
		}
		
		return allUserList;
	}
	
	public int getNumberOfRow() { return numberOfRow; }
	
	public boolean isUserPasswordAuthenticated(int id, String oldPassword) {
		String oldPasswordDB = "";
		
		boolean isCorrect = false;
		
		try {
			conn = DB.getConnection();
			stmt = conn.createStatement();
			
			String query = "SELECT password FROM users_1 WHERE user_id = ?";
			System.out.println("nagquery");
			PreparedStatement pstmt = conn.prepareStatement(query);	
			pstmt.setInt(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			System.out.println(rs.next());
			
				oldPasswordDB = rs.getString("password");
				System.out.println("old:"+oldPasswordDB);
				
				if(oldPassword.equals(oldPasswordDB)) {
					isCorrect = true;
					
					System.out.println("nagtrue naman");
				}

			
			System.out.println("tapos na");
			
			rs.close();
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return isCorrect;
	}
	
	public void changeUserProfileEmail(int id, String newEmail) {
		
		try {
			conn = DB.getConnection();
			conn.setAutoCommit(false);
			
			String query = "UPDATE users_1 SET email = ? WHERE user_id = ?";
	
			PreparedStatement pstmt = conn.prepareStatement(query);	

			pstmt.setString(1, newEmail);
			pstmt.setInt(2, id);

			Integer res = pstmt.executeUpdate();
			
			System.out.println("Change User Email Success!");

			conn.commit();
			pstmt.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Failed to change User Email");
		}
	}
	
	public void changeUserProfilePassword(int id, String newPassword) {
		
		try {
			conn = DB.getConnection();
			conn.setAutoCommit(false);
			
			String query = "UPDATE users_1 SET password = ? WHERE user_id = ?";
	
			PreparedStatement pstmt = conn.prepareStatement(query);	

			pstmt.setString(1, newPassword);
			pstmt.setInt(2, id);

			Integer res = pstmt.executeUpdate();
			
			System.out.println("Change User Password Success!");

			conn.commit();
			pstmt.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Failed to change User Password");
		}
	}

	public Users getUserByUsername(String username) {
		Users users = new Users();
		
		try {
			conn = DB.getConnection();
			stmt = conn.createStatement();
			
			String query = "SELECT * FROM users_1 WHERE username = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(query);	
			pstmt.setString(1, username);
			
			ResultSet rs = pstmt.executeQuery();

			if(rs.next()) {
				users.setUserId(rs.getInt("user_id"));
				users.setRoleId(rs.getInt("role_id"));
				users.setPassword(rs.getString("password"));
				users.setUsername(rs.getString("username"));
				users.setEmail(rs.getString("email"));
				users.setUserStatus(rs.getString("user_status"));
			}
			
			rs.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("Failed to get Username : "+ e);
		}
		
		return users;
	}

	public List<Users> searchUserByUsername(String username) {
        List<Users> userList = new ArrayList<>();

        try {
            conn = DB.getConnection();
            stmt = conn.createStatement();

            String query = "SELECT * FROM users_1 WHERE username LIKE ?";

            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, username + "%");

            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                Users users = new Users();

                users.setUserId(rs.getInt("user_id"));
                users.setRoleId(rs.getInt("role_id"));
                users.setPassword(rs.getString("password"));
                users.setUsername(rs.getString("username"));
                users.setEmail(rs.getString("email"));
                users.setUserStatus(rs.getString("user_status"));

                userList.add(users);
            }

            rs.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Failed to get Username : "+ e);
        }

        return userList;
    }
	
	
	public List<ExpectedOrders> getAllExpectedOrder(String query, int pageNum, int pageSize, String orderby, String sortby){
		List<ExpectedOrders> allExpectedOrder = new ArrayList<>();
			String query2 = "";
		try {
			conn = DB.getConnection();
			
		 //query = "SELECT pd.product_name, od.delivery_date, ot.quantity FROM orderdetails_1 ot, orders_1 od, product_1 pd WHERE ot.product_id = pd.product_id AND  ot.order_id = od.order_id AND od.delivery_date >= trunc(sysdate)"; 
			query2 = query + " order by "+orderby+" "+sortby+" offset "+pageNum+" rows fetch next "+pageSize+" rows only";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query2);

			while(rs.next()) {
				ExpectedOrders expectedOrders = new ExpectedOrders();

				expectedOrders.setProductName(rs.getString("product_name"));
				expectedOrders.setDeliveryDate(rs.getString("delivery_date"));
				expectedOrders.setQuantities(rs.getInt("quantity"));
				allExpectedOrder.add(expectedOrders);

			}

			rs = stmt.executeQuery("select count(*) totalpages from product_1");
			
			if(rs.next()) {
				this.numberOfRow = rs.getInt("totalpages");
			}
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Failed to list all users");
		}

		return allExpectedOrder;
	}



	public List<showProductForProduction> showAllProduct(String query, int pageNum, int pageSize, String orderby, String sortby){
		List<showProductForProduction> showAllProduct = new ArrayList<>();
		String query2 = "";
		try {
			conn = DB.getConnection();

			query2 = query + " order by "+orderby+" "+sortby+" offset "+pageNum+" rows fetch next "+pageSize+" rows only";

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query2);

			while(rs.next()) {
				showProductForProduction showProduct = new showProductForProduction();

				showProduct.setProductName(rs.getString("product_name"));
				showProduct.setUrl(rs.getString("product_picture"));
				showProduct.setDate(rs.getString("date_produced"));

				showAllProduct.add(showProduct);

			}

			rs = stmt.executeQuery("select count(*) totalpages from product_1");
			
			if(rs.next()) {
				this.numberOfRow = rs.getInt("totalpages");
			}
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Failed to list all users");
		}

		return showAllProduct;
	}
	
	
	public void addProduction(String[] productName, String[] quantity, String username) {
		String query = "";
		try {
			conn = DB.getConnection();
			conn.setAutoCommit(false);	

			for(int i = 0; i<productName.length; i++) {
				query = "INSERT INTO production_1 VALUES (production_id_1_seq.nextval, ?, CURRENT_TIMESTAMP, ?, (SELECT product_id FROM product_1 WHERE product_name = '" + productName[i] + "'))";
				System.out.println(i);
				PreparedStatement pstmt = conn.prepareStatement(query);
				pstmt.setString(1, username);
				pstmt.setString(2, quantity[i]);

				pstmt.executeUpdate();
			}

			System.out.println("Add New User Success!");

			conn.commit();
			conn.close();
		} catch (Exception e) {

			e.printStackTrace();
			System.out.println("Failed to add new user");
		}
	}


}

