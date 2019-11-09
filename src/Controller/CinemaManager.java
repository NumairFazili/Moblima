package Controller;



import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CinemaManager {
    private int rows = 8;
    private int cols = 7;
    public CinemaManager(){};
    public CinemaManager(int r, int c){
        this.rows = r;
        this.cols = c;
    }

@Test
    public List<Integer> Convert_Index_to_1D(List<Integer> seats){
        List<Integer> res = Arrays.asList(rows*cols);
        System.out.println(res.get(10));
        return res;

    }


}
