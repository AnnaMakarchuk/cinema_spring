package org.study.cinema.integration.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.study.cinema.dto.MovieDto;
import org.study.cinema.services.impl.MovieServiceImpl;
import org.study.cinema.utils.AttributesNames;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureMockMvc
public class MovieControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MovieServiceImpl movieService;

    @Test
    public void shouldReturnHomepageWithActiveMovies() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(model().attributeHasNoErrors())
                .andExpect(model().attribute(AttributesNames.MOVIES, equalTo(createTestActiveMoviesDtoList())));
    }

    @Test
    public void shouldReturnActiveMoviesForAdminPAge() throws Exception {
        mockMvc.perform(get("/admin/movies"))
                .andExpect(status().isOk())
                .andExpect(model().attributeHasNoErrors())
                .andExpect(model().attribute(AttributesNames.MOVIES, equalTo(createTestActiveMoviesDtoList())));
    }

    @Test
    public void shouldReturnNonActiveMoviesForAdminPAge() throws Exception {
        mockMvc.perform(get("/admin/unactivemovies"))
                .andExpect(status().isOk())
                .andExpect(model().attributeHasNoErrors())
                .andExpect(model().attribute(AttributesNames.MOVIES, equalTo(createTestNonActiveMoviesDtoList())));
    }

    @Test
    public void shouldCancelMovieByAdmin() throws Exception {
        int cancelMovieId = 3;
        mockMvc.perform(post("/admin/cancelmovie")
                .param(AttributesNames.MOVIE_ID, String.valueOf(cancelMovieId)))
                .andExpect(status().isOk())
                .andExpect(model().attributeHasNoErrors())
                .andExpect(model().attribute(AttributesNames.SCHEDULES, hasSize(7)))
                .andExpect(model().attribute(AttributesNames.CLIENTS, hasSize(3)));
    }

    private List<MovieDto> createTestActiveMoviesDtoList() {
        return movieService.viewAllAvailableMovies();
    }

    private List<MovieDto> createTestNonActiveMoviesDtoList() {
        return movieService.viewAllUnAvailableMovies();
    }
}
