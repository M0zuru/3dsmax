package com.shurima.dsmax.services;

import com.shurima.dsmax.dao.entity.Video;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface VideoService {
    Video getVideoById(Integer id);
    Video save(Video video, MultipartFile uploadVideo) throws IOException;
    Video save(Video video, MultipartFile uploadVideo, MultipartFile attachment) throws IOException;
}
