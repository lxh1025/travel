package com.lxh.controller;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lxh.common.Result;
import com.lxh.pojo.Post;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.lxh.service.PostService;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@CrossOrigin
@RestController
@RequestMapping("/post")
@Slf4j
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("add")
    public Result<String> addPost(@RequestBody Post post, HttpServletRequest request){
        post.setTime(new Date());
        postService.save(post);
        return Result.success("上传成功");
    }
    @DeleteMapping("/delete")
    public Result<String> deletePost(@RequestBody Post post, HttpServletRequest request){
        postService.removeById(post.getId());
        return Result.success("删除成功");
    }
    @GetMapping("/look")
    public Result<String> lookPost(@RequestParam("id") Integer id){
        LambdaQueryWrapper<Post> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Post::getId,id);
        Post post = postService.getOne(queryWrapper);
        post.setLook(post.getLook()+1);
        postService.updateById(post);
        return Result.success("浏览加一");
    }
    @GetMapping("/page")
    public  Result<Page> selectAll(@RequestParam("page") int page, @RequestParam("pagesize") int pageSize) {
        Page<Post> pageInfo = new Page<>(page,pageSize);
        LambdaQueryWrapper<Post> lambdaQueryWrapper = new LambdaQueryWrapper();
        postService.page(pageInfo, lambdaQueryWrapper);
        return Result.success(pageInfo);
    }


}
