package com.security.springsecuritydemo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.security.springsecuritydemo.entity.Notice;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
    @Query(value = "from Notice n where CURRENT_DATE BETWEEN noticeBegDt AND noticeEndDt")
	List<Notice> findAllActiveNotices();
}
