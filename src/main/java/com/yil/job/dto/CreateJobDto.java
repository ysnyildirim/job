package com.yil.job.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateJobDto {
    @NotBlank
    @Length(min = 1, max = 1000)
    private String name;
    private Integer parentId;
    private Boolean isActive;
}
