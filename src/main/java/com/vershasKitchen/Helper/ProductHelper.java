package com.vershasKitchen.Helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductHelper {
	
	public static final String CAKES = "Cakes";
	public static final String SNACKSANDAPPETIZERS = "SnacksAndAppetizers";
	public static final String DESERTS = "Deserts";
	public static final String OTHERS = "Others";
	
	
	public static Map<String, List<String>> CATEGORYMAP;
	static {
		CATEGORYMAP = new HashMap<>();
		
		List<String> cakesList = new ArrayList<>();
		cakesList.add("ClassicFlavourCakes");
		cakesList.add("PhotoCakes");
		cakesList.add("MultiTierCakes");
		cakesList.add("PinataCakes");
		cakesList.add("SpecialCakes");
		cakesList.add("FloralCakes");
		cakesList.add("ThemeCakes");
		cakesList.add("ChocoManiaCakes");
		cakesList.add("FruitCakes");
		
		List<String> snacksAndAppetizers = new ArrayList<>();
		snacksAndAppetizers.add("BreadsAndMore");
		snacksAndAppetizers.add("Chinese");
		snacksAndAppetizers.add("Indian");
		
		List<String> deserts = new ArrayList<>();
		deserts.add("IndianDeserts");
		deserts.add("IceCreams");
		deserts.add("Bakingo");
		
		List<String> others = new ArrayList<String>();
		
		CATEGORYMAP.put(CAKES, cakesList);
		CATEGORYMAP.put(SNACKSANDAPPETIZERS, snacksAndAppetizers);
		CATEGORYMAP.put(CAKES, cakesList);
		CATEGORYMAP.put(DESERTS, deserts);
		CATEGORYMAP.put(OTHERS, others);
		
	}
	
	public static String getImageUrl(String path) {
		return "https://raw.githubusercontent.com/12MayankJha/VershasKitchenAssets/main/" + path + ".jpg";
	}
	
}
