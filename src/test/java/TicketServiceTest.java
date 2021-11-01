import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.revature.models.Ticket;
import com.revature.services.TicketService;
import com.revature.models.User;
import com.revature.models.Ticket.Status;
import com.revature.models.Ticket.Type;
import com.revature.models.User.Role;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.jupiter.api.*;
 
@TestMethodOrder(OrderAnnotation.class)
public class TicketServiceTest{

    public Date date = new Date();
    public static TicketService ticketService;
    List<Ticket> tickets = new ArrayList<Ticket>();
    List<Ticket> ticketsDB = new ArrayList<Ticket>();
    public static Logger log = LoggerFactory.getLogger(TicketServiceTest.class);
    public static Logger myLogger = LoggerFactory.getLogger("myLogger");
    User author;
    User resolver;

    User author1 = new User(1,"agent smith", "pass1".hashCode(), "agent", "smith", "humansSuck@gmail.com", Role.EMPLOYEE);
    User author2 = new User(2,"oracle", "pass2".hashCode(), "the", "oracle", "knowThyself@gmail.com", Role.EMPLOYEE);
    User author3 = new User(3,"neo one", "pass3".hashCode(), "the", "one", "theOne@gmail.com", Role.MANAGER);
    User author4 = new User(4,"morpheus", "pass4".hashCode(), "morph", "us", "morphUs@gmail.com", Role.MANAGER);


    Ticket ticket;
    Ticket ticket1 = new Ticket(1,50,date,date,"desc","receipt",author1,
    resolver, Status.APPROVED,Type.LODGING);
    Ticket ticket2 = new Ticket(2,75,date,date,"desc","receipt",author2,
    resolver, Status.APPROVED,Type.OTHER);
    Ticket ticket3 = new Ticket(3,100.25,date,date,"desc","receipt",author3,
        resolver, Status.PENDING,Type.LODGING);
    Ticket ticket4 = new Ticket(4,100.50,date,date,"desc","receipt",author4,
        resolver, Status.DENIED,Type.TRAVEL);
    Ticket ticket5 = new Ticket(5,100.75,date,date,"desc","receipt",author1,
        resolver, Status.PENDING,Type.OTHER);
    Ticket ticket6 = new Ticket(6,200,date,date,"desc","receipt",author2,
        resolver, Status.PENDING,Type.FOOD);
    Ticket ticketDB;
    boolean testPass;

    @BeforeAll
    public static void setTicketService(){
        ticketService = new TicketService();
    }

    @BeforeEach
    public void setVars(){
        testPass = false;
        ticket = null;
        ticketDB = null;
        ticketsDB = null;
        tickets = Arrays.asList(ticket1,ticket2,ticket3,ticket4,ticket5,ticket6);
    }

    @Test
    @Order(1)
    public void addTicketTest(){
        log.info("---Begin of addTicket-------------------------------------");
		testPass = ticketService.addTicket(ticket1);
        assertTrue(testPass);

        testPass = ticketService.addTicket(ticket2);
        assertTrue(testPass);

        log.info("---End of addTicket---------------------------------------");
	}

    @Test
    @Order(2)
	public void deleteTicket(){
        log.info("---Begin of deleteTicket-------------------------------------");
		testPass = ticketService.deleteTicket(ticket1.getId());
        assertTrue(testPass);

        testPass = ticketService.deleteTicket(ticket2.getId());
        assertTrue(testPass);
        log.info("---End of deleteTicket---------------------------------------");
	}

    @Test
    @Order(3)
    public void getAllTicketsTest(){
		log.info("---Begin of getAllTickets-------------------------------------");

		testPass = ticketService.addTicket(ticket1);
        assertTrue(testPass);

        testPass = ticketService.addTicket(ticket2);
        assertTrue(testPass);

        testPass = ticketService.addTicket(ticket3);
        assertTrue(testPass);

        testPass = ticketService.addTicket(ticket4);
        assertTrue(testPass);

        testPass = ticketService.addTicket(ticket5);
        assertTrue(testPass);
        
        testPass = ticketService.addTicket(ticket6);
        assertTrue(testPass);

        ticketsDB = ticketService.getAllTickets();
        assertNotNull(ticketsDB);

        int i = 0;

        for (Ticket ticketDB : ticketsDB) {
            assertEquals(tickets.get(i),ticketDB);
            i++;
        }
        
        log.info("---End of getAllTickets---------------------------------------");
	}

    @Test
    @Order(4)
	public void getTicketByIdTest(){
        log.info("---Begin of getTicketById-------------------------------------");
        ticketDB = ticketService.getTicketById(ticket4.getId());
        assertNotNull(ticketDB);
        assertEquals(ticket4.getId(),ticketDB.getId());

        ticketDB = ticketService.getTicketById(ticket5.getId());
        assertNotNull(ticketDB);
        assertEquals(ticket5.getId(),ticketDB.getId());
        log.info("---End of getTicketById---------------------------------------");
	}

	@Test
    @Order(5)
	public void updateTicketTest(){
        log.info("---Begin of updateTicket-------------------------------------");
        assertEquals(ticket3.getAmount(),100.25);
        ticket3.setAmount(200.25);
		testPass = ticketService.updateTicket(ticket3);
        assertTrue(testPass);

        // make sure that a new ticket isn't created
        log.info("---End of updateTicket---------------------------------------");
	}

	@Test
    @Order(6)
	public void getAllByAuthorTest(){
        log.info("---Begin of getAllByAuthorTest-------------------------------------");
        
        ticketsDB = ticketService.getAllByAuthor(1);
        assertNotNull(ticketsDB);
        
        for(Ticket ticket : ticketsDB){
            myLogger.info(ticket.toString());
        }

        log.info("---End of getAllByAuthorTest---------------------------------------");
	}

	
    @AfterEach
    public void clear(){
        ticket = null;
    }

    @AfterAll
    public static void clearTicketService(){
        ticketService = null;
    }
}