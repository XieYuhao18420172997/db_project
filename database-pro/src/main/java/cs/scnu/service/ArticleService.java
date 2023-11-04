package cs.scnu.service;

import cs.scnu.entity.Post;
import cs.scnu.result.PageResult;

import java.util.List;


public interface ArticleService {
    PageResult page(Integer page, Integer pageSize, Integer user_id, String title);

    Post getByPostId(Integer postId);

    void updatePost(Post post);

    void deleteBatch(List<Integer> ids);

    void addPost(Post post);

    PageResult pagePosted(Integer page, Integer pageSize);
}
