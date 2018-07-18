package day004_Quartz_001;


import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

public class MyJop implements Job {
	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

	@SuppressWarnings({ "resource", "deprecation" })
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		
		String url = "http://www.xinfadi.com.cn/marketanalysis/0/list/1.shtml?prodname=%E7%99%BD%E6%9D%A1%E7%8C%AA%EF%BC%88%E8%82%A5%EF%BC%89&begintime=&endtime=";
		List<Item> list = new ArrayList<>();
		
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet get= new HttpGet(url);
		try {
			CloseableHttpResponse response = client.execute(get);
			HttpEntity entity = response.getEntity();
			String html = EntityUtils.toString(entity, "utf-8");
			
			Document doc = Jsoup.parse(html);
			Elements trs = doc.getElementsByClass("hq_table").first().getElementsByTag("tr");
			Elements reTrs = trs.remove();
			
			for(Element tr:reTrs){
				Elements tds = tr.getElementsByTag("td");
				String name = tds.get(0).text();
				Double minPrice = Double.parseDouble(tds.get(1).text());
				Double averagePrice = Double.parseDouble(tds.get(2).text());
				Double maxPrice = Double.parseDouble(tds.get(3).text());
				
				String d = tds.get(6).text();
				Date date = sf.parse(d);

				list.add(new Item(null ,name, minPrice, averagePrice, maxPrice, date));
			}
			
		MyStatement.getStatement(list);
			
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		JavaMailSenderImpl mailSend = (JavaMailSenderImpl) context.getBean("mailSend");
		
		MimeMessage message = mailSend.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		Date date= new Date(System.currentTimeMillis());
		String localeString = date.toLocaleString();
		helper.setFrom("hk109@126.com");
		helper.setTo("863689218@.qq.com");
		helper.setSubject(localeString+":报表");
		helper.setText("报表");
		
		File file = new File("d://chart//statement.pdf");
		
		helper.addAttachment("报表", file);
		
		mailSend.send(message);
		System.out.println("ok");
		
			
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
