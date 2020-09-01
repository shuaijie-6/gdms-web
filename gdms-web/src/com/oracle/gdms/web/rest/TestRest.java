package com.oracle.gdms.web.rest;


import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.alibaba.fastjson.JSONObject;
import com.oracle.gdms.entity.GoodsEntity;
import com.oracle.gdms.entity.GoodsModel;
import com.oracle.gdms.entity.GoodsType;
import com.oracle.gdms.entity.ResponseEntity;
import com.oracle.gdms.service.GoodsService;
import com.oracle.gdms.util.Factory;

@Path("/huazhao")
public class TestRest {
	
	@Path("/hello")
	@GET
	public void hello() {
		System.out.println("helloworld!");
	}
	
	//��ĳ����Ʒ���ͳ�ȥ
	@Path("push/one/{goodsid}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public GoodsModel pushOne(@PathParam("goodsid") int goodsid) {
		GoodsService service = (GoodsService) Factory.getInstance().getObject("goods.service.impl");
		GoodsModel goods = service.findOne(goodsid);
		return goods;
	}
	
	//��ĳ����Ʒ���ͳ�ȥ
		@Path("push/two")
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public GoodsModel pushTwo(@QueryParam("goodsid") int goodsid) {
			GoodsService service = (GoodsService) Factory.getInstance().getObject("goods.service.impl");
			GoodsModel goods = service.findOne(goodsid);
			return goods;
		}
		
		//��ĳ����Ʒ���ͳ�ȥ
		@Path("push/three")
		@POST
		@Produces(MediaType.APPLICATION_JSON)
		public GoodsModel pushThree(@FormParam ("goodsid") int goodsid) {
			GoodsService service = (GoodsService) Factory.getInstance().getObject("goods.service.impl");
			GoodsModel goods = service.findOne(goodsid);
			return goods;
		}
		
		//��ĳ����Ʒ���ͳ�ȥ
		@Path("push/four")
		@POST
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)	//����Ĳ�����JSON��ʽ
		public GoodsModel pushFour(GoodsEntity g) {
			GoodsService service = (GoodsService) Factory.getInstance().getObject("goods.service.impl");
			GoodsModel goods = service.findOne(g.getGoodsid());
			return goods;
		}
		
		//��ĳ����Ʒ���ͳ�ȥ
		@Path("push/five")
		@POST
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)	//����Ĳ�����JSON��ʽ
		public GoodsModel pushFive(String param) {
			System.out.println("�������գ�");
			System.out.println(param);
			JSONObject j = JSONObject.parseObject(param);
			System.out.println("id=" + j.getString("goodsid"));
			System.out.println("name=" + j.getString("name"));
			System.out.println("sex=" + j.getString("sex"));
			GoodsService service = (GoodsService) Factory.getInstance().getObject("goods.service.impl");
			GoodsModel goods = service.findOne(Integer.parseInt(j.getString("goodsid")));
			return goods;
		}
		
		@Path("push/six")
		@POST
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)	//����Ĳ�����JSON��ʽ
		public GoodsModel pushSix(String param) {
			System.out.println("�������գ�");
			System.out.println(param);
			JSONObject j = JSONObject.parseObject(param);
			System.out.println("type=" + j.get("type"));
			GoodsService service = (GoodsService) Factory.getInstance().getObject("goods.service.impl");
			GoodsModel goods = service.findAll(j.getString("goodsid"));
			return goods;
		}
		
		@Path("push/goods/bytype")
		@POST
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public List<GoodsModel> pushGoodsByType(GoodsType type){
			List<GoodsModel> list = null;
			
			GoodsService service = (GoodsService) Factory.getInstance().getObject("goods.service.impl");
			list = service.findByType(type.getGtid());
			System.out.println("size="+list.size());
			return list;
			
		}
		
		@Path("push/goods/one")
		@POST
		@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
		@Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
		public ResponseEntity pushGoods(String jsonstr){
			JSONObject j =JSONObject.parseObject(jsonstr);
			GoodsEntity goods = JSONObject.parseObject(j.getString("goods"),GoodsEntity.class);
			
			System.out.println("goodsid="+goods.getGoodsid());
			System.out.println("name="+goods.getName());
			System.out.println("area="+goods.getArea());
			ResponseEntity rs = new ResponseEntity();
			
			
			//�����յ�����Ʒ����,д�����˵����ݱ�
			GoodsService service = (GoodsService) Factory.getInstance().getObject("goods.service.impl");
			
			int count = service.add(goods);
			
			rs.setCode(count>0 ? 0:1099);
			rs.setMessage(count>0 ? "LYJ���ͳɹ�":"����ʧ�ܣ�д����Ʒ����ʧ��");
			rs.setData(null);
			return rs;
			
		}
	

}
