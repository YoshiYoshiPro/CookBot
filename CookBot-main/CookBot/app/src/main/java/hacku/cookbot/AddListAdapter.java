package hacku.cookbot;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.widget.EditText;
import android.widget.ImageButton;
import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class AddListAdapter extends RecyclerView.Adapter<AddListAdapter.SampViewHolder>{

    private final List<String> arrayList;
    private final List<String> dateList;
    private AddListActivity activity;
    private SecondFragment fragment;

    // アダプターのコンストラクタ
    AddListAdapter(List<String> arrayList, List<String> dateList) {
        this.arrayList = arrayList;
        this.dateList = dateList;
    }

    //　ビューホルダーを生成
    @NonNull
    @Override
    public SampViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // レイアウトファイルに対応したViewオブジェクトを生成
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_addlist, parent, false);

        // MainActivityを取得
        activity = (AddListActivity) parent.getContext();



        // ビューホルダーを生成してreturn
        return new SampViewHolder(view);
    }

    // ビューホルダーにデータを割り当てる　
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(SampViewHolder holder, int position) {

        // EditTextにデータを設定
       holder.edit_contents.setText(arrayList.get(position));
       System.out.println("arrayList.get("+position+")="+arrayList.get(position));
       System.out.println("EditText arrayList="+arrayList.get(position));
       holder.edit_contents2.setText(dateList.get(position));
       System.out.println("dateList.get("+position+")="+dateList.get(position));
       System.out.println("EditText dateList="+dateList.get(position));






        // テキストウォッチャーリスナーが既にあれば削除
        if (holder.textWatcher  != null) {
            holder.edit_contents.removeTextChangedListener(holder.textWatcher);
            holder.edit_contents2.removeTextChangedListener(holder.textWatcher);


        }
        // テキストウォッチャーを設定
        holder.textWatcher = createEditTextWatcher(holder);
        holder.edit_contents.addTextChangedListener(holder.textWatcher);
        holder.edit_contents2.addTextChangedListener(holder.textWatcher);


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
        });

        /*
        // 削除ボタンをクリック
        holder.btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != -1) {
                    System.out.println("delete arrayList("+adapterPosition+")="+arrayList.get(adapterPosition));
                    arrayList.remove(adapterPosition);
                    dateList.remove(adapterPosition);
                    System.out.println("arrayList.size="+arrayList.size());
                    System.out.println("dateList.size="+dateList.size());

                    //System.out.println("dateList("+adapterPosition+")="+dateList.get(adapterPosition));
                    //dateList.remove(adapterPosition);

                    notifyItemRemoved(adapterPosition);
                }
            }
        });*/
    }

    // アイテム数を取得
    @Override
    public int getItemCount() {
        return arrayList.size();

    }

    // ビューホルダー
    public static class SampViewHolder extends RecyclerView.ViewHolder {


        // ビューに配置されたウィジェットへの参照を保持しておくためのフィールド
        public EditText    edit_contents;  // リストの内容
        public EditText    edit_contents2;
        public ImageButton btn_move;       // 移動ボタン
        //public ImageButton btn_del;        // 削除ボタン

        // テキストウォッチャー
        public TextWatcher textWatcher;

        // ビューホルダーのコンストラクタ
        public SampViewHolder(View view) {
            super(view);


            // ウィジェットへの参照を取得
            edit_contents = (EditText) view.findViewById(R.id.edit_contents);
            edit_contents2 = (EditText) view.findViewById(R.id.edit_contents2);
            btn_move      = (ImageButton) view.findViewById(R.id.btn_move);
            //btn_del       = (ImageButton) view.findViewById(R.id.btn_del);


        }
    }

    // テキストウォッチャー



        private TextWatcher createEditTextWatcher(final SampViewHolder viewHolder) {
            return new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                // 入力されたら、List内のデータを更新
                @Override
                public void afterTextChanged(Editable editable) {
                    System.out.println("editable="+editable);
                    System.out.println("(after)viewHolder.edit_contents.getText()"+viewHolder.edit_contents.getText());
                    System.out.println("(after)viewHolder.edit_contents2.getText()"+viewHolder.edit_contents2.getText());
                    if (editable == viewHolder.edit_contents.getText()){
                        arrayList.set(viewHolder.getAdapterPosition(), editable.toString());
                        System.out.println("(edit_contents)editable.toString=" + editable.toString());
                    }
                    if(editable == viewHolder.edit_contents2.getText()){
                        dateList.set(viewHolder.getAdapterPosition(), editable.toString());
                        System.out.println("(edit_contents2)editable.toString=" + editable.toString());
                    }

                }


            };


        }

}



