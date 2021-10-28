/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.sorts;

import data.Member;
import java.util.Comparator;

/**
 *
 * @author STOECKLERKilianLeo
 */
public class SortByScore implements Comparator<Member>{

    @Override
    public int compare(Member o1, Member o2) {
        if(o1.getScore()>o2.getScore()){
            return 1;
        }
        if(o1.getScore()<o2.getScore()){
            return 2;
        }
        return 0;
    }
    
}
