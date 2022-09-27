package com.cinemastore.privateservice.controller;

import com.cinemastore.privateservice.controller.helpers.ReviewEndpoints;
import com.cinemastore.privateservice.controller.helpers.StudioEndpoints;
import com.cinemastore.privateservice.dto.ReviewRequestDto;
import com.cinemastore.privateservice.dto.ReviewResponseDto;
import com.cinemastore.privateservice.dto.StudioRequestDto;
import com.cinemastore.privateservice.dto.StudioResponseDto;
import com.cinemastore.privateservice.exception.NoSuchContentException;
import com.cinemastore.privateservice.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(ReviewEndpoints.REVIEW)
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping(ReviewEndpoints.ADD)
    public ResponseEntity<ReviewResponseDto> save(@RequestParam String title,
                                                  @RequestParam(required = false) String comment,
                                                  @RequestParam Boolean isRecommend) throws NoSuchContentException {
        return new ResponseEntity<>(reviewService.save(ReviewRequestDto.builder()
                .userId(0L)
                .title(title)
                .comment(comment)
                .isRecommend(isRecommend)
                .build()),
                HttpStatus.CREATED);
    }

    @GetMapping(ReviewEndpoints.GET_BY_ID)
    public ResponseEntity<ReviewResponseDto> getById(@PathVariable UUID id) throws NoSuchContentException {
        return new ResponseEntity<>(reviewService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping(ReviewEndpoints.DELETE_BY_ID)
    public ResponseEntity<HttpStatus> deleteById(@PathVariable UUID id) throws NoSuchContentException {
        reviewService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @PutMapping(ReviewEndpoints.UPDATE)
//    public ResponseEntity<ReviewResponseDto> update(@PathVariable UUID id, @RequestParam String title) throws NoSuchContentException {
//        return new ResponseEntity<>(reviewService.updateById(id, new ReviewRequestDto(title)), HttpStatus.OK);
//    }
}
