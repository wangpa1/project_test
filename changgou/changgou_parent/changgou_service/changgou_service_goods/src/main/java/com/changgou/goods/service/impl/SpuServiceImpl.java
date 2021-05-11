package com.changgou.goods.service.impl;

import com.alibaba.fastjson.JSON;
import com.changgou.goods.dao.*;
import com.changgou.goods.pojo.*;
import com.changgou.goods.service.SpuService;
import com.changgou.util.IdWorker;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class SpuServiceImpl implements SpuService {
    @Autowired
    private SpuMapper spuMapper;

    /**
     * 查询全部列表
     * @return
     */
    @Override
    public List<Spu> findAll() {
        return spuMapper.selectAll();
    }

    /**
     * 跟据ID查询
     * @param id
     * @return
     */
    @Override
    public Spu findById(String id) {
        return spuMapper.selectByPrimaryKey(id);
    }
    @Autowired
    private IdWorker idWorker;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private BrandMapper brandMapper;

    @Autowired
    private SkuMapper skuMapper;

    @Autowired
    private CategoryBrandMapper categoryBrandMapper;

    /**
     * 增加
     * @param goods
     */
    @Transactional
    @Override
    public void add(Goods goods) {
    Spu spu = goods.getSpu();
    long spuId = idWorker.nextId();
    spu.setId(String.valueOf(spuId));
    spu.setIsDelete("0");
    spu.setIsMarketable("0");
    spu.setStatus("0");
    spuMapper.insertSelective(spu);
    this.saveSkuList(goods);
    }

    private void saveSkuList(Goods goods) {
        Spu spu = goods.getSpu();
        Category category = categoryMapper.selectByPrimaryKey(spu.getCategory3Id());
        Brand brand = brandMapper.selectByPrimaryKey(spu.getBrandId());
        CategoryBrand categoryBrand = new CategoryBrand();
        categoryBrand.setBrandId(spu.getBrandId());
        int count = categoryBrandMapper.selectCount(categoryBrand);
        if (count==0){
            categoryBrandMapper.insert(categoryBrand);
        }
        List<Sku>skuList = goods.getSkuList();
        if (skuList!=null){
            for (Sku sku:skuList){

            }
        }

    }

    @Override
    public void update(Goods goods) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public List<Spu> findList(Map<String, Object> searchMap) {
        return null;
    }

    @Override
    public Page<Spu> findPage(int page, int size) {
        return null;
    }

    @Override
    public Page<Spu> findPage(Map<String, Object> searchMap, int page, int size) {
        return null;
    }

    @Override
    public Goods findGoodsById(String id) {
        return null;
    }

    @Override
    public void audit(String id) {

    }

    @Override
    public void pull(String id) {

    }

    @Override
    public void put(String id) {

    }

    @Override
    public void restore(String id) {

    }

    @Override
    public void realDel(String id) {

    }
}
