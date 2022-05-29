package com.online.test.onlinetest.services;
import com.online.test.onlinetest.dto.ExamDTO;

public interface ExamService{
    public ExamDTO create(NewExamDTO examDTO);
    public ExamDTO retrieve(Lond id) throws Exception;
    public ExamDTO update(ExamDTO examDTO) throws Exception;
    public ExamDTO delete(Long id) throws Exception;

    public List<ExamDTO>lilst();
}