/*
package hacku.cookbot;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.ItemTouchHelper.SimpleCallback;
import java.util.ArrayList;
import java.util.Collections;


public class MainActivity_FoodList extends AppCompatActivity {

    // データ格納用のList
    private ArrayList<String> arrayList;
    // アダプター
   private SampleAdapter adapter;
    // ドラッグアンドドロップなどをするためのユーティリティクラス
    ItemTouchHelper itemTouchHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // activity_mainのコンテント
        setContentView(R.layout.activity_main);
        // データ準備
        arrayList = new ArrayList<>();
        for (int i=1; i < 6; i++) {
            arrayList.add("コンテンツ" + i);
        }

        // リサイクラービューへの参照を取得
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.mainList);
        // レイアウトマネージャーを準備
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
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
                new SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN ,
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
                        // 移動したことを通知
                        adapter.notifyItemMoved(fromPos, toPos);
                        return true;
                    }
                    // スワイプで削除
                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                        // アイテムを削除
                        arrayList.remove(viewHolder.getAdapterPosition());
                        // 削除したことを通知
                        adapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                    }
                });
        // ItemTouchHelper を RecyclerView にアタッチ
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    // 完了ボタンをクリックした場合の処理
    public void onButtonClick(View view) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        // AlertDialogのタイトル設定します

        alertDialogBuilder.setTitle("確認");

        // AlertDialogのメッセージ設定
        alertDialogBuilder.setMessage("この内容で登録しますか？");

        // AlertDialogのYesボタンのコールバックリスナーを登録
        alertDialogBuilder.setPositiveButton("はい", (dialog, which) -> {

            /*データ保存の処理*/
/*
        });

        // AlertDialogのNoボタンのコールバックリスナーを登録
        alertDialogBuilder.setNeutralButton("いいえ", (dialog, which) -> {
        });
        // AlertDialogのキャンセルができるように設定
        alertDialogBuilder.setCancelable(true);

        AlertDialog alertDialog = alertDialogBuilder.create();
        // AlertDialogの表示
        alertDialog.show();
    }
}
*/
