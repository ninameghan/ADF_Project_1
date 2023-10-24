package org.example.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Salon {
    private int id;
    private String name;
    private String address;
    private String phoneNumber;
    private String daysOpen;
}
