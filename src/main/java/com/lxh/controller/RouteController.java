package com.lxh.controller;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lxh.common.Result;
import com.lxh.pojo.Route;
import com.lxh.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("route")
public class RouteController{
    /**
     * 服务对象
     */
    @Autowired
    private RouteService routeService;

    /**
     * 分页查询所有数据
     */
    @GetMapping("/page")
    public Result<Page> page(int page, int pageSize) {
        Page pageInfo = new Page(page, pageSize);
        //查询构造器
        LambdaQueryWrapper<Route> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.orderByDesc(Route::getHot);
        routeService.page(pageInfo, lambdaQueryWrapper);
        //返回查询结果
        return Result.success(pageInfo);

    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/detail")
    public Result selectOne(int id) {
        return Result.success(this.routeService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param route 实体对象
     * @return 新增结果
     */
    @PostMapping("/add")
    public Result<String> add(@RequestBody Route route) {

        routeService.save(route);

        return Result.success("添加成功");
    }
    /**
     * 删除数据
     */
    @DeleteMapping
    public Result<String> delete(int ids) {
        LambdaQueryWrapper<Route> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Route::getId, ids);
        routeService.removeById(ids);
        return Result.success("删除成功");
    }
}

