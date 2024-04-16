package com.lxh.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lxh.mapper.PostMapper;
import com.lxh.pojo.Post;
import com.lxh.service.PostService;
import org.springframework.stereotype.Service;

/**
 * (Post)表服务实现类
 *
 * @author makejava
 * @since 2024-04-12 17:29:01
 */
@Service("postService")
public class PostServiceImpl extends ServiceImpl<PostMapper,Post> implements PostService {

}

