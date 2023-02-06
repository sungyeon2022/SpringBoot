package com.example.demo.Admin;

import com.example.demo.question.Question;
import com.example.demo.question.QuestionService;
import com.example.demo.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/admin")
public class AdminController {

    private final QuestionService questionService;
    private final UserService userService;

    @RequestMapping("/mainpage")
    public String modify(Model model, @RequestParam(value = "page",defaultValue = "0") int page,
                         @RequestParam(value = "kw", defaultValue = "") String kw,
                         @RequestParam(value = "subcount", defaultValue = "10") int subcount,
                         @RequestParam(value = "order", defaultValue = "createDate")	String order) {
        log.info("page{}, kw:{}",page, kw);
        Page<Question> paging = this.questionService.getList(page, subcount, kw, order);
        model.addAttribute("paging",paging);
        model.addAttribute("kw",kw);
        model.addAttribute("subcount",subcount);
        model.addAttribute("order",order);
        return "adminpage";
    }
}
