    package com.collegeMS.CMS.Services;

    import com.collegeMS.CMS.DTOs.AdmissionRecordDTO;
    import com.collegeMS.CMS.Entity.AdmissionRecord;
    import com.collegeMS.CMS.Exceptions.AddmissionDetailsNotfoundException;
    import com.collegeMS.CMS.Exceptions.StudentDetailsNotfoundException;
    import com.collegeMS.CMS.Repository.AdmissionRecordRepository;
    import com.collegeMS.CMS.Repository.StudentRepository;
    import org.modelmapper.ModelMapper;
    import org.springframework.stereotype.Service;
     import org.springframework.transaction.annotation.Transactional;

    import java.util.List;

    @Service
    public class AdmissionRecordService {
        private final ModelMapper modelMapper;
        private final AdmissionRecordRepository admissionRecordRepository;
        private final StudentRepository studentRepository;

        public AdmissionRecordService(ModelMapper modelMapper, AdmissionRecordRepository admissionRecordRepository, StudentRepository studentRepository) {
            this.modelMapper = modelMapper;
            this.admissionRecordRepository = admissionRecordRepository;
            this.studentRepository = studentRepository;
        }

        public AdmissionRecordDTO getAddmissionRecordById(Long admissionId) {
            AdmissionRecord record = admissionRecordRepository.findById(admissionId)
                    .orElseThrow(() -> new AddmissionDetailsNotfoundException("Admission Record with the id:" + admissionId + " not Exist"));
            return modelMapper.map(record, AdmissionRecordDTO.class);
        }

        public List<AdmissionRecordDTO> getAllAdmissionDetails() {
            List<AdmissionRecord> records = admissionRecordRepository.findAll();
            return records
                    .stream()
                    .map(record->modelMapper.map(record, AdmissionRecordDTO.class))
                    .toList();
        }

        @Transactional
        public AdmissionRecordDTO addAddmissionRecord(AdmissionRecordDTO admissionDTO) {
            isStudentIdExist(admissionDTO.getStudentId());
            AdmissionRecord record = modelMapper.map(admissionDTO,AdmissionRecord.class);
            return modelMapper.map(admissionRecordRepository.save(record), AdmissionRecordDTO.class);
        }

        @Transactional
        public AdmissionRecordDTO updateAddmissionRecord(Long admissionRecId,AdmissionRecordDTO admissionDTO) {
            isStudentIdExist(admissionDTO.getStudentId());
            isAdmissionIdExist(admissionRecId);

            AdmissionRecord record = modelMapper.map(admissionDTO,AdmissionRecord.class);
            record.setId(admissionRecId);
            return modelMapper.map(admissionRecordRepository.save(record), AdmissionRecordDTO.class);
        }
        @Transactional
        public Boolean deleteAdmissionRecord(Long admissionRecId) {
            isAdmissionIdExist(admissionRecId);
            admissionRecordRepository.deleteById(admissionRecId);
            return true;
        }

        private void isAdmissionIdExist(Long admissionId) {
            if(!admissionRecordRepository.existsById(admissionId)) throw new AddmissionDetailsNotfoundException("Admission Record with the id:"+admissionId+" not Exist");
        }

        private void isStudentIdExist(Long studentId) {
            if(!studentRepository.existsById(studentId)) throw new StudentDetailsNotfoundException("Student with the id:"+studentId+" not Exist");

        }
    }
