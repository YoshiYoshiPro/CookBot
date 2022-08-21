package hacku.cookbot;

import android.app.Activity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

public class MainActivity_CheckBox_1 extends Activity {

    private String choiceFood;

    // リストに設定するアイテム
    private final String[] item = new String [] {
            "牛肉",
            "豚肉",
            "鶏肉",
            "リンゴ",
            "バナナ",
            "イチゴ",
            "牛乳",
            "じゃがいも",
            "人参",
    };

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_1);

        // 追加するアイテムを生成する
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_multiple_choice, item);

        // リストビューにアイテム (adapter) を追加
        ListView listView1 = (ListView)findViewById(R.id.listView1);
        listView1.setAdapter(adapter);

        // ボタンクリックイベント
        Button btn = (Button)findViewById(R.id.btnOk);
        btn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity_CheckBox_1.this);

                // AlertDialogのタイトル設定します
                alertDialogBuilder.setTitle("確認");

                // AlertDialogのメッセージ設定
                alertDialogBuilder.setMessage("この食材を選択しますか？");

                // AlertDialogのYesボタンのコールバックリスナーを登録
                alertDialogBuilder.setPositiveButton("はい", (dialog, which) -> {

                    /*データ保存の処理*/

                    // 選択アイテムを取得
                    ListView listView1 = (ListView)findViewById(R.id.listView1);
                    SparseBooleanArray checked = listView1.getCheckedItemPositions();

                    // チェックされたアイテムの文字列を生成
                    // checked には、「チェックされているアイテム」ではなく、
                    // 「一度でもチェックされたアイテム」が入ってくる。
                    // なので、現在チェックされているかどうかを valueAt の戻り値
                    // で判定する必要がある！！！
                    StringBuilder sb = new StringBuilder();
                    for (int i=0; i<checked.size(); i++) {
                        if (checked.valueAt(i)) {
                            choiceFood = item[i];

                            //sb.append(item[i]+",");
                        }
                    }
                    // 通知
                    Toast.makeText(MainActivity_CheckBox_1.this,
                            sb.toString(), Toast.LENGTH_SHORT).show();

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
        });
    }
}