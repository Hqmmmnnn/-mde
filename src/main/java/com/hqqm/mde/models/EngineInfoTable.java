package com.hqqm.mde.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EngineInfoTable {
    private String name;
    private List<EngineInfoRow> rows;
}