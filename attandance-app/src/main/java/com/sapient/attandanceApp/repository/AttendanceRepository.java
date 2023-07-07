package com.sapient.attandanceApp.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sapient.attandanceApp.entity.Attendance;

@Repository
public interface AttendanceRepository extends CrudRepository<Attendance, Attendance.AttendanceId> {

	public List<Attendance> findByIdAttendanceDate(LocalDate attendanceDate);

	public List<Attendance> findByIdEmployeeId(int employeeId);

}
