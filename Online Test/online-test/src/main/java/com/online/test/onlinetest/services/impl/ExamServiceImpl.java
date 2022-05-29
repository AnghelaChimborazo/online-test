package com.online.test.services.ExamServiceImpl;

@Service
public class ExamServiceImpl implements ExamService{
    final ModelMapper modelMapper;
    final ExamRepository examRepository;

    @Autowired
    public ExamServiceImpl(@Autowired ExamRepository repository, ModelMapper mapper){
        this.examRepository = repository;
        this.modelMapper = mapper;
    } 

    @Override 
    @Transactional
    public ExamDTO create(NewExamDTO examDTO){
        Exam exam = modelMapper.map(examDTO, Exam.class);
        examRepository.save(exam);
        ExamDTO examDTOCreated= modelMapper.map(exam,ExamDTO.class);
        return exam;
    }
//Optional (java.util.optional)
    @Override
     @Transactional(readOnly = true)
    public ExamDTO retrieve(Long id) throws Exception{
        //Exam exam = examRepository.findById(id).orElseThrow(()->new Exception(message: "Exam not found"));
        Optional <Exam> exam = examRepository.findById(id);
        if(exam.isEmpty()){
            throw new Exception("Exam not found");
        }
        return modelMapper.map(exam,ExamDTO.class);
    }

    @Override
    @Transactional
    public ExamDTO update(NewExamDTO examDTO) throws Exception{
        Exam exam = examRepository.findById(examDTO.getId()).orElseThrow(()->new Exception(message: "Exam not found"));
        exam = modelMapper.map(examDTO, Exam.class);
        return modelMapper.map(exam,Exam.class);
    }

    @Override
    @Transactional
    public ExamDTO delete(Long id) throws Exception{
        Exam exam = examRepository.findById(id).orElseThrow(()->new Exception(message: "Exam not found"));

        examRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExamDTO> list(){
        List<Exam> exams = examRepository.findAll();
        return exams.strem().map(exam -> modelMapper.map(exam, ExamDTO.class)).collect(Collectors.toList());
    }
}
