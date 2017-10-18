package com.bwie.lianxi0927;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bwie.lianxi0927.bean.UserLogin;
import com.google.gson.Gson;
import com.umeng.socialize.UMShareAPI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.bwie.lianxi0927.common.Api.USERINFO_API;
import static java.lang.String.valueOf;

public class GerenxinxiActivity extends AppCompatActivity {

    private ImageView gerenxinxi_iv_touxiang;
    protected static final int CHOOSE_PICTURE = 0;
    protected static final int TAKE_PICTURE = 1;
    private static final int CROP_SMALL_PICTURE = 2;
    protected static Uri tempUri;
    private TextView csrq;
    private TextView xb;
    private TextView yhm;
    private TextView nc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenxinxi);
        initView();
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        OkHttpClient client = new OkHttpClient();
        FormBody.Builder builder = new FormBody.Builder();
        FormBody build = builder.build();
        final int uid = getSharedPreferences("con", MODE_PRIVATE).getInt("uid", 0);
        Request request = new Request.Builder().url(USERINFO_API+"?uid="+uid).post(build).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (response!=null&&response.isSuccessful()){
                    final String result;
                    try {
                        result = response.body().string();
                        System.out.println("result======="+result);
                        Gson gson = new Gson();
                        UserLogin userLogin = gson.fromJson(result, UserLogin.class);
                        final UserLogin.DataBean data = userLogin.getData();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                String icon = data.getIcon();
                                Glide.with(GerenxinxiActivity.this).load(icon).diskCacheStrategy(DiskCacheStrategy.NONE).into(gerenxinxi_iv_touxiang);
                                String username = data.getUsername();
                                yhm.setText(username);
                                nc.setText(data.getNickname()+"");
                                nc.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Intent in = new Intent(GerenxinxiActivity.this,NicknameActivity.class);
                                      startActivityForResult(in,101);
                                    }
                                });
                            }
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        gerenxinxi_iv_touxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // TODO: 2017/10/13
                Toast.makeText(GerenxinxiActivity.this, "sssssss", Toast.LENGTH_SHORT).show();
                showChoosePicDialog();
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

    private void initView() {
        gerenxinxi_iv_touxiang = (ImageView) findViewById(R.id.gerenxinxi_iv_touxiang);
        ImageView iiv_back = (ImageView) findViewById(R.id.iiv_back);
        iiv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        csrq = (TextView) findViewById(R.id.gerenxinxi_tv_csrq);
        xb = (TextView) findViewById(R.id.gerenxinxi_tv_xb);
        yhm = (TextView) findViewById(R.id.gerenxinxi_tv_yhm);
        nc = (TextView) findViewById(R.id.gerenxinxi_tv_nc);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 101){
            String nickname = data.getStringExtra("nickname");
            nc.setText(nickname);
        }
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
                        setImageToView(data,gerenxinxi_iv_touxiang); // 让刚才选择裁剪得到的图片显示在界面上
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
                    params.put("uid", getSharedPreferences("con",MODE_PRIVATE).getInt("uid",0));
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

                            System.out.println("e =========失败了 " + e);
                        }
                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            System.out.println("=====sssssssssss====" + response.body().string());
                        }
                    });
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
    }


}
