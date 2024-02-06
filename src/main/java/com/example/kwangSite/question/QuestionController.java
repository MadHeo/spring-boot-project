package com.example.kwangSite.question;

import ch.qos.logback.core.net.SyslogOutputStream;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/question")
public class QuestionController {

    private final QuestionRepository questionRepository;
    @Autowired
    public QuestionService questionService;

    @GetMapping("/list")
    public String list(Model model) {
        List<Question> questionList = this.questionRepository.findAll();
        model.addAttribute("questionList", questionList);
        return "question_list";
    }

    @GetMapping("/create")
    public String questionCreate(Model model) {

        return "question_form";
    }

    @PostMapping("/create")
    public String create(@RequestParam(value = "subject") String subject, @RequestParam(value = "content") String content){
        this.questionService.create(subject, content);

        return "redirect:/question/list";
    }
}
