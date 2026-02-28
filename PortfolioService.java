package com.innomax.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.innomax.demo.entity.Portfolio;
import com.innomax.demo.repository.PortfolioRepository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

@Service
public class PortfolioService {

    private final PortfolioRepository repository;

    public PortfolioService(PortfolioRepository repository) {
        this.repository = repository;
    }

    // Upload CSV and save to DB
    public void uploadCSV(MultipartFile file) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

            String line = br.readLine(); // header
            System.out.println("HEADER: " + line);

            while ((line = br.readLine()) != null) {

                if (line.trim().isEmpty()) continue;

                System.out.println("ROW: " + line);

                String[] data = line.split(",");

                System.out.println("Columns count: " + data.length);

                for (int i = 0; i < data.length; i++) {
                    System.out.println("Column " + i + ": " + data[i]);
                }

                String stockName = data[0].trim();
                int quantity = Integer.parseInt(data[2].trim());
                double buyPrice = Double.parseDouble(data[3].trim());
                double currentPrice = Double.parseDouble(data[4].trim());

                Portfolio portfolio = new Portfolio(
                        stockName,
                        quantity,
                        buyPrice,
                        currentPrice
                );

                repository.save(portfolio);
            }

        } catch (Exception e) {
            e.printStackTrace();  // VERY IMPORTANT
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    // Get portfolio summary
    public Map<String, Double> getSummary() {
        List<Portfolio> list = repository.findAll();

        double totalInvested = 0;
        double totalCurrent = 0;

        for (Portfolio p : list) {
            totalInvested += p.getQuantity() * p.getBuyPrice();
            totalCurrent += p.getQuantity() * p.getCurrentPrice();
        }

        double totalReturn = totalCurrent - totalInvested;
        double returnPercentage = totalInvested == 0 ? 0 : (totalReturn / totalInvested) * 100;

        Map<String, Double> summary = new HashMap<>();
        summary.put("totalInvested", totalInvested);
        summary.put("totalCurrentValue", totalCurrent);
        summary.put("totalReturn", totalReturn);
        summary.put("returnPercentage", returnPercentage);

        return summary;
    }
}