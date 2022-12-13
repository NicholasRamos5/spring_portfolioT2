package com.nighthawk.spring_portfolio.mvc.lightboard;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.GetMapping;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/lightboard/")
public class LightBoardAPIController {

    private LightBoard lightBoard;
    private JsonNode json;


    @GetMapping("/create/{rows}/{cols}")
    public ResponseEntity<JsonNode> createLightBoard(@PathVariable int rows, @PathVariable int cols) 
    throws JsonMappingException, JsonProcessingException {
        lightBoard = new LightBoard(rows, cols);

        ObjectMapper mapper = new ObjectMapper();
        System.out.println(lightBoard.toString());
        json = mapper.readTree(lightBoard.toString());

        return ResponseEntity.ok(json);
    }

    @PostMapping("/allOn")
    public ResponseEntity<JsonNode> allOn() throws JsonMappingException, JsonProcessingException {
        lightBoard.allOn();

        ObjectMapper mapper = new ObjectMapper(); 
        json = mapper.readTree(lightBoard.toString()); 

        return ResponseEntity.ok(json);
    }

    @PostMapping("/allOff")
    public ResponseEntity<JsonNode> allOff() throws JsonMappingException, JsonProcessingException {
        lightBoard.allOff();

        ObjectMapper mapper = new ObjectMapper(); 
        json = mapper.readTree(lightBoard.toString()); 

        return ResponseEntity.ok(json);
    }

    @PostMapping("/toggleLight/{row}/{col}")
    public ResponseEntity<JsonNode> getLight(@PathVariable int row, @PathVariable int col) throws JsonMappingException, JsonProcessingException {
        lightBoard.lightToggle(row, col);

        ObjectMapper mapper = new ObjectMapper(); 
        json = mapper.readTree(lightBoard.toString()); 

        return ResponseEntity.ok(json);
    }
    
}