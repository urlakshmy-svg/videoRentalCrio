package com.videoRental.controller;

import org.springframework.web.bind.annotation.*;

import com.videoRental.dto.VideoRequest;
import com.videoRental.model.Video;
import com.videoRental.service.VideoService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@RestController
public class VideoController {

    @Autowired
    VideoService videoService;

    @PostMapping("/videos/list")
    public List<Video> getVideos() {
        return videoService.getAllAvailableVideos();
    }

    @PostMapping("/admin/videos/create")
    public ResponseEntity<String> createVideo(@RequestBody VideoRequest request) {
        return ResponseEntity.ok(videoService.createVideo(request));
    }

     @PutMapping("/admin/videos/update/{id}")
    public ResponseEntity<String> updateVideo( @PathVariable Long id, @RequestBody VideoRequest request) {
        return ResponseEntity.ok(videoService.updateVideo(id,request));
    }

     @DeleteMapping("/admin/videos/delete/{id}")
    public ResponseEntity<String> deleteVideo(@PathVariable Long id) {
        videoService.deleteVideo(id);
        return ResponseEntity.ok("Video deleted successfully");
    }

}

