package com.TCorp.FitNetServer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = FitNetServerApplication.class)
public class RootHealthTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    public void getRoot_success() throws Exception {
        System.out.println("Starting RootHealthTest...");
        mockMvc.perform(MockMvcRequestBuilders
                .get("/"))
                .andExpect(status().isOk());
    }
}
