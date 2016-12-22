package com.company.bandlist;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BandSimpleDAO implements BandDAO {
private final List<Band> bands = new ArrayList<Band>();
    
    @Override
    public Long addBand(Band band) {
        Long id = generateBandId();
        band.setId(id);
        bands.add(band);
        return id;
    }
 
    
    @Override
    public void updateBand(Band band) {
        Band oldBand = getBand(band.getId());
        if(oldBand != null) {
        	oldBand.setName(band.getName());
        	oldBand.setPhone(band.getPhone());
        	oldBand.setEmail(band.getEmail());
        }
    }
 
    @Override
    public void deleteBand(Long id) {
        for(Iterator<Band> it = bands.iterator(); it.hasNext();) {
            Band cnt = it.next();
            if(cnt.getId().equals(id)) {
                it.remove();
            }
        }
    }
 
    @Override
    public Band getBand(Long bandId) {
        for(Band band : bands) {
            if(band.getId().equals(bandId)) {
                return band;
            }
        }
        return null;
    }
    
    @Override
    public List<Band> findBands() {
        return bands;
    }
 
    private Long generateBandId() {
        Long BandId = Math.round(Math.random() * 1000 + System.currentTimeMillis());
        while(getBand(BandId) != null) {
        	BandId = Math.round(Math.random() * 1000 + System.currentTimeMillis());
        }
        return BandId;
    }
}
