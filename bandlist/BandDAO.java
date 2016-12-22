package com.company.bandlist;

import java.util.List;

public interface BandDAO {
	public Long addBand(Band band);

    public void updateBand(Band band);

    public void deleteBand(Long bandId);

    public Band getBand(Long bandId);

    public List<Band> findBands();
}
