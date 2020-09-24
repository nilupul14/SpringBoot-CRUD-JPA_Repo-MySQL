package com.myproject.spring.controller;

import com.myproject.spring.model.Tour;
import com.myproject.spring.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TourController {

    @Autowired
    private TourService tourService;

    @GetMapping("/tours")
    private ResponseEntity <List<Tour >> getAllTours(){
        return ResponseEntity.ok().body(tourService.getAllTours());
    }

    @GetMapping("/tours/{id}")
    private ResponseEntity <Tour> getTourById(@PathVariable long id){
        return ResponseEntity.ok().body(this.tourService.getTourById(id));
    }

    @PostMapping("/addTours")
    private ResponseEntity <Tour> createTour(@RequestBody Tour tour){
        return ResponseEntity.ok().body(this.tourService.createTour(tour));
    }

    @PutMapping("/editTour/{id}")
    public ResponseEntity < Tour > updateTour(@PathVariable long id, @RequestBody Tour tour) {
        tour.setId(id);
        return ResponseEntity.ok().body(this.tourService.updateTour(tour));
    }

    @DeleteMapping("/tours/{id}")
    public HttpStatus deleteTour(@PathVariable long id) {
        this.tourService.deleteTour(id);
        return HttpStatus.OK;
    }
}
