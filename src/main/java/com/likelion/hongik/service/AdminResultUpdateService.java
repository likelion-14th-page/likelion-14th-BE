package com.likelion.hongik.service;

import com.likelion.hongik.domain.StudentResult;
import com.likelion.hongik.domain.enums.ResultType;
import com.likelion.hongik.repository.StudentResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminResultUpdateService {

    private final StudentResultRepository studentResultRepository;

    @Transactional
    public void updateDocument(Long studentId, boolean checked) {
        StudentResult sr = studentResultRepository.findByStudentIdWithStudent(studentId)
                .orElseThrow(() -> new IllegalArgumentException("학생 결과가 없습니다. studentId=" + studentId));

        sr.updateDocumentResult(checked ? ResultType.합격 : ResultType.불합격);
    }

    @Transactional
    public void updateFinal(Long studentId, boolean checked) {
        StudentResult sr = studentResultRepository.findByStudentIdWithStudent(studentId)
                .orElseThrow(() -> new IllegalArgumentException("학생 결과가 없습니다. studentId=" + studentId));

        sr.updateFinalResult(checked ? ResultType.합격 : ResultType.불합격);
    }
}
