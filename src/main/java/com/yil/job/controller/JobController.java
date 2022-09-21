package com.yil.job.controller;

import com.yil.job.base.ApiConstant;
import com.yil.job.base.Mapper;
import com.yil.job.base.PageDto;
import com.yil.job.dto.CreateJobDto;
import com.yil.job.dto.JobDto;
import com.yil.job.exception.JobNotFoundException;
import com.yil.job.model.Job;
import com.yil.job.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/prs/v1/jobs")

public class JobController {
    private final JobService jobService;

    private final Mapper<Job, JobDto> mapper = new Mapper<>(JobService::toDto);

    @GetMapping
    public ResponseEntity<PageDto<JobDto>> findAll(
            @RequestParam(required = false, defaultValue = ApiConstant.PAGE) int page,
            @RequestParam(required = false, defaultValue = ApiConstant.PAGE_SIZE) int size) {
        if (page < 0)
            page = 0;
        if (size <= 0 || size > 1000)
            size = 1000;
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(mapper.map(jobService.findAll(pageable)));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> create(@RequestHeader(value = ApiConstant.AUTHENTICATED_USER_ID) Long authenticatedUserId,
                                         @Valid @RequestBody CreateJobDto dto) throws JobNotFoundException {
        jobService.save(dto, authenticatedUserId);
        return ResponseEntity.created(null).body("Meslek bilgisi eklendi");
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity replace(@RequestHeader(value = ApiConstant.AUTHENTICATED_USER_ID) Long authenticatedUserId,
                                  @PathVariable Long id,
                                  @Valid @RequestBody CreateJobDto dto) throws JobNotFoundException {
        jobService.replace(id, dto, authenticatedUserId);
        return ResponseEntity.ok().body("Meslek bilgisi güncellendi.");

    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> delete(@RequestHeader(value = ApiConstant.AUTHENTICATED_USER_ID) Long authenticatedPersonId,
                                         @PathVariable Long id) throws JobNotFoundException {
        Job job = jobService.findById(id);
        jobService.delete(job);
        return ResponseEntity.ok("Meslek datası silindi.");
    }
}
