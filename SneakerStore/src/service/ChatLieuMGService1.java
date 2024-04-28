package service;

import java.util.ArrayList;
import model.ChatLieuMatGiay1;

/**
 *
 * @author ADMIN
 */
public interface ChatLieuMGService1 {

    public ArrayList<ChatLieuMatGiay1> getAllChatLieuMG();

    Integer addChatLieuMG(ChatLieuMatGiay1 chatLieu);

    Integer updateChatLieu(ChatLieuMatGiay1 chatLieu);
    
    Integer deleteChatLieu(int MaCL);
}
