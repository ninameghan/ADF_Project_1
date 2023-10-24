package org.example.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Stylist {
    private int id;
    private String name;
    private String phoneNumber;
    private double annualSalary;
    private int salonId;
}
