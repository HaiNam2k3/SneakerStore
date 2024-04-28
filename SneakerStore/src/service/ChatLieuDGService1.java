package service;

import java.util.ArrayList;
import model.ChatLieuDeGiay1;

/**
 *
 * @author ADMIN
 */
public interface ChatLieuDGService1 {

    ArrayList<ChatLieuDeGiay1> getAllChatLieDG();
    Integer addChatLieuDG(ChatLieuDeGiay1 cldg);
    Integer updateChatLieuDG(ChatLieuDeGiay1 cldg);
    Integer deleteChatLieuDG(int MaCLDe);
}
