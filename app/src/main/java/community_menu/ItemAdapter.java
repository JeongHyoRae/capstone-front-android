package community_menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cookandroid.capstone_front_android.R;
import com.cookandroid.capstone_front_android.network.ServiceApi;
import com.cookandroid.capstone_front_android.data.*;
import com.cookandroid.capstone_front_android.network.*;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.util.List;

public class ItemAdapter extends BaseAdapter {
    private TextView title;
    private TextView content;
    public ArrayList<ItemDTO> item_list = new ArrayList<>();

    private final String BASE_URL = "http://116.126.165.167:8080";
    private ServiceApi service;
    Retrofit retrofit;
    ServiceApi apiService;
    jmap jm;

    public ItemAdapter() {
    }

    @Override
    public int getCount() { // 리스트 안 item 개수.
        return item_list.size();
    }

    @Override
    public Object getItem(int i) { // 위치에 있는 데이터.
        return item_list.get(i);
    }

    @Override
    public long getItemId(int i) { // id리턴.
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final int pos = i;
        final Context context = viewGroup.getContext();

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item, viewGroup, false);
        }

        title = (TextView) view.findViewById(R.id.title);
        content = (TextView) view.findViewById(R.id.content);

        ItemDTO item = item_list.get(i);

        title.setText(item.getTitle());
        content.setText(item.getContent());

        LinearLayout layout = (LinearLayout) view.findViewById(R.id.layout);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), item_list.get(pos).getContent(), Toast.LENGTH_SHORT).show();
            }
        });

        /*title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), item_list.get(pos).getContent(), Toast.LENGTH_SHORT).show();
            }
        });*/

        //retrofit = new Retrofit.Builder().baseUrl(ServiceApi.BASE_URL).build();
        //apiService = retrofit.create(ServiceApi.class);
        //service = RetrofitClient.getClient().create(ServiceApi.class);
        //createBoard(1, 0);

        return view;
    }

    public void addItem(String title, String content) {
        ItemDTO item = new ItemDTO();
        item.setTitle(title);
        item.setContent(content);

        item_list.add(item);
    }

    /*public void addItem(int a, int b) {
        ItemDTO item = new ItemDTO();

        retrofit = new Retrofit.Builder().baseUrl(ServiceApi.BASE_URL).build();
        service = RetrofitClient.getClient().create(ServiceApi.class);
        createBoard(a, b);

        item_list.add(item);
    }*/

    private void createBoard(int user, int num) {
        /*Call<GBoard> call = service.GetBoard(1);

        call.enqueue(new Callback<GBoard>() {
            @Override
            public void onResponse(Call<GBoard> call, Response<GBoard> response) {
                if(!response.isSuccessful()){
                    title.setText("code: " + response.code());
                    return;
                }
                GBoard boardResponse = response.body();

                int i = boardResponse.getId();
                String string = boardResponse.getTitle();
                title.setText(i);
                content.setText(string);

            }

            @Override
            public void onFailure(Call<GBoard> call, Throwable t) {
                title.setText(t.getMessage());
            }
        });*/

        /*service.GetBoard(user).enqueue(new Callback<List<BoardDTO>>() {
            @Override
            public void onResponse(Call<List<BoardDTO>> call, Response<List<BoardDTO>> response) {
                if(!response.isSuccessful()){
                    title.setText("code: " + response.code());
                    return;
                }

                List<BoardDTO> boards = response.body();

                String i = String.valueOf(boards.get(num).getId());
                String string = boards.get(num).getTitle();
                title.setText(i);
                content.setText(string);

            }

            @Override
            public void onFailure(Call<List<BoardDTO>> call, Throwable t) {

            }
        });*/

        /*Board board = new Board(1, 1, "제목4", "내용4");

        Call<Board> call = service.createBoard(board);

        call.enqueue(new Callback<Board>() {
            @Override
            public void onResponse(Call<Board> call, Response<Board> response) {
                if(!response.isSuccessful()){
                    title.setText("code: " + response.code());
                    return;
                }
                Board boardResponse = response.body();

                String string = boardResponse.getTitle();
                String string2 = boardResponse.getBody();
                title.setText(string);
                content.setText(string2);

            }

            @Override
            public void onFailure(Call<Board> call, Throwable t) {
                title.setText(t.getMessage());
            }
        });*/
    }

}
