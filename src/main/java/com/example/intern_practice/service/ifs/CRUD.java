package com.example.intern_practice.service.ifs;

public interface CRUD<RQ, RS> {

	public RS add(RQ request);
	
	public RS get(RQ request);
	
	public RS find(RQ request);
	
	public RS revise(RQ request);

	public RS delete(RQ request);
	
}
