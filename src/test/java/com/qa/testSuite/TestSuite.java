package com.qa.testSuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.qa.DBtests.AccountDBTest;
import com.qa.MapTests.AccountServiceTest;
import com.qa.MapTests.AccountServiceTestJack;

@RunWith(Suite.class)

@Suite.SuiteClasses({AccountDBTest.class, AccountServiceTest.class, AccountServiceTestJack.class})
public class TestSuite {

}
