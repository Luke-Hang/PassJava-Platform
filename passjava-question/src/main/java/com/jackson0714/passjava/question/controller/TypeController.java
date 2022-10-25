package com.jackson0714.passjava.question.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jackson0714.passjava.question.entity.TypeEntity;
import com.jackson0714.passjava.question.service.ITypeService;
import com.jackson0714.passjava.common.utils.PageUtils;
import com.jackson0714.passjava.common.utils.R;



/**
 * 题目-题目类型表
 *
 * @author 公众号：悟空聊架构
 * @site www.passjava.cn
 * @date 2020-04-25 22:34:04
 */
@RestController
@RequestMapping("question/type")
public class TypeController {

    @Autowired
    private ITypeService ITypeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("question:type:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = ITypeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("question:type:info")
    public R info(@PathVariable("id") Long id){
		TypeEntity type = ITypeService.getById(id);

        return R.ok().put("type", type);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("question:type:save")
    public R save(@RequestBody TypeEntity type){
		ITypeService.save(type);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("question:type:update")
    public R update(@RequestBody TypeEntity type){
		ITypeService.updateById(type);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("question:type:delete")
    public R delete(@RequestBody Long[] ids){
		ITypeService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }


    /**
     * 测试添加 @Transactional 注解后，Redis 操作是否有问题
     * 悟空聊架构
     */
    @RequestMapping("/test-redis")
    public R testRedisWithOutTransaction() {
        Long result = ITypeService.testWithOutTransactionAnnotations();
        return R.ok(String.valueOf(result));
    }

    /**
     * 测试添加 @Transactional 注解后，Redis 操作是否有问题
     * 悟空聊架构
     */
    @RequestMapping("/test-transaction-annotations")
    public R testRedisWithTransaction() {
        Long result = ITypeService.testTransactionAnnotations();
        return R.ok(String.valueOf(result));
    }

    @RequestMapping("/test-redis-multi")
    public R testRedisMulti(){
        ITypeService.testRedisMutil();
        return R.ok();
    }

    @RequestMapping("/enable-transaction-support")
    public R enableTransactionSupport(@RequestParam Boolean flag){
        ITypeService.enableTransactionSupport(flag);

        return R.ok("flag:" + String.valueOf(flag));
    }
}
