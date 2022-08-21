package hacku.cookbot;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Set;


public class FirstFragment extends Fragment {
    private RecipeAPI recipeAPI;
    private String MaterialName;
    private MaterialName_MaterialCategoryId MCL;
    private OkHttpClient okHttpClient = new OkHttpClient();
    public static String result;
    private ArrayList<String> nameList;
    private ArrayList<String> dateList;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
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

        System.out.println("nameList="+nameList);
        System.out.println("dateList="+dateList);



        ArrayList<String> dateList2 = new ArrayList<>(dateList);
        ArrayList<String> nameList2 = new ArrayList<>(nameList);
        System.out.println("nameList2.size="+nameList2.size());
        Collections.sort(dateList2);

        if(nameList.size()>=1) {
            for (int i = 0; i < dateList.size(); i++) {
                int index = -1;
                for (int j = 0; j < dateList2.size(); j++) {

                    if (dateList.get(i).equals(dateList2.get(j))) {
                        index = j;
                        break;
                    }
                }System.out.println("index="+index);
                nameList2.set(index, nameList.get(i));
            }
        }
        System.out.println("nameList2="+nameList2);
        System.out.println("dateList2="+dateList2);

        // 追加するアイテムを生成する
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_multiple_choice, nameList2);

        // リストビューにアイテム (adapter) を追加
        ListView listView1 = getActivity().findViewById(R.id.listView1);
        listView1.setAdapter(adapter);

        view.findViewById(R.id.button_first_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_HomeFragment);
            }
        });
        view.findViewById(R.id.button_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*データ保存の処理*/

                // 選択アイテムを取得
                ListView listView1 = getActivity().findViewById(R.id.listView1);
                SparseBooleanArray checked = listView1.getCheckedItemPositions();

                // チェックされたアイテムの文字列を生成
                // checked には、「チェックされているアイテム」ではなく、
                // 「一度でもチェックされたアイテム」が入ってくる。
                // なので、現在チェックされているかどうかを valueAt の戻り値
                // で判定する必要がある！！！
                int i=0;

                System.out.println("int i="+i);

                /*for (i=0; i<nameList2.size(); i++) {
                    if(checked.valueAt(i)) {

                    }
                }

                 */
                MaterialName = nameList2.get(checked.keyAt(0));
                System.out.println("MaterialName="+MaterialName);
/*
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());

                // AlertDialogのタイトル設定します

                alertDialogBuilder.setTitle("確認");

                // AlertDialogのメッセージ設定
                alertDialogBuilder.setMessage("この食材を選択しますか？");

                // AlertDialogのYesボタンのコールバックリスナーを登録
                alertDialogBuilder.setPositiveButton("はい", (dialog, which) -> {

                });

                // AlertDialogのNoボタンのコールバックリスナーを登録
                alertDialogBuilder.setNeutralButton("いいえ", (dialog, which) -> {
                });
                // AlertDialogのキャンセルができるように設定
                alertDialogBuilder.setCancelable(true);

                AlertDialog alertDialog = alertDialogBuilder.create();
                // AlertDialogの表示
                alertDialog.show();
*/
                try {
                    System.out.println(MaterialName);
                    MCL = new MaterialName_MaterialCategoryId();
                    MCL.set();
                    MCL.setMaterialName(MaterialName);
                    MCL.setRecipeId();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Request request = new Request.Builder()
                        .url("https://app.rakuten.co.jp/services/api/Recipe/CategoryRanking/20170426?format=String&applicationId=1048445474139185419&elements=mediumImageUrl%2CrecipeTitle%2CrecipeCost%2CrecipeId%2CrecipeIndication%2CrecipeUrl&categoryId=" + MCL.getCategoryId())
                        .addHeader("Accept", "text/plain")
                        .build();

                okHttpClient.newCall(request).enqueue(new Callback() {

                    @Override
                    public void onFailure(@NonNull Request request, IOException e) {
                        Log.d("okhttp", "Request failed " + e.getMessage());
                    }

                    @Override
                    public void onResponse(@NonNull Response response) throws IOException {
                        result = response.body().string();
                        System.out.println(result);

                        }

                });


                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_ThirdFragment);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // データ準備
        nameList = new ArrayList<>();
        dateList = new ArrayList<>();

        //SharedPreferencesに保存されたデータの取り出し
        //取り出されたデータを(ArrayList)listに格納
        Bundle bundle = new Bundle();  //保存用のバンドル
        @NonNull
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
    }
}
