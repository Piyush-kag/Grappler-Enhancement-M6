package com.example.grappler.services;

import com.example.grappler.Entity.Worklogs;
import com.example.grappler.Repositories.WorkLogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class WorklogsService {

    @Autowired
    private WorkLogsRepository worklogRepository;

    public Worklogs addWorklog(Worklogs worklog) {
        return worklogRepository.save(worklog);
    }
}
