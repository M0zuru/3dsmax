package com.shurima.dsmax.controllers;

import com.shurima.dsmax.dao.entity.Video;
import com.shurima.dsmax.services.CourseService;
import com.shurima.dsmax.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@Controller
public class VideoController {

    @Autowired
    private VideoService videoService;
    @Autowired
    private CourseService courseService;

    @GetMapping("/video/{id}")
    public String getVideo(@PathVariable("id") Integer id, Model model) {
        Video video = videoService.getVideoById(id);
        model.addAttribute("video", video);
        model.addAttribute("course", video.getCourse());
        return "video";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/add_video")
    public String getAddVideoForm(@RequestParam("courseId") Integer courseId, Model model) {
        Video video = new Video();
        model.addAttribute("video", video);
        model.addAttribute("courseId", courseId);
        return "addVideo";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/add_video")
    public String addVideo(Model model, @Valid Video video, Errors errors,
                           @RequestParam("video") MultipartFile uploadVideo, @RequestParam("courseId") Integer courseId) throws IOException {
        if (!errors.hasErrors()) {
            video.setCourse(courseService.getCourseById(courseId));
            videoService.save(video, uploadVideo);
            return "redirect:/video/" + video.getId();
        }
        model.addAttribute("video", video);
        return "addVideo";
    }
}
