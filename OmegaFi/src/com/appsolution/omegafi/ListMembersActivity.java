package com.appsolution.omegafi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.actionbarsherlock.widget.SearchView;

import android.os.Bundle;
import android.app.Activity;
import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class ListMembersActivity extends OmegaFiActivity implements SearchView.OnQueryTextListener{

	private EditText search;
	private ListView listMembers;
	private AlphabeticAdapter members;
	
	private SearchView mSearchView;
    private TextView mStatusView;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_members);
		listMembers=(ListView)findViewById(R.id.listViewMembers);
		members=new AlphabeticAdapter(this,android.R.layout.simple_list_item_1, getArrayTest());
		listMembers.setAdapter(members);
		mStatusView=(TextView)findViewById(R.id.status_text);
	}
	
	public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu) {
		super.onCreateOptionsMenu(menu);
		 
        com.actionbarsherlock.view.MenuInflater inflater = getSupportMenuInflater();
        inflater.inflate(R.menu.search_view_in_menu, menu);
        com.actionbarsherlock.view.MenuItem searchItem = menu.findItem(R.id.action_search);
        mSearchView = (SearchView) searchItem.getActionView();
        setupSearchView(searchItem);
	    return true;
	 }
	
	 private void setupSearchView(com.actionbarsherlock.view.MenuItem searchItem) {
		 
	        if (isAlwaysExpanded()) {
	            mSearchView.setIconifiedByDefault(false);
	        } else {
	            searchItem.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_IF_ROOM
	                    | MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
	        }
	 
	        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
	        if (searchManager != null) {
	            List<SearchableInfo> searchables = searchManager.getSearchablesInGlobalSearch();
	 
	            SearchableInfo info = searchManager.getSearchableInfo(getComponentName());
//	            for (SearchableInfo inf : searchables) {
//	                if (inf.getSuggestAuthority() != null
//	                        && inf.getSuggestAuthority().startsWith("applications")) {
//	                    info = inf;
//	                }
//	            }
	            mSearchView.setSearchableInfo(info);
	        }
	 
	        mSearchView.setOnQueryTextListener(this);
	    }



	@Override
	    public boolean onOptionsItemSelected(com.actionbarsherlock.view.MenuItem item) {
//		super.onOptionsItemSelected(item);
	        switch (item.getItemId()) {
	            case 1:
	                search = (EditText) item.getActionView();
	                search.addTextChangedListener(filterTextWatcher);
	                search.requestFocus();
	                InputMethodManager imm = (InputMethodManager) getSystemService(Context
	                		.INPUT_METHOD_SERVICE);
	                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
	        }   
	        return true;
	    }       

	private TextWatcher filterTextWatcher = new TextWatcher() {
	    public void afterTextChanged(Editable s) {
	    }

	    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
	    }

	    public void onTextChanged(CharSequence s, int start, int before, int count) {
	        // your search logic here
	    }

	};	
	
	@Override
	protected void optionsActionBar() {
		actionBar.setTitle("Members Roster");
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setDisplayShowHomeEnabled(false);
	}
	
	private List<String> getArrayTest(){
		ArrayList<String> stringList=new ArrayList<String>();
		stringList.add("aback");  
        stringList.add("abash");  
        stringList.add("abbey");  
        stringList.add("abhor");  
        stringList.add("abide");  
        stringList.add("abuse");  
        stringList.add("candidate");  
        stringList.add("capture");  
        stringList.add("careful");  
        stringList.add("catch");  
        stringList.add("cause");  
        stringList.add("celebrate");  
        stringList.add("forever");  
        stringList.add("fable");  
        stringList.add("fidelity");  
        stringList.add("fox");  
        stringList.add("funny");  
        stringList.add("fail");  
        stringList.add("jail");  
        stringList.add("jade");  
        stringList.add("jailor");  
        stringList.add("january");  
        stringList.add("jasmine");  
        stringList.add("jazz");  
        stringList.add("zero");  
        stringList.add("zoo");  
        stringList.add("zeus");  
        stringList.add("zebra");  
        stringList.add("zest");  
        stringList.add("zing"); 
        stringList.add("celebrate");  
        stringList.add("forever");  
        stringList.add("fable");  
        stringList.add("fidelity");
        String[] aux=Arrays.copyOf(stringList.toArray(), stringList.size(), String[].class);
        Arrays.sort(aux);
		return (List<String>)Arrays.asList(aux);
	}
	
	 public boolean onQueryTextChange(String newText) {
	        members.getFilter().filter(newText);
	        return false;
	    }
	 
	    public boolean onQueryTextSubmit(String query) {
	       
	        return false;
	    }
	 
	    public boolean onClose() {
	        mStatusView.setText("Closed!");
	        return false;
	    }
	 
	    protected boolean isAlwaysExpanded() {
	        return false;
	    }

}