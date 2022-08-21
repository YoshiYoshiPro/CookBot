package hacku.cookbot;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.OkHttpClient;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

public class SecondFragment extends Fragment  {
    // データ格納用のList
    private ArrayList<String> nameList;
    private ArrayList<String> dateList;
    // アダプター
    private AddListAdapter adapter;
    // ドラッグアンドドロップなどをするためのユーティリティクラス
    ItemTouchHelper itemTouchHelper;
    private View view;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // データ準備
        nameList = new ArrayList<>();
        dateList = new ArrayList<>();

        //SharedPreferencesに保存されたデータの取り出し
        //取り出されたデータを(ArrayList)listに格納
        Bundle bundle = new Bundle();  //保存用のバンドル
        Map<String, ?> prefKV = getActivity().getApplicationContext().getSharedPreferences("shared_preference", Context.MODE_PRIVATE).getAll();
        Set<String> keys = prefKV.keySet();
        for(String key : keys){
            Object value = prefKV.get(key);
            if(value instanceof String){
                bundle.putString(key, (String) value);
            }else if(value instanceof Integer){
                // …略
            }
        }
        String stringList1 = bundle.getString("name");  //key名が"name"のものを取り出す
        String stringList2 = bundle.getString("date");


        ArrayList<String> list = new ArrayList<>();
        ArrayList<String> list2 = new ArrayList<>();


        if(stringList1 != null) {
            try {
                JSONArray array = new JSONArray(stringList1);
                for (int i = 0, length = array.length(); i < length; i++) {
                    list.add(array.optString(i));
                    nameList = list;
                }
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
        }
        if(stringList2 != null) {
            try {
                JSONArray array2 = new JSONArray(stringList2);
                for (int i = 0, length = array2.length(); i < length; i++) {
                    list2.add(array2.optString(i));
                    dateList = list2;
                }
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
        }
        System.out.println("(main)nameList="+nameList);
        System.out.println("(main)dateList="+dateList);


        // リサイクラービューへの参照を取得
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.mainList);
        // レイアウトマネージャーを準備
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        // レイアウトマネージャーを縦スクロールに設定
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        // リサイクラービューにレイアウトマネージャーを設定
        recyclerView.setLayoutManager(layoutManager);
        // アダプターを生成
        adapter = new AddListAdapter(nameList,dateList);

        // リサイクラービューにアダプターを設定
        recyclerView.setAdapter(adapter);
        // リサイクラービューに枠線を追加
        RecyclerView.ItemDecoration itemDecoration =
                new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        // ドラッグアンドドロップで移動
        itemTouchHelper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN ,
                        ItemTouchHelper.LEFT){
                    // 長押しで移動
                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView,
                                          @NonNull RecyclerView.ViewHolder viewHolder,
                                          @NonNull RecyclerView.ViewHolder target) {
                        final int fromPos = viewHolder.getAdapterPosition();
                        final int toPos = target.getAdapterPosition();
                        // データを入れ替え
                        Collections.swap(nameList, fromPos, toPos);
                        Collections.swap(dateList,fromPos,toPos);

                        // 移動したことを通知
                        adapter.notifyItemMoved(fromPos, toPos);
                        return true;
                    }
                    // スワイプで削除
                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                        // アイテムを削除
                        nameList.remove(viewHolder.getAdapterPosition());
                        dateList.remove(viewHolder.getAdapterPosition());
                        System.out.println("(swipe)nameList.size()="+nameList.size());
                        System.out.println("(swipe)dateList.size()="+dateList.size());


                        // 削除したことを通知
                        adapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                    }
                });
        // ItemTouchHelper を RecyclerView にアタッチ
        itemTouchHelper.attachToRecyclerView(recyclerView);

        view.findViewById(R.id.button_second_previous).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //FirstFragmentへ遷移
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });


        view.findViewById(R.id.fab_reg).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // 新規のアイテムを追加
                System.out.println("(main)nameList.size()(before)="+nameList.size());
                System.out.println("(main)dateList.size()(before)="+dateList.size());
                nameList.add("");

                System.out.println("(main)nameList="+nameList.get(nameList.size()-1));
                dateList.add("");
                System.out.println("(main)dateList="+dateList.get(dateList.size()-1));

                // アイテムを追加したことを通知
                adapter.notifyItemInserted(nameList.size()//adapter.getItemCount()
        );
            }
        });

    }
