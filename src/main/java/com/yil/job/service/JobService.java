package com.yil.job.service;

import com.yil.job.dto.CreateJobDto;
import com.yil.job.dto.JobDto;
import com.yil.job.exception.JobNotFoundException;
import com.yil.job.model.Job;
import com.yil.job.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@RequiredArgsConstructor
@Service
public class JobService {
    private final JobRepository jobRepository;

    public static JobDto toDto(Job job) throws NullPointerException {
        if (job == null)
            throw new NullPointerException("Job inform is null");
        JobDto dto = new JobDto();
        dto.setId(job.getId());
        dto.setName(job.getName());
        dto.setParentId(job.getParentId());
        dto.setIsActive(job.getIsActive());
        return dto;
    }

    public Page<Job> findAll(Pageable pageable) {
        return jobRepository.findAll(pageable);
    }

    public Job save(CreateJobDto dto, long userId) {
        Job job = new Job();
        return getJobInform(dto, userId, job);
    }

    private Job getJobInform(CreateJobDto dto, long userId, Job job) {
        job.setName(dto.getName());
        job.setParentId(dto.getParentId());
        job.setIsActive(dto.getIsActive());
        if (job.getId() == null) {
            job.setCreatedUserId(userId);
            job.setCreatedDate(new Date());
        } else {
            job.setLastModifyUserId(userId);
            job.setLastModifyDate(new Date());
        }
        return jobRepository.save(job);
    }

    public Job replace(long id, CreateJobDto dto, long userId) throws JobNotFoundException {
        Job job = findById(id);
        return getJobInform(dto, userId, job);
    }

    public Job findById(Long id) throws JobNotFoundException {
        return jobRepository.findById(id).orElseThrow(JobNotFoundException::new);
    }

    public void delete(Job job) {
        jobRepository.delete(job);
    }
}
