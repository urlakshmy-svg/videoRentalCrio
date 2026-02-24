package com.videoRental.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.videoRental.model.Video;

public interface VideoRepository extends JpaRepository<Video, Long> {
    List<Video> findByAvailableTrue();
}

