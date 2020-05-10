package com.digi.trade;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureStubRunner(ids="com.digi:contractrepo:+:stubs:9001",
                         stubsMode = StubRunnerProperties.StubsMode.LOCAL)
class TradeApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldSeeeleTradeWhenBrockrageIsHigh() throws Exception {

        mockMvc.perform(
                post("/trade-settlement")
                        .contentType(APPLICATION_JSON)
                        .content("{" +
                                "\"tradeRefNumber\": 2345," +
                                "\"brokerageType\": \"PLATINUM\"" +
                                "}"
                        ))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .json("{" +
                                "\"status\":\"APPROVED\"" +
                                "}"))
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON));
    }

}
