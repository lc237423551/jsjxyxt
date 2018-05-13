package com.java.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//验证码
@Controller

public class ImageController {
	private static final long serialVersionUID = 1L;
	private final int WIDTH = 120;
	private final int HEIGHT= 30;
	@RequestMapping(value="/image")
	public void image(HttpServletRequest request,HttpServletResponse res) throws IOException{
		BufferedImage br = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics = (Graphics2D) br.getGraphics();
		graphics.setColor(new Color(198,255,255));
		graphics.fillRect(0, 0, WIDTH, HEIGHT);
		String base ="1234567890";
		Random rand = new Random();
		graphics.setColor(Color.RED);
		graphics.setFont(new Font("黑体", Font.PLAIN, 18));
		int m =18;
		StringBuffer sb=new StringBuffer();
		for (int i = 0; i < 4; i++) {
			int index = rand.nextInt(base.length());
			char charAt = base.charAt(index);
			int jiaodu = rand.nextInt(60)-30;
			double theta = jiaodu*Math.PI/180;
			graphics.rotate(theta,m, 20);
			graphics.drawString(charAt+"", m, 20);
			sb.append(charAt);
			graphics.rotate(-theta,m, 20);
			m+=25;
		}
		request.getSession().setAttribute("checked", sb.toString());
		graphics.setColor(Color.GREEN);
		 
		for (int i = 0; i < 4; i++) {
			
			int x1 = rand.nextInt(WIDTH);
			int x2 = rand.nextInt(WIDTH);
			int y1 = rand.nextInt(HEIGHT);
			int y2 = rand.nextInt(HEIGHT);
			graphics.drawLine(x1, y1, x2, y2);
		}
		graphics.dispose();
		
		res.setHeader("Pragma", "No-cache");  
		res.setHeader("Cache-Control", "no-cache");  
		res.setDateHeader("Expires", 0);  
		res.setContentType("image/png"); 
        
		ImageIO.write(br, "png", res.getOutputStream());
		
	}
}
