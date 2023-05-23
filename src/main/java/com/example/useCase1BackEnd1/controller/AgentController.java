package com.example.useCase1BackEnd1.controller;


import com.example.useCase1BackEnd1.model.Agent;
import com.example.useCase1BackEnd1.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RestController
public class AgentController {

    @Autowired
    public CrudService crudService;

    //get all agents
    @GetMapping("/getAllAgents")
    public ResponseEntity<List<Agent>> getAllAgents() {
        try {
            List<Agent> allAgents = crudService.getAllAgents();
            return  ResponseEntity.ok(allAgents);
        } catch (HttpClientErrorException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //get agent by id
    @GetMapping("/getAgent/{id}")
    public ResponseEntity<Agent> getAgentById(@PathVariable("id") String agentId) {
        try {
            return ResponseEntity.ok(crudService.getAgentsById(agentId));
        }catch (HttpClientErrorException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //add an agent
    @PostMapping("/addAgent")
    public ResponseEntity<Agent> addAgent(@RequestBody Agent agent) {
        try{
            return ResponseEntity.ok(crudService.addAgent(agent));
        } catch (HttpClientErrorException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    //delete agent by id
    @DeleteMapping("/deleteAgent/{id}")
    public void deleteAgentById(@PathVariable("id") String agentId) {
        crudService.deleteAgentById(agentId);
    }

    //update agent by id
    @PutMapping("/updateAgent/{id}")
    public ResponseEntity<Agent> updateAgentById(@PathVariable("id") String agentId, @RequestBody Agent agent) {
        try {
            return ResponseEntity.ok(crudService.updateAgent(agentId, agent));
        } catch (HttpClientErrorException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
