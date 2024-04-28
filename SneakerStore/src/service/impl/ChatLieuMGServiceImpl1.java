package service.impl;

import java.util.ArrayList;
import model.ChatLieuMatGiay1;
import repository.ChatLieuMGRepository1;
import service.ChatLieuMGService1;

/**
 *
 * @author ADMIN
 */
public class ChatLieuMGServiceImpl1 implements ChatLieuMGService1 {

    private final ChatLieuMGRepository1 chatLieuMGRepository = new ChatLieuMGRepository1();

    @Override
    public ArrayList<ChatLieuMatGiay1> getAllChatLieuMG() {
        return chatLieuMGRepository.getAllChatLieuMG();
    }

    @Override
    public Integer addChatLieuMG(ChatLieuMatGiay1 chatLieu) {
        return chatLieuMGRepository.addChatLieuMG(chatLieu);
    }

    @Override
    public Integer updateChatLieu(ChatLieuMatGiay1 chatLieu) {
        return chatLieuMGRepository.updateChatLieu(chatLieu);
    }

    @Override
    public Integer deleteChatLieu(int MaCL) {
        return chatLieuMGRepository.deleteChatLieu(MaCL);
    }
    
    
}
