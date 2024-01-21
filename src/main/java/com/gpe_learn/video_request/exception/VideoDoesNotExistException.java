package com.gpe_learn.video_request.exception;

import java.util.UUID;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.CONFLICT) // 409
public
class VideoDoesNotExistException extends RuntimeException{
  public VideoDoesNotExistException(UUID videoId){
    super("Video " + videoId + " does not exist.");
  }
}
