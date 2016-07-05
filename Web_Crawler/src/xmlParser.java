import java.io.BufferedWriter;
import java.io.File;  

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.xml.parsers.*;  

import org.w3c.dom.Document;  
import org.w3c.dom.Element;  
import org.w3c.dom.Node;  
import org.w3c.dom.NodeList;  
import org.xml.sax.Attributes;  
import org.xml.sax.SAXException;  
import org.xml.sax.helpers.DefaultHandler;  


public class xmlParser {  

	private static final String XML_FILE_PATH ="/Users/KimHongTae/Downloads/xml_folder2/150502_카페_메타정보_";
	private static final int perPage = 15;			// 하나의 XML 파일당 게시글의   
	
	public static void main(String[] args) throws Exception, IOException 
	{  
		xmlParser xpt = new xmlParser();

		File fout = new File("/Users/KimHongTae/Downloads/xml_folder2/150507_카페_메타정보_텍스트.txt");
		FileOutputStream fos = new FileOutputStream(fout);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos,"UTF8"));

		for(int tmp=1; tmp<=1203; tmp++)
		{
			try
			{
				xpt.domParseTest(tmp,bw);

			}
			catch(Exception e11)
			{
				bw.write("Exception"+"\n");
				continue;
			}
		}

		bw.close();
	}  

	
	public void domParseTest(int idx, BufferedWriter bw) throws Exception {  

		File xmlFile = new File(XML_FILE_PATH+idx+".xml");  

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();  
		DocumentBuilder db = dbf.newDocumentBuilder();  
		Document doc = db.parse(xmlFile);  

		doc.getDocumentElement().normalize();  

		//		System.out.printf("Root element:%s\n", doc.getDocumentElement().getNodeName());  
		NodeList itemNodeList = doc.getElementsByTagName("message");  

		Node itemNode = itemNodeList.item(0); 
		Element itemElement = (Element)itemNode;  

		NodeList articleIdNodeList = itemElement.getElementsByTagName("articleid");  
		NodeList nickNameNodeList = itemElement.getElementsByTagName("nickname");  
		NodeList SubjectNodeList = itemElement.getElementsByTagName("subject");  
		NodeList DateNodeList = itemElement.getElementsByTagName("writedate");  
		NodeList ReadCntNodeList = itemElement.getElementsByTagName("readCount");
		NodeList CommCntNodeList = itemElement.getElementsByTagName("commentCount");
		
		for (int s = 0; s < perPage; s++) {  

			Element articleIdElement = (Element)articleIdNodeList.item(s);  				
			System.out.printf("[articleId : %s]\t", (articleIdElement.getTextContent()));
			bw.write( "articleId:" + articleIdElement.getTextContent() + "\t");

			Element nickNameElement = (Element) nickNameNodeList.item(s);  
			System.out.printf("[nickName : %s]\t", (nickNameElement.getTextContent()));
			bw.write("nickName:" + nickNameElement.getTextContent() + "\t");

			Element SubjectElement = (Element) SubjectNodeList.item(s);
			System.out.printf("[Subject : %s]\t", (SubjectElement.getTextContent()));
			bw.write("subject:"+ SubjectElement.getTextContent() + "\t");

			Element DateElement = (Element) DateNodeList.item(s);
			System.out.printf("[Date : %s]\t", (DateElement.getTextContent()));
			bw.write("date:"+ DateElement.getTextContent() + "\t");

			Element ReadCntElement = (Element) ReadCntNodeList.item(s);
			System.out.printf("[ReadCnt : %s]\t", (ReadCntElement.getTextContent()));
			bw.write("readCnt:"+ ReadCntElement.getTextContent() + "\t");

			Element CommCntElement = (Element) CommCntNodeList.item(s);
			System.out.printf("[CommCnt : %s]\n", (CommCntElement.getTextContent()));
			bw.write("commCnt:"+ CommCntElement.getTextContent() + "\n");
		}
	}
 
	public void saxParseTest() throws Exception {  

		System.out.println("==============================");  
		System.out.println("saxParseTest()");  
		System.out.println("==============================");  

		File xmlFile = new File(XML_FILE_PATH);  

		SAXParser parser = SAXParserFactory.newInstance().newSAXParser();  
		DefaultHandler dh = new DefaultHandler() {  

			private boolean firstElement = true;  
			private boolean inItem = false;  
			private boolean inTitle = false;  
			private boolean inLink = false;  
			private StringBuilder characterSB;  

			@Override  
			public void startDocument() throws SAXException {  
				System.out.println("startDocument");  
				super.startDocument();  
			}  

			@Override  
			public void endDocument() throws SAXException {  
				System.out.println("endDocument");  
				super.endDocument();  
			}  

			@Override  
			public void startElement(String uri, String localName,  
					String qName, Attributes attributes) throws SAXException {  

				if (firstElement) {  
					System.out.printf("Root element:%s\n", qName);  
					firstElement = false;  
				}  

				if (qName.equals("item")) {  
					inItem = true;  
				} else if (qName.equals("title")) {  
					inTitle = true;  
				} else if (qName.equals("link")) {  
					inLink = true;  
				}  

				if (inItem && (inTitle || inLink)) {  
					characterSB = new StringBuilder();  
				}  

				super.startElement(uri, localName, qName, attributes);  
			}  

			@Override  
			public void characters(char[] ch, int start, int length)  
					throws SAXException {  

				if (characterSB != null) {  
					characterSB.append(handleCharacters(ch, start, length));  
				}  

				super.characters(ch, start, length);  
			}  

			@Override  
			public void endElement(String uri, String localName, String qName)  
					throws SAXException {  

				if (characterSB != null) {  
					if (inItem && inTitle) {  
						System.out.printf("[title : %s]\n", characterSB.toString());  
					} else if (inItem && inLink) {  
						System.out.printf("[link : %s]\n", characterSB.toString());  
					}  
					characterSB = null;  
				}  

				if (qName.equals("item")) {  
					inItem = false;  
				} else if (qName.equals("title")) {  
					inTitle = false;  
				} else if (qName.equals("link")) {  
					inLink = false;  
				}  

				super.endElement(uri, localName, qName);  
			}  


			/** 
			 *  
			 * @param ch 
			 * @param start 
			 * @param end 
			 * @return 
			 */  
			public String handleCharacters(char[] ch, int start, int length) {  

				StringBuilder sb = new StringBuilder();  
				for (int i = start; i < start + length; i++) {  
					sb.append(ch[i]);  
				}  
				return sb.toString();  
			}  
		};  
		parser.parse(xmlFile, dh);  
	}  
}  