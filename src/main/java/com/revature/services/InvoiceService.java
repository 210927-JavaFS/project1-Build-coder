package com.revature.services;

import java.util.List;

import com.revature.models.Invoice;
import com.revature.daos.InvoiceDAO;
import com.revature.daos.InvoiceDAOImpl;

public class InvoiceService {
	private InvoiceDAO invoiceDao = new InvoiceDAOImpl();
	
	public List<Invoice> getAllInvoices(){
		return invoiceDao.findAllInvoices();
	}

	public Invoice getInvoiceById(int id){
		Invoice invoice = invoiceDao.findById(id);
		return invoice;
	}
	
	public Invoice getInvoiceByName(String name){
		Invoice invoice = invoiceDao.findByName(name);
		if (invoice!=null) {
			return invoice;
		} else {
			return new Invoice();
		}
	}
	
	public boolean addInvoice(Invoice invoice){
		return invoiceDao.addInvoice(invoice);
	}
	
	public boolean updateInvoice(Invoice invoice){
		return invoiceDao.updateInvoice(invoice);
	}
	
	public boolean deleteInvoice(int id){
		Invoice invoice = getInvoiceById(id);
		return invoiceDao.deleteInvoice(invoice);
	}
}