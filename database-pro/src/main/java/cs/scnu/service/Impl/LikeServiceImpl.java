package cs.scnu.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import cs.scnu.context.BaseContext;
import cs.scnu.entity.Like;
import cs.scnu.entity.VO.PostVO;
import cs.scnu.mapper.LikeMapper;
import cs.scnu.result.PageResult;
import cs.scnu.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.dsig.keyinfo.PGPData;
import java.time.LocalDateTime;

@Service
public class LikeServiceImpl implements LikeService {
    @Autowired
    private LikeMapper likeMapper;

    public void addLike(Integer postId) {
        if(likeMapper.getLikeByPostIdAndUserId(postId,BaseContext.getCurrentId().intValue())!=null)return;

        Like like = Like.builder()
                .userId(BaseContext.getCurrentId().intValue())
                .postId(postId)
                .createTime(LocalDateTime.now())
                .build();

        likeMapper.addLike(like);
    }

    public void deleteLike(Integer postId) {
        if(likeMapper.getLikeByPostIdAndUserId(postId,BaseContext.getCurrentId().intValue())==null)return;

        likeMapper.deleteByPostId(postId);
    }

    public PageResult getLike(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);

        Page<PostVO> pg=likeMapper.getLike(BaseContext.getCurrentId().intValue());

        return new PageResult(pg.getTotal(),pg.getResult());
    }
}
