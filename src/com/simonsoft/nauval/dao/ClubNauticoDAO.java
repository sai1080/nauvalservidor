package com.simonsoft.nauval.dao;

import java.util.List;

import com.simonsoft.nauval.modelo.ClubNautico;


public interface ClubNauticoDAO {
	public List<ClubNautico> recuperarClubesNauticos();
	public ClubNautico recuperaClubNautico(Integer id);
	

}
