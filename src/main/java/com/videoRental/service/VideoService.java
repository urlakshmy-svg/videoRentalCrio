package com.videoRental.service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.videoRental.dto.VideoRequest;
import com.videoRental.model.Rented;
import com.videoRental.model.User;
import com.videoRental.model.Video;
import com.videoRental.repository.RentalRepository;
import com.videoRental.repository.UserRepository;
import com.videoRental.repository.VideoRepository;
@Service
public class VideoService{

    @Autowired 
    VideoRepository videoRepository;

    @Autowired 
    RentalRepository rentalRepository;

    @Autowired
    private UserRepository userRepository;

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

    public String rentVideo(Long videoId, String username) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Video video = videoRepository.findById(videoId)
                .orElseThrow(() -> new RuntimeException("Video not found"));
                
        long activeRentals = rentalRepository.countByUserAndReturnedFalse(user);

        if (activeRentals >= 2) {
            throw new RuntimeException("User already has 2 active rentals");
        }
        if (!video.isAvailable()) {
            throw new RuntimeException("Video not available");
        }
        Rented rental = new Rented();
        rental.setUser(user);
        rental.setVideo(video);
        rental.setRentedAt(LocalDateTime.now());
        rental.setReturned(false);
        rentalRepository.save(rental);

        video.setAvailable(false);
        videoRepository.save(video);

        return "Video rented successfully";
    }

    public String returnVideo(Long videoId, String username) {

    User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("User not found"));
    Video video = videoRepository.findById(videoId)
            .orElseThrow(() -> new RuntimeException("Video not found"));

    Rented rental = rentalRepository
            .findByUserAndVideoAndReturnedFalse(user, video)
            .orElseThrow(() -> new RuntimeException("No active rental found"));

    rental.setReturned(true);
    rental.setReturnedAt(LocalDateTime.now());
    rentalRepository.save(rental);

    video.setAvailable(true);
    videoRepository.save(video);

    return "Video returned successfully";
}
    
}
