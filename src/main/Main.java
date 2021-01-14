package main;

import util.FileOperationsUtil;
import util.HttpRequestUtil;

public class Main {

	public static void main(String[] args) {
		// step1, 循环搜索页面，把页面的html保留存下来
		String baiduString = HttpRequestUtil.sendGet("https://www.baidu.com");
		// true means append to file, false means override it
		FileOperationsUtil.writeFile("baidu.txt", baiduString + "\n", false);
		
		// step2, 循环本地文件，正则表达式提取对应的信息(即商品的访问url)
		String baiduStringFromFile = FileOperationsUtil.readFile("baidu.txt");
		
		//String result1 = operation(baiduStringFromFile);
		//FileOperationsUtil.writeFile("baidu_result.txt", result1 + "\n", false);
		
		// step3, 遍历访问商品页面，保存页面源码至本地
		
		// step4, 读取页面，并且抽取对应的信息
		
		// step5, 将信息以逗号的格式保留至文件，并以csv结尾
		

		System.out.println(baiduStringFromFile);
	}

}
