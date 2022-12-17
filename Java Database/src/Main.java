import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
public class Main {

	public static void main(String[] args) throws InstantiationException,IllegalAccessException,ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
		Database db = new Database("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/library","root","root");
		
		Scanner sc = new Scanner(System.in);
		String option;
		String query;
		Boolean running = true;
		
		while(running) {
			System.out.println("-------------------Library Application-------------------");
			System.out.println("Select from the following options:");
			System.out.println("   1: Show all users");
			System.out.println("   2: Show all books");
			System.out.println("   3: Show all users that have books loaned");
			System.out.println("   4: Show all books that are loaned by users");
			System.out.println("   5: Show all loaned books and the users who loaned them");
			System.out.println("   6: Show all overdue books");
			System.out.println("   7: Custom query");
			System.out.println("   8: Exit this program");
			System.out.print(">> ");
			
			option = sc.nextLine();
			
			switch(option) {
			case "1":
				db.query("SELECT * FROM users;");
				break;
			case "2": 
				db.query("SELECT * FROM books;");
				break;
			case "3":
				db.query("SELECT l.user_id,u.first_name,u.last_name FROM loaned l JOIN users u ON l.user_id=u.user_id;");
				break;
			case "4":
				db.query("SELECT l.ISBN,b.title,b.author FROM loaned l JOIN books b on l.ISBN=b.ISBN;");
				break;
			case "5":
				db.query("SELECT l.user_id,u.first_name,u.last_name,l.ISBN,b.title,b.author FROM loaned l JOIN users u on l.user_id=u.user_id JOIN books b ON b.ISBN=l.ISBN;");
				break;
			case "6":
				db.query("SELECT l.user_id,u.first_name,u.last_name,b.title,b.author,l.date_due FROM loaned l JOIN users u ON l.user_id=u.user_id JOIN books b ON b.ISBN = l.ISBN WHERE date_due < CURDATE();");
				break;
			case "7":
				System.out.println("Please enter your query:");
				db.query(sc.nextLine());
				break;
			case "8":
				running = false;
				break;
				
			}
			
			if(running) db.printResults();

			
			
			
		}

	}

}
