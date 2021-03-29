package com.deloitte.Test_Deloitte;

import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.ActiveProfiles;

import org.springframework.test.context.transaction.AfterTransaction;


@SpringBootTest(classes = TestDeloitteApplication.class)
class TestDeloitteApplicationTests {

	
	@AfterTransaction
    public void clean() {
        // = Clean dirty stuffs after transaction
    }
}
