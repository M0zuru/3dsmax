package com.shurima.dsmax.controllers;

import com.shurima.dsmax.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class VideoController {

    @Autowired
    private VideoService videoService;

    @GetMapping("/video/{id}")
    public String getVideo(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("video", videoService.getVideoById(id));
        return "video";
    }
}
