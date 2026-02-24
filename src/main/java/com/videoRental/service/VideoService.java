package com.videoRental.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import com.videoRental.dto.VideoRequest;
import com.videoRental.model.Video;
import com.videoRental.repository.VideoRepository;

public class VideoService{

    @Autowired 
    VideoRepository videoRepository;

    public List<Video> getAllAvailableVideos() {
        List<Video> videos = videoRepository.findByAvailableTrue();
        return videos;
    }

    public String createVideo(VideoRequest request) {

        Video video = Video.builder()
                .title(request.getTitle())
                .genre(request.getGenre())
                .available(request.isAvailable())
                .build();

        videoRepository.save(video);
        return "Successfully created";
    }

    public void deleteVideo(Long id) {

        Video video = videoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Video not found"));

        videoRepository.delete(video);
    }

     public String updateVideo(Long id, VideoRequest request) {

        Video video = videoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Video not found"));

        video.setTitle(request.getTitle());
        video.setGenre(request.getGenre());
        video.setAvailable(request.isAvailable());
        videoRepository.save(video);

        return "Successfully edited";
    }
    
}
