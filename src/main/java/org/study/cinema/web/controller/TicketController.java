package org.study.cinema.web.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.study.cinema.dto.TicketDto;
import org.study.cinema.services.TicketService;
import org.study.cinema.utils.AttributesNames;

@Controller
public class TicketController {

    private static final Logger LOGGER = LogManager.getLogger(TicketController.class);

    @Autowired
    private TicketService ticketService;

    @GetMapping(value = {"/admin/tickets", "/admin/tickets/{page}"})
    public String viewAllAvailableTickets(@PathVariable(required = false, name = AttributesNames.PAGE) String page,
                                          Model model) {
        int pageQuantities = ticketService.countPagesQuantity();
        model.addAttribute(AttributesNames.PAGES, pageQuantities);
        LOGGER.info("Total pages quantity for 5 entry is " + pageQuantities);

        Page<TicketDto> ticketDtoListOnPage = ticketService.allTicketsWithPagination(Integer.parseInt(page));
        LOGGER.info("List of tickets for page " + page + "was selected");

        model.addAttribute(AttributesNames.TICKETS, ticketDtoListOnPage);
        return "adminPages/admin_tickets_list";
    }
}
