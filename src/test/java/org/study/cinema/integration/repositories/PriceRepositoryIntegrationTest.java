package org.study.cinema.integration.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.study.cinema.dto.PlaceDto;
import org.study.cinema.dto.PositionDto;
import org.study.cinema.entity.Price;
import org.study.cinema.repositories.PriceRepository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.stringContainsInOrder;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional
public class PriceRepositoryIntegrationTest {

    @Autowired
    private PriceRepository priceRepository;

    @Test
    public void shouldReturnPriceForRow() {
        int hallId = 1;
        List<Price> resultPrices = createTestPositionDto().getPlaces().stream()
                .map(p -> priceRepository.findAllByRowAndHallId(p.getRow(), hallId).get())
                .collect(Collectors.toList());

        System.out.println(resultPrices.toString());

        assertThat("Prices List size for selected places is ", resultPrices, hasSize(3));
        assertThat(resultPrices.get(0).getPrice(), equalTo(50.00));
        assertThat(resultPrices.get(1).getPrice(), equalTo(75.00));
        assertThat(resultPrices.get(2).getPrice(), equalTo(150.00));
    }

    private PositionDto createTestPositionDto() {
        List<PlaceDto> placeDtos = Arrays.asList
                (new PlaceDto(1, 3), new PlaceDto(4, 6), new PlaceDto(6, 1));
        return PositionDto.builder()
                .scheduleId(5)
                .places(placeDtos)
                .build();
    }
}
