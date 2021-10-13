package com.cedge.poweredge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cedge.poweredge.dto.TellerMaster;

public interface ReportValidate extends JpaRepository<TellerMaster, String> {
	
	@Query(value="SELECT * FROM teller_master WHERE tellerid=?1 AND pwd=?2 AND ip=?3", nativeQuery=true)
	public TellerMaster findAllUsernamePasswordwithIp(String tellerid,String pwd,String ip);
	
	@Query(value="SELECT * FROM teller_master WHERE tellerid=?1 AND pwd=?2 AND ip=?3 AND bank_code=?4", nativeQuery=true)
	public TellerMaster findAllUsernamePasswordIpwithBankcode(String tellerid,String pwd,String ip,String bankcode);

}