/*
    //他アクティビティから遷移してきたときに実行される
    @Override
    public void onStart() {
        super.onStart();

        // データ準備
        nameList = new ArrayList<>();
        dateList = new ArrayList<>();

        //SharedPreferencesに保存されたデータの取り出し
        //取り出されたデータを(ArrayList)listに格納
        Bundle bundle = new Bundle();  //保存用のバンドル
        Map<String, ?> prefKV = getApplicationContext().getSharedPreferences("shared_preference", Context.MODE_PRIVATE).getAll();
        Set<String> keys = prefKV.keySet();
        for(String key : keys){
            Object value = prefKV.get(key);
            if(value instanceof String){
                bundle.putString(key, (String) value);
            }else if(value instanceof Integer){
                // …略
            }
        }
        String stringList1 = bundle.getString("name");  //key名が"name"のものを取り出す
        String stringList2 = bundle.getString("date");


        ArrayList<String> list = new ArrayList<>();
        ArrayList<String> list2 = new ArrayList<>();


        if(stringList1 != null) {
            try {
                JSONArray array = new JSONArray(stringList1);
                for (int i = 0, length = array.length(); i < length; i++) {
                    list.add(array.optString(i));
                    nameList = list;
                }
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
        }
        if(stringList2 != null) {
            try {
                JSONArray array2 = new JSONArray(stringList2);
                for (int i = 0, length = array2.length(); i < length; i++) {
                    list2.add(array2.optString(i));
                    dateList = list2;
                }
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
        }
        System.out.println("(main)nameList="+nameList);
        System.out.println("(main)dateList="+dateList);


        // リサイクラービューへの参照を取得
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.mainList);
        // レイアウトマネージャーを準備
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        // レイアウトマネージャーを縦スクロールに設定
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        // リサイクラービューにレイアウトマネージャーを設定
        recyclerView.setLayoutManager(layoutManager);
        // アダプターを生成
        adapter = new AddListAdapter(nameList,dateList);

        // リサイクラービューにアダプターを設定
        recyclerView.setAdapter(adapter);
        // リサイクラービューに枠線を追加
        RecyclerView.ItemDecoration itemDecoration =
                new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        // ドラッグアンドドロップで移動
        itemTouchHelper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN ,
                        ItemTouchHelper.LEFT){
                    // 長押しで移動
                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView,
                                          @NonNull RecyclerView.ViewHolder viewHolder,
                                          @NonNull RecyclerView.ViewHolder target) {
                        final int fromPos = viewHolder.getAdapterPosition();
                        final int toPos = target.getAdapterPosition();
                        // データを入れ替え
                        Collections.swap(nameList, fromPos, toPos);
                        Collections.swap(dateList,fromPos,toPos);

                        // 移動したことを通知
                        adapter.notifyItemMoved(fromPos, toPos);
                        return true;
                    }
                    // スワイプで削除
                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                        // アイテムを削除
                        nameList.remove(viewHolder.getAdapterPosition());
                        dateList.remove(viewHolder.getAdapterPosition());
                        System.out.println("(swipe)nameList.size()="+nameList.size());
                        System.out.println("(swipe)dateList.size()="+dateList.size());


                        // 削除したことを通知
                        adapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                    }
                });
        // ItemTouchHelper を RecyclerView にアタッチ
        itemTouchHelper.attachToRecyclerView(recyclerView);



        ////////FoodListActivityに遷移するので，いらなくなったら消して！////////
        Button sendButton = view.findViewById(R.id.button01);
        sendButton.setOnClickListener(v -> {

            JSONArray array = new JSONArray();
            JSONArray array2 = new JSONArray();
            for (int i = 0, length = nameList.size(); i < length; i++) {
                try {
                    array.put(i, nameList.get(i));
                    array2.put(i,dateList.get(i));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            SharedPreferences.Editor editor = getApplicationContext().getSharedPreferences("shared_preference", Context.MODE_PRIVATE).edit();
            editor.putString("name", array.toString());  //key名を"name"としてシリアライズ化したデータを保存
            editor.putString("date",array2.toString());
            editor.commit();



            Intent intent = new Intent(getApplication(), MainActivity_FoodList.class);
            startActivity(intent);
        });

    // 「+」フローティング操作ボタンがタップされたときに実行される
    public void onAddItem(View view) {

        // 新規のアイテムを追加
        System.out.println("(main)nameList.size()(before)="+nameList.size());
        System.out.println("(main)dateList.size()(before)="+dateList.size());
        nameList.add("");

        System.out.println("(main)nameList="+nameList.get(nameList.size()-1));
        dateList.add("");
        System.out.println("(main)dateList="+dateList.get(dateList.size()-1));

        // アイテムを追加したことを通知
       adapter.notifyItemInserted(nameList.size()//adapter.getItemCount());
       );
}


    //端末の戻るボタンを押したときの処理
    //nameListをJSONArrayにして，SharedPreferencesに保存
    @Override
    public void onBackPressed(){
        JSONArray array = new JSONArray();
        JSONArray array2 = new JSONArray();
        for (int i = 0, length = nameList.size(); i < length; i++) {
            try {
                array.put(i, nameList.get(i));
                array2.put(i,dateList.get(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        SharedPreferences.Editor editor = getActivity().getApplicationContext().getSharedPreferences("shared_preference", Context.MODE_PRIVATE).edit();
        editor.putString("name", array.toString());  //key名を"name"としてシリアライズ化したデータを保存
        editor.putString("date",array2.toString());
        editor.commit();


    }

 */
}