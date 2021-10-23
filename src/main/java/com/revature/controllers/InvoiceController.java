package com.revature.controllers;

import java.util.List;

import com.revature.models.Invoice;
import com.revature.services.InvoiceService;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class InvoiceController implements Controller{
    
	private InvoiceService invoiceService = new InvoiceService();
	
	public Handler getAllInvoices = (ctx) -> {
		if (ctx.req.getSession(false) != null) {
			List<Invoice> list = invoiceService.getAllInvoices();
			ctx.json(list);
			ctx.status(200);
		} else {
			ctx.status(401);
		}
	};
	
	public Handler getInvoice = (ctx) -> {
		if (ctx.req.getSession(false) != null) {
			try {
				String idString = ctx.pathParam("invoice");
				int id = Integer.parseInt(idString);
				Invoice invoice = invoiceService.getInvoiceById(id);
				ctx.json(invoice);
				ctx.status(200);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				ctx.status(406);
			}
		} else {
			ctx.status(401);
		}
	};
	
	public Handler addInvoice = (ctx) -> {
		if (ctx.req.getSession(false) != null) {
			Invoice invoice = ctx.bodyAsClass(Invoice.class);
			if (invoiceService.addInvoice(invoice)) {
				ctx.status(201);
			} else {
				ctx.status(400);
			}
		} else {
			ctx.status(401);
		}
	};
	
	public Handler updateInvoice = (ctx) -> {
		if (ctx.req.getSession(false) != null) {
			Invoice invoice = ctx.bodyAsClass(Invoice.class);
			if (invoiceService.updateInvoice(invoice)) {
				ctx.status(200);
			} else {
				ctx.status(400);
			}
		} else {
			ctx.status(401);
		}
	};
	
	public Handler deleteInvoice = (ctx) -> {
		if (ctx.req.getSession(false) != null) {
			String id = ctx.pathParam("invoice");
			try {
				int invoice = Integer.parseInt(id);
				if (invoiceService.deleteInvoice(invoice)) {
					ctx.status(200);
				} else {
					ctx.status(400);
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
				ctx.status(406);
			}
		} else {
			ctx.status(401);
		}
	};
	
	@Override
	public void addRoutes(Javalin app) {
		app.get("/invoices", this.getAllInvoices);
		app.get("/invoices/:invoice", this.getInvoice);
		app.post("/invoices", this.addInvoice);
		app.put("/invoices", this.updateInvoice);
		app.delete("/invoices/:invoice", this.deleteInvoice);
	}
}