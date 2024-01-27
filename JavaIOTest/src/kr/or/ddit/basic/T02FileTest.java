package kr.or.ddit.basic;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class T02FileTest {
	public static void main(String[] args) {
		
		int sum = 0;
		for(int i=1; i<=200; i++) {
			if(i%2==0) {
				sum += i;
			}
		}
		System.out.println(sum);
		
		File f1 = new File("d:/D_Other/sample.txt");
		File f2 = new File("d:/D_Other/test.txt");
		
		if(f1.exists()) {
			System.out.println(f1.getAbsolutePath()+"은 존재합니다.");
		}else {
			System.out.println(f1.getAbsolutePath()+"은 없는 파일입니다.");
			
			try {
				if(f1.createNewFile()) {
					System.out.println(f1.getAbsolutePath()+" 파일을 새로 만들었습니다.");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(f2.exists()) {
			System.out.println(f2.getAbsolutePath()+"은 존재합니다.");
		}else {
			System.out.println(f1.getAbsolutePath()+"은 없는 파일입니다.");
		}
		System.out.println("------------------------------------------");
		
		File f3 = new File("d:/D_Other");
		File[] files = f3.listFiles();
		
		for(File f : files) {
			System.out.print(f.getName()+" => ");
			if(f.isFile()) {
				System.out.println("파일");
			}else if(f.isDirectory()) {
				System.out.println("디렉토리(폴더)");
			}
		}
		System.out.println("===========================================");
		
		String[] strFiles = f3.list();
		for(String strFile : strFiles) {
			System.out.println(strFile);
		}
		System.out.println("-------------------------------------------");
		System.out.println();
		
		displayFileList(new File("d:/D_Other"));
		
	}

	private static void displayFileList(File dir) {
		
		System.out.println("["+dir.getAbsolutePath()+"] 디렉토리 내용");
		
		//디렉토리 안의 모든 파일 목록 가져오기
		File[] files = dir.listFiles();
		
		//하위 디렉토리 인덱스 정보를 저장할 ArrayList 생성
		List<Integer> subDirList = new ArrayList<>();
		
		//날짜 출력을 위한 포맷 설정
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd a hh:mm");
		
		for(int i=0; i<files.length; i++) {
			String attr = ""; //파일 속성정보(읽기, 쓰기, 히든, 디렉토리 구분)
			String size = ""; //파일 용량
			
			if(files[i].isDirectory()) {	//디렉토리면
				attr = "<DIR>";				//파일속성에 디렉토리로
				subDirList.add(i);
			}else {
				size = files[i].length() + "";
				attr = files[i].canRead() ? "R" : " ";	//메서드 호출해서 true면 R
				attr += files[i].canWrite() ? "W" : " ";//+= 누적시켜서 쓰이게
				attr += files[i].isHidden() ? "H" : " ";
			}
			System.out.printf("%s %5s %12s %s\n"
							, sdf.format(new Date(files[i].lastModified()))
								  ,attr, size, files[i].getName());
		}
		
		int dirCount = subDirList.size(); //하위폴더 개수
		int fileCount = files.length - dirCount; //폴더 제외한 파일개수
		
		System.out.println(fileCount + "개의 파일, "+dirCount + "개의 디렉토리(폴더)");
		System.out.println();
		
		System.out.println(subDirList);	//디렉토리인 것의 인덱스 번호
		for(Integer idx : subDirList) {
			displayFileList(files[idx]);
		}
	}
}