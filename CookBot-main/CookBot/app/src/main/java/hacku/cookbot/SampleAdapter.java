package hacku.cookbot;

import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.widget.ImageButton;
import android.annotation.SuppressLint;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class SampleAdapter extends RecyclerView.Adapter<SampleAdapter.SampViewHolder>{

    private final List<String> arrayList;

    private FifthFragment activity;

    // アダプターのコンストラクタ
    SampleAdapter(List<String> arrayList) {

        this.arrayList = arrayList;

    }

    //　ビューホルダーを生成
    @NonNull
    @Override
    public SampViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // レイアウトファイルに対応したViewオブジェクトを生成
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list, parent, false);

        // MainActivityを取得
//        activity = (FifthFragment) parent.getContext();

        // ビューホルダーを生成してreturn
        return new SampViewHolder(view);
    }

    // ビューホルダーにデータを割り当てる　
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(SampViewHolder holder, int position) {
        //TextViewにarrayListをセット
        holder.nameList.setText(arrayList.get(position));
        /*
        // 移動ボタンをタッチ
        holder.btn_move.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
                    // 長押しではなく、タッチしてすぐにドラッグ状態にする
                    activity.itemTouchHelper.startDrag(holder);
                    return true;
                }
                return v.onTouchEvent(event);
            }
        });*/

        holder.btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != -1) {
                    System.out.println("delete arrayList("+adapterPosition+")="+arrayList.get(adapterPosition));
                    arrayList.remove(adapterPosition);

                    System.out.println("arrayList.size="+arrayList.size());


                    //System.out.println("dateList("+adapterPosition+")="+dateList.get(adapterPosition));
                    //dateList.remove(adapterPosition);

                    notifyItemRemoved(adapterPosition);
                }
            }
        });
    }

    // アイテム数を取得
    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    // ビューホルダー
    public static class SampViewHolder extends RecyclerView.ViewHolder {

        // ビューに配置されたウィジェットへの参照を保持しておくためのフィールド
        public ImageButton btn_move;       // 移動ボタン
        public ImageButton btn_del;
        public TextView nameList;

        // ビューホルダーのコンストラクタ
        public SampViewHolder(View view) {
            super(view);

            // ウィジェットへの参照を取得
            btn_move      = (ImageButton) view.findViewById(R.id.btn_move);
            btn_del       = (ImageButton) view.findViewById(R.id.btn_del);
            nameList      = (TextView) view.findViewById(R.id.nameList);
        }
    }
}
