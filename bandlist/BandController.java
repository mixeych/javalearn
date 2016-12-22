package com.company.bandlist;

import java.util.List;

public class BandController {

	private BandDAO dao;
	
	public BandController()
	{
		dao = BandDAOFactory.getBandDAO();
	}
	
    public Long addBand(Band band) {
        return dao.addBand(band);
    }
    
    public void updateBand(Band band) {
        dao.updateBand(band);
    }
 
    public void deleteBand(Long id) {
        dao.deleteBand(id);
    }
 
    public Band getBand(Long id) {
        return dao.getBand(id);
    }
    
    public List<Band> findBands() {
        return dao.findBands();
    }
	
}
