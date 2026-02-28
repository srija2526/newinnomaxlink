package com.innomax.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.innomax.demo.entity.Portfolio;
import com.innomax.demo.repository.PortfolioRepository;
import com.innomax.demo.service.PortfolioService;

import java.util.*;

@RestController
@RequestMapping("/api/portfolio")
@CrossOrigin
public class PortfolioController {

    private final PortfolioService service;
    private final PortfolioRepository repository;

    public PortfolioController(PortfolioService service, PortfolioRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    // Upload CSV file
    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {
        service.uploadCSV(file);
        return ResponseEntity.ok("File uploaded successfully");
    }

    // Get portfolio summary
    @GetMapping("/summary")
    public Map<String, Double> summary() {
        return service.getSummary();
    }

    // Get portfolio by ID
    @GetMapping("/{id}")
    public Portfolio getById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow();
    }

    // Get all portfolios
    @GetMapping("/all")
    public List<Portfolio> getAll() {
        return repository.findAll();
    }

    // Delete portfolio by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.ok("Deleted successfully");
    }
}