package com.cedge.poweredge.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cedge.poweredge.dto.TellerMaster;
import com.cedge.poweredge.repository.ReportDao;
import com.cedge.poweredge.repository.ReportValidate;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ReportServiceImpl implements  ReportService {

	@Autowired
	ReportDao reportDao;
	
	@Autowired
	ReportValidate reportValidate;
	
	@Override
	public List<String> retreiveBankNames() {
		log.info("ReportServiceImpl::retreiveBankNames::Start");
		Optional<List<String>> banks = reportDao.findAllBankNameByOrderByBankNameAsc();
		if (banks.isPresent()) {
			return banks.get();
		}
		log.info("list of bank Names",banks);
		return null;
	}

	@Override
	public TellerMaster validateUserLogin(String tellerid, String pwd, String ip) {
		log.info("ReportServiceImpl::validateUserLogin::Start");
		TellerMaster findAllUsernamePasswordwithIp = reportValidate.findAllUsernamePasswordwithIp(tellerid,pwd,ip);
		return findAllUsernamePasswordwithIp;
	}


	@Override
	public TellerMaster validateMasterLogin(String tellerid, String pwd, String ip, String bankcode) {
		log.info("ReportServiceImpl::validateMasterLogin::Start");
		TellerMaster findAllUsernamePasswordIpwithbankcode = reportValidate.findAllUsernamePasswordIpwithBankcode(tellerid,pwd,ip,bankcode);
		return findAllUsernamePasswordIpwithbankcode;
	}

}
