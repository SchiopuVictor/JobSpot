package com.jobspot.service;

import com.jobspot.dto.InterviewRequestDto;
import com.jobspot.entity.Application;
import com.jobspot.entity.Interview;
import com.jobspot.mapper.InterviewMapper;
import com.jobspot.repository.ApplicationRepository;
import com.jobspot.repository.InterviewRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InterviewService {
    private final ApplicationRepository applicationRepository;
    private final InterviewRepository interviewRepository;

    @Transactional
    public Interview createInterview(InterviewRequestDto request){
        Interview interview = InterviewMapper.toEntity(request);

        Application application = applicationRepository.findById(request.getApplicationId())
                        .orElseThrow(()->new RuntimeException("Application not found!!"));

        interview.setApplication(application);

        return interviewRepository.save(interview);
    }

    public Interview getInterview(Long id){
        return interviewRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Interview not foud!!"));
    }

    @Transactional
    public Interview updateInterview(InterviewRequestDto request,Long id){
        Interview interview = interviewRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Interview not foud!!"));

        Application application = applicationRepository.findById(request.getApplicationId())
                .orElseThrow(()->new RuntimeException("Application not found!!"));

        interview.setApplication(application);
        interview.setDate(request.getDate());
        interview.setStatus(request.getStatus());
        interview.setTime(request.getTime());
        interview.setLocation(request.getLocation());
        interview.setType(request.getType());
        interview.setMeetingLink(request.getMeetingLink());

        return interviewRepository.save(interview);
    }

    @Transactional
    public void deleteInterview(Long id){
        interviewRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Interview not foud!!"));
        interviewRepository.deleteById(id);
    }
}
