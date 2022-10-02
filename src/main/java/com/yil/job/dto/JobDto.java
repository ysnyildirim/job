package com.yil.job.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobDto implements Serializable {
    private Long id;
    private String name;
    private Integer parentId;
    private Boolean isActive;
}
