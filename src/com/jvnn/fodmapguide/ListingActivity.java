package com.jvnn.fodmapguide;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ListingActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing);
        
        Data data = Data.getInstance(getApplicationContext());
        ArrayList<Data.Category> categories = data.getData();
        LayoutInflater li = LayoutInflater.from(this);
        LinearLayout main_ll = (LinearLayout) findViewById(R.id.listing_ll);
        
        for (Data.Category c : categories) {
        	View cat_view = li.inflate(R.layout.category_layout, main_ll, false);
        	TextView cat_name = (TextView) cat_view.findViewById(R.id.category_name);
        	Log.d("UGH", c.category);
        	cat_name.setText(c.category);
        	LinearLayout cat_ll = (LinearLayout) cat_view.findViewById(R.id.category_ll);
        	cat_name.setOnClickListener(new CategoryOpener(cat_ll));
        	
        	for (Data.Item i : c.items) {
        		View item_view = li.inflate(R.layout.item_layout, cat_ll, false);
        		TextView item_name = (TextView) item_view.findViewById(R.id.item_name);
        		TextView item_info = (TextView) item_view.findViewById(R.id.item_info);
        		item_name.setText(i.name);
        		
        		if (i.info != null && i.info.length() > 0) {
        			item_info.setText(i.info);
        		} else {
        			item_info.setVisibility(View.GONE);
        		}
        		
        		ImageView item_img = (ImageView) item_view.findViewById(R.id.item_usage);
        		
        		switch (i.level) {
        		case 0:
        			item_img.setImageResource(R.drawable.red);
        			break;
        		case 1:
        			item_img.setImageResource(R.drawable.yellow);
        			break;
        		case 2:
        			item_img.setImageResource(R.drawable.green);
        			break;
        		}
        		
        		cat_ll.addView(item_view);
        	}
        	main_ll.addView(cat_view);
        }
    }
    
    public class CategoryOpener implements OnClickListener {
    	private LinearLayout cat_ll;
    	public CategoryOpener(LinearLayout ll) {
    		cat_ll = ll;
    	}

		@Override
		public void onClick(View v) {
			if (cat_ll.getVisibility() == View.GONE) {
				cat_ll.setVisibility(View.VISIBLE);
			} else {
				cat_ll.setVisibility(View.GONE);
			}
		}
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.listing, menu);
        return true;
    }
    
}
