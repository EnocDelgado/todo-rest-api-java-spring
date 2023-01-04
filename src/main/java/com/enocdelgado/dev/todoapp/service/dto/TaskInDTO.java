package com.enocdelgado.dev.todoapp.service.dto;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskInDTO {
    //DTO define what kind of data we should ask for
    private String title;
    private  String description;
    private LocalDateTime eta;

}
