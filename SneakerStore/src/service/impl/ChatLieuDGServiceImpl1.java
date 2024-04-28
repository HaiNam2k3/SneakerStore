/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import java.util.ArrayList;
import model.ChatLieuDeGiay1;
import repository.ChatLieuDGRepository1;
import service.ChatLieuDGService1;

/**
 *
 * @author ADMIN
 */
public class ChatLieuDGServiceImpl1 implements ChatLieuDGService1{
    
    private final ChatLieuDGRepository1 chatLieuDGRepository = new ChatLieuDGRepository1();

    @Override
    public ArrayList<ChatLieuDeGiay1> getAllChatLieDG() {
        return chatLieuDGRepository.getAllChatLieDG();
    }

    @Override
    public Integer addChatLieuDG(ChatLieuDeGiay1 cldg) {
        return chatLieuDGRepository.addChatLieuDG(cldg);
    }

    @Override
    public Integer updateChatLieuDG(ChatLieuDeGiay1 cldg) {
        return chatLieuDGRepository.updateChatLieuDG(cldg);
    }

    @Override
    public Integer deleteChatLieuDG(int MaCLDe) {
        return chatLieuDGRepository.deleteChatLieuDG(MaCLDe);
    }
    
}
