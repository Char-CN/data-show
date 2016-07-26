package org.blazer.datashow.proxy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.blazer.datashow.util.IPUtil;

public class DataServiceProxy extends HttpServlet {

	private static final long serialVersionUID = 436744608091105703L;

	private static final String DATA_SERVICE_GETCONFIG = "http://10.127.133.91:8080/data/dataservice/getconfig.do?";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " ip : " + IPUtil.getIpAddr(req));
		java.net.URL url = new java.net.URL(DATA_SERVICE_GETCONFIG + req.getQueryString());
		InputStream is = url.openStream();
		BufferedReader buffer = new BufferedReader(new InputStreamReader(is));
		StringBuffer bs = new StringBuffer();
		String l = null;
		while ((l = buffer.readLine()) != null) {
			bs.append(l);
		}
		resp.setHeader("Content-Type", "text/html;charset=UTF-8");
		resp.setCharacterEncoding("utf-8");
		resp.getWriter().write(bs.toString());
	}

	public static void main(String[] args) throws IOException {
		java.net.URL url = new java.net.URL(DATA_SERVICE_GETCONFIG + "id=10&today=20160721&yesterday=20160720");
		InputStream is = url.openStream();
		BufferedReader buffer = new BufferedReader(new InputStreamReader(is));
		StringBuffer bs = new StringBuffer();
		String l = null;
		while ((l = buffer.readLine()) != null) {
			bs.append(l).append("/n");
		}
		System.out.println(bs.toString());
	}

}
