package Database;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.mysql.*;

public class DatabaseTest {
	String[] users = {"jsmith@uca.edu","msmith@uca.edu","tjones@yahoo.com","jjones@yahoo.com"};
	String[] passwords = {"hello123","pass123","123456","hello1234"};

	private Database database;
	
	@Before
	public void setUp() {
		database = new Database();
	}
	
	//testing with a valid properties file
	@Test
	public void testSetConnection() {
		database.setConnection("Database/db.properties");
		assertNotNull(database.getConnection());
	}
	
	//when a invalid connection info sent, connection should be null
	@Test
	public void testConnection(){
		database.setConnection("Database/fakeFile.txt");
		assertNull(database.getConnection());
	}
	
	//testing query, is query works, we should get the expected value returned
	@Test
	public void testQuery() {
		database.setConnection("Database/db.properties");
		Connection conn = database.getConnection();
		int rando = ((int) Math.random() * users.length);
		
		String username = users[rando];
		String expected = passwords[rando];
		
		String query = "select aes_decrypt(password, 'hello') from users where username = '" + username + "'";
		
		ArrayList<String> result = database.query(query);
		String actual = result.get(0);
		
		String message = "Username = " + username;
		assertEquals(message, expected, actual);
		
	}
	
	
	//testing adding a user that already exist, if it does exist, we get a SQLException sure to
	//primary key constraint
	@Test
	public void testExecuteDML()throws SQLException {
		database.setConnection("Database/db.properties");
		Connection conn = database.getConnection();
		String dml = "insert into users values('jjones@yahoo.com', aes_encrypt('hello123', 'hello'))";
		assertFalse(database.executeDML(dml));
	}
	
	//testing adding a user that doesn't exist, no exception expected
	@Test
	public void testDML() throws SQLException {
		database.setConnection("Database/db.properties");
		Connection conn = database.getConnection();
		
		//delete user to make sure there is not a copy of this user in the database
		String dml = "delete from users where username = 'axel@yahoo.com'";
		database.executeDML(dml);
		
		dml = "insert into users values('axel@yahoo.com', aes_encrypt('hello123', 'hello'))";
		database.executeDML(dml);
	}
	

}

