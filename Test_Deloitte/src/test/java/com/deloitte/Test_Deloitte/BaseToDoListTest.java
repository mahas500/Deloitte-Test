package com.deloitte.Test_Deloitte;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
public class BaseToDoListTest extends TestDeloitteApplicationTests {

	@Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    public MockMvc getMockMvc() {
        if (this.mockMvc == null) {
            this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        }
        return this.mockMvc;
    }
}
