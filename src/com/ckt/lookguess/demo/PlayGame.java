/**
 * 
 */
package com.ckt.lookguess.demo;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.gitonway.lee.niftymodaldialogeffects.lib.R;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author user
 *
 */
public class PlayGame extends Activity {

	private Button game_answer_1_button;
	private Button game_answer_2_button;
	private Button game_answer_3_button;
	private Button game_answer_4_button;
	
	private TextView game_answer_reuslt;
	
	//bathroom, bedroom, school, kitchen

	private final int ANSWER_A = 0;
	private final int ANSWER_B = 1;
	private final int ANSWER_C = 2;
	private final int ANSWER_D = 3;
	
	private int current_question = 1;
	private boolean first_init = true;
	
	HashMap<Integer, HashMap<String, String>> question_answer = null;
	
	String[][] all_answer = {{"bathroom", "false"},
							  {"bedroom", "false"},
							  {"school", "false"},
							  {"kitchen", "true"}};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.play_game);
		
		initPlayGame();
		initData();
	}
	


	private void initPlayGame() {
		game_answer_1_button = (Button) findViewById(R.id.play_game_answer_button_1);
		game_answer_2_button = (Button) findViewById(R.id.play_game_answer_button_2);
		game_answer_3_button = (Button) findViewById(R.id.play_game_answer_button_3);
		game_answer_4_button = (Button) findViewById(R.id.play_game_answer_button_4);
		
		game_answer_reuslt = (TextView) findViewById(R.id.play_game_answer_result);

		game_answer_1_button.setOnClickListener(myButtOnClickListener);
		game_answer_2_button.setOnClickListener(myButtOnClickListener);
		game_answer_3_button.setOnClickListener(myButtOnClickListener);
		game_answer_4_button.setOnClickListener(myButtOnClickListener);
	}
	
	private void initData() {
		if(question_answer == null) {
			question_answer = new HashMap<Integer, HashMap<String,String> >();
		}
		HashMap<String, String> tempValue = new HashMap<String, String>();
		for(int lenght = 0; lenght < all_answer.length; ++lenght) {
			tempValue.put(all_answer[lenght][0], all_answer[lenght][1]);
			if ((lenght / 4 + 1) == current_question) {
				if (lenght % 4 == ANSWER_A) {
					game_answer_1_button.setText(all_answer[lenght][0]);
				} else if (lenght % 4 == ANSWER_B) {
					game_answer_2_button.setText(all_answer[lenght][0]);
				} else if (lenght % 4 == ANSWER_C) {
					game_answer_3_button.setText(all_answer[lenght][0]);
				} else if (lenght % 4 == ANSWER_D) {
					game_answer_4_button.setText(all_answer[lenght][0]);
				}
			}
			
			if(lenght % 4  == 3) {
				question_answer.put(lenght / 4 + 1, (HashMap<String, String>)tempValue.clone());
				tempValue.clear();
			}
		}
/*		for(int i = 0; i < question_answer.size(); ++i){
			Set<Map.Entry<String, String> > entrySet = question_answer.get(i + 1).entrySet();
			for(Map.Entry<String, String> entry : entrySet){
			}
		}*/
	}
	
	private OnClickListener myButtOnClickListener = new OnClickListener() {
	
		@Override
		public void onClick(View view) {
			// TODO Auto-generated method stub
			String value = null;
			if(R.id.play_game_answer_button_1 == view.getId()){
				value = (String) game_answer_1_button.getText();
			} else if(R.id.play_game_answer_button_2 == view.getId()){
				value = (String) game_answer_2_button.getText();
			} else if(R.id.play_game_answer_button_3 == view.getId()){
				value = (String) game_answer_3_button.getText();
			} else if(R.id.play_game_answer_button_4 == view.getId()){
				value = (String) game_answer_4_button.getText();
			}
			
			if(question_answer.get(current_question).get(value).equals("true")) {
				game_answer_reuslt.setTextColor(Color.GREEN);
				game_answer_reuslt.setText("Right!!!");
				Toast.makeText(view.getContext(), "Right", Toast.LENGTH_LONG).show();
			}else {
				game_answer_reuslt.setTextColor(Color.RED);
				game_answer_reuslt.setText("Wrong !!!");
			}
		}
	};
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}
	
}
