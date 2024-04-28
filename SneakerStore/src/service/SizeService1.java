package service;

import java.util.ArrayList;
import model.Size1;

/**
 *
 * @author ADMIN
 */
public interface SizeService1 {
    ArrayList<Size1> getAllSize();
    Integer addSize(Size1 size);
    Integer updateSize(Size1 size);
    Integer deleteSize(int MaSize);
}
