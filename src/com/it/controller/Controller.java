package com.it.controller;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@org.springframework.stereotype.Controller
public class Controller {

	@RequestMapping("demo1")
	public String Demo1(HttpServletRequest req, HttpSession session2) {

		// request在第一次请求就会产生
		req.setAttribute("name", "request的值");
		// session，只有servlet调用request.getSession()才会产生session
		// 第一种
		HttpSession session1 = req.getSession();
		session1.setAttribute("session1", "session1的方式的值");
		// 第二种
		session2.setAttribute("session2", "session2的方式的值");
		// application，tomcat启动时就会产生
		ServletContext application = req.getServletContext();
		application.setAttribute("application", "application的值");

		return "/index.jsp";
	}

	// map的值存在request的域中
	@RequestMapping("demo2")
	public String demo2(Map<String, Object> map) {
		map.put("map", "map的值");

		return "/index.jsp";
	}

	@RequestMapping("demo3")
	public ModelAndView demo3() {

		ModelAndView mav = new ModelAndView("/index.jsp");
		mav.addObject("mav", "mav的值");

		return mav;
	}

	// 文件下载
	@RequestMapping("download")
	public String download(String fileName, HttpServletResponse res, HttpServletRequest req) throws IOException {
		// 设置响应流中文件进行下载
		res.setHeader("Content-Disposition", "attachment;filename=a.txt");
		// 把二进制流放入到响应体中
		ServletOutputStream os = res.getOutputStream();
		String path = req.getServletContext().getRealPath("file");
		System.out.println(path);
		File file = new File(path, fileName);
		byte[] bytes = FileUtils.readFileToByteArray(file);
		os.write(bytes);
		os.flush();
		os.close();

		return "download.jsp";
	}
	
	// 文件上传
	@RequestMapping("upload")	
	public String upload(MultipartFile file) throws IOException {
		//获取文件名
		String fileName = file.getOriginalFilename();
		//截取文件名的后缀
		String suffix = fileName.substring(fileName.lastIndexOf("."));
		//随机一个不重复的字符串
		String uuid = UUID.randomUUID().toString();
		//上传文件到指定文件夹
		FileUtils.copyInputStreamToFile(file.getInputStream(), new File("E:/"+uuid+suffix));
		
		
		return "upload.jsp";
	}

}
