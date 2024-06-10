package com.techelevator.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class IssueTimeMapper implements RowMapper<IssueTime> {
    @Override
    public IssueTime mapRow(ResultSet rs, int rowNum) throws SQLException {
        IssueTime issueTime = new IssueTime();
        // Assuming the start_time and end_time columns are in ISO 8601 format without seconds
        String startTimeString = rs.getString("start_time") + ":00"; // Appending seconds
        String endTimeString = rs.getString("end_time") + ":00"; // Appending seconds
        issueTime.setStartTime(LocalDateTime.parse(startTimeString, DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        issueTime.setEndTime(LocalDateTime.parse(endTimeString, DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        return issueTime;
    }
}

