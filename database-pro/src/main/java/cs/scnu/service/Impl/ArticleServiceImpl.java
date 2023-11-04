package cs.scnu.service.Impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import cs.scnu.context.BaseContext;
import cs.scnu.entity.Post;
import cs.scnu.entity.VO.PostVO;
import cs.scnu.mapper.ArticleMapper;
import cs.scnu.mapper.LikeMapper;
import cs.scnu.result.PageResult;
import cs.scnu.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private LikeMapper likeMapper;

    public PageResult page(Integer page, Integer pageSize, Integer user_id,  String title) {
        PageHelper.startPage(page,pageSize);

        List<PostVO> list= articleMapper.pageQuery(user_id,title);
        Page<PostVO> pg=(Page<PostVO>)list;

        return new PageResult(pg.getTotal(),pg.getResult());
    }

    public Post getByPostId(Integer postId) {
        return articleMapper.getById(postId);
    }

    public void updatePost(Post post) {
        articleMapper.updatePost(post);
    }

    @Transactional(rollbackFor=Exception.class)
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            likeMapper.deleteByPostId(id);
            articleMapper.deleteByPostId(id);
        }
    }

    public void addPost(Post post) {
        post.setCreateTime(LocalDateTime.now());
        //todo  增加user_id
        post.setUserId(BaseContext.getCurrentId().intValue());
        articleMapper.addPost(post);
    }

    public PageResult pagePosted(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);

        List<PostVO> list= articleMapper.pagePostedQuery(BaseContext.getCurrentId().intValue());
        Page<PostVO> pg=(Page<PostVO>)list;

        return new PageResult(pg.getTotal(),pg.getResult());

    }
}
