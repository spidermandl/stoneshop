package org.goshop.goods.service;

import org.goshop.goods.i.GoodsClassStapleService;
import org.goshop.goods.mapper.master.GoodsClassMapper;
import org.goshop.goods.mapper.master.GoodsClassStapleMapper;
import org.goshop.goods.pojo.GoodsClass;
import org.goshop.goods.pojo.GoodsClassStaple;
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
    GoodsClassMapper goodsClassMapper;


    @Override
    public List<GoodsClassStaple> findByMemberId(Long memberId) {
        return goodsClassStapleMapper.findByMemberId(memberId) ;
    }

    @Override
    public GoodsClassStaple findOne(Integer id) {
        return goodsClassStapleMapper.selectByPrimaryKey(id);
    }

    @Override
    public int checkSaveStaple(User user, GoodsClass goodsClass) {
        Assert.notNull(goodsClass, "商品3级分类不能为空！");
        GoodsClass goodsClass2 = goodsClassMapper.selectByPrimaryKey(goodsClass.getGcParentId());
        Assert.notNull(goodsClass2, "商品2级分类不能为空！");
        GoodsClass goodsClass1 = goodsClassMapper.selectByPrimaryKey(goodsClass2.getGcParentId());
        Assert.notNull(goodsClass1, "商品1级分类不能为空！");

        GoodsClassStaple goodsClassStaple=goodsClassStapleMapper.findOneByGcId3AndMemberId(goodsClass.getGcId(), user.getId());
        if(goodsClassStaple!=null){
            goodsClassStaple.setCounter(goodsClassStaple.getCounter()+1);
            goodsClassStapleMapper.updateByPrimaryKeySelective(goodsClassStaple);
            return 1;
        }
        goodsClassStaple=new GoodsClassStaple();
        goodsClassStaple.setMemberId(user.getId());
        goodsClassStaple.setGcId3(goodsClass.getGcId());
        goodsClassStaple.setGcId2(goodsClass2.getGcId());
        goodsClassStaple.setGcId1(goodsClass1.getGcId());
        goodsClassStaple.setTypeId(goodsClass.getTypeId());

        String pattern="{0}>{1}>{2}";
        String stapleName = MessageFormat.format(pattern, goodsClass1.getGcName(), goodsClass2.getGcName(), goodsClass.getGcName());
        goodsClassStaple.setStapleName(stapleName);
        goodsClassStaple.setCounter(1);
        return goodsClassStapleMapper.insertSelective(goodsClassStaple);
    }

    @Override
    public int delete(Integer stapleId) {
        return goodsClassStapleMapper.deleteByPrimaryKey(stapleId);
    }

    @Override
    public JsonGoodsClass selectGoodsClassStaple(Integer stapleId) {
        GoodsClassStaple goodsClassStaple=goodsClassStapleMapper.selectByPrimaryKey(stapleId);

        List<GoodsClass> one=goodsClassMapper.findByGcParentId(0);
        List<GoodsClass> two = goodsClassMapper.findByGcParentId(goodsClassStaple.getGcId1());
        List<GoodsClass> three = goodsClassMapper.findByGcParentId(goodsClassStaple.getGcId2());

        JsonGoodsClass json = new JsonGoodsClass();
        json.setGc_id(goodsClassStaple.getGcId3()+"");
        json.setType_id("0");
        json.setDone(true);
        json.setOne(this.getGoodsClassStr(one,goodsClassStaple.getGcId1()));
        json.setTwo(this.getGoodsClassStr(two,goodsClassStaple.getGcId2()));
        json.setThree(this.getGoodsClassStr(three,goodsClassStaple.getGcId3()));
        return json;
    }

    public String getGoodsClassStr(List<GoodsClass> goodsClassList,Integer id){
        StringBuffer oneBuffer = new StringBuffer();
        for(GoodsClass g:goodsClassList){
            oneBuffer.append("<li data-param=\"{gcid:'");
            oneBuffer.append(g.getGcId());
            oneBuffer.append("',deep:1,tid:0}\" nctype=\"selClass\" >");
            oneBuffer.append("<a ");
            if(g.getGcId()==id){
                oneBuffer.append(" class='classDivClick' ");
            }
            oneBuffer.append(" href=\"javascript:void(0)\">");
            oneBuffer.append("<i class=\"icon-double-angle-right\"></i>");
            oneBuffer.append(g.getGcName());
            oneBuffer.append("</a></li> ");
        }
        return oneBuffer.toString();
    }
}
