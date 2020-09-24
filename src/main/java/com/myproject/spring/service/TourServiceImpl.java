package com.myproject.spring.service;

import com.myproject.spring.exception.ResourceNotFoundException;
import com.myproject.spring.model.Tour;
import com.myproject.spring.repository.TourRepository;
import jdk.management.resource.ResourceRequestDeniedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional

public class TourServiceImpl implements TourService {

    @Autowired
    private TourRepository tourRepository;

    @Override
    public Tour createTour(Tour tour) {
        return tourRepository.save(tour);
    }

    @Override
    public Tour updateTour(Tour tour) {
        Optional<Tour> tourDB = this.tourRepository.findById(tour.getId());

        if (tourDB.isPresent()){
            Tour tourUpdate = tourDB.get();
            tourUpdate.setId(tour.getId());
            tourUpdate.setName(tour.getName());
            tourUpdate.setLocation(tour.getLocation());
            tourUpdate.setProvince(tour.getProvince());
            tourUpdate.setState(tour.getState());
            tourUpdate.setType(tour.getType());
            return tourUpdate;
        } else {
            throw new ResourceNotFoundException("Record not found with id : " + tour.getId());
        }
    }

    @Override
    public List<Tour> getAllTours() {
        return this.tourRepository.findAll();
    }

    @Override
    public Tour getTourById(long tourId) {

        Optional<Tour> tourDB = this.tourRepository.findById(tourId);

        if (tourDB.isPresent()){
            return tourDB.get();
        } else {
            throw new ResourceNotFoundException("Record not found with id : " + tourId);
        }
    }

    @Override
    public void deleteTour(long id) {
        Optional< Tour > tourOptional = this.tourRepository.findById(id);

        if (tourOptional.isPresent()){
            this.tourRepository.delete(tourOptional.get());
        } else {
            throw new ResourceNotFoundException("Record not found with id : " + id);
        }
    }
}
