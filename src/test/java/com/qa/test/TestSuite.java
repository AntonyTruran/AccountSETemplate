package com.qa.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({ AccountServiceTest.class, AccountServiceTestJack.class, AccountTestRepo.class, AccountTestEndpoint.class, AccountTestService.class })

public class TestSuite {
}