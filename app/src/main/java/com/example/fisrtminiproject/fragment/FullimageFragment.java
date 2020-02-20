package com.example.fisrtminiproject.fragment;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.fisrtminiproject.R;
import com.example.fisrtminiproject.model.Image;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.UUID;

import javax.net.ssl.HttpsURLConnection;

public class FullimageFragment extends Fragment {
    private String image;
    ImageView imageIMG, imgBack;
    TextView tvSetWall;
    private static final int WRITE_REQUSET_CODE = 1;
    private List<Image> imageList;
    int posision;
    public FullimageFragment() {
    }

    public FullimageFragment(List<Image> imageList, int posision) {
        this.imageList = imageList;
        this.posision = posision;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_full_image,container,false);
        imageIMG = view.findViewById(R.id.img_full_image);
        image = imageList.get(posision).getUrls().getSmall();
        Glide.with(getContext()).load(image).placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher)
                .into(imageIMG);
        imgBack = view.findViewById(R.id.img_back);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        tvSetWall = view.findViewById(R.id.tv_set_wall);
        tvSetWall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String permission[] = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(permission, WRITE_REQUSET_CODE);
                }

            }
        });
        return view;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case WRITE_REQUSET_CODE:
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    new DowloadImage().execute(image);
                }
                break;
        }
    }
    public class DowloadImage extends AsyncTask<String, Integer, String> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(getContext());
            progressDialog.setTitle("Dowloading....");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setMax(100);
            progressDialog.setProgress(0);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            String link = strings[0];

            int file_length = 0;
            try {
                URL url = new URL(link);
                HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
                connection.connect();
                file_length = connection.getContentLength();

                InputStream inputStream = new BufferedInputStream(url.openStream(),8192);
                byte data[] = new byte[1024];
                int total = 0;
                int count = 0;
                File path = new File("data/data/com.example.fisrtminiproject/storageImages");
                if(!path.exists()){
                    path.mkdir();
                }
                File file = new File(path,"image"+ UUID.randomUUID().toString()+".jpg");

                FileOutputStream outputStream = new FileOutputStream(file);
                while ((count = inputStream.read(data))!= -1){
                    total+=count;
                    outputStream.write(data,0,count);
                    int progress = (int) total*100/file_length;
                    publishProgress( progress);
                }
                progressDialog.dismiss();

                outputStream.close();
                inputStream.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "Dowload complete";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressDialog.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }
}
