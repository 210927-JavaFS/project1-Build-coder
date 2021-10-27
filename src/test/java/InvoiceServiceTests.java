import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import java.sql.Timestamp;

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

public class InvoiceServiceTests{

    public Date date = new Date();
    // public Timestamp ts = new Timestamp(date.getTime());
    public static InvoiceService invoiceService;
    List<Invoice> invoices = new ArrayList<Invoice>();
    List<Invoice> invoicesDB = new ArrayList<Invoice>();
    public static Logger log = LoggerFactory.getLogger(InvoiceServiceTests.class);
    // timeStamp = Date.now();
    User author;
    User resolver;

    Invoice invoice;
    // Invoice invoice1 = new Invoice(Status.APPROVED, Type.FOOD);
    // Invoice invoice2 = new Invoice(date,date);
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
        invoices = Arrays.asList(invoice3,invoice4,invoice5);
    }

    @Test
    public void addInvoiceTest(){
        log.info("---Begin of addInvoice-------------------------------------");
		testPass = invoiceService.addInvoice(invoice3);
        assertTrue(testPass);

        testPass = invoiceService.addInvoice(invoice4);
        assertTrue(testPass);

		testPass = invoiceService.addInvoice(invoice5);
        assertTrue(testPass);
        log.info("---End of addInvoice---------------------------------------");
	}

    @Test
    public void getAllInvoicesTest(){
		log.info("---Begin of getAllInvoices-------------------------------------");
        invoicesDB = invoiceService.getAllInvoices();
        assertNotNull(invoicesDB);

        for (Invoice invoice : invoicesDB) {
            log.info("Invoice: "+invoice.toString());
        }

        assertEquals(invoicesDB,invoices);
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
	
    @Test
	public void deleteInvoice(){
        log.info("---Begin of deleteInvoice-------------------------------------");
		testPass = invoiceService.deleteInvoice(invoice3.getId());
        assertTrue(testPass);
        log.info("---End of deleteInvoice---------------------------------------");
	}

    @AfterEach
    public void clearResult(){
        invoice = null;
    }

    @AfterAll
    public static void clearInvoiceService(){
        invoiceService = null;
    }
}