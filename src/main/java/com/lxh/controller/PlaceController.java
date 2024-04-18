package com.lxh.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lxh.pojo.Place;
import com.lxh.common.Result;
import com.lxh.pojo.PlaceRank;
import com.lxh.service.PlaceRankService;
import com.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.Serializable;

@CrossOrigin
@RestController
@RequestMapping("/place")
public class PlaceController {
    /**
     * 服务对象
     */
    @Autowired
    private PlaceService placeService;
    @Autowired
    private PlaceRankService placeRankService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @return 所有数据
     */
    @GetMapping("/page")
    public  Result<Page> selectAll(@RequestParam("page") int page, @RequestParam("pagesize") int pageSize) {
        Page<Place> pageInfo = new Page<>(page,pageSize);
        LambdaQueryWrapper<Place> lambdaQueryWrapper = new LambdaQueryWrapper();
        placeService.page(pageInfo, lambdaQueryWrapper);
        return Result.success(pageInfo);
    }

    @GetMapping("/placerank")
    public Result<Page> hotRank(@RequestParam("page") int page, @RequestParam("pagesize") int pageSize){
        Page<Place> pageInfo = new Page<>(page,pageSize);
        LambdaQueryWrapper<Place> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.orderByDesc(Place::getHot);
        placeService.page(pageInfo, queryWrapper);
        return Result.success(pageInfo);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/detail")
    public  Result<Place> selectOne(int id) {
        LambdaQueryWrapper<Place> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Place::getId,id);
        Place place1 = this.placeService.getOne(queryWrapper);
        if (place1 == null) {
            return Result.error("未找到数据");
        }else {
            return Result.success(place1);
        }
    }

    /**
     * 新增数据
     *
     * @param place 实体对象
     * @return 新增结果
     */
    @PostMapping
    public  Result<String> insert(@RequestBody Place place) {

        String name = place.getName();
        String province = place.getProvince();
        LambdaQueryWrapper<Place> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Place::getName,name);
        Place place1 = this.placeService.getOne(queryWrapper);

        if (place1==null){
            this.placeService.save(place);
            return Result.success("添加成功");
        }else {
            return Result.error("添加失败");
        }
    }

    /**
     * 修改数据
     *
     * @param place 实体对象
     * @return 修改结果
     */
    @PutMapping
    public Result<String> update(@RequestBody Place place) {
        System.out.println("更新"+Thread.currentThread().getName());
        //拿新的状态值
        placeService.updateById(place);
        return Result.success("更新成功");
    }

    @GetMapping("/score")
    public Result<String> score(@RequestParam("userid") int userid,@RequestParam("placeid") int placeid,@RequestParam("score") float score) {
        LambdaQueryWrapper<Place> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Place::getId,placeid);
        Place place = this.placeService.getOne(queryWrapper);
        place.setScore((place.getScore()+score)/place.getScore());
        place.setScorenum(place.getScorenum()+1);
        placeService.updateById(place);
        //再写一个评分表
        PlaceRank placeRank = new PlaceRank();
        placeRank.setPlaceid(placeid);
        placeRank.setScore(score);
        placeRank.setUserid(userid);
        placeRankService.save(placeRank);
        return Result.success("评分成功");
    }

}

