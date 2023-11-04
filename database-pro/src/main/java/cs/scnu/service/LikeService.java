package cs.scnu.service;

import cs.scnu.result.PageResult;

public interface LikeService {

    void addLike(Integer postId);

    void deleteLike(Integer postId);

    PageResult getLike(Integer page, Integer pageSize);
}
