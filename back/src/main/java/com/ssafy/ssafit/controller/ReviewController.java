package com.ssafy.ssafit.controller;

import com.ssafy.ssafit.dao.ReviewDao;
import com.ssafy.ssafit.dto.Review;
import com.ssafy.ssafit.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    
    // 리뷰 등록
    @PostMapping("/review")
    public ResponseEntity<Void> registReview(Review review) {
        reviewService.regist(review);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PutMapping ("/update")
    public ResponseEntity<Void> ModifyReview(Review review) {
        reviewService.modify(review);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @DeleteMapping ("delete")
    public ResponseEntity<Void> deleteReview(String  reviewid) {
        reviewService.remove(reviewid);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping("/review")
    public ResponseEntity<List<Review>> getReviews(String youtubeid) {
        List<Review> reviews = reviewService.selectById(youtubeid);
        return new ResponseEntity<List<Review>>(reviews, HttpStatus.OK);
    }

}
