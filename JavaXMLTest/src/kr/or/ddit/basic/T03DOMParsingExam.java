package kr.or.ddit.basic;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class T03DOMParsingExam {
	
	public void parseDoc() throws ParserConfigurationException, SAXException, IOException {
		String svcKey = "Grid_20150827000000000227_1";  // 레시피 재료 정보 조회 서비스
		String apiKey = "1df7e8571e8df3f8cbc9b87691ca7d3e4d04f03c593d477e52bf67b03f0b6e1c"; // 개인별 발급.
		String startIdx = "1";  	// 레시피 재료 시작 순번
		String endIdx = "5";		// 레시피 재료 종료 순번
		String recipeId = "195428";	// 래시피가 궁금한 음식 ID
		
		URL url = new URL("http://211.237.50.150:7080/openapi/"+ apiKey
				+ "/xml/"+ svcKey + "/"+startIdx +"/" + endIdx
				+"?RECIPE_ID=" +  recipeId);
		
		//XML문서 생성을 위한 DocumentBuilder 객체 생성하기
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = dbf.newDocumentBuilder();
		
		//url을 String으로 바꿔서 uri를 이용한 Document 객체 생성
		Document document = builder.parse(url.toString());
		
		//루트 엘리먼트 가져오기
		Element rootEl = document.getDocumentElement();
		System.out.println("루트 엘리먼트 태그명 : " + rootEl.getTagName());
		
		String totalCnt = rootEl.getElementsByTagName("totalCnt").item(0).getTextContent();
		
		endIdx = totalCnt;
		
		url = new URL("http://211.237.50.150:7080/openapi/"+ apiKey
				+ "/xml/"+ svcKey + "/"+startIdx +"/" + totalCnt
				+"?RECIPE_ID=" +  recipeId);
		
		document = builder.parse(url.toString());
		
		rootEl = document.getDocumentElement();
		System.out.println("루트 엘리먼트 태그명 : " + rootEl.getTagName());
		
		String code = rootEl.getElementsByTagName("code").item(0).getTextContent();
		
		if(code.equals("INFO-000")) {
			NodeList rowNodeList = rootEl.getElementsByTagName("row");
			
			for(int i=0; i<rowNodeList.getLength(); i++) {
				Element rowEl = (Element) rowNodeList.item(i);
				
				String rowNum = rowEl.getElementsByTagName("ROW_NUM").item(0).getTextContent();
				String irdntNm = rowEl.getElementsByTagName("IRDNT_NM").item(0).getTextContent();
				String irdntCpcty = rowEl.getElementsByTagName("IRDNT_CPCTY").item(0).getTextContent();
				String irdntTyNm = rowEl.getElementsByTagName("IRDNT_TY_NM").item(0).getTextContent();
				
				System.out.printf("%3s %8s %10s %10s %8s\n", rowNum, recipeId, irdntTyNm, irdntNm, irdntCpcty);
				System.out.println("--------------------------------------------");
				
			}
			
		}
		
	}
	
	
	public static void main(String[] args) throws Exception {
		
		new T03DOMParsingExam().parseDoc();
		
	}
}
