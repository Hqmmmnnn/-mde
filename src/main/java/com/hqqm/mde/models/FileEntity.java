package com.hqqm.mde.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileEntity {
    private Long engineId;
    private String name;
    private String contentType;
    private String location;
    private Date createdAt;
}
