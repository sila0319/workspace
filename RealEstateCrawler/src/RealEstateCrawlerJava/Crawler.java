package RealEstateCrawlerJava;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Crawler {
	private String url;
	
	Crawler(){//생성자
		
	}
	
	public void geturl () {
		ArrayList<Element> list = new ArrayList<>();
		
		this.url = 
				"https://new.land.naver.com/complexes?ms=35.8661338,129.1925249,10&a=APT:ABYG:JGC&e=RETAIL";
		//주소를 url에 다가 담는다 임시이므로 추후에 고칠것
		
		
	
	
		
		
		try {
			
			//전체 html코드 읽어오기
			Document doc = Jsoup.connect(this.url).get();
			
			
			 // 제목을 가지고 있는 요소 반환
	        // <ul class="img_board">   -> ul.img_board
	        // <h4 class="tit1 sch-rslt"> ->  h4.tit1.sch-rslt
			
			//Elements list_fixed_elem = doc.select("#summaryInfo > div.complex_title");
			/*
			 * Elements test = doc.getElementsByClass("list_complex_info ");
			 * //아파트명,매매가,전세가가져오기 System.out.println(test.size()); for(Element e : test) {
			 * System.out.println(e);
			 * 
			 * }
			 */
//			System.out.println(list_fixed_elem.size());
//			for(Element e : list_fixed_elem) {
//				System.out.println(e);
//			}
			System.out.println(doc);
			System.out.println("\n"+ url);
			
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		
	}
	public static void main(String[] args) {
		Crawler c = new Crawler();
		c.geturl();
		
		
	}
	

}
