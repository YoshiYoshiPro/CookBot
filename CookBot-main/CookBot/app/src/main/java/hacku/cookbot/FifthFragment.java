package hacku.cookbot;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

import hacku.cookbot.databinding.ActivityMainFifthBinding;
import hacku.cookbot.databinding.FragmentFourthBinding;

public class FifthFragment extends Fragment {
    private ActivityMainFifthBinding binding;

    // データ格納用のList
    private ArrayList<String> arrayList;
    private ArrayList<String> nameList;
    private ArrayList<String> dateList;
    // アダプター
    private SampleAdapter adapter;
    // ドラッグアンドドロップなどをするためのユーティリティクラス
    ItemTouchHelper itemTouchHelper;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState

    ) {
        binding = ActivityMainFifthBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // データ準備
        arrayList = new ArrayList<>();
        dateList = new ArrayList<>();
        /*

        arrayList = new ArrayList<>();
        for (int i=1; i < 6; i++) {
            arrayList.add("コンテンツ" + i);
        }*/
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
                    arrayList = list;
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



        // リサイクラービューへの参照を取得
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.mainList);
        // レイアウトマネージャーを準備
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        // レイアウトマネージャーを縦スクロールに設定
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        // リサイクラービューにレイアウトマネージャーを設定
        recyclerView.setLayoutManager(layoutManager);
        // アダプターを生成
        adapter = new SampleAdapter(arrayList);
        // リサイクラービューにアダプターを設定
        recyclerView.setAdapter(adapter);
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
                        Collections.swap(arrayList, fromPos, toPos);
                        Collections.swap(dateList, fromPos, toPos);
                        // 移動したことを通知
                        adapter.notifyItemMoved(fromPos, toPos);
                        return true;
                    }
                    // スワイプで削除
                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                        // アイテムを削除
                        arrayList.remove(viewHolder.getAdapterPosition());
                        dateList.remove(viewHolder.getAdapterPosition());
                        // 削除したことを通知
                        adapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                    }
                });
        // ItemTouchHelper を RecyclerView にアタッチ
        itemTouchHelper.attachToRecyclerView(recyclerView);

        binding.button01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
                // AlertDialogのタイトル設定します

                //alertDialogBuilder.setTitle("確認");

                // AlertDialogのメッセージ設定
                //alertDialogBuilder.setMessage("この内容で登録しますか？");

                // AlertDialogのYesボタンのコールバックリスナーを登録
                //alertDialogBuilder.setPositiveButton("はい", (dialog, which) -> {

                /*データ保存の処理*/
                JSONArray array = new JSONArray();
                JSONArray array2 = new JSONArray();
                for (int i = 0, length = arrayList.size(); i < length; i++) {
                    try {
                        array.put(i, arrayList.get(i));
                        array2.put(i,dateList.get(i));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                SharedPreferences.Editor editor = getActivity().getApplicationContext().getSharedPreferences("shared_preference", Context.MODE_PRIVATE).edit();
                editor.putString("name", array.toString());  //key名を"name"としてシリアライズ化したデータを保存
                editor.putString("date",array2.toString());
                editor.commit();

                //});

                // AlertDialogのNoボタンのコールバックリスナーを登録
                //alertDialogBuilder.setNeutralButton("いいえ", (dialog, which) -> {
               // });
                // AlertDialogのキャンセルができるように設定
                //alertDialogBuilder.setCancelable(true);

                //AlertDialog alertDialog = alertDialogBuilder.create();
                // AlertDialogの表示
                //alertDialog.show();

                NavHostFragment.findNavController(FifthFragment.this)
                        .navigate(R.id.action_FifthFragment_to_HomeFragment);
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
