package com.ssafy.ssafit.controller;

import com.ssafy.ssafit.dto.Review;
import com.ssafy.ssafit.dto.Video;
import com.ssafy.ssafit.service.ReviewService;
import com.ssafy.ssafit.service.UserService;
import com.ssafy.ssafit.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class VideoController {

    @Autowired
    private VideoService videoservice;

    @Autowired
    private ReviewService reviewService;


    @PostMapping("video")
    public ResponseEntity<List<Video>> partVideo(String part) {
        List<Video> videos = videoservice.selectByPart(part);

        return new ResponseEntity<List<Video>>(videos, HttpStatus.OK);
    }

//    @PostMapping("detail")
//    public String detailVideo(String id, Model model) {
//        List<Review> reviews = reviewService.selectById(id);
//        model.addAttribute("reviews", reviews);
//        model.addAttribute("youtubeId", id);
//        return "review";
//    }



}
