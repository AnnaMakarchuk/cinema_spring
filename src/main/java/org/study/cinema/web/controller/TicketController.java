package org.study.cinema.web.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.study.cinema.dto.TicketDto;
import org.study.cinema.services.TicketService;
import org.study.cinema.utils.AttributesNames;

import java.util.List;

@Controller
public class TicketController {

    private static final Logger LOGGER = LogManager.getLogger(TicketController.class);

    @Autowired
    private TicketService ticketService;

    //TODO add user id as a parameter or authentication
    @GetMapping("/client/tickets")
    public String viewAllTicketsByUser(Model model) {
        int userId = 3;
        List<TicketDto> ticketDtoList = ticketService.getAllTicketsByUser(userId);
        LOGGER.info("List of tickets bought by user with id " + userId + " is created");
        model.addAttribute(AttributesNames.TICKETS, ticketDtoList);

        return "clientPages/client_tickets";
    }

    @GetMapping(value = {"/admin/tickets", "/admin/tickets/{page}"})
    public String viewAllAvailableTickets(@RequestParam(name = AttributesNames.PAGE, defaultValue = "1") String page,
                                          Model model) {
        int pageQuantities = ticketService.countPagesQuantity();
        model.addAttribute(AttributesNames.PAGES, pageQuantities);
        LOGGER.info("Total pages quantity for 5 entry is " + pageQuantities);

        List<TicketDto> ticketDtoListOnPage = ticketService.allTicketsWithPagination(Integer.parseInt(page));
        LOGGER.info("List of tickets for page " + page + " was selected");

        model.addAttribute(AttributesNames.TICKETS, ticketDtoListOnPage);

        return "adminPages/admin_tickets_list";
    }
}
