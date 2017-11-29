package org.goshop.goods.service;

import org.goshop.goods.i.GoodsClassStapleService;
import org.goshop.goods.mapper.master.GoodsClassMapper;
import org.goshop.goods.mapper.master.GoodsClassStapleMapper;
import org.goshop.goods.mapper.master.GsGoodsClassMapper;
import org.goshop.goods.pojo.GoodsClass;
import org.goshop.goods.pojo.GoodsClassStaple;
import org.goshop.goods.pojo.GsGoodsClass;
import org.goshop.goods.pojo.JsonGoodsClass;
import org.goshop.users.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.text.MessageFormat;
import java.util.List;

@Service("goodsClassStapleService")
public class GoodsClassStapleServiceImpl implements GoodsClassStapleService {

    @Autowired
    GoodsClassStapleMapper goodsClassStapleMapper;

    @Autowired
    GsGoodsClassMapper gsGoodsClassMapper;

    @Override
    public List<GoodsClassStaple> findByMemberId(Long memberId) {
        return goodsClassStapleMapper.findByMemberId(memberId) ;
    }

    @Override
    public GoodsClassStaple findOne(Long id) {
        return goodsClassStapleMapper.selectByPrimaryKey(id);
    }

    @Override
    public int checkSaveStaple(User user, GsGoodsClass goodsClass) {
        Assert.notNull(goodsClass, "商品3级分类不能为空！");
        GsGoodsClass goodsClass2 = gsGoodsClassMapper.selectByPrimaryKey(goodsClass.getParentId());
        Assert.notNull(goodsClass2, "商品2级分类不能为空！");
        GsGoodsClass goodsClass1 = gsGoodsClassMapper.selectByPrimaryKey(goodsClass2.getParentId());
        Assert.notNull(goodsClass1, "商品1级分类不能为空！");

        GoodsClassStaple goodsClassStaple=goodsClassStapleMapper.findOneByGcId3AndMemberId(goodsClass.getId(), user.getId());
        if(goodsClassStaple!=null){
            goodsClassStaple.setCounter(goodsClassStaple.getCounter()+1);
            goodsClassStapleMapper.updateByPrimaryKeySelective(goodsClassStaple);
            return 1;
        }
        goodsClassStaple=new GoodsClassStaple();
        goodsClassStaple.setMemberId(user.getId());
        goodsClassStaple.setGcId3(goodsClass.getId());
        goodsClassStaple.setGcId2(goodsClass2.getId());
        goodsClassStaple.setGcId1(goodsClass1.getId());
        goodsClassStaple.setTypeId(goodsClass.getId());

        String pattern="{0}>{1}>{2}";
        String stapleName = MessageFormat.format(pattern, goodsClass1.getClassname(), goodsClass2.getClassname(), goodsClass.getClassname());
        goodsClassStaple.setStapleName(stapleName);
        goodsClassStaple.setCounter(1);
        return goodsClassStapleMapper.insertSelective(goodsClassStaple);
    }

    @Override
    public int delete(Long stapleId) {
        return goodsClassStapleMapper.deleteByPrimaryKey(stapleId);
    }

    @Override
    public JsonGoodsClass selectGoodsClassStaple(Long stapleId) {
        GoodsClassStaple goodsClassStaple=goodsClassStapleMapper.selectByPrimaryKey(stapleId);

        List<GsGoodsClass> one=gsGoodsClassMapper.findByGcParentId(0L);
        List<GsGoodsClass> two = gsGoodsClassMapper.findByGcParentId(goodsClassStaple.getGcId1());
        List<GsGoodsClass> three = gsGoodsClassMapper.findByGcParentId(goodsClassStaple.getGcId2());

        JsonGoodsClass json = new JsonGoodsClass();
        json.setGc_id(goodsClassStaple.getGcId3()+"");
        json.setType_id("0");
        json.setDone(true);
        json.setOne(this.getGoodsClassStr(one,goodsClassStaple.getGcId1()));
        json.setTwo(this.getGoodsClassStr(two,goodsClassStaple.getGcId2()));
        json.setThree(this.getGoodsClassStr(three,goodsClassStaple.getGcId3()));
        return json;
    }

    public String getGoodsClassStr(List<GsGoodsClass> goodsClassList,Long id){
        StringBuffer oneBuffer = new StringBuffer();
        for(GsGoodsClass g:goodsClassList){
            oneBuffer.append("<li data-param=\"{gcid:'");
            oneBuffer.append(g.getId());
            oneBuffer.append("',deep:1,tid:0}\" nctype=\"selClass\" >");
            oneBuffer.append("<a ");
            if(g.getId()==id){
                oneBuffer.append(" class='classDivClick' ");
            }
            oneBuffer.append(" href=\"javascript:void(0)\">");
            oneBuffer.append("<i class=\"icon-double-angle-right\"></i>");
            oneBuffer.append(g.getClassname());
            oneBuffer.append("</a></li> ");
        }
        return oneBuffer.toString();
    }
}
