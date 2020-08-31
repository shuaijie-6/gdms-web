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
		//����ajax�����Ĳ���
		request.setCharacterEncoding("UTF-8");
		
		String[] goodsid = request.getParameterValues("goodsid");
		
	
		GoodsService service = (GoodsService) Factory.getInstance().getObject("goods.service.impl");
		service.deldata(goodsid[0]);//���ظ��µĽ��
		//response.setContentType("application/json;charset=utf-8");
		response.sendRedirect("list.php");
//		JSONObject j = new JSONObject();
//		
//			j.put("code",0);
//			j.put("msg","ɾ���ɹ�");
//		
//			j.put("code",10882);
//			j.put("msg","��Ʒʧ��");
//		
//		response.getWriter().print(j.toJSONString());
		
	}

}
