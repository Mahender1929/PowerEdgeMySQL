package com.cedge.poweredge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cedge.poweredge.dto.TellerMaster;
import com.cedge.poweredge.service.ReportService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class MyController {

	@Autowired
	ReportService reportService;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@GetMapping(value="/banknames", produces="application/json")
	public List<String> getBankNames() throws JsonProcessingException
	{
		log.info("MyController::getBankNames::Start");
		List<String> retreiveBankNames = reportService.retreiveBankNames();
		if (!ObjectUtils.isEmpty(retreiveBankNames))
		{
			log.info("List of Bank Names {}",objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(retreiveBankNames));
			return retreiveBankNames;
		}
		return retreiveBankNames;
	}
	
	@GetMapping(value="/userValidate", consumes ="application/json", produces ="application/json")
	public TellerMaster getValidateUser(@RequestBody TellerMaster tellerMaster) throws JsonProcessingException
	{
		log.info("MyController::getValidateUser::Start");
		String tellerid = tellerMaster.getTellerid();
		String pwd = tellerMaster.getPwd();
		String ip = tellerMaster.getIp();
		TellerMaster validateLoginUser = reportService.validateUserLogin(tellerid,pwd,ip);
		if (!ObjectUtils.isEmpty(validateLoginUser))
		{
			log.info("List of User Details {}",objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(validateLoginUser));
			return validateLoginUser;
		}
		return validateLoginUser;
	}
	
	@GetMapping(value="/masterValidate", consumes ="application/json", produces ="application/json")
	public TellerMaster getValidateSuperUser(@RequestBody TellerMaster tellerMaster) throws JsonProcessingException
	{
		log.info("MyController::getValidateSuperUser::Start");
		String tellerid = tellerMaster.getTellerid();
		String pwd = tellerMaster.getPwd();
		String ip = tellerMaster.getIp();
		String bankCode = tellerMaster.getBankCode();
		TellerMaster validateMasterLogin = reportService.validateMasterLogin(tellerid,pwd,ip,bankCode);
		if (ObjectUtils.isEmpty(validateMasterLogin))
		{
			log.error("Not a Valid Super User {}",validateMasterLogin);
			throw new NullPointerException("Teller Master Object is Null:::Not a Valid Super User");
		}
		log.info("List of Master Details {}",objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(validateMasterLogin));
		return validateMasterLogin;
	}
	
}
