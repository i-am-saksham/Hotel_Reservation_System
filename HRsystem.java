//package reserve;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.*;
//
////import com.mysql.cj.xdevapi.Statement;
//public class HRsystem {
//	
//	private static final String url ="jdbc:mysql://localhost:3306/hotel_db";
//	private static final String user ="root";
//	private static final String password ="root";
//	
//	public static void main(String[] args) throws ClassNotFoundException, SQLException{
//		 
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//		}
//		catch(ClassNotFoundException e) {
//			System.out.println(e.getMessage());
//		}
//		
//		try {
//			Connection con = DriverManager.getConnection(url, user, password);
//			
//			while(true) {
//				System.out.println();
//				System.out.println("HOTEL RESERVATION SYSTEM");
//				System.out.println("1. Reserve a room");
//				System.out.println("2. View Reservation");
////				System.out.println("3. Get Room Number");
//				System.out.println("4. Update Reservation");
//				System.out.println("5. Delete Reservation");
//				System.out.println("6. Exit");
//				System.out.println("Choose an option");
//				
//				Scanner sc=new Scanner(System.in);
//				int choice = sc.nextInt();
//				switch(choice) {
//				case 1:
//					reserveRoom(con, sc);
//					break;
//				case 2:
//					viewReservations(con);
//					break;
////				case 3:
////					getRoomNumber(con);
////					break;
//				case 3:
//					updateReservation(con, sc);
//					break;
//				case 4:
//					deleteReservation(con, sc);
//					break;
//				case 0:
//					exit();
//					sc.close();
//					return;
//				default:
//					System.out.println("Invalid choice. Try again.");	
//				}			
//			}
//		}
//		catch(SQLException e) {
//			e.printStackTrace();
//		}
////		exception from exit();
//		catch(InterruptedException e) {
//			throw new RuntimeException(e);
//		}
//	}
//	
//	private static void reserveRoom(Connection con, Scanner sc) {
//
//		System.out.println("Enter guest name: ");
//		String guestName = sc.next();
//		sc.nextLine();
//		System.out.println("Enter room number: ");
//		int roomNumber = sc.nextInt();
//		System.out.println("Enter contact number: ");
//		int contactNumber = sc.nextInt();
//		
////		query
//		String query="INSERT INTO reservation(guest_name,room_number,contact_number) VALUES(?,?,?)";
//		try {
//			PreparedStatement ps = con.prepareStatement(query);
//			ps.setString(1, guestName);
//			ps.setInt(2, roomNumber);
//			ps.setInt(3, contactNumber);
//			
//			int rowsAffected = ps.executeUpdate();
//			if(rowsAffected>0) {
//				System.out.println("Room Reserved Successfully!");
//			}
//			else {
//				System.out.println("Failed to Reserve Room");
//			}
//			
//		}
//		catch(SQLException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	private static void viewReservations(Connection con) throws SQLException{
//		String query = "SELECT reservation_id, guest_name, room_number, contact_number, reservation_date from reservations";
//		try {
//			PreparedStatement ps = con.prepareStatement(query);
//			ResultSet rs = ps.executeQuery();
//			
//			System.out.println("Current Reservations: ");
//			System.out.println("+----------------+-------------+-------------+-----------------+----------------------+");
//			System.out.println("| Reservation ID | Guest       | Room Number | Contact Number  | Reservation Date     |");
//			System.out.println("+----------------+-------------+-------------+-----------------+----------------------+");
//		
//			while(rs.next()) {
//				int reservationId = rs.getInt("reservation_id");
//				String guestName= rs.getString("guest_name");
//				int roomNumber = rs.getInt("room_number");
//				String contactNumber = rs.getString("contact_number");
//				String reservationDate = rs.getTimestamp("reservation_date").toString();
//				
//				System.out.printf("| %-14d | %-11s | %-11d | %-15s | %-20s |\n",reservationId,guestName,roomNumber, contactNumber, reservationDate);
//			}
//			System.out.println("+----------------+-------------+-------------+-----------------+----------------------+");
//			
//		}
//		catch(SQLException e) {
//			e.printStackTrace();
//		}
//		
//	}
//	
//	private static void updateReservation(Connection con, Scanner sc) {
//		System.out.println("Enter reservation ID to update: ");
//		int reservationId = sc.nextInt();
////		consume the new line character
//		sc.nextLine();
//		
//		if(!reservationExists(con,reservationId)) {
//			System.out.println("Reservation Not Found");
//			return;
//		}
//		
//		System.out.println("Enter guest name: ");
//		String newGuestName = sc.next();
//		System.out.println("Enter room number: ");
//		int newRoomNumber = sc.nextInt();
//		System.out.println("Enter contact number: ");
//		int newContactNumber = sc.nextInt();
//		
//		String query = "UPDATE reservations SET guest_name = '" + newGuestName + "', " +
//                "room_number = " + newRoomNumber + ", " +
//                "contact_number = '" + newContactNumber + "' " +
//                "WHERE reservation_id = " + reservationId;
//		
//		try {
//			PreparedStatement ps = con.prepareStatement(query);
////			ps.setString(1, newGuestName);
////			ps.setInt(2, newRoomNumber);
////			ps.setInt(3, newContactNumber);
//			
//			int rowsAffected = ps.executeUpdate();
//			if(rowsAffected>0) {
//				System.out.println("Reservation Updated Successfully!");
//			}
//			else {
//				System.out.println("Room Update Failed ");
//			}
//			
//		}
//		catch(SQLException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	private static void deleteReservation(Connection con, Scanner sc) {
//		 System.out.print("Enter reservation ID to delete: ");
//         int reservationId = sc.nextInt();
//         
//         if (!reservationExists(con, reservationId)) {
//             System.out.println("Reservation not found for the given ID.");
//             return;
//         }
//         String query = "DELETE FROM reservations WHERE reservation_id = " + reservationId;
//         try {
//        	 
// 			PreparedStatement ps = con.prepareStatement(query);
//// 			ps.setString(1, newGuestName);
//// 			ps.setInt(2, newRoomNumber);
//// 			ps.setInt(3, newContactNumber);
// 			
// 			int rowsAffected = ps.executeUpdate();
// 			if(rowsAffected>0) {
// 				System.out.println("Reservation Deleted Successfully!");
// 			}
// 			else {
// 				System.out.println("Reservation Deletion Failed ");
// 			}
// 			
// 		}
// 		catch(SQLException e) {
// 			e.printStackTrace();
// 		}
//	}
//	
//	private static boolean reservationExists(Connection con, int reservationId) {
//		
//			String query = "SELECT reservation_id FROM reservations WHERE reservation_id = " + reservationId;
//			try {
//				PreparedStatement ps = con.prepareStatement(query);
//				ps.setInt(1,reservationId);
//				ResultSet rs = ps.executeQuery();
//				
//				if(rs.next()) {
//					return true;
//				}
//				else {
//					return false;
//				}
//
//			}catch(SQLException e) {
//				e.printStackTrace();
//				return false;
//			}
//		}
//	
//	public static void exit() throws InterruptedException {
//        System.out.print("Exiting System");
//        int i = 5;
//        while(i!=0){
//            System.out.print(".");
//            Thread.sleep(1000);
//            i--;
//        }
//        System.out.println();
//        System.out.println("ThankYou For Using Hotel Reservation System!!!");
//    }
//	
//
//}
