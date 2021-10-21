package com.revature.services;

import java.util.List;

import com.revature.daos.ReimbursementDAO;
import com.revature.daos.ReimbursementDAOImpl;
import com.revature.models.Reimbursement;

public class ReimbursementServices {
	private ReimbursementDAO reimburseDAO = new ReimbursementDAOImpl();
	
	public List<Reimbursement> getAllReimbursements(){
		return reimburseDAO.getAllReimbursements();
	}
	
	public Reimbursement getReimbursement(int id){
		Reimbursement reimbursement = reimburseDAO.getReimbursementByID(id);
		return reimbursement;
	}
	
	public boolean addReimbursement(Reimbursement reimbursement){
		return reimburseDAO.insert(reimbursement);
	}
	
	public boolean updateReimbursement(Reimbursement reimbursement){
		return reimburseDAO.update(reimbursement);
	}
	
	public boolean deleteReimbursement(int id){
		Reimbursement reimbursement = getReimbursement(id);
		if(reimbursement == null) return false;
		return reimburseDAO.delete(reimbursement);
	}
}