package cn.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import cn.com.webxml.ArrayOfString;
import cn.com.webxml.WeatherWebServiceSoap;

public class GetWeather {

	public static String queryWeather(String cityname) {
		// 创建服务视图
		// WeatherWebService service=new WeatherWebService();

		URL url = null;
		try {
			url = new URL("http://www.webxml.com.cn/WebServices/WeatherWebService.asmx?WSDL");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		QName qName = new QName("http://WebXml.com.cn/", "WeatherWebService");

		Service service = Service.create(url, qName);
		WeatherWebServiceSoap portType = service.getPort(WeatherWebServiceSoap.class);
		// service.getWeatherWebServiceSoap();

		ArrayOfString arrayOfString = portType.getWeatherbyCityName(cityname);

		List<String> list = arrayOfString.getString();
		String string = list.get(1) + "：" + list.get(6) + " " + list.get(5) + " " + list.get(7);
		return string;
	}

}
