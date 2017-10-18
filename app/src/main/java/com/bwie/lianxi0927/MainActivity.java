package com.bwie.lianxi0927;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bwie.lianxi0927.bean.UserLogin;
import com.bwie.lianxi0927.presenter.LoginPresenter;
import com.bwie.lianxi0927.presenter.ZhucePresenter;
import com.bwie.lianxi0927.view.LoginView;
import com.bwie.lianxi0927.view.ZhuceView;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static java.lang.String.valueOf;

public class MainActivity extends AppCompatActivity implements LoginView,ZhuceView{

    private EditText mobile;
    private EditText pwd;
    private ProgressBar mProgressbar;
    private LoginPresenter loginPresenter;
    private ZhucePresenter zhucePresenter;
    protected static final int CHOOSE_PICTURE = 0;
    protected static final int TAKE_PICTURE = 1;
    private static final int CROP_SMALL_PICTURE = 2;
    protected static Uri tempUri;
    private ImageView iv_personal_icon;
    private TextView tv_zhuce;
    private ImageView iv_qq_login;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp = getSharedPreferences("con", MODE_PRIVATE);
        initView();
        initData();
    }

    private void initData() {
        loginPresenter = new LoginPresenter(this);
        zhucePresenter = new ZhucePresenter(this);
    }

    private void initView() {
        mobile = (EditText) findViewById(R.id.et_mobile);
        pwd = (EditText) findViewById(R.id.et_pwd);
        tv_zhuce = (TextView) findViewById(R.id.tv_zhuce);
        mProgressbar = (ProgressBar) findViewById(R.id.pb);
        Button btn_change = (Button) findViewById(R.id.btn_change);
        iv_personal_icon = (ImageView) findViewById(R.id.iv_personal_icon);
        iv_qq_login = (ImageView) findViewById(R.id.iv_qq_login);
        iv_qq_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UMShareAPI.get(MainActivity.this).getPlatformInfo(MainActivity.this, SHARE_MEDIA.QQ, umAuthListener);
            }
        });
        btn_change.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showChoosePicDialog();
            }
        });
        tv_zhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        zhucePresenter.zhuce(mobile.getText().toString(),pwd.getText().toString());
                    }
                });
            }
        });
    }
    private void showChoosePicDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("设置头像");
        String[] items = { "选择本地照片", "拍照" };
        builder.setNegativeButton("取消", null);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case CHOOSE_PICTURE: // 选择本地照片
                        Intent openAlbumIntent = new Intent(
                                Intent.ACTION_GET_CONTENT);
                        openAlbumIntent.setType("image/*");
                        startActivityForResult(openAlbumIntent, CHOOSE_PICTURE);
                        break;
                    case TAKE_PICTURE: // 拍照
                        Intent openCameraIntent = new Intent(
                                MediaStore.ACTION_IMAGE_CAPTURE);
                        tempUri = Uri.fromFile(new File(Environment
                                .getExternalStorageDirectory(), "image.jpg"));
                        // 指定照片保存路径（SD卡），image.jpg为一个临时文件，每次拍照后这个图片都会被替换
                        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
                        startActivityForResult(openCameraIntent, TAKE_PICTURE);
                        break;
                }
            }
        });
        builder.create().show();
    }

    private UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //授权开始的回调
        }
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            Toast.makeText(getApplicationContext(), "Authorize succeed", Toast.LENGTH_SHORT).show();
            //Glide.with(MainActivity.this).load(data.get("iconurl")).into(iv_qq_login);

        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText( getApplicationContext(), "Authorize fail", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText( getApplicationContext(), "Authorize cancel", Toast.LENGTH_SHORT).show();
        }
    };
    public void login(View view){

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                loginPresenter.login(mobile.getText().toString(),pwd.getText().toString());
            }
        });

    }

    @Override
    public void showProgressbar() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mProgressbar.setVisibility(View.VISIBLE);
            }
        });

    }

    @Override
    public void hideProgressbar() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mProgressbar.setVisibility(View.GONE);
            }
        });

    }

    @Override
    public void nameError(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this,msg,Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void passError(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this,msg,Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void loginSuccess(String code, final int uid) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                sp.edit().putBoolean("isLogin", true).commit();
                sp.edit().putInt("uid",uid).commit();
                Intent intent = new Intent(MainActivity.this,ZhuActivity.class);
                startActivity(intent);

            }
        });
    }

    @Override
    public void zhuceSuccess(String code, final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void zhuceFail(String code, final String msg) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void loginFail(String code, final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this,msg,Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void failure(Call call, IOException e) {

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) { // 如果返回码是可以用的
            switch (requestCode) {
                case TAKE_PICTURE:
                    startPhotoZoom(tempUri); // 开始对图片进行裁剪处理
                    break;
                case CHOOSE_PICTURE:
                    startPhotoZoom(data.getData()); // 开始对图片进行裁剪处理
                    break;
                case CROP_SMALL_PICTURE:
                    if (data != null) {
                        setImageToView(data,iv_personal_icon); // 让刚才选择裁剪得到的图片显示在界面上
                    }
                    break;
            }
        }
    }
        protected void startPhotoZoom(Uri uri) {
            if (uri == null) {
                Log.i("tag", "The uri is not exist.");
            }
            tempUri = uri;
            Intent intent = new Intent("com.android.camera.action.CROP");
            intent.setDataAndType(uri, "image/*");
            // 设置裁剪
            intent.putExtra("crop", "true");
            // aspectX aspectY 是宽高的比例
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);
            // outputX outputY 是裁剪图片宽高
            intent.putExtra("outputX", 150);
            intent.putExtra("outputY", 150);
            intent.putExtra("return-data", true);
            startActivityForResult(intent, CROP_SMALL_PICTURE);
        }
    protected void setImageToView(Intent data, ImageView imgview) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            try {
                Bitmap photo = extras.getParcelable("data");
                imgview.setImageBitmap(photo);
                File file=new File("mnt/sdcard/icon.png");
                if(!file.exists()){
                    try {
                        file.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                FileOutputStream outputStream = new FileOutputStream(file);
                photo.compress(Bitmap.CompressFormat.PNG,100,outputStream);
                if(file!=null) {
                    String filename = file.getName();
                    Map<String, Object> params = new HashMap<>();
                    params.put("uid", "15297526557");
                    OkHttpClient okHttpClient = new OkHttpClient();
                    MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
                    RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
                    builder.addFormDataPart("file", filename, requestBody);
                    if (params != null) {
                        // map 里面是请求中所需要的 key 和 value
                        for (Map.Entry entry : params.entrySet()) {
                            builder.addFormDataPart(valueOf(entry.getKey()), valueOf(entry.getValue()));
                        }
                    }
                    Request request = new Request.Builder().url("http://120.27.23.105/file/upload").post(builder.build()).build();
                    Call call = okHttpClient.newCall(request);
                    call.enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }
                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            System.out.println("=========" + response.body().string());
                        }
                    });
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

   /* private void uploadPic(Bitmap bitmap) {
        // 上传至服务器
        // ... 可以在这里把Bitmap转换成file，然后得到file的url，做文件上传操作
        // 注意这里得到的图片已经是圆形图片了
        // bitmap是没有做个圆形处理的，但已经被裁剪了

        String imagePath = Utils.savePhoto(bitmap, Environment
                .getExternalStorageDirectory().getAbsolutePath(), String
                .valueOf(System.currentTimeMillis()));
        Log.e("imagePath", imagePath+"");
        if(imagePath != null){
            // 拿着imagePath上传了
            // ...
        }
    }*/

}
