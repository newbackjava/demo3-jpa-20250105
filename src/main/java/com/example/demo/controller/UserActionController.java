package com.example.demo.controller;

import com.example.demo.entity.UserAction;
import com.example.demo.repository.UserActionRepository;
import com.example.demo.service.UserActionProducer;
import com.example.demo.service.UserActionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user-actions")
@RequiredArgsConstructor
public class UserActionController {
    private final UserActionProducer userActionProducer;
    private final UserActionService userActionService;

    @GetMapping("/actions") //user-actions/actions
    public String actions() {
        return "kafka/actions";
    }

    @PostMapping("/send")
    @ResponseBody
    public String sendUserAction(@RequestBody UserAction userAction) {
        userActionProducer.sendUserAction(userAction);
        return "ok";
    }

    @GetMapping("/allActions") //analysis용
    public String getAllActions(Model model) {
        List<UserAction> actions = userActionService.findAll();
        model.addAttribute("actions", actions);
        return "kafka/allActions";
    }

}