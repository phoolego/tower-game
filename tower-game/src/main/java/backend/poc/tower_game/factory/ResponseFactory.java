package backend.poc.tower_game.factory;

import backend.poc.tower_game.constant.ResponseConstant;
import backend.poc.tower_game.controller.response.GeneralResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ResponseFactory {

    public ResponseFactory() {
        //default constructor
    }

    public <T> ResponseEntity<GeneralResponse<T>> success() {
        GeneralResponse<T> responseObject = new GeneralResponse<>();
        responseObject.setCode(ResponseConstant.SUCCESS_CODE);
        return ResponseEntity.ok(responseObject);
    }

    public <T> ResponseEntity<GeneralResponse<T>> success(T data) {
        GeneralResponse<T> responseObject = new GeneralResponse<>();
        responseObject.setCode(ResponseConstant.SUCCESS_CODE);
        responseObject.setData(data);
        return ResponseEntity.ok(responseObject);
    }

    public <T> ResponseEntity<GeneralResponse<T>> error(HttpStatus httpStatus, String errorCode) {
        GeneralResponse<T> responseObject = new GeneralResponse<>();
        responseObject.setCode(errorCode);
        return new ResponseEntity<>(responseObject, httpStatus);
    }

    public <T> ResponseEntity<GeneralResponse<T>> error(HttpStatus httpStatus, String errorCode, T data) {
        GeneralResponse<T> responseObject = new GeneralResponse<>();
        responseObject.setCode(errorCode);
        responseObject.setData(data);
        return new ResponseEntity<>(responseObject, httpStatus);
    }

}
