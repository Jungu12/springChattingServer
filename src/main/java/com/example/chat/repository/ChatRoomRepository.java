package com.example.chat.repository;

// import 생략....

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.example.chat.model.ChatRoom;

@Repository
public class ChatRoomRepository {

	private Map<String, ChatRoom> chatRoomMap;

	@PostConstruct
	private void init() {
		// 채팅방을 생성 순서에 맞춰 관리하기 위해 LinkedHashMap 사용
		chatRoomMap = new LinkedHashMap<>();
	}

	public List<ChatRoom> findAllRoom() {
		// 채팅방 생성 순서 최근 순으로 반환
		List chatRooms = new ArrayList<>(chatRoomMap.values());
		Collections.reverse(chatRooms);
		return chatRooms;
	}

	public ChatRoom findRoomById(String id) {
		return chatRoomMap.get(id);
	}

	public ChatRoom createChatRoom(String name) {
		ChatRoom chatRoom = ChatRoom.create(name);
		chatRoomMap.put(chatRoom.getRoomId(), chatRoom);
		return chatRoom;
	}
}