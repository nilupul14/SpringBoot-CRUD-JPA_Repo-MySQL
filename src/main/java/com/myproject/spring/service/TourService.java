package com.myproject.spring.service;

import com.myproject.spring.model.Tour;

import java.util.List;

public interface TourService {

    Tour createTour(Tour tour);

    Tour updateTour(Tour tour);

    List< Tour > getAllTours();

    Tour getTourById(long tourId);

    void deleteTour(long id);
}
