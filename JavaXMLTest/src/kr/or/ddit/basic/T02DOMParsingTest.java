package kr.or.ddit.basic;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class T02DOMParsingTest {
	public void parseDoc() throws ParserConfigurationException, SAXException, IOException {
		//XML문서 생성을 위한 DocumentBuilder 객체 생성하기
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = dbf.newDocumentBuilder();
		
		//이미 만들어진 xml문서를 읽어서 Document 객체 생성
		Document document = builder.parse(new File("./src/new_book.xml"));
		
		//Document객체로부터 루트 엘리먼트 가져오기 - <root>접근
		//문서안에 있는 데이터에 접근
		//element타입이라면 getTagName이 있음. 태그이름
		Element rootEl = document.getDocumentElement();
		System.out.println("루트 엘리먼트 태그명 : " + rootEl.getTagName());
		
		//하위 엘리먼트 접근하기 : <root> - <booklist> - <book> 접근
		//NodeList는 item을 가지고 있음
		NodeList bookNodeList = rootEl.getElementsByTagName("book");
		Node firstBookNode = bookNodeList.item(0); //첫번째 항목
		Element firstBookEl = (Element) firstBookNode;
		
		//속성값 접근하기
		//getAttribute() : Element만이 제공하는 메소드
		System.out.println("엘리먼트 객체의 getAttribute() 이용 => "
							+ firstBookEl.getAttribute("isbn"));
		
		//엘리먼트의 TextContent 접근하기
		//item(0) : title에 접근
		String title2 = firstBookEl.getElementsByTagName("title").item(0).getTextContent();
		System.out.println("첫번째 book의 title 정보 : "+title2);
		
		//////////////////////////////////////////////////////////////////////////////////////////
		
		//전체 정보 출력하기
		System.out.println("------------------------------------------------");
		System.out.printf("%8s %8s %12s %8s\n", "ISBN", "분류", "제목", "저자", "가격");
		System.out.println("------------------------------------------------");
		
		for(int i=0; i<bookNodeList.getLength(); i++) {
			Element bookEl = (Element) bookNodeList.item(i);
			
			String isbn = bookEl.getAttribute("isbn");
			String kind = bookEl.getAttribute("kind");
			String title = bookEl.getElementsByTagName("title").item(0).getTextContent().trim();
			String author = bookEl.getElementsByTagName("author").item(0).getTextContent().trim();
			String price = bookEl.getElementsByTagName("price").item(0).getTextContent().trim();
			
			System.out.printf("%8s %8s %12s %8s\n", isbn, kind, title, author, price);
		}
		System.out.println("------------------------------------------------");
	}
	
	public static void main(String[] args) throws Exception {
		new T02DOMParsingTest().parseDoc();
	}
	
}
