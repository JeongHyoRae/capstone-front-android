package community_menu;

import android.content.ClipData;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.cookandroid.capstone_front_android.data.BoardDTO;
import com.cookandroid.capstone_front_android.network.MainActivity;
import com.cookandroid.capstone_front_android.R;
import com.cookandroid.capstone_front_android.network.RetrofitClient;
import com.cookandroid.capstone_front_android.network.ServiceApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class community_all extends Fragment {
    private View view;

    private MainActivity activity;

    // 커뮤니티 버튼.
    private Button btn_all; // 전체보기.
    private Button btn_together; // 같이 갈 사람.
    private Button btn_review; // 리뷰.
    private Button btn_write; // 글쓰기.

    private ListView listView;

    private ServiceApi service;
    Retrofit retrofit;
    ServiceApi apiService;
    ItemAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.item_community_all,container,false);

        retrofit=new Retrofit.Builder().baseUrl(ServiceApi.BASE_URL).build();
        apiService =retrofit.create(ServiceApi.class);
        service = RetrofitClient.getClient().create(ServiceApi.class);

        activity = (MainActivity) getActivity();

        // 버튼 설정.
        btn_all = view.findViewById(R.id.all);
        btn_together = view.findViewById(R.id.together);
        btn_review = view.findViewById(R.id.review);
        btn_write = view.findViewById(R.id.write);

        // 버튼이벤트.
        btn_all.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) { activity.setCommunity(0); }
        });

        btn_together.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                activity.setCommunity(1);
            }
        });

        btn_review.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                activity.setCommunity(2);
            }
        });

        btn_write.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) { activity.setCommunity(3); }
        });

        listView = (ListView) view.findViewById(R.id.list);

        adapter = new ItemAdapter();
        listView.setAdapter(adapter);

        postBoard();
        getBoard();

        adapter.addItem("제목2", "내용2");
        adapter.addItem("제목3", "내용3");
        adapter.notifyDataSetChanged();

        return view;
    }

    private void postBoard() {
        BoardDTO board = new BoardDTO(1L, "제목1", "내용1");

        Call<BoardDTO> call = service.postBoard(board);


        call.enqueue(new Callback<BoardDTO>() {
            @Override
            public void onResponse(Call<BoardDTO> call, Response<BoardDTO> response) {
                if (!response.isSuccessful()) {
                    Log.i("제목: ", "포스트반응실패");
                    return;
                }
                Log.i("제목: ", "포스트성공");
                //BoardDTO boardResponse = response.body();
                //adapter.addItem(boardResponse.getTitle(), boardResponse.getContent());
                //adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<BoardDTO> call, Throwable t) {
                Log.i("제목: ", "포스트실패");
            }
        });
    }

    private void getBoard(){
        Log.i("제목: ", "내용");
        service.getBoard().enqueue(new Callback<List<BoardDTO>>() {
            @Override
            public void onResponse(Call<List<BoardDTO>> call, Response<List<BoardDTO>> response) {
                if (!response.isSuccessful()) {
                    Log.i("코드: " , String.valueOf(response.code()));
                    return;
                }
                List<BoardDTO> boardResponse = response.body();
                Log.i("제목: ", boardResponse.get(0).getTitle());
                adapter.addItem(boardResponse.get(0).getTitle(), boardResponse.get(0).getContent());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<BoardDTO>> call, Throwable t) {
                Log.i("제목: ", "실패");
            }
        });
    }

    /*private void createBoard(int user, int num) {
        service.GetBoard(user).enqueue(new Callback<List<BoardDTO>>() {
            @Override
            public void onResponse(Call<List<BoardDTO>> call, Response<List<BoardDTO>> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                List<BoardDTO> boards = response.body();
                adapter.addItem(String.valueOf(boards.get(num).getId()), boards.get(num).getTitle());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<BoardDTO>> call, Throwable t) {

            }
        });
    }*/
}
