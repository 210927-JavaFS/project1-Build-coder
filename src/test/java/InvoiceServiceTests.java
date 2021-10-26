import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.sql.Timestamp;

import com.revature.models.Invoice;
import com.revature.services.InvoiceService;

import org.junit.jupiter.api.AfterAll;
// import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.models.User;
import com.revature.models.Invoice.Status;
import com.revature.models.Invoice.Type;


public class InvoiceServiceTests{

    public Date date = new Date();
    // public Timestamp ts = new Timestamp(date.getTime());
    public static InvoiceService invoiceService;
    List<Invoice> invoices = new ArrayList<Invoice>();
    public static Logger log = LoggerFactory.getLogger(InvoiceServiceTests.class);
    timeStamp = Date.now();
    User author;
    User resolver;

    Invoice invoice;
    Invoice invoice1 = new Invoice(Status.APPROVED, Type.FOOD);
    Invoice invoice2 = new Invoice(date,date);
    Invoice invoice3 = new Invoice(100.25,date,date,"desc","receipt",author,
        resolver, Status.PENDING,Type.LODGING);
    boolean testPass;

    @BeforeEach
    public void setVars(){
       testPass = false;
    }

    public void getAllInvoicesTest(){
		
	}

	public void getInvoiceByIdTest(){

	}
	
	public void getInvoiceByNameTest(){

	}
	
	public void addInvoiceTest(){
		testPass = invoiceService.addInvoice(invoice);
        assertTrue(testPass);
	}
	
	public void updateInvoiceTest(){
		testPass = invoiceService.updateInvoice(invoice);
        assertTrue(testPass);
	}
	
	public void deleteInvoice(){
		testPass = invoiceService.deleteInvoice(invoice3.getId());
        assertTrue(testPass);
	}

    // @AfterEach
    // public void clearResult(){

    // }

    @AfterAll
    public static void clearInvoiceService(){
        invoiceService = null;
    }

}