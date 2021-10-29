import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.revature.models.User;
import com.revature.models.User.Role;
import com.revature.services.UserService;

import org.hibernate.Session;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserServiceTest{

    public Date date = new Date();
    public static UserService userService;
    List<User> users = new ArrayList<User>();
    List<User> usersDB = new ArrayList<User>();
    public static Logger log = LoggerFactory.getLogger(UserServiceTest.class);
    User author;
    User resolver;
    Session session;

    User user;
    User userDB;
    User user1 = new User(1,"agent smith", "pass1".hashCode(), "agent", "smith", "humansSuck@gmail.com", Role.EMPLOYEE);
    User user2 = new User(2,"oracle", "pass2".hashCode(), "know", "thyself", "knowThyself@gmail.com", Role.EMPLOYEE);
    User user3 = new User(3,"neo one", "pass3".hashCode(), "new", "one", "theOne@gmail.com", Role.MANAGER);
    User user4 = new User(4,"morpheus", "pass4".hashCode(), "morph", "us", "morphUs@gmail.com", Role.MANAGER);

    boolean testPass;

    @BeforeAll
    public static void setUserService(){
        userService = new UserService();
    }

    @BeforeEach
    public void setVars(){
        testPass = false;
        user = null;
        userDB = null;
        usersDB = null;
        users = Arrays.asList(user1,user2,user3,user4);
    }

    @Test
    public void addUserTest(){
        log.info("---Begin of addUser-------------------------------------");
		testPass = userService.addUser(user1);
        assertTrue(testPass);

        testPass = userService.addUser(user2);
        assertTrue(testPass);

        log.info("---End of addUser---------------------------------------");
	}

    @Test
	public void deleteuser(){
        log.info("---Begin of deleteuser-------------------------------------");
		testPass = userService.deleteUser(user1.getId());
        assertTrue(testPass);

        testPass = userService.deleteUser(user2.getId());
        assertTrue(testPass);
        log.info("---End of deleteuser---------------------------------------");
	}

    @Test
    public void getAllusersTest(){
		log.info("---Begin of getAllusers-------------------------------------");

		testPass = userService.addUser(user1);
        assertTrue(testPass);
        
        testPass = userService.addUser(user2);
        assertTrue(testPass);

        testPass = userService.addUser(user3);
        assertTrue(testPass);

        testPass = userService.addUser(user4);
        assertTrue(testPass);

        usersDB = userService.getAllUsers();
        assertNotNull(usersDB);

        int i = 0;

        for (User userDB : usersDB) {
            assertEquals(users.get(i),userDB);
            i++;
        }
        
        log.info("---End of getAllusers---------------------------------------");
	}

    @Test
	public void getuserByIdTest(){
        log.info("---Begin of getuserById-------------------------------------");
        userDB = userService.getUserById(user3.getId());
        assertNotNull(userDB);
        assertEquals(user3.getId(),userDB.getId());

        userDB = userService.getUserById(user4.getId());
        assertNotNull(userDB);
        assertEquals(user4.getId(),userDB.getId());
        log.info("---End of getuserById---------------------------------------");
	}

	@Test
	public void updateuserTest(){
        log.info("---Begin of updateuser-------------------------------------");
        assertEquals(user3.getEmail(),"theOne@gmail.com");
        user3.setEmail("notTheOne@gmail.com");
		testPass = userService.updateUser(user3);
        assertTrue(testPass);
        log.info("---End of updateuser---------------------------------------");
	}
	

    @AfterEach
    public void clear(){
        user = null;
    }

    @AfterAll
    public static void clearuserService(){
        userService = null;
    }
}