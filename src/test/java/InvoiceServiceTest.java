import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.revature.models.Invoice;
import com.revature.services.InvoiceService;
import com.revature.models.User;
import com.revature.models.Invoice.Status;
import com.revature.models.Invoice.Type;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InvoiceServiceTest{

    public Date date = new Date();
    public static InvoiceService invoiceService;
    List<Invoice> invoices = new ArrayList<Invoice>();
    List<Invoice> invoicesDB = new ArrayList<Invoice>();
    public static Logger log = LoggerFactory.getLogger(InvoiceServiceTest.class);
    // timeStamp = Date.now();
    User author;
    User resolver;

    Invoice invoice;
    Invoice invoice1 = new Invoice(1,50,date,date,"desc","receipt",author,
    resolver, Status.PENDING,Type.LODGING);
    Invoice invoice2 = new Invoice(2,75,date,date,"desc","receipt",author,
    resolver, Status.PENDING,Type.LODGING);
    Invoice invoice3 = new Invoice(3,100.25,date,date,"desc","receipt",author,
        resolver, Status.PENDING,Type.LODGING);
    Invoice invoice4 = new Invoice(4,100.50,date,date,"desc","receipt",author,
        resolver, Status.PENDING,Type.LODGING);
    Invoice invoice5 = new Invoice(5,100.75,date,date,"desc","receipt",author,
        resolver, Status.PENDING,Type.LODGING);
    Invoice invoice6 = new Invoice(6,200,date,date,"desc","receipt",author,
        resolver, Status.PENDING,Type.LODGING);
    Invoice invoiceDB;
    boolean testPass;

    @BeforeAll
    public static void setInvoiceService(){
        invoiceService = new InvoiceService();
    }

    @BeforeEach
    public void setVars(){
        testPass = false;
        invoice = null;
        invoiceDB = null;
        invoicesDB = null;
        invoices = Arrays.asList(invoice1,invoice2,invoice3,invoice4);
    }

    @Test
    public void addInvoiceTest(){
        log.info("---Begin of addInvoice-------------------------------------");
		testPass = invoiceService.addInvoice(invoice1);
        assertTrue(testPass);

        testPass = invoiceService.addInvoice(invoice2);
        assertTrue(testPass);

        log.info("---End of addInvoice---------------------------------------");
	}

    @Test
	public void deleteInvoice(){
        log.info("---Begin of deleteInvoice-------------------------------------");
		testPass = invoiceService.deleteInvoice(invoice1.getId());
        assertTrue(testPass);

        testPass = invoiceService.deleteInvoice(invoice2.getId());
        assertTrue(testPass);
        log.info("---End of deleteInvoice---------------------------------------");
	}

    @Test
    public void getAllInvoicesTest(){
		log.info("---Begin of getAllInvoices-------------------------------------");

		testPass = invoiceService.addInvoice(invoice1);
        assertTrue(testPass);

        testPass = invoiceService.addInvoice(invoice2);
        assertTrue(testPass);

        testPass = invoiceService.addInvoice(invoice3);
        assertTrue(testPass);

        testPass = invoiceService.addInvoice(invoice4);
        assertTrue(testPass);

        invoicesDB = invoiceService.getAllInvoices();
        assertNotNull(invoicesDB);

        int i = 0;

        for (Invoice invoiceDB : invoicesDB) {
            assertEquals(invoices.get(i),invoiceDB);
            i++;
        }
        
        log.info("---End of getAllInvoices---------------------------------------");
	}

    @Test
	public void getInvoiceByIdTest(){
        log.info("---Begin of getInvoiceById-------------------------------------");
        invoiceDB = invoiceService.getInvoiceById(invoice4.getId());
        assertNotNull(invoiceDB);
        assertEquals(invoice4.getId(),invoiceDB.getId());

        invoiceDB = invoiceService.getInvoiceById(invoice5.getId());
        assertNotNull(invoiceDB);
        assertEquals(invoice5.getId(),invoiceDB.getId());
        log.info("---End of getInvoiceById---------------------------------------");
	}

	@Test
	public void updateInvoiceTest(){
        log.info("---Begin of updateInvoice-------------------------------------");
        assertEquals(invoice3.getAmount(),100.25);
        invoice3.setAmount(200.25);
		testPass = invoiceService.updateInvoice(invoice3);
        assertTrue(testPass);
        log.info("---End of updateInvoice---------------------------------------");
	}
	


    @AfterEach
    public void clear(){
        invoice = null;
    }

    @AfterAll
    public static void clearInvoiceService(){
        invoiceService = null;
    }
}