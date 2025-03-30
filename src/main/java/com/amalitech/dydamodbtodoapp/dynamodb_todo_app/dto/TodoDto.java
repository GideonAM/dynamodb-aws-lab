package com.amalitech.dydamodbtodoapp.dynamodb_todo_app.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TodoDto {
    private String id;
    private String title;
    private String description;
    private boolean completed;
    private String createdAt;
    private String updatedAt;
}
