package com.example.threelevelexpandablelistview;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.View.MeasureSpec;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {
	public static CustomExpandableListView list;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		int noObjectsLevel1= 5;
		int noObjectsLevel2= 4;
		int noObjectsLevel3= 7;
		
		List<Object> objectsLvl1= new ArrayList<Object>();
		for (int i=0; i<noObjectsLevel1; i++) {
			List<Object> objectsLvl2= new ArrayList<Object>();
			for (int j=0; j<noObjectsLevel2; j++) {
				List<Object> objectsLvl3= new ArrayList<Object>();
				for (int k=0; k<noObjectsLevel3; k++) {
					objectsLvl3.add(new Object("lvl3_"+String.valueOf(k), null));
				}
				objectsLvl2.add(new Object("lvl2_"+String.valueOf(j), objectsLvl3));
			}
			objectsLvl1.add(new Object("lvl1_"+String.valueOf(i), objectsLvl2));
		}
		
		RelativeLayout parent= (RelativeLayout) findViewById(R.id.parent);
		
		list= new CustomExpandableListView(this);
		Adapter adapter= new Adapter(this, objectsLvl1);
        list.setAdapter(adapter);
        
        parent.addView(list);    
	}
}

class CustomExpandableListView extends ExpandableListView {	
	public CustomExpandableListView(Context context) {
		super(context);     
	}
	
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		/*
		 * Adjust height
		 */
		heightMeasureSpec = MeasureSpec.makeMeasureSpec(500, MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}  
}
