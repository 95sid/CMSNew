package com.collegeMS.CMS.Advices;

import com.collegeMS.CMS.Exceptions.AddmissionDetailsNotfoundException;
import com.collegeMS.CMS.Exceptions.ProfessorNotFoundException;
import com.collegeMS.CMS.Exceptions.StudentDetailsNotfoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProfessorNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleProfessorException(ProfessorNotFoundException exception){
        ApiError apiError = ApiError
                .builder()
                .message(exception.getMessage())
                .status(HttpStatus.NOT_FOUND)
                .build();

        return handleApiReponse(apiError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleMethodArgumentException(MethodArgumentNotValidException exception){
        List<String> errors = exception
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error->error.getDefaultMessage())
                .toList();

        ApiError apiError = ApiError
                .builder()
                .message("Validation Failed")
                .status(HttpStatus.BAD_REQUEST)
                .errors(errors)
                .build();

        return handleApiReponse(apiError);
    }

    @ExceptionHandler(StudentDetailsNotfoundException.class)
    public ResponseEntity<ApiResponse<?>> handleStudentDetailsNotFoundException(StudentDetailsNotfoundException exception){
        ApiError apiError = ApiError
                .builder()
                .message(exception.getMessage())
                .status(HttpStatus.NOT_FOUND)
                .build();

        return handleApiReponse(apiError);
    }

    @ExceptionHandler(AddmissionDetailsNotfoundException.class)
    public ResponseEntity<ApiResponse<?>> handleAddmissionDetailsNotfoundException(AddmissionDetailsNotfoundException exception){
        ApiError apiError = ApiError
                .builder()
                .message(exception.getMessage())
                .status(HttpStatus.NOT_FOUND)
                .build();

        return handleApiReponse(apiError);
    }


    ResponseEntity<ApiResponse<?>> handleApiReponse(ApiError apiError){
        return new ResponseEntity<ApiResponse<?>>(new ApiResponse<>(apiError),apiError.getStatus());
    }

}
