package com.cf.markettrade;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.cf.markettrade.controller.TradeMessageControllerTest;
import com.cf.markettrade.domain.TradeMessageTest;
import com.cf.markettrade.service.StatisticServiceTest;
import com.cf.markettrade.service.TradeMessageServiceTest;

/**
 * Test Suite. 
 * Includes unit tests for Domain classes, Controllers and Services of the Spring Boot Application.
 * 
 * @author pangio
 */
@RunWith(Suite.class)
@SuiteClasses({ 
	TradeMessageTest.class, 
	TradeMessageControllerTest.class, 
	StatisticServiceTest.class, 
	TradeMessageServiceTest.class })
public class AllTests {
}
