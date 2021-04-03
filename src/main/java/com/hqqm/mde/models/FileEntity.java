package com.hqqm.mde.models;

import lombok.Data;

import java.util.Date;

@Data
public class FileEntity {
    private Long engineId;
    private String name;
    private String contentType;
    private String location;
    private Date createdAt;
}
