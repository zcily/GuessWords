package com.ckt.lookguess.demo;

import org.simple.eventbus.EventBus;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.gitonway.lee.niftymodaldialogeffects.lib.R;
import com.yx.utils.SPUtils;
import com.ckt.lookguess.demo.*;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {
	public static final String KEY_USR_NAME = "usr_name";
	
	private String usrName;
	private String usrLevel;
	private TextView mUsrName;
	private TextView mUsrLevel;
	
	private Button mContinueGameButton;
	private Button mNewGameButton;
	private Button mAboutButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
//		EventBus.getDefault().register(this);
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login);
		
		mContinueGameButton = (Button)findViewById(R.id.continueGameButton);
		mNewGameButton = (Button)findViewById(R.id.newGameButton);
		mAboutButton = (Button)findViewById(R.id.aboutButton);
		
		View usrInfoView = findViewById(R.id.user_info);
		mUsrName = (TextView)usrInfoView.findViewById(R.id.user_name);
		mUsrLevel = (TextView)usrInfoView.findViewById(R.id.user_level);
		if(SPUtils.contains(this, KEY_USR_NAME)){
			usrName = (String)SPUtils.get(this, KEY_USR_NAME, "Tom");
			usrInfoView.setVisibility(View.VISIBLE);
			mUsrName.setText(usrName);
			mUsrLevel.setText(usrLevel);
			mContinueGameButton.setVisibility(View.VISIBLE);
		}else{
			usrInfoView.setVisibility(View.GONE);
			mContinueGameButton.setVisibility(View.GONE);
		}
	}
	
	private void newUsrRegister(String name){
		SPUtils.put(this, KEY_USR_NAME, name);
	}
	
	private void startGame(String name){
		newUsrRegister(name);
		
		//begin game
		Intent intent = new Intent();
		intent.setClass(LoginActivity.this, PlayGame.class);
		startActivity(intent);
	}
	
	public void onClikView(View v){
		switch (v.getId()) {
		case R.id.continueGameButton:
			break;
			
		case R.id.newGameButton:
			showNameEditDialog(v);
			break;

		case R.id.aboutButton:
			break;
			
		default:
			break;
		}
	}
	
	private void showNameEditDialog(View v){
		Effectstype effect = Effectstype.Newspager;
		final NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(this);
		final EditText view = (EditText)LayoutInflater.from(v.getContext()).inflate(R.layout.edit_name_view, null);
		dialogBuilder
        .withTitle("your game name")                                  //.withTitle(null)  no title
        .withTitleColor("#FFFFFF")                                  //def
        .withDividerColor("#11000000")                              //def
        .withMessage(null)                     //.withMessage(null)  no Msg
//        .withMessageColor("#FFFFFFFF")                              //def  | withMessageColor(int resid)
        .withDialogColor("#FF72CAE1")                               //def  | withDialogColor(int resid)                               //def
        .withIcon(getResources().getDrawable(R.drawable.icon))
        .isCancelableOnTouchOutside(false)                           //def    | isCancelable(true)
        .withDuration(700)                                          //def
        .withEffect(effect)                                         //def Effectstype.Slidetop
        .withButton1Text("OK")                                      //def gone
        .withButton2Text("Cancel")                                  //def gone
        .setCustomView(view,v.getContext())         //.setCustomView(View or ResId,context)
        .setButton1Click(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	String name = view.getText().toString();
            	if(name != null && !name.equals("")){
            		startGame(name);
            		dialogBuilder.dismiss();
            	} else {
					Toast.makeText(v.getContext(), "Please entry the name", Toast.LENGTH_SHORT);
				}
            	
            }
        })
        .setButton2Click(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dialogBuilder.dismiss();
			}
		})
        .show();
	}

	@Override
	protected void onDestroy() {
//		EventBus.getDefault().unregister(this);
		super.onDestroy();
	}
}
