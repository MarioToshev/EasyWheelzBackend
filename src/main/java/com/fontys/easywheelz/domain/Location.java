package com.fontys.easywheelz.domain;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Location {

    private Long id;
    private String name;
    private String address;
    private String phone;
    private String hours;

}
