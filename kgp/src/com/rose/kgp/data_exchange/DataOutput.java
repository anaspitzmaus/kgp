package com.rose.kgp.data_exchange;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * the interface for all classes, for transferring data of the examination
 * @author Administrator
 *
 */
public interface DataOutput {
	/**
	 * the data of the examination must be packed in an HashMap<String, HashMap<String, ArrayList<String>>>
	 * the key of the hashMap is a string that classifies the group of examinations data
	 * the value is an hashMap that contains notification of the data as key (String) and an arrayList of the corresponding values
	 * @return
	 */
	public HashMap<String, HashMap<String, ArrayList<String>>> getExamData();
}
