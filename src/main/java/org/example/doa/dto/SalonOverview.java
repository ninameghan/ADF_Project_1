package org.example.doa.dto;

import org.example.entities.Stylist;

import java.util.List;

public record SalonOverview(int id, String name, String phoneNumber, double annualSalary, int salonId, String salonName) {
}
