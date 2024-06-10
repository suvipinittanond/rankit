package com.techelevator.model;

import java.time.LocalDateTime;

public class IssueTime {
    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
