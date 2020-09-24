package com.doan.cnpm.service.dto;

import org.springframework.stereotype.Service;

@Service
public class ScheduleDTO {

    private Long id;

    private Long weekday;

    private Long lesson;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWeekday() {
        return weekday;
    }

    public void setWeekday(Long weekday) {
        this.weekday = weekday;
    }

    public Long getLesson() {
        return lesson;
    }

    public void setLesson(Long lesson) {
        this.lesson = lesson;
    }
}
