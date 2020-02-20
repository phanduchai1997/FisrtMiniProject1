package com.example.fisrtminiproject.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.fisrtminiproject.FullImageActivity;
import com.example.fisrtminiproject.R;
import com.example.fisrtminiproject.adapter.ImageAdapter;
import com.example.fisrtminiproject.api.ImageAPI;
import com.example.fisrtminiproject.listener.OnClickItem;
import com.example.fisrtminiproject.model.Image;
import com.example.fisrtminiproject.model.Image1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.fisrtminiproject.model.Image.LAYOUT_IMAGE;
import static com.example.fisrtminiproject.model.Image.LAYOUT_PROS;

public class ImageFragment extends Fragment implements OnClickItem {
    private List<Image> listImage;
    private ImageAdapter imageAdapter;
    private RecyclerView recyclerView;
    private static ImageFragment INSTANCE;
    public int pageNumber = 1;
    private SwipeRefreshLayout refreshLayout;
    private ImageView imgSearch, imgBack;
    private SearchView svSearch;
    private TextView tvBG;

    public ImageFragment() {
    }

    public static ImageFragment getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ImageFragment();
        }
        return INSTANCE;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_imge, container, false);

        listImage = new ArrayList<>();
        addData();
        imageAdapter = new ImageAdapter(listImage, getContext(), ImageFragment.this);
        final GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView = view.findViewById(R.id.rv_list_image);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(imageAdapter);
        imgSearch = view.findViewById(R.id.img_search);
        imgBack = view.findViewById(R.id.img_back);
        tvBG = view.findViewById(R.id.textView);

        svSearch = view.findViewById(R.id.sv_search);
        svSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                ImageAPI.getService().findImage(pageNumber, query).enqueue(new Callback<Image1>() {
                    @Override
                    public void onResponse(Call<Image1> call, Response<Image1> response) {
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                Image1 image1 = response.body();
                                int length = image1.getResults().size();
                                List<Image> list = new ArrayList<>();
                                for (int i = 0; i < length; i++) {
                                    Image image = new Image(image1.getResults().get(i).getUrls());
                                    list.add(image);
                                }
                                listImage.clear();
                                listImage.addAll(list);
                                imageAdapter.notifyDataSetChanged();
                            } else {
                                Toast.makeText(getContext(), "None Images....!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            // errror
                            Toast.makeText(getContext(), "None Images....!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Image1> call, Throwable t) {
                    }
                });
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        imgBack.setVisibility(View.GONE);
        svSearch.setVisibility(View.GONE);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgBack.setVisibility(View.GONE);
                tvBG.setVisibility(View.VISIBLE);
                imgSearch.setVisibility(View.VISIBLE);
                svSearch.setVisibility(View.GONE);
                pageNumber = 1;
                ImageAPI.getService().getAllImage(pageNumber).enqueue(new Callback<List<Image>>() {
                    @Override
                    public void onResponse(Call<List<Image>> call, Response<List<Image>> response) {
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                List<Image> list = response.body();
                                listImage.clear();
                                listImage.addAll(list);
                                imageAdapter.notifyDataSetChanged();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Image>> call, Throwable t) {

                    }
                });
            }

        });

        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvBG.setVisibility(View.GONE);
                imgSearch.setVisibility(View.GONE);
                imgBack.setVisibility(View.VISIBLE);
                svSearch.setVisibility(View.VISIBLE);
            }
        });
        refreshLayout = view.findViewById(R.id.refresh);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                final Handler mHandler = new Handler();
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        addData();
                        refreshLayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });

//        new DowloadData().execute("https://api.unsplash.com/photos");
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastPosision = layoutManager.findLastCompletelyVisibleItemPosition();
                layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        switch (imageAdapter.getItemViewType(position)) {
                            case LAYOUT_IMAGE:
                                return 1;
                            case LAYOUT_PROS:
                                return 2;
                            default:
                                return -1;
                        }
                    }
                });
                if ((lastPosision == (listImage.size() - 1))) {
                    pageNumber++;
                    perform();
                }
                if(listImage.get(listImage.size()-1).getType()==1)
                    removeImage();
            }
        });
        return view;
    }

    private void removeImage() {
        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                listImage.remove(listImage.size() - 1);
                imageAdapter.notifyDataSetChanged();
            }
        }, 5000);
    }

    private void addData() {
        pageNumber = 1;
        ImageAPI.getService().getAllImage(pageNumber).enqueue(new Callback<List<Image>>() {
            @Override
            public void onResponse(Call<List<Image>> call, Response<List<Image>> response) {
                if (response.isSuccessful()) {
                    List<Image> images = response.body();
                    if (images != null) {
                        listImage.clear();
                        listImage.addAll(images);
                        imageAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Image>> call, Throwable t) {

            }
        });
    }

    public void perform() {
        ImageAPI.getService().getAllImage(pageNumber).enqueue(new Callback<List<Image>>() {
            @Override
            public void onResponse(Call<List<Image>> call, Response<List<Image>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        List<Image> images = response.body();
                        listImage.addAll(images);
                        if (listImage.get(listImage.size() - 1).getType() != 1) {
                            listImage.add(new Image(1));
                            imageAdapter.notifyDataSetChanged();
                        }
                        Toast.makeText(getContext(), "image is loaded", Toast.LENGTH_SHORT).show();
                    } else Toast.makeText(getContext(), "None Image", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<List<Image>> call, Throwable t) {

            }
        });

    }


    @Override
    public void onClickImage(int position) {
        Intent intent = new Intent(getContext(), FullImageActivity.class);
        intent.putExtra("posision", position);
        intent.putExtra("list", (Serializable) listImage);
        startActivity(intent);
    }


//    public class DowloadData extends AsyncTask<String, Void , List<Image>>{
//
//        @Override
//        protected List<Image> doInBackground(String... strings) {
//            String link = strings[0];
//            try {
//                URL url = new URL(link);
//                HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
//                InputStream inputStream = connection.getInputStream();
//                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//                StringBuilder builder = new StringBuilder();
//                String line = reader.readLine();
//                while (line != null){
//                    builder.append(line);
//                    line = reader.readLine();
//                }
//                List<Image> images = parsJson("{\"photo\":" + builder.toString() + "}");
//                return images;
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//
//        private List<Image> parsJson(String json) {
//            List<Image> listImages = new ArrayList<>();
//            try {
//                JSONObject root = new JSONObject(json);
//                JSONArray images = root.getJSONArray("photo");
//                for (int i=0; i<images.length();i++){
//                    JSONObject jsonObject = images.getJSONObject(i);
//                    JSONObject jsonObject1 = jsonObject.getJSONObject("urls");
//                    String image = jsonObject1.getString("raw");
//                    Image image1 = new Image(image);
//                    listImages.add(image1);
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            return listImages;
//        }
//
//        @Override
//        protected void onProgressUpdate(Void... values) {
//            super.onProgressUpdate(values);
//        }
//
//        @Override
//        protected void onPostExecute(List<Image> listImages) {
//            super.onPostExecute(listImages);
//            listImage.clear();
//            listImage.addAll(listImages);
//            imageAdapter.notifyDataSetChanged();
//        }
//    }

}
