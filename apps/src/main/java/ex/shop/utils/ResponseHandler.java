package ex.shop.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ResponseHandler {

    public ResponseEntity<Object> SuccessHandler() {
        Map<String, String> map = new HashMap<>();
        map.put("result", "success");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    public ResponseEntity<Object> FailHandler() {
        Map<String, String> map = new HashMap<>();
        map.put("result", "failed");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    public ResponseEntity<Object> ReplyHandler(Object object) {
        return new ResponseEntity<>(object, HttpStatus.OK);
    }

}
