package com.company.bandlist;

import java.util.List;

public class BandTest {
	public static void main(String[] args) {
        BandController cm = new BandController();
        
        Band c1 = new Band("Metallica", "+7-911-890-7766", "sokolov@yandex.ru");
        Band c2 = new Band("NoizeMC", "+7-911-890-7755", "ivanov@google.com");
        Band c3 = new Band("Rammstein", "+7-911-890-1164", "semenova@mail.ru");
 
        System.out.println("ADD Band ==============");
        Long cId1 = cm.addBand(c1);
        Long cId2 = cm.addBand(c2);
        Long cId3 = cm.addBand(c3);
        List<Band> result1 = cm.findBands();
        for(Band c : result1) {
            System.out.println(c);
        }
        
        System.out.println("UPDATE Band ==============");
        Band change = new Band(cId1, "Megadeth", "+7-911-890-7766", "sokolov@yandex.ru");
        cm.updateBand(change);
        List<Band> result2 = cm.findBands();
        for(Band c : result2) {
            System.out.println(c);
        }
        
        System.out.println("DELETE Band ==============");
        cm.deleteBand(cId1);
        List<Band> result3 = cm.findBands();
        for(Band c : result3) {
            System.out.println(c);
        }
 
        System.out.println("GET Band ==============");
        Band contact = cm.getBand(cId2);
        System.out.println(contact);
    }
}
