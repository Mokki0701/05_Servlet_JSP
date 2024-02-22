package edu.kh.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import edu.kh.todoList.model.dto.Todo.Todo;



public class TitleService {
	
	private final String PATH = "/io_test/TodoList2.dat";
	
	private ObjectInputStream ois = null;
	private ObjectOutputStream oos = null;
	private List<Todo> todoList = null;
	
	public TitleService() {
		
		File file = new File(PATH);
		
		if(file.exists()) {
			try{
				
				ois = new ObjectInputStream(new FileInputStream("/io_test/TodoList.dat"));
				todoList = (ArrayList<Todo>)ois.readObject();
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else { // 파일이 존재하지 않으면
			
			// 폴더, 파일 만들기
			File directory = new File("/io_test");
			if(!directory.exists()) directory.mkdir(); // 폴더 없으면 생성
//			file.createNewFile();
			
			// 객체 출력 스트림을 이용해서 파일 생성 + 샘플 데이터 추가
			todoList = new ArrayList<Todo>();
			todoList.add(new Todo("자바 공부", "수업 내용 복습하기", false, LocalDateTime.now()));
			todoList.add(new Todo("CSS 공부", "수업 내용 복습하기", false, LocalDateTime.now()));
			todoList.add(new Todo("HTML 공부", "수업 내용 복습하기", false, LocalDateTime.now()));
			
			try {
				// 객체 출력 스트림 생성
				oos = new ObjectOutputStream(new FileOutputStream(PATH));
				
				oos.writeObject(todoList); // todoList를 파일로 출력
				
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				try {
					oos.close(); // flush() 내장되어 있어 다 밀어내고 닫기					
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			
			System.out.println("**** TodoList.dat 파일 생성 완료 ****");
			
		}
	}
	
	
	public List<Todo> Select(){
		return todoList;
	}
}
