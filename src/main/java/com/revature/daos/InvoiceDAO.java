package com.revature.daos;

import java.util.List;

import com.revature.models.Invoice;

public interface InvoiceDAO {
	public List<Invoice> findAllInvoices();
	public Invoice findById(int id);
	public Invoice findByName(String name);
	public boolean addInvoice(Invoice reimbursement);
	public boolean updateInvoice(Invoice reimbursement);
	public boolean deleteInvoice(Invoice reimbursement);
}