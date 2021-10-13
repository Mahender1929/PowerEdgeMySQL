package com.cedge.poweredge.service;

import java.util.List;

import com.cedge.poweredge.dto.TellerMaster;

/**
 * @author mahenderkumar.g
 *
 */
public interface ReportService {

	public List<String> retreiveBankNames();

	public TellerMaster validateUserLogin(String tellerid, String pwd, String ip);
	
	public TellerMaster validateMasterLogin(String tellerid, String pwd, String ip, String bankcode);

	
}
