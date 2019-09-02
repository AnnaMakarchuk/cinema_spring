package org.study.cinema.integration.controller;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.study.cinema.CinemaApplicationTests;
import org.study.cinema.utils.AttributesNames;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class TicketControllerIntegrationTest extends CinemaApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnTicketsByUser() throws Exception {
        mockMvc.perform((get("/client/tickets")))
                .andExpect(status().isOk())
                .andExpect(model().attributeHasNoErrors())
                .andExpect(model().attribute(AttributesNames.TICKETS, hasSize(10)));
    }

    @Test
    public void shouldReturnAllTicketsByPages() throws Exception {
        int pageNumber = 3;
        mockMvc.perform(get("/admin/tickets")
                .param(AttributesNames.PAGE, String.valueOf(pageNumber)))
                .andExpect(status().isOk())
                .andExpect(model().attributeHasNoErrors())
                .andExpect(model().attribute(AttributesNames.TICKETS, hasSize(5)));
    }
}
