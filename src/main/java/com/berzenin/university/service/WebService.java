package com.berzenin.university.service;

import java.util.Collection;

public interface WebService<V,M> {

	boolean deleteById(long itemsId);

	boolean createNewItem(M items);
	
	Collection<V> getAllandSendForServlet(long Id);

	boolean update(M update);

	V toView(M model);
	
	boolean isItemsPresent(M items);

}