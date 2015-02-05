package com.loopfire.meitaotao.function.register;

import java.io.File;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.loopfire.meitaotao.R;
import com.loopfire.meitaotao.api.UserAPI;
import com.loopfire.meitaotao.common.BaseActivity;
import com.loopfire.meitaotao.entity.UserInfo;
import com.loopfire.meitaotao.util.FileUtils;
import com.loopfire.meitaotao.util.Util;

/**
 * 理发师审核 上传身份证
 * 
 * @author Administrator
 * 
 */
public class UploadIDcardActivity extends BaseActivity {
	private static final int IMAGE_MAX_SIZE = 5000000;// 单位byte
	private static final int UPLOAD_IDCARD = 1002;
	private static final int IMAGE_SELECT = 1001;
	private ImageView idcard;
	private Button bt_next;
	private LinearLayout btn_upload_idcard;
	private String idCardPath;
	private UserAPI api;

	@Override
	public void initView() {
		super.initView();
		api = new UserAPI();
		idcard = (ImageView) findViewById(R.id.iv_idcard);
		bt_next = (Button) findViewById(R.id.btn_next);
		btn_upload_idcard = (LinearLayout) findViewById(R.id.btn_upload_idcard);
	}

	@Override
	public void initListener() {
		super.initListener();
		bt_next.setOnClickListener(this);
		btn_upload_idcard.setOnClickListener(this);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_upload_idcard);
		initView();
		initListener();
		setTitle("上传身份证");
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.btn_next:
			uploadIdcard();
			// startActivity(new Intent(this, CurWorkLocActivity.class));
			break;
		case R.id.btn_upload_idcard:// 上传身份证按钮
			selectIdCardFromLocal();
			break;
		}
	}

	@Override
	public void refresh(Object... param) {
		super.refresh(param);
		final int flag = Integer.parseInt(param[0].toString());
		switch (flag) {
		case UPLOAD_IDCARD:
			Log.i("result", param[1].toString());
			int code = 100;
			String message;
			try {
				JSONObject o = new JSONObject(param[1].toString());
				code = o.getInt("code");
				message = o.getString("code");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			if (code == 0) {
				startActivity(new Intent(this, CurWorkLocActivity.class));
			} else {
				Util.toastInfo(getApplicationContext(), "上传失败，请重新上传");
			}
			break;
		default:
			// Util.toastInfo(getApplicationContext(), msg);
			break;
		}
	}

	private void uploadIdcard() {
		if (TextUtils.isEmpty(idCardPath)) {
			Util.toastInfo(getApplicationContext(), "找不到图片");
			return;
		} else {

			String userId = FileUtils.readuserInfo(getApplicationContext())
					.getUserId();
			String uploadImagePath = FileUtils.compressAndSaveImage(idCardPath,
					IMAGE_MAX_SIZE);
			File file = new File(uploadImagePath);
			Log.i("userId", userId);
			Log.i("file size", file.length() + "|" + file.getName());
			if (file != null) {
				// 0默认上传身份证正面
				api.uploadIdcard(userId, 0, file, this, UPLOAD_IDCARD);
			}
		}
	}

	/**
	 * 从图库获取图片
	 */
	private void selectIdCardFromLocal() {
		Intent intent;
		if (Build.VERSION.SDK_INT < 19) {
			intent = new Intent(Intent.ACTION_GET_CONTENT);
			intent.setType("image/*");
		} else {
			intent = new Intent(
					Intent.ACTION_PICK,
					android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		}
		startActivityForResult(intent, IMAGE_SELECT);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == Activity.RESULT_OK) {
			switch (requestCode) {
			case IMAGE_SELECT:
				if (data != null) {
					Uri selectedImage = data.getData();
					if (selectedImage != null) {
						sureIdCard(selectedImage);
					}
				}
				break;
			}
		}
	}

	/**
	 * 确认身份证照片
	 * 
	 * @param selectedImage
	 */
	private void sureIdCard(Uri selectedImage) {
		Cursor cursor = getContentResolver().query(selectedImage, null, null,
				null, null);
		if (cursor != null) {
			cursor.moveToFirst();
			int columnIndex = cursor.getColumnIndex("_data");
			String picturePath = cursor.getString(columnIndex);
			cursor.close();
			cursor = null;
			if (TextUtils.isEmpty(picturePath)) {
				Util.toastInfo(getApplicationContext(), "找不到图片");
				return;
			}
			show(picturePath);

		} else {
			File file = new File(selectedImage.getPath());
			if (!file.exists()) {
				Util.toastInfo(getApplicationContext(), "找不到图片");
				return;
			}
			show(file.getAbsolutePath());
		}
	}

	/**
	 * 显示照片
	 * 
	 * @param picturePath
	 */
	private void show(String picturePath) {
		idCardPath = picturePath;
		Options option = new Options();
		Bitmap bitmap = BitmapFactory.decodeFile(picturePath, option);
		if (bitmap != null) {
			idcard.setImageBitmap(bitmap);
			String userid = FileUtils.readuserInfo(getApplicationContext())
					.getUserId();
			// System.out.println(userid+"2222");
		}

	}

}
