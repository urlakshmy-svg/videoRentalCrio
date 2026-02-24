package com.videoRental.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.videoRental.model.Rented;
import com.videoRental.model.User;
import com.videoRental.model.Video;

public interface RentalRepository extends JpaRepository<Rented, Long> {

    long countByUserAndReturnedFalse(User user);

    Optional<Rented> findByUserAndVideoAndReturnedFalse(User user, Video video);
}

