package com.eventlabs.dao;

import java.util.List;
import java.util.Map;

import com.eventlabs.beans.EventInfo;
import com.eventlabs.beans.MasterData;
import com.eventlabs.beans.SearchEvent;

public interface EventDAO {
	public void saveEvent(EventInfo eventInfo);

	public List<EventInfo> searchEvent(SearchEvent searchParm);

	public Map getMasterData();
	

}
