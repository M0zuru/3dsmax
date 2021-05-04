package com.shurima.dsmax.services.impl;

import com.shurima.dsmax.dao.entity.Video;
import com.shurima.dsmax.dao.repository.VideoRepository;
import com.shurima.dsmax.services.UploadService;
import com.shurima.dsmax.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoRepository videoRepository;
    @Autowired
    private UploadService uploadService;
    @Value("${video.storage.dir}")
    private String imagesDir;

    @Override
    public Video getVideoById(Integer id) {
        return videoRepository.getOne(id);
    }

    @Override
    public Video save(Video video, MultipartFile uploadVideo) throws IOException {
        if ((!uploadVideo.isEmpty()) && (uploadVideo.getSize() != 0)) {
            saveProfilePicture(uploadVideo, video);
        }
        videoRepository.save(video);
        return video;
    }

    private void saveProfilePicture(MultipartFile uploadVideo, Video video) throws IOException {
        String imageName = StringUtils.cleanPath(uploadVideo.getOriginalFilename());
        uploadService.saveFile(imagesDir, imageName, uploadVideo);
        video.setPath(uploadService.getPath(imagesDir, imageName));
        videoRepository.save(video);
    }
}
