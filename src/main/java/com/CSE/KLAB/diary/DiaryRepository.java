package com.CSE.KLAB.diary;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
    List<Diary> findByMemberUserId(Long userId);
}