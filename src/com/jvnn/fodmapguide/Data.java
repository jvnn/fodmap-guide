package com.jvnn.fodmapguide;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import android.content.Context;
import android.util.Log;

public class Data {
	private static Data me;
	private static final String FILENAME = "data.json";
	private static final String LOG_TAG = "Data";
	private DataStructure data;
	
	private Data(Context ctx) {
		String json = "";
		try {
			BufferedReader rdr = new BufferedReader(new InputStreamReader(ctx.getAssets().open(FILENAME)));
			
			String line = rdr.readLine();
			while (line != null) {
				json += line;
				line = rdr.readLine();
			}
			
		} catch (IOException e) {
			Log.e(LOG_TAG, "Couldn't open data file: " + e.toString());
		}
		
		Gson gson = new GsonBuilder().create();
		data = gson.fromJson(json, DataStructure.class);
	}
	
	public static Data getInstance(Context ctx) {
		if (me == null) {
			me = new Data(ctx);
		}
		return me;
	}
	
	
	public ArrayList<Category> getData() {
		return data.data;
	}
	
	
	private class DataStructure {
		public ArrayList<Category> data;
	}
	
	public class Category {
		public String category;
		public ArrayList<Item> items;
	}
	
	public class Item {
		public String name;
		public String info;
		public int level;
	}
}