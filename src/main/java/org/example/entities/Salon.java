package org.example.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import java.time.DayOfWeek;

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

    public void printDays(){
        System.out.println("\nDays open:");
        for (char day:daysOpen.toCharArray()) {
            System.out.println(DayOfWeek.of(Integer.parseInt(String.valueOf(day))).name());
        }
    }
}
