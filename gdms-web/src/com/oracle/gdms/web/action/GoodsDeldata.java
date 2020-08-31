package com.oracle.gdms.web.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.oracle.gdms.entity.GoodsEntity;
import com.oracle.gdms.service.GoodsService;
import com.oracle.gdms.util.Factory;

/**
 * Servlet implementation class GoodsDeldata
 */
@WebServlet("/admin/goods/deldata.php")
public class GoodsDeldata extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//接收ajax传来的参数
		request.setCharacterEncoding("UTF-8");
		
		String[] goodsid = request.getParameterValues("goodsid");
		
	
		GoodsService service = (GoodsService) Factory.getInstance().getObject("goods.service.impl");
		service.deldata(goodsid[0]);//返回更新的结果
		//response.setContentType("application/json;charset=utf-8");
		response.sendRedirect("list.php");
//		JSONObject j = new JSONObject();
//		
//			j.put("code",0);
//			j.put("msg","删除成功");
//		
//			j.put("code",10882);
//			j.put("msg","商品失败");
//		
//		response.getWriter().print(j.toJSONString());
		
	}

}
