package com.shurima.dsmax.services.impl;

import com.shurima.dsmax.dao.entity.Video;
import com.shurima.dsmax.dao.repository.VideoRepository;
import com.shurima.dsmax.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoRepository videoRepository;

    @Override
    public Video getVideoById(Integer id) {
        return videoRepository.getOne(id);
    }
}
